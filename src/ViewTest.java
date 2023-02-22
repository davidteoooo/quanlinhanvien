import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.tools.Tool;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Taskbar.State;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ViewTest extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	
	final String header1[]= {"Ma nhan vien","Ten nhan vien","Dia chi","Ngay sinh",""};
	final DefaultTableModel tableModel1=new DefaultTableModel(header1,0);
	final String header2[]= {"Ma du an","Ten du an","Ngay bat dau",""};
	final DefaultTableModel tableModel2=new DefaultTableModel(header2,0);
	final String header3[]= {"Ma nhan vien","Ma du an","Luong"};
	final DefaultTableModel tableModel3=new DefaultTableModel(header3,0);
	final String header4[]= {"ID","Ten nhan vien","Tong luong"};
	final DefaultTableModel tableModel4=new DefaultTableModel(header4,0);
	final String header5[]= {"ID du an","Ten du an","Tong chi phi"};
	final DefaultTableModel tableModel5=new DefaultTableModel(header5,0);
	
	Test cn=new Test();
	Connection con;
	DefaultTableCellRenderer cellRenderer=new DefaultTableCellRenderer();
	DefaultTableCellRenderer cellRenderer1=new DefaultTableCellRenderer();
	Locale lc=new Locale("vi","VN");
	NumberFormat nf=NumberFormat.getInstance(lc);
	private JTable table_1;
	private JTable table_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	public JDateChooser dateChooser;
	public JDateChooser textField_6;
	public JComboBox comboBox_1;
	public JComboBox comboBox;
	public JComboBox comboBox_2;
	
	/**
	 * Launch the application.
	 */
	public void loadBang() {
		try {
			con=cn.getConnection();
			int number;
			Vector row;
			
			String sql="select * from nhanvien";
			Statement st=(Statement) con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
			number=metaData.getColumnCount();
			tableModel1.setRowCount(0);
			while(rs.next()) {
				row=new Vector();
				for(int i=1;i<=number;i++) {
					row.addElement(rs.getString(i));
					
				}
				
				tableModel1.addRow(row);;
				table.setModel(tableModel1);
			}
			cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
			cellRenderer1.setHorizontalAlignment(JLabel.CENTER);
			table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
			table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer1);
			table.getColumnModel().getColumn(4).setPreferredWidth(30);
			table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
			table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JTextField()));
			rs.close();
			con.close();
		}catch(Exception e) {
			
		}
	}
	public void loadBang2() {
		try {
			con=cn.getConnection();
			int number;
			Vector row;
			String sql="Select * from duan";
			Statement st=(Statement) con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
			number=metaData.getColumnCount();
			tableModel2.setRowCount(0);
			while(rs.next()) {
				row=new Vector<>();
				for(int i=1;i<=number;i++) {
					row.addElement(rs.getString(i));
					
				}
				tableModel2.addRow(row);
				table_1.setModel(tableModel2);
				cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
				cellRenderer1.setHorizontalAlignment(JLabel.CENTER);
				table_1.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
				table_1.getColumnModel().getColumn(2).setCellRenderer(cellRenderer1);
				table_1.getColumnModel().getColumn(3).setPreferredWidth(5);
				table_1.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer2());
				table_1.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor2(new JTextField()));
			}
			rs.close();
			st.close();
			con.close();
			
		}catch(Exception e) {
			
		}
	}
	public void loadBang3() {
		try {
			con=cn.getConnection();
			int number;
			String sql="select * from participate_in";
			Vector row;
			Statement st=(Statement) con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
			number=metaData.getColumnCount();
			tableModel3.setRowCount(0);
			while(rs.next()) {
				row=new Vector<>();
				for(int i=1;i<=number;i++) {
					if(i==number) {
						double luong=Double.parseDouble(rs.getString(i));
						row.addElement(nf.format(luong));
					}
					row.addElement(rs.getString(i));
				}
				tableModel3.addRow(row);
				table_2.setModel(tableModel3);
				cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
				table_2.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
				table_2.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
				table_2.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
				
			}
			st.close();
			con.close();
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTest frame = new ViewTest();
					frame.setTitle("Quan li nhan vien");
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		});
		
	}

	/**
	 * Create the frame.
	 */
	public ViewTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1365, 682);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.getHSBColor(206/360, 33/100, 15));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Insert1");
		btnNewButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("Actions-list-add-icon.png"))));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(10, 601, 131, 34);
		btnNewButton.addActionListener(new ActionListener() {
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				if(textField.getText().equals("")||textField_1.getText().equals("")||textField_3.getText().equals("") ||dateChooser.getDate()==null)
					JOptionPane.showMessageDialog(rootPane, "Ban can nhap day du thong tin de them vao bang nhan vien","ERROR",JOptionPane.ERROR_MESSAGE);
				else {
					try {
						int maNV1=Integer.parseInt(textField.getText());
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(rootPane, "nhap sai kieu du lieu cua ID","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				int maNV=Integer.parseInt(textField.getText());
				String tenNV=textField_1.getText();
				Date ngaySinh=dateChooser.getDate();
				String diaChi=textField_3.getText();
				Nhan_Vien nv=new Nhan_Vien(maNV, tenNV, sdf.format(ngaySinh), diaChi);
				nv.setMaNhanVien(maNV);
				nv.setHoVaTen(tenNV);
				nv.setNgaySinh(sdf.format(ngaySinh));
				nv.setDiaChi(diaChi);
				try {
					con=cn.getConnection();
					String sIn="Insert into nhanvien values ("+nv.getMaNhanVien()+",'"+nv.getHoVaTen()+"','"+nv.getDiaChi()+"','"+nv.getNgaySinh()+"')";
					Statement st=(Statement) con.createStatement();
					st.executeUpdate(sIn);
					JOptionPane.showMessageDialog(rootPane, "Insert success!!","Thong bao",JOptionPane.INFORMATION_MESSAGE);
					st.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(rootPane, "Duplicate keys or data error!! Please check try again");
				}
				xoaKhoangTrang3(textField, textField_1, textField_3, dateChooser);
				textField.setEnabled(true);
				}
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("Edit-validated-icon.png"))));
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.setModel(tableModel1);
				int row=table.getSelectedRow();	
					try {
						con=cn.getConnection();
						
						if(textField_1.getText().equals("")||textField_3.getText().equals("")||dateChooser.getDate()==null) {
							JOptionPane.showMessageDialog(rootPane, "Vui long nhap du thong tin truoc khi cap nhat","Loi",JOptionPane.ERROR_MESSAGE);
						}else {
				
							if(dateChooser.getDate()==null) {
								JOptionPane.showMessageDialog(rootPane, "Du lieu date khong duoc bo trong","ERROR",JOptionPane.ERROR_MESSAGE);
							}
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
							Date ngaySinh=dateChooser.getDate();
							Statement st=(Statement) con.createStatement();
							String sUp="Update nhanvien set Name='"+textField_1.getText()+"',Address='"+textField_3.getText()+"', Date='"+sdf.format(ngaySinh)+"' where ID="+textField.getText();
							int kq= st.executeUpdate(sUp);
							if(kq>0) {
								JOptionPane.showMessageDialog(rootPane, "Cap nhat thanh cong","Thong bao",JOptionPane.PLAIN_MESSAGE );
							}
							xoaKhoangTrang3(textField, textField_1, textField_3, dateChooser);
							textField.setEnabled(true);
							st.close();
							con.close();
						}
					}catch(Exception e1	) {
						e1.printStackTrace();
					}
					
				
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBounds(280, 601, 131, 34);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete2");
		btnDelete.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("Actions-edit-delete-icon.png"))));
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table_1.setModel(tableModel2);
				int row=table_1.getSelectedRow();
				
				if(row<0) {
					JOptionPane.showMessageDialog(rootPane, "Vui long chon hang de xoa!","ERROR",JOptionPane.ERROR_MESSAGE);
				}else {
				int ID_Plan=Integer.valueOf(table_1.getValueAt(row, 0)+"");
				String tenDuAn=table_1.getValueAt(row, 1)+"";
				String ngayBatDau=table_1.getValueAt(row, 2)+"";
				Du_An duan=new Du_An(ID_Plan, tenDuAn, ngayBatDau);
				duan.setID_Plan(ID_Plan);
				int luaChon=JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa hàng này không","Thong bao",JOptionPane.OK_OPTION);
				if(luaChon==JOptionPane.YES_OPTION) {
					try {
						con=cn.getConnection();
						String sDe1="Delete from participate_in where participate_in.ID_Plan="+duan.getID_Plan();
						String sDe="Delete from duan where ID_Plan="+duan.getID_Plan()+"";
						Statement st=(Statement) con.createStatement();
						st.executeUpdate(sDe1);
						st.executeUpdate(sDe);
						tableModel2.removeRow(row);
						JOptionPane.showMessageDialog(rootPane, "Delete success!!","Thong bao",JOptionPane.INFORMATION_MESSAGE);
						st.close();
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(rootPane, "Delete unsuccess!!","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					xoaKhoangTrang1(textField_4, textField_5, textField_6);
					textField_4.setEnabled(true);
					
				}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(585, 601, 131, 34);
		contentPane.add(btnDelete);
		
		JLabel lblTenNhanVien = new JLabel("Ten nhan vien");
		lblTenNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTenNhanVien.setBounds(30, 447, 121, 34);
		contentPane.add(lblTenNhanVien);
		
		JLabel lblDiaChi = new JLabel("Dia chi");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDiaChi.setBounds(30, 491, 92, 34);
		contentPane.add(lblDiaChi);
		
		JLabel lblNgaySinh = new JLabel("Ngay sinh");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNgaySinh.setBounds(30, 541, 92, 34);
		contentPane.add(lblNgaySinh);
		
		JLabel lblIdplan = new JLabel("ID_Plan");
		lblIdplan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdplan.setBounds(497, 447, 92, 34);
		contentPane.add(lblIdplan);
		
		JLabel lblTenDuAn = new JLabel("Ten du an");
		lblTenDuAn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTenDuAn.setBounds(497, 491, 92, 34);
		contentPane.add(lblTenDuAn);
		
		JLabel lblNgayBatDau = new JLabel("Ngay bat dau");
		lblNgayBatDau.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNgayBatDau.setBounds(497, 541, 111, 34);
		contentPane.add(lblNgayBatDau);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(30, 397, 92, 34);
		contentPane.add(lblId);
		
		JLabel lblId_1 = new JLabel("ID");
		lblId_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId_1.setBounds(988, 447, 92, 34);
		contentPane.add(lblId_1);
		
		JLabel lblIdplan_1 = new JLabel("ID_Plan");
		lblIdplan_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdplan_1.setBounds(988, 491, 92, 34);
		contentPane.add(lblIdplan_1);
		
		JLabel lblLuong = new JLabel("Luong");
		lblLuong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLuong.setBounds(988, 543, 92, 34);
		contentPane.add(lblLuong);
		
		textField = new JTextField();
		textField.setBounds(145, 397, 131, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(145, 447, 131, 30);
		contentPane.add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(145, 497, 131, 30);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(617, 453, 131, 30);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(617, 497, 131, 30);
		contentPane.add(textField_5);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(1090, 447, 133, 30);
		contentPane.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(1090, 497, 133, 30);
		contentPane.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(1090, 547, 133, 30);
		contentPane.add(textField_9);
		
		JButton btnDelete_3 = new JButton("Delete1");
		btnDelete_3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("Actions-edit-delete-icon.png"))));
		btnDelete_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(tableModel1);
				int row=table.getSelectedRow();
				if(row<0) {
					JOptionPane.showMessageDialog(rootPane, "Vui long chon hang de xoa!","ERROR",JOptionPane.ERROR_MESSAGE);
					
				}else {
				int maNV=Integer.valueOf(tableModel1.getValueAt(row, 0)+"");
				String tenNV=tableModel1.getValueAt(row, 1)+"";
				String ngaySinh=tableModel1.getValueAt(row, 2)+"";
				String diaChi=tableModel1.getValueAt(row, 3)+"";
				Nhan_Vien nv=new Nhan_Vien(maNV, tenNV, ngaySinh, diaChi);
				nv.setMaNhanVien(maNV);
				int luachon=JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa hàng này không","Thong bao",JOptionPane.OK_OPTION);
				if(luachon==JOptionPane.YES_OPTION) {
				try {
					con=cn.getConnection();
					String sDe1="Delete from participate_in where participate_in.ID="+nv.maNhanVien;
					String sDe="Delete from nhanvien where ID="+nv.maNhanVien+" ";
					Statement st=(Statement) con.createStatement();
					st.executeUpdate(sDe1);
					st.executeUpdate(sDe);
					tableModel1.removeRow(row);
					JOptionPane.showMessageDialog(rootPane, "Delete success!!","Thong bao",JOptionPane.INFORMATION_MESSAGE);
					st.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(rootPane, "Delete unsuccess!!","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				xoaKhoangTrang3(textField, textField_1, textField_3, dateChooser);
				textField.setEnabled(true);
				}
				}
			}
		});
		btnDelete_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete_3.setBounds(145, 601, 131, 34);
		contentPane.add(btnDelete_3);
		
		JButton btnDelete_1 = new JButton("Delete3");
		btnDelete_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("Actions-edit-delete-icon.png"))));
		btnDelete_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table_2.setModel(tableModel3);
				int row=table_2.getSelectedRow();
				
				if(row<0) {
					JOptionPane.showMessageDialog(rootPane, "Vui long chon hang de xoa!","ERROR",JOptionPane.ERROR_MESSAGE);
					
				}else {
				int ID=Integer.valueOf(table_2.getValueAt(row, 0)+"");
				int ID_Plan=Integer.valueOf(table_2.getValueAt(row, 1)+"");
				double salary=Double.valueOf(table_2.getValueAt(row, 2)+"");
				Participate_in pi=new Participate_in(ID, ID_Plan, salary);
				pi.setID(ID);
				pi.setID_Plan(ID_Plan);
				int luaChon=JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn xóa hàng này không","Thong bao",JOptionPane.OK_OPTION);
				if(luaChon==JOptionPane.YES_OPTION) {
					try {
						con=cn.getConnection();
						String sDe="Delete from participate_in where ID="+pi.getID()+" and ID_Plan="+pi.getID_Plan()+"";
						Statement st=(Statement) con.createStatement();
						st.executeUpdate(sDe);
						tableModel3.removeRow(row);
						JOptionPane.showMessageDialog(rootPane, "Delete success!!","Thong bao",JOptionPane.INFORMATION_MESSAGE);
						st.close();
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(rootPane, "Delete unsuccess!!","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					xoaKhoangTrang2(textField_7, textField_8, textField_9);
					textField_7.setEnabled(true);
					textField_8.setEnabled(true);
					
				}
				}
			}
		});
		btnDelete_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete_1.setBounds(1021, 601, 131, 34);
		contentPane.add(btnDelete_1);
		
		JButton btnDelete_3_1 = new JButton("Insert2");
		btnDelete_3_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("Actions-list-add-icon.png"))));
		btnDelete_3_1.addActionListener(new ActionListener() {
			
			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(textField_4.getText().equals("")||textField_5.getText().equals("")||textField_6.getDate()==null )
					JOptionPane.showMessageDialog(rootPane, "Ban can nhap day du thong tin de them vao bang du an","ERROR",JOptionPane.ERROR_MESSAGE);
				else {
					try {
						int maNV1=Integer.parseInt(textField_4.getText());
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(rootPane, "nhap sai kieu du lieu cua ID","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				int maDuAn=Integer.parseInt(textField_4.getText());
				String tenDuAn=textField_5.getText();
				Date ngayBatDau=textField_6.getDate();
				Du_An duan=new Du_An(maDuAn, tenDuAn, sdf.format(ngayBatDau));
				duan.setID_Plan(maDuAn);
				duan.setPlan_Name(tenDuAn);
				duan.setDate_Start(sdf.format(ngayBatDau));
				try {
					con=cn.getConnection();
					String sIn="Insert into duan() values ("+duan.getID_Plan()+",'"+duan.getPlan_Name()+"','"+duan.getDate_Start()+"')";
					Statement st=(Statement) con.createStatement();
					st.executeUpdate(sIn);
					JOptionPane.showMessageDialog(rootPane, "Insert success!!","Thong bao",JOptionPane.INFORMATION_MESSAGE);
					st.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(rootPane, "Duplicate keys or data error!! Please check try again");				
					}
				xoaKhoangTrang1(textField_4, textField_5, textField_6);
				textField_4.setEnabled(true);
			}
			}
		});
		btnDelete_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete_3_1.setBounds(446, 601, 127, 34);
		contentPane.add(btnDelete_3_1);
		
		JButton btnDelete_3_1_1 = new JButton("Insert3");
		btnDelete_3_1_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("Actions-list-add-icon.png"))));
		btnDelete_3_1_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(textField_7.getText().equals("")||textField_8.getText().equals("")||textField_9.getText().equals(""))
					JOptionPane.showMessageDialog(rootPane, "Ban can nhap day du thong tin de them vao bang tham gia","ERROR",JOptionPane.ERROR_MESSAGE);
				else {
					try {
						int maNV1=Integer.parseInt(textField_7.getText());
						int maNV2=Integer.parseInt(textField_8.getText());
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(rootPane, "nhap sai kieu du lieu cua ID","ERROR",JOptionPane.ERROR_MESSAGE);
					}
				int maNV=Integer.parseInt(textField_7.getText());
				int maDuAn=Integer.parseInt(textField_8.getText());
				
				
				try {
					double luong1 =Double.parseDouble(textField_9.getText());
					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(rootPane, "Nhap sai kieu du lieu cua luong","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				double luong=Double.parseDouble(textField_9.getText());
				Participate_in pi=new Participate_in(maNV, maDuAn, luong);
				pi.setID(maNV);
				pi.setID_Plan(maDuAn);
				pi.setSalary(luong);
				try {
					con=cn.getConnection();
					String sIn="Insert into participate_in() values ("+pi.getID()+",'"+pi.getID_Plan()+"','"+pi.getSalary()+"')";
					Statement st=(Statement) con.createStatement();
					st.executeUpdate(sIn);
					JOptionPane.showMessageDialog(rootPane, "Insert success!!","Thong bao",JOptionPane.INFORMATION_MESSAGE);
					st.close();
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(rootPane, "Duplicate keys!! Please check and try again");				
					}
				xoaKhoangTrang2(textField_7, textField_8, textField_9);
				textField_7.setEnabled(true);
				textField_8.setEnabled(true);
				}
			}
		});
		btnDelete_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete_3_1_1.setBounds(887, 601, 124, 34);
		contentPane.add(btnDelete_3_1_1);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		verticalBox.setBounds(30, 181, 359, 169);
		contentPane.add(verticalBox);
		
		JLabel lblNewLabel = new JLabel("Nhan vien");
		verticalBox.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JScrollPane pane=new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		verticalBox.add(pane);
		
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
		

			@Override
			public void mouseClicked(MouseEvent e) {
				table.setModel(tableModel1);
				int row=table.getSelectedRow();
				if(row>=0) {
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					textField.setText(tableModel1.getValueAt(row, 0)+"");
					textField_1.setText(tableModel1.getValueAt(row, 1)+"");
					
					textField_3.setText(tableModel1.getValueAt(row, 2)+"");
					
					try {
						
						dateChooser.setDate(sdf.parse(tableModel1.getValueAt(row, 3)+""));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						
						
					}
					
					textField.setEnabled(false);
					
				}
			}
		});
		pane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ma nhan vien", "Ten nhan vien", "Dia chi", "Ngay sinh", ""
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(63);
		table.getColumnModel().getColumn(4).setPreferredWidth(33);
		
		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		verticalBox_1.setBounds(466, 237, 376, 175);
		contentPane.add(verticalBox_1);
		
		JLabel lblDuAn = new JLabel("Du an");
		verticalBox_1.add(lblDuAn);
		lblDuAn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JScrollPane pane_1 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		verticalBox_1.add(pane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table_1.setModel(tableModel2);
				int row=table_1.getSelectedRow();
				if(row>=0) {
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					textField_4.setText(tableModel2.getValueAt(row, 0)+"");
					textField_5.setText(tableModel2.getValueAt(row, 1)+"");
					String day=tableModel2.getValueAt(row, 2)+"";
					try {
						textField_6.setDate(sdf.parse(day));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textField_4.setEnabled(false);
					
				}
			}
		});
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ma du an", "Ten du an", "Ngay bat dau", ""
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(52);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(23);
		pane_1.setViewportView(table_1);
		
		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		verticalBox_2.setBounds(915, 222, 334, 169);
		contentPane.add(verticalBox_2);
		
		JLabel lblThamGia = new JLabel("Tham gia");
		verticalBox_2.add(lblThamGia);
		lblThamGia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JScrollPane pane_2 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		verticalBox_2.add(pane_2);
		
		table_2 = new JTable();
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				table_2.setModel(tableModel3);
				int row=table_2.getSelectedRow();
				if(row>=0) {
					textField_7.setText(tableModel3.getValueAt(row, 0)+"");
					textField_8.setText(tableModel3.getValueAt(row, 1)+"");
					String luong=(tableModel3.getValueAt(row, 2)+"").replace('.', ' ');
					luong=luong.replaceAll(" ", "");
					textField_9.setText(luong+"");
					textField_7.setEnabled(false);
					textField_8.setEnabled(false);
					
				}
			}
		});
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "ID_Plan", "Luong"
			}
		));
		pane_2.setViewportView(table_2);
		
		JButton btnCheck = new JButton("Show all & Refresh");
		btnCheck.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("Programming-Show-Property-icon.png"))));
		btnCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loadBang();
				loadBang2();
				loadBang3();
			}
		});
		btnCheck.setBounds(627, 25, 246, 50);
		contentPane.add(btnCheck);
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnUpdate_2 = new JButton("Update2");
		btnUpdate_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("Edit-validated-icon.png"))));
		btnUpdate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_1.setModel(tableModel2);
				int row=table_1.getSelectedRow();		
					try {
						con=cn.getConnection();
						if(textField_6.getDate()==null) {
							JOptionPane.showMessageDialog(rootPane, "Du lieu ngay bat dau du an khong duoc bo trong","Loi",JOptionPane.ERROR_MESSAGE);
						}
						if(textField_5.getText().equals("")) {
							JOptionPane.showMessageDialog(rootPane, "Du lieu ten du an khong duoc bo trong","Loi",JOptionPane.ERROR_MESSAGE);
						}else {
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
							Statement st=(Statement) con.createStatement();
							Date ngay=textField_6.getDate();
							String sUp="Update duan set Name='"+textField_5.getText()+"',DateStart='"+sdf.format(ngay)+"' where ID_Plan="+textField_4.getText();
							int kq= st.executeUpdate(sUp);
							if(kq>0) {
								JOptionPane.showMessageDialog(rootPane, "Cap nhat thanh cong","Thong bao",JOptionPane.PLAIN_MESSAGE );
							}
							xoaKhoangTrang1(textField_4, textField_5, textField_6);
							textField_4.setEnabled(true);
							st.close();
							con.close();
						}
					}catch(Exception e1	) {
						e1.printStackTrace();
					}
					
				
				
			
			}
		});
		btnUpdate_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate_2.setBounds(726, 601, 133, 34);
		contentPane.add(btnUpdate_2);
		
		JButton btnUpdate_1_1 = new JButton("Update3");
		btnUpdate_1_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("Edit-validated-icon.png"))));
		btnUpdate_1_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table_2.setModel(tableModel3);
				int row=table_2.getSelectedRow();
				try {
						con=cn.getConnection();
						if(textField_9.getText().equals("")) {
							JOptionPane.showMessageDialog(rootPane, "Vui long nhap muc luong truoc khi cap nhat","Loi",JOptionPane.ERROR_MESSAGE);
						}else {
							Statement st=(Statement) con.createStatement();
							
							try {
								int checkLuong=Integer.parseInt(textField_9.getText());
							}catch(Exception e2) {
								JOptionPane.showMessageDialog(rootPane, "Nhap sai kieu du lieu muc luong","ERROR",JOptionPane.ERROR_MESSAGE);
								xoaKhoangTrang2(textField_7, textField_8, textField_9);
								textField_8.setEnabled(true);
								textField_7.setEnabled(true);
							}
							String sUp="Update participate_in set Salary="+textField_9.getText()+" where ID_Plan="+textField_8.getText()+" and ID="+textField_7.getText();
							int kq= st.executeUpdate(sUp);
							if(kq>0) {
								JOptionPane.showMessageDialog(rootPane, "Cap nhat thanh cong","Thong bao",JOptionPane.PLAIN_MESSAGE );
							}
							xoaKhoangTrang2(textField_7, textField_8, textField_9);
							textField_8.setEnabled(true);
							textField_7.setEnabled(true);
							st.close();
							con.close();
						}
					}catch(Exception e1) {
							e1.printStackTrace();
						}
			
			}
		});
		btnUpdate_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate_1_1.setBounds(1162, 601, 132, 34);
		contentPane.add(btnUpdate_1_1);
		
		textField_6 = new JDateChooser();
		textField_6.setBounds(615, 541, 219, 39);
		contentPane.add(textField_6);
				
		dateChooser = new JDateChooser();
		dateChooser.setBounds(145, 541, 199, 34);
		contentPane.add(dateChooser);
		
		Box verticalBox_3 = Box.createVerticalBox();
		verticalBox_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		verticalBox_3.setBounds(10, 373, 401, 218);
		contentPane.add(verticalBox_3);
		
		Box verticalBox_4 = Box.createVerticalBox();
		verticalBox_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		verticalBox_4.setBounds(446, 422, 424, 169);
		contentPane.add(verticalBox_4);
		
		Box verticalBox_5 = Box.createVerticalBox();
		verticalBox_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		verticalBox_5.setBounds(893, 400, 384, 191);
		contentPane.add(verticalBox_5);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1328, 22);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Option");
		menuBar.add(mnNewMenu);
		
		BTLControl control= new BTLControl(this);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Check salary employee");
		mntmNewMenuItem.addActionListener(control);
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem menuItem_1 = new JMenuItem("Check total expense plan");
		menuItem_1.addActionListener(control);
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(menuItem_1);
		
		JMenu exit = new JMenu("Exit");
		menuBar.add(exit);
		
		
		
		JMenuItem exit1 = new JMenuItem("Exit");
		exit1.addActionListener(control);
		exit1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		exit.add(exit1);
		
		JButton btnDeleteAllBlank = new JButton("Delete all filled box");
		btnDeleteAllBlank.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("Editing-Delete-icon (1).png"))));
		btnDeleteAllBlank.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField.setEnabled(true);
				textField_4.setEnabled(true);
				textField_7.setEnabled(true);
				textField_8.setEnabled(true);
				xoaKhoangTrang3(textField, textField_1, textField_3, dateChooser);
				xoaKhoangTrang1(textField_4, textField_5, textField_6);
				xoaKhoangTrang2(textField_7, textField_8,textField_9 );
			}
		});
		btnDeleteAllBlank.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDeleteAllBlank.setBounds(883, 26, 251, 48);
		contentPane.add(btnDeleteAllBlank);
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("Nhap ten nhan vien can tim kiem");
		textField_2.setBounds(51, 123, 141, 48);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(comboBox.getSelectedItem().equals("Ten nhan vien")) {
				String ten=textField_2.getText();
				searchHienThiNV(ten,"Name");
				}else if(comboBox.getSelectedItem().equals("Ma nhan vien")) {
					searchHienThiNV(textField_2.getText(), "ID");
				}else if(comboBox.getSelectedItem().equals("Dia chi")) {
					searchHienThiNV(textField_2.getText(), "Address");
				}else if(comboBox.getSelectedItem().equals("Ngay sinh")) {
					searchHienThiNV(textField_2.getText(), "Date");
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("search-icon.png"))));
		btnNewButton_1.setBounds(51, 84, 141, 30);
		contentPane.add(btnNewButton_1);
		
		Box verticalBox_6 = Box.createVerticalBox();
		verticalBox_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		verticalBox_6.setBounds(30, 32, 359, 143);
		contentPane.add(verticalBox_6);
		
		textField_10 = new JTextField();
		textField_10.setToolTipText("Nhap ten du an can tim kiem");
		textField_10.setColumns(10);
		textField_10.setBounds(485, 172, 141, 48);
		contentPane.add(textField_10);
		
		JButton btnNewButton_1_1 = new JButton("Search");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(comboBox_1.getSelectedItem().equals("Ten du an")) {
				String tenDuAn=textField_10.getText();
				searchHienThiPlan(tenDuAn,"Name");
				}else if(comboBox_1.getSelectedItem().equals("Ma du an")) {
					searchHienThiPlan(textField_10.getText(), "ID_Plan");
				}else if(comboBox_1.getSelectedItem().equals("Ngay bat dau")) {
					searchHienThiPlan(textField_10.getText(), "DateStart");
				}
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("search-icon.png"))));
		btnNewButton_1_1.setBounds(485, 128, 141, 30);
		contentPane.add(btnNewButton_1_1);
		
		Box verticalBox_7 = Box.createVerticalBox();
		verticalBox_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		verticalBox_7.setBounds(466, 77, 376, 155);
		contentPane.add(verticalBox_7);
		
		JButton btnNewButton_1_2 = new JButton("Ascending");
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_2.setBounds(202, 89, 173, 37);
		btnNewButton_1_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("sort (1).png"))));
		btnNewButton_1_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filter("ASC");
			}
		});
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_2_1 = new JButton("Descending");
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filter2("DESC");
			}

			
		});
		btnNewButton_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_2_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("sort-az.png"))));
		btnNewButton_1_2_1.setBounds(649, 181, 173, 39);
		contentPane.add(btnNewButton_1_2_1);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ma nhan vien", "Ten nhan vien", "Dia chi", "Ngay sinh"}));
		comboBox.setBounds(112, 45, 173, 34);
		contentPane.add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Ma du an", "Ten du an", "Ngay bat dau"}));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_1.setBounds(563, 91, 173, 34);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton_1_2_2 = new JButton("Ascending");
		btnNewButton_1_2_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filter2("ASC");
			}
		});
		btnNewButton_1_2_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("sort (1).png")))); 
		btnNewButton_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_2_2.setBounds(649, 136, 173, 37);
		contentPane.add(btnNewButton_1_2_2);
		
		JButton btnNewButton_1_2_1_1 = new JButton("Descending");
		btnNewButton_1_2_1_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				filter("DESC");	
			}
		});
		btnNewButton_1_2_1_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("sort-az.png"))));
		btnNewButton_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_2_1_1.setBounds(202, 132, 173, 39);
		contentPane.add(btnNewButton_1_2_1_1);
		
		JButton btnNewButton_1_2_1_2 = new JButton("Descending");
		btnNewButton_1_2_1_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filter3("DESC");
			}
		});
		btnNewButton_1_2_1_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("sort-az.png"))));
		btnNewButton_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_2_1_2.setBounds(1074, 173, 173, 39);
		contentPane.add(btnNewButton_1_2_1_2);
		
		JButton btnNewButton_1_2_2_1 = new JButton("Ascending");
		btnNewButton_1_2_2_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("sort (1).png"))));
		btnNewButton_1_2_2_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filter3("ASC");
			}
		});
		btnNewButton_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_2_2_1.setBounds(1074, 125, 173, 37);
		contentPane.add(btnNewButton_1_2_2_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Ma nhan vien", "Ma du an", "Luong"}));
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_2.setBounds(940, 83, 173, 34);
		contentPane.add(comboBox_2);
		
		textField_11 = new JTextField();
		textField_11.setToolTipText("Nhap ten nhan vien can tim kiem");
		textField_11.setColumns(10);
		textField_11.setBounds(923, 164, 141, 48);
		contentPane.add(textField_11);
		
		JButton btnNewButton_1_1_1 = new JButton("Search");
		btnNewButton_1_1_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("search-icon.png"))));
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			if(comboBox_2.getSelectedItem().equals("Ma nhan vien")) {
				searchThamGia(textField_11.getText(),"ID");
			}else if(comboBox_2.getSelectedItem().equals("Ma du an")) {
				searchThamGia(textField_11.getText(), "ID_Plan");
			}else if(comboBox_2.getSelectedItem().equals("Luong")) {
				searchThamGia(textField_11.getText(), "Salary");
			}
			}
		});
		btnNewButton_1_1_1.setBounds(923, 128, 141, 30);
		contentPane.add(btnNewButton_1_1_1);
		
		Box verticalBox_7_1 = Box.createVerticalBox();
		verticalBox_7_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		verticalBox_7_1.setBounds(915, 77, 334, 143);
		contentPane.add(verticalBox_7_1);
		
		JButton btnNewButton_2_2 = new JButton("Filter");
		btnNewButton_2_2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("filter.png"))));
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2_2.setBounds(1123, 86, 121, 29);
		btnNewButton_2_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getFilter();
			}

			private void getFilter() {
				// TODO Auto-generated method stub
				try {
				String[] options= {">","<",">=","<=","=","<>"}; 
				String mes=JOptionPane.showInputDialog(null, "Nhap muc luong can loc:","Filter", 3);
				double luongg =Double.parseDouble(mes);
				if(luongg>=0) {
				int x=JOptionPane.showOptionDialog(null, "Chon kieu so sanh:", "Filter", JOptionPane.DEFAULT_OPTION, 3, null, options, options[0]);
				if(x==0) {
					createFilter(">",luongg);
				}else if(x==1) {
					createFilter("<", luongg);
				}else if(x==2) {
					createFilter(">=", luongg);
				}else if(x==3) {
					createFilter("<=", luongg);
				}else if(x==4) {
					createFilter("=", luongg);
				}else if(x==5) {
					createFilter("<>", luongg);
				}
				}else {
					JOptionPane.showMessageDialog(null, "Luong phai la so khong am","ERROR",0);
				}
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Vui long nhap lai dung kieu (double)","ERROR",0);
				}
			}

			
		});
		contentPane.add(btnNewButton_2_2);
	}
	protected void createFilter(String string,double luongg) {
		// TODO Auto-generated method stub
		try {
			con=cn.getConnection();
			int number;
			String sql="select * from participate_in where Salary "+string+" "+luongg;
			Vector row;
			Statement st=(Statement) con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
			number=metaData.getColumnCount();
			tableModel3.setRowCount(0);
			while(rs.next()) {
				row=new Vector<>();
				for(int i=1;i<=number;i++) {
					if(i==number) {
						double luong=Double.parseDouble(rs.getString(i));
						row.addElement(nf.format(luong));
					}
					row.addElement(rs.getString(i));
				}
				tableModel3.addRow(row);
				table_2.setModel(tableModel3);
				cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
				table_2.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
				table_2.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
				table_2.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
				
			}
			st.close();
			con.close();
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public void dieuKien1(String str,String name,int ID,String tenBang,String Id) {
		
			try {
				con=cn.getConnection();
				Statement st=(Statement) con.createStatement();
				String sUp="Update "+tenBang+" set "+name+"='"+str+"' where "+Id+"="+ID+"";
				st.executeUpdate(sUp);
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}
			
	}
	public void dieuKien2(String str1,String name1,String str2,String name2,int ID,String tenBang,String Id) {
		
		try {
			con=cn.getConnection();
			Statement st=(Statement) con.createStatement();
			String sUp="Update " +tenBang+" set "+name1+"='"+str1+"' and " +name2+"='"+str2+"' where "+Id+"="+ID+"";
			st.executeUpdate(sUp);
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		public void dieuKien3(String str1,String name1,String str2,String name2,String str3,String name3,int ID) {
			
			try {
				con=cn.getConnection();
				Statement st=(Statement) con.createStatement();
				String sUp="Update nhanvien set "+name1+"='"+str1+"' and " +name2+"='"+str2+"' and " +name3+"='"+str3+"' where ID="+ID+"";
				st.executeUpdate(sUp);
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}
		}	public void xoaKhoangTrang3(JTextField j1,JTextField j2,JTextField j3,JDateChooser j4) {
			j1.setText("");
			j2.setText("");
			j3.setText("");
			j4.setDate(null);
		}
		
			public void xoaKhoangTrang1(JTextField j1,JTextField j2,JDateChooser j3) {
				j1.setText("");
				j2.setText("");
				
				j3.setDate(null);
			}
			public void xoaKhoangTrang2(JTextField j1,JTextField j2,JTextField j3) {
				j1.setText("");
				j2.setText("");
				j3.setText("");
				
			}
			private JFrame frame1;
			private JPanel contentPane1;
			private JTable table_3;
			public void createJframe() {
				// TODO Auto-generated method stub
				frame1=new JFrame();
				frame1.setVisible(true);
				frame1.setTitle("Salary employee");
				frame1.setBounds(150, 150, 488, 422);
				frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JScrollPane pane4 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				pane4.setBounds(80, 57, 309, 253);
				contentPane1 =new JPanel();
				contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame1.setContentPane(contentPane1);
				frame1.getContentPane().setLayout(null);
				
				table_3 = new JTable();
				table_3.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"ID nhan vien", "Ten nhan vien", "Tong luong"
					}
				));
				pane4.setViewportView(table_3);
				contentPane1.add(pane4);
				
				JLabel lblNewLabel = new JLabel("Salary employee(only participate in plan suggested)");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblNewLabel.setBounds(30, 10, 469, 42);
				contentPane1.add(lblNewLabel);
				
			}
			public void checkSalary() {
				// TODO Auto-generated method stub
					tableModel4.setRowCount(0);
					table_3.setModel(tableModel4);
				try {
					con=cn.getConnection();
					String sCheck="SELECT nhanvien.ID, nhanvien.Name, sum(participate_in.Salary) FROM nhanvien INNER JOIN participate_in ON nhanvien.ID=participate_in.ID GROUP BY nhanvien.ID";
					Statement st=(Statement) con.createStatement();
					ResultSet rs=st.executeQuery(sCheck);
					ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
					int number=metaData.getColumnCount();
					Vector row;
					while(rs.next()) {
						row=new Vector();
						for(int i=1;i<=number;i++) {
							if(i==number) {
								double luong= Double.parseDouble(rs.getString(i));
								row.addElement(nf.format(luong));
							}
							row.addElement(rs.getString(i));
						}
						tableModel4.addRow(row);
						table_3.setModel(tableModel4);
					}
					cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
					table_3.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
					table_3.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
					st.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			private JFrame frame2;
			private JPanel contentPane2;
			private JTable table_4;
			public JTextField textField_2;
			public JTextField textField_10;
			public JTextField textField_11;
			public void createJframe2() {
				// TODO Auto-generated method stub
				frame2=new JFrame();
				frame2.setVisible(true);
				frame2.setTitle("Expense plan");
				frame2.setBounds(150, 150, 488, 422);
				frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JScrollPane pane4 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				pane4.setBounds(80, 57, 309, 253);
				contentPane2 =new JPanel();
				contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame2.setContentPane(contentPane2);
				frame2.getContentPane().setLayout(null);
				
				table_4 = new JTable();
				table_4.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"ID Du an", "Ten du an", "Tong chi phi"
					}
				));
				pane4.setViewportView(table_4);
				contentPane2.add(pane4);
				
				JLabel lblNewLabel = new JLabel("Total expense plan (only participate in)");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblNewLabel.setBounds(80, 10, 469, 42);
				contentPane2.add(lblNewLabel);
			}
			public void checkExpense() {
				// TODO Auto-generated method stub
				tableModel5.setRowCount(0);
				table_4.setModel(tableModel5);
			try {
				con=cn.getConnection();
				String sCheck="SELECT duan.ID_Plan, duan.Name, sum(participate_in.Salary) FROM duan INNER JOIN participate_in ON duan.ID_Plan=participate_in.ID_Plan GROUP BY duan.ID_Plan";
				Statement st=(Statement) con.createStatement();
				ResultSet rs=st.executeQuery(sCheck);
				ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
				int number=metaData.getColumnCount();
				Vector row;
				while(rs.next()) {
					row=new Vector();
					for(int i=1;i<=number;i++) {
						if(i==number) {
							double tien=Double.parseDouble(rs.getString(i));
							row.addElement(nf.format(tien));
						}
						row.addElement(rs.getString(i));
					}
					tableModel5.addRow(row);
					table_4.setModel(tableModel5);
				}
				cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
				table_4.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
				table_4.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
			public void searchHienThiNV(String str ,String str1) {
				// TODO Auto-generated method stub
				try {
					con=cn.getConnection();
					int number;
					Vector row;
					
					String sql="select * from nhanvien where nhanvien."+str1 +" LIKE '%"+str+"%'";
					Statement st=(Statement) con.createStatement();
					ResultSet rs=st.executeQuery(sql);
					ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
					number=metaData.getColumnCount();
					tableModel1.setRowCount(0);
					while(rs.next()) {
						row=new Vector();
						for(int i=1;i<=number;i++) {
							row.addElement(rs.getString(i));
							
						}
						
						tableModel1.addRow(row);;
						table.setModel(tableModel1);
					}
					cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
					cellRenderer1.setHorizontalAlignment(JLabel.CENTER);
					table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
					table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer1);
					table.getColumnModel().getColumn(4).setPreferredWidth(30);
					table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
					table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JTextField()));
					rs.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			public void searchHienThiPlan(String str,String str1) {
				try {
					con=cn.getConnection();
					int number;
					Vector row;
					
					String sql="select * from duan where duan."+str1+" LIKE '%"+str+"%'";
					Statement st=(Statement) con.createStatement();
					ResultSet rs=st.executeQuery(sql);
					ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
					number=metaData.getColumnCount();
					tableModel2.setRowCount(0);
					while(rs.next()) {
						row=new Vector();
						for(int i=1;i<=number;i++) {
							row.addElement(rs.getString(i));
							
						}
						
						tableModel2.addRow(row);;
						table_1.setModel(tableModel2);
					}
					cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
					cellRenderer1.setHorizontalAlignment(JLabel.CENTER);
					table_1.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
					table_1.getColumnModel().getColumn(2).setCellRenderer(cellRenderer1);
					table_1.getColumnModel().getColumn(3).setPreferredWidth(5);
					table_1.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer2());
					table_1.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor2(new JTextField()));
					rs.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void filter(String tangGiam) {
				try {
					String inf= (String) comboBox.getSelectedItem();
					String inf2="";
					if(inf.equals("Ma nhan vien")) {
						inf2="ID";
					}else if(inf.equals("Ten nhan vien")){
						inf2="Name";
					}else if(inf.equals("Dia chi")) {
						inf2="Address";
					}else if(inf.equals("Ngay sinh")) {
						inf2="Date";
					}
					con=cn.getConnection();
					int number;
					Vector row;
					String des="select * from nhanvien ORDER BY "+inf2+" "+tangGiam;
					Statement st=(Statement) con.createStatement();
					ResultSet rs=st.executeQuery(des);
					ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
					number=metaData.getColumnCount();
					tableModel1.setRowCount(0);
					while(rs.next()) {
						row=new Vector();
						for(int i=1;i<=number;i++) {
							row.addElement(rs.getString(i));
							
						}
						
						tableModel1.addRow(row);;
						table.setModel(tableModel1);
					}
					
					cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
					cellRenderer1.setHorizontalAlignment(JLabel.CENTER);
					table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
					table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer1);
					table.getColumnModel().getColumn(4).setPreferredWidth(30);
					table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
					table.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JTextField()));
					rs.close();
					st.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void filter2(String tangGiam) {
				
					try {
						String inf= (String) comboBox_1.getSelectedItem();
						String inf2="";
						if(inf.equals("Ma du an")) {
							inf2="ID_Plan";
						}else if(inf.equals("Ten du an")){
							inf2="Name";
						}else if(inf.equals("Ngay bat dau")) {
							inf2="DateStart";
						}
						con=cn.getConnection();
						int number;
						Vector row;
						String des="select * from duan ORDER BY "+inf2+" "+tangGiam;
						Statement st=(Statement) con.createStatement();
						ResultSet rs=st.executeQuery(des);
						ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
						number=metaData.getColumnCount();
						tableModel2.setRowCount(0);
						while(rs.next()) {
							row=new Vector();
							for(int i=1;i<=number;i++) {
								row.addElement(rs.getString(i));
								
							}
							
							tableModel2.addRow(row);;
							table_1.setModel(tableModel2);
						}
						
						cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
						cellRenderer1.setHorizontalAlignment(JLabel.CENTER);
						table_1.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
						table_1.getColumnModel().getColumn(2).setCellRenderer(cellRenderer1);
						table_1.getColumnModel().getColumn(3).setPreferredWidth(5);
						table_1.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer2());
						table_1.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor2(new JTextField()));
						rs.close();
						st.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
			}
			public void searchThamGia(String str,String str1) {
				try {
					con=cn.getConnection();
					int number;
					Vector row;
					
					String sql="select * from participate_in where participate_in."+str1+" LIKE '%"+str+"%'";
					
					Statement st=(Statement) con.createStatement();
					ResultSet rs=st.executeQuery(sql);
					ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
					number=metaData.getColumnCount();
					tableModel3.setRowCount(0);
					while(rs.next()) {
						row=new Vector<>();
						for(int i=1;i<=number;i++) {
							if(i==number) {
								double luong=Double.parseDouble(rs.getString(i));
								row.addElement(nf.format(luong));
							}
							row.addElement(rs.getString(i));
							
						}
						tableModel3.addRow(row);
						table_2.setModel(tableModel3);
						cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
						table_2.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
						table_2.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
						table_2.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
					}
						
						
					
					rs.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void filter3(String str) {
				try {
					String inf= (String) comboBox_2.getSelectedItem();
					String inf2="";
					if(inf.equals("Ma nhan vien")) {
						inf2="ID";
					}else if(inf.equals("Ma du an")){
						inf2="ID_Plan";
					}else if(inf.equals("Luong")) {
						inf2="Salary";
					}
					con=cn.getConnection();
					int number;
					Vector row;
					
					String sql="select * from participate_in ORDER BY "+inf2+" "+str;
					
					Statement st=(Statement) con.createStatement();
					ResultSet rs=st.executeQuery(sql);
					ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
					number=metaData.getColumnCount();
					tableModel3.setRowCount(0);
					while(rs.next()) {
						row=new Vector<>();
						for(int i=1;i<=number;i++) {
							if(i==number) {
								double luong=Double.parseDouble(rs.getString(i));
								row.addElement(nf.format(luong));
							}
							row.addElement(rs.getString(i));
							
						}
						tableModel3.addRow(row);
						table_2.setModel(tableModel3);
						cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
						table_2.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
						table_2.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
						table_2.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
					}
						
						
					
					rs.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
}
