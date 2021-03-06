package cn.just.hive.HiveServer2;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class HiveServer2Client {
	 private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	 
	  /**
	   * @param args
	   * @throws SQLException
	   */
	  public static void main(String[] args) throws SQLException {
	      try {
	      Class.forName(driverName);
	    } catch (ClassNotFoundException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      System.exit(1);
	    }
	    //replace "hive" here with the name of the user the queries should run as
	    Connection con = DriverManager.getConnection("jdbc:hive2://hadoop-senior.shinelon.com:10000/db_hive", 
	    		"root", 	//�û���
	    		"123456"	//����
	    		);
	    Statement stmt = con.createStatement();
	    String tableName = "emp";
	    // select * query
	    String sql = "select * from " + tableName;
	    System.out.println("Running: " + sql);
	    ResultSet res = stmt.executeQuery(sql);
	    while (res.next()) {
	      System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
	    }
	  }
	}
