import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer2 extends JButton implements TableCellRenderer {
	public ButtonRenderer2() {
		setOpaque(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
			JLabel cell = new JLabel();
			if(column == 3){
				cell.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("eye-icon.png"))));
				}else{
				cell.setText(value.toString());
				}
		return cell;
	}
	

}
