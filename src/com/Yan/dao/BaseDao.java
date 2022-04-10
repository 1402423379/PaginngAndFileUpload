package com.Yan.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Yan
 * @Date: 2022/3/16 - 03 - 16 - 22:49
 * @Description: com.Yan.Dao
 * @version: 1.0
 */
public abstract class BaseDao {
    public  int BaseUpdate(String sql,Object ... args){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            connection = Myconnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i <args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            rows = preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            Myconnection.returnConnection(connection);
        }

        return rows;
    }

    public List BaseQuary(Class clazz,String sql, Object ... args){
        List List = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = Myconnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            List = new ArrayList<>();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
            }
            while (resultSet.next())
            {
                Object obj = clazz.newInstance();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    Object data = resultSet.getObject(fieldName);
                    field.set(obj,data);
                }
                List.add(obj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            Myconnection.returnConnection(connection);
        }
        return List;
    }
    public int BaseQuaryint(String sql, Object ... args){
        int count=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = Myconnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                count = resultSet.getInt(1);

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            Myconnection.returnConnection(connection);
        }
        return count;
    }

}
