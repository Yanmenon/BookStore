package com.yangmingyue.test;


import com.yangmingyue.utils.JdbcUtils;
import org.junit.Test;

import java.sql.SQLOutput;


public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils(){
        System.out.println(JdbcUtils.getConnection());
    }
}
