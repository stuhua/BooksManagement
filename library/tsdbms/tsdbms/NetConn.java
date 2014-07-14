package tsdbms;

import java.sql.*;

public class NetConn {
	Connection con;
	Statement sql;
	String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=library";
	String userName="sa";
	  String userPwd="llh123456";
	public Statement connect() {
		try {
//			 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("成功加载SQL驱动程序");
		} catch (ClassNotFoundException e1) {
			System.out.println("找不到SQL驱动程序");
		}
		try {
//			con = DriverManager.getConnection("jdbc:odbc:library");
		con = DriverManager.getConnection(dbURL,userName,userPwd);
	
			sql = con.createStatement();
			System.out.println("---------------------------------netconn_sql"+sql);
		} catch (SQLException e2) {
			System.out.println("数据库异常啦-------------");
		}
		return sql;
	}
}
