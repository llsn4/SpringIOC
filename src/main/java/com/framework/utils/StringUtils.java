package com.framework.utils;

/**
 * @author 苏航
 * @description
 * @date 2023/4/14 15:56
 **/
public class StringUtils {
    private StringUtils(){


    }

    public static String getSetterMethodByFieldName(String fieldName){
        StringBuilder sb=new StringBuilder();
      //  userDao-->setUserDao
        return sb.append("set")
                .append(fieldName.substring(0,1).toUpperCase())
                .append(fieldName.substring(1)).toString();

    }

}
