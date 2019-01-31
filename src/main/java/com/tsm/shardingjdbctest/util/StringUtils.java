package com.tsm.shardingjdbctest.util;


public class StringUtils {

    /**
     * null to ""
     * @param o
     * @return
     */
    public static String dealNull(Object o){
        return (o == null) ? "" : o.toString();
    }

}
