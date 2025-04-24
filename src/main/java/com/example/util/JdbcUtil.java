package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil {
	// Connection info
	private static final String URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1"; // SID使用
	private static final String USER = "hao";
	private static final String PASSWORD = "hao_pw";
    
    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
