package com.demo.util;

import java.util.HashMap;

public class PropertiesUtil {
    static HashMap<Object, Object> properties = new HashMap<>();

    public static Object get(String key){
        return properties.get(key);
    }

    public static void put(String key, String val) {
        properties.put(key,val);
    }
}
