import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Test {
	public Connection getConnection() throws SQLException {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlinhanvien","root", null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
