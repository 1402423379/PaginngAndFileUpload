package com.Yan.dao;

import com.Yan.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @Auther: Yan
 * @Date: 2022/3/17 - 03 - 17 - 12:29
 * @Description: com.Yan.Dao
 * @version: 1.0
 */
public class Myconnection {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private static int initsize;
    private static int maxsize;

    private static LinkedList<Connection> pool;

    static {
        //初始化参数
        PropertiesUtil propertiesutil = new PropertiesUtil("/jdbc.properties");
        driver=propertiesutil.getProperties("driver");
        url=propertiesutil.getProperties("url");
        user=propertiesutil.getProperties("user");
        password=propertiesutil.getProperties("password");
        initsize=Integer.parseInt(propertiesutil.getProperties("initsize"));
        maxsize=Integer.parseInt(propertiesutil.getProperties("maxsize"));

        //加载驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        pool = new LinkedList<Connection>();
        for (int i = 0; i <initsize ; i++) {
            Connection connection =initConnection();
            if(null!=connection){
                pool.add(connection);
                System.out.println("初始化连接"+connection.hashCode()+"放入连接池 ");
            }
        }

    }
    private static  Connection initConnection(){
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection(){
        Connection connection = null;
        if(pool.size()>0){
            connection = pool.removeFirst();
            System.out.println("连接池中还有连接:"+connection.hashCode());
        }else {
            connection = initConnection();
            System.out.println("连接池空,新建连接:"+connection.hashCode());
        }
        return connection;
    }

    public static void returnConnection(Connection connection){
        if(null!=connection){
            try {
                if(!connection.isClosed()){
                    if(pool.size()<maxsize){
                        try {
                            connection.setAutoCommit(true);
                            System.out.println("设置连接:"+connection.hashCode()+"自动提交为true");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        pool.addLast(connection);
                        System.out.println("连接池未满,归还连接:"+connection.hashCode());
                    }else {
                        try {
                            connection.close();
                            System.out.println("连接池满了,关闭连接"+connection.hashCode());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }else {
                    System.out.println("连接"+connection.hashCode()+"已关闭");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("传入的连接为空不可归还");
        }
    }
}
