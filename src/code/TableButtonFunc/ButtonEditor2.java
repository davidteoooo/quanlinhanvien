import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class ButtonEditor2 extends DefaultCellEditor{
	protected JButton btn;
	   private String roww;
	   private String lbl;
	   private Boolean clicked;
	   Connection con;
	   Test cn=new Test();
	   public ButtonEditor2(JTextField txt) {
	    super(txt);
	   
	    btn=new JButton();
	    btn.setOpaque(true);
	    btn.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {

	          fireEditingStopped();
	        }
		
	      });
	   }
	    public Component getTableCellEditorComponent(JTable table, Object obj,
	    	      boolean selected, int row, int col) {   	     
	    		DefaultTableModel tableModel=(DefaultTableModel) table.getModel();
	    	    roww=table.getSelectedRow()+"";
	    	    int maDuAn=Integer.parseInt(tableModel.getValueAt(row, 0)+"");
	    	    String src="";
	    	    try {
					con=cn.getConnection();
					Statement st=(Statement) con.createStatement();
					String info="select duan.ID_Plan,participate_in.ID,nhanvien.Name,participate_in.Salary  \r\n"
							+ "from nhanvien,participate_in,duan\r\n"
							+ "where duan.ID_Plan =participate_in.ID_Plan and participate_in.ID =nhanvien.ID  \r\n"
							+ "order by duan.ID_Plan \r\n"
							+ "";
					ResultSet rs=st.executeQuery(info);
					ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
					int number=table.getRowCount();
					int count=0;
					double chiPhi=0;
					Locale lc=new Locale("vi","VI");
					NumberFormat nf=NumberFormat.getInstance(lc);
					ArrayList<String> list=new ArrayList<String>();
					while(rs.next()) {	
							if((maDuAn+"").equals(rs.getString(1))) {
								count++;
								list.add(rs.getString(3));
								chiPhi+=rs.getDouble(4);
							}
					}
					
					
					for(String str:list) {
						src+=str+", ";
					}
					if(src.length()>0) {
//					src=src.substring(0, src.lastIndexOf(", "));
//					JOptionPane.showMessageDialog(table, "Du an co ID="+maDuAn+" da duoc thuc hien boi "+count+" nhan vien, bao gom cac nhan vien: "+src+"\nVa tong chi phi la "+nf.format(chiPhi)+"$");
					createTable(maDuAn);
					
					}else {
						JOptionPane.showMessageDialog(table, "Du an co ID="+maDuAn+" chua duoc thuc hien boi nhan vien nao");
					}
					
					rs.close();
					st.close();
					con.close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
	    	    
	    	    btn.setText("...");
	    	    clicked=true;
	    	    return btn;
	    	  
	   }
	    public Object getCellEditorValue() {
	    	if(clicked) {

	    	}
	    	clicked=false;
			return new String(roww);
	    	
	    }
	    public boolean stopCellEditing() {
	    	clicked=false;
	    	return super.stopCellEditing();
	    }
	    protected void fireEditingStopped() {
	    	super.fireEditingStopped();
	    }
	    private JTable table_5;
	    long  sum;
	    int soNguoi;
		private JFrame frame3;
		private JPanel contentPane3;
		DefaultTableCellRenderer cellRenderer=new DefaultTableCellRenderer();
		final String header6[]= {"Ma nhan vien","Ten nhan vien","Tong luong"};
		final DefaultTableModel tableModel6=new DefaultTableModel(header6,0);
		Locale lc=new Locale("vi","VN");
		NumberFormat nf=NumberFormat.getInstance(lc);
		public void goiBang(int ma) {
			// TODO Auto-generated method stub
			tableModel6.setRowCount(0);
			table_5.setModel(tableModel6);
		try {
			sum=0;
			soNguoi=0;
			con=cn.getConnection();
			String sCheck="SELECT nhanvien.ID,nhanvien.Name, sum(participate_in.Salary) FROM nhanvien INNER JOIN participate_in ON nhanvien.ID=participate_in.ID and participate_in.ID_Plan="+ma+" GROUP BY nhanvien.ID";
			Statement st=(Statement) con.createStatement();
			ResultSet rs=st.executeQuery(sCheck);
			ResultSetMetaData metaData=(ResultSetMetaData) rs.getMetaData();
			int number=metaData.getColumnCount();
			Vector row;
			while(rs.next()) {
				soNguoi++;
				row=new Vector();
				for(int i=1;i<=number;i++) {
					if(i==number) {
						double luong= Double.parseDouble(rs.getString(i));
						sum+=luong;
						
						row.addElement(nf.format(luong));
					}
					row.addElement(rs.getString(i));
				}
				tableModel6.addRow(row);
				table_5.setModel(tableModel6);
			}
			cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
			table_5.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
			table_5.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
			st.close();
			con.close();
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		}
		public void createTable(int ma) {
			// TODO Auto-generated method stub
				
				frame3=new JFrame();
				frame3.setVisible(true);
				frame3.setBounds(150, 150, 670, 462);
				frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JScrollPane pane5 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				pane5.setBounds(140, 130, 309, 253);
				contentPane3 =new JPanel();
				contentPane3.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame3.setContentPane(contentPane3);
				frame3.getContentPane().setLayout(null);
				
				table_5 = new JTable();
				table_5.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Ma nhan vien","Ten nhan vien", "Tong luong"
					}
				));
				pane5.setViewportView(table_5);
				contentPane3.add(pane5);
				goiBang(ma);
				frame3.setTitle("Dự án có ID="+ma);
				JLabel lblNewLabel = new JLabel("Dự án có ID="+ma+" đã đươc thực hiện bởi "+soNguoi+" nhân viên");
				JLabel jLabel=new JLabel("Số tiền nhân viên tham gia dự án nhận: "+nf.format(sum)+"$");
				JLabel jLabel2=new JLabel("Trung bình tiền lương 1 nhân viên tham gia dự án này là "+nf.format(sum/soNguoi)+"$");
				sum=0;
				soNguoi=0;
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblNewLabel.setBounds(95, 10, 469, 42);
				jLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
				jLabel.setBounds(90, 40, 469, 42);
				jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 18));
				jLabel2.setBounds(40, 70, 700, 42);
				contentPane3.add(lblNewLabel);
				contentPane3.add(jLabel);
				contentPane3.add(jLabel2);
				
			
		}
}
