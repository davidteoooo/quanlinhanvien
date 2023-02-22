import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;



public class BTLControl implements ActionListener{
	private ViewTest vt;
	public BTLControl(ViewTest vt) {
		this.vt=vt;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String com=e.getActionCommand();
		if(com.equals("Exit")) {
			System.exit(0);
		}else if(com.equals("Check salary employee")){
			
			vt.createJframe();
			vt.checkSalary();

		}else if(com.equals("Check total expense plan")) {
			vt.createJframe2();
			vt.checkExpense();
		}
	}
	
	

}
