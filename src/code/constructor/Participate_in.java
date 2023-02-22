import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Participate_in {
	int ID;
	int ID_Plan;
	double salary;
	public Participate_in(int iD, int iD_Plan, double salary) {
		ID = iD;
		ID_Plan = iD_Plan;
		this.salary = salary;
	}
	
	public int getID() {
		return ID;
	}
	public int getID_Plan() {
		return ID_Plan;
	}
	public double getSalary() {
		return salary;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setID_Plan(int iD_Plan) {
		ID_Plan = iD_Plan;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

}
