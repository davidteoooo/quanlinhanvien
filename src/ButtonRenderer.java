import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer{
	public ButtonRenderer() {
		setOpaque(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		JLabel cell = new JLabel();

		if(column == 4){
		cell.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("eye-icon.png"))));
		}else{
		cell.setText(value.toString());
		}
		return cell;
	}

}
