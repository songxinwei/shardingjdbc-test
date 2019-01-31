package com.tsm.shardingjdbctest.util;


public class StringUtils {

    /**
     * 将null转为空字符串
     * @param o
     * @return
     */
    public static String dealNull(Object o){
        return (o == null) ? "" : o.toString();
    }

}
