package com.Yan.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Auther: Yan
 * @Date: 2022/3/21 - 03 - 21 - 10:46
 * @Description: com.Yan.util
 * @version: 1.0
 */
public class PropertiesUtil {
    private Properties properties;
    public PropertiesUtil(String path){
        properties = new Properties();
        InputStream inputstream = this.getClass().getResourceAsStream(path);
        try {
            properties.load(inputstream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getProperties(String key){
        return properties.getProperty(key);
    }
}
