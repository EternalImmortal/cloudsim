package org.yunji.cloudsimrd;

import org.yunji.cloudsimrd.load.Load;

import java.io.*;
import java.util.*;

import com.alibaba.fastjson.JSON;

/**
 * @author weirenjie
 * @date 2019/11/14
 */
public class PropertiesUtil {
    public void writePropertiesFromMap(String fileName, Map propertiesMap) {
        Properties properties = new Properties();
        // properties.putAll(propertiesMap);
        OutputStream output = null;
        String filePath = new StringBuilder(Constants.PROJECT_PATH).append(fileName).toString();
        try {
            Iterator<Map.Entry<Double, List<Load>>> entries = propertiesMap.entrySet().iterator();

            while (entries.hasNext()) {
                Map.Entry<Double, List<Load>> entry = entries.next();
                String key = JSON.toJSONString(entry.getKey());
                String value = JSON.toJSONString(entry.getValue());
                properties.setProperty(key, value);
                System.out.println("已写入属性：" + key + "=" + value);
            }
            properties.store(new FileOutputStream(filePath), new Date().toString());
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadProperties(String fileName) {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(fileName);
            properties.load(input);
            System.out.println("url:" + properties.getProperty("url"));
            System.out.println("username:" + properties.getProperty("username"));
            System.out.println("password:" + properties.getProperty("password"));
            System.out.println("database:" + properties.getProperty("database"));
        } catch (IOException io) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<String, String> loadProperties() {
        Map<String, String> resultMap = new HashMap<>();

        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("/Users/WeiJoseph/Documents/GitHub/cloudsim/modules/cloudsim/src/main/java/org/yunji/cloudsimrd/load.properties");
            properties.load(input);
            for (String key : properties.stringPropertyNames()) {
                resultMap.put(key, properties.get(key).toString());
            }
        } catch (IOException io) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultMap;
    }

}
