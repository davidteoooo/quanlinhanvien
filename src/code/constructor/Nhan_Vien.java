import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Nhan_Vien {
	int maNhanVien;
	String hoVaTen;
	String ngaySinh;
	String diaChi;
	public Nhan_Vien(int maNhanVien, String hoVaTen, String ngaySinh,String diaChi) {
		this.maNhanVien = maNhanVien;
		this.hoVaTen = hoVaTen;
		this.ngaySinh = ngaySinh;
		this.diaChi=diaChi;
	}

	public int getMaNhanVien() {
		return maNhanVien;
	}
	public String getHoVaTen() {
		return hoVaTen;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
}

