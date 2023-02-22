import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Du_An {
	int ID_Plan;
	String Plan_Name;
	String Date_Start;
	public Du_An(int iD_Plan, String plan_Name, String date_Start) {
		ID_Plan = iD_Plan;
		Plan_Name = plan_Name;
		Date_Start = date_Start;
	}
	public int getID_Plan() {
		return ID_Plan;
	}
	public String getPlan_Name() {
		return Plan_Name;
	}
	public String getDate_Start() {
		return Date_Start;
	}
	public void setID_Plan(int iD_Plan) {
		ID_Plan = iD_Plan;
	}
	public void setPlan_Name(String plan_Name) {
		Plan_Name = plan_Name;
	}
	public void setDate_Start(String date_Start) {
		Date_Start = date_Start;
	}
	
}
