package JLab3;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {
	private JPanel panel = new JPanel();
	private JLabel label = new JLabel();

	private String needle = null;
	private String rangeStart = null;
	private String rangeEnd = null;
	private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();

	public GornerTableCellRenderer() {
		formatter.setMaximumFractionDigits(5);
		formatter.setGroupingUsed(false);
		DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
		dottedDouble.setDecimalSeparator('.');
		formatter.setDecimalFormatSymbols(dottedDouble);
		panel.add(label);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col) {
		String formattedDouble = formatter.format(value);
		label.setText(formattedDouble);
		if (col > 0 && col < 3 && needle != null && needle.equals(formattedDouble)) {
			panel.setBackground(Color.RED);
		}else if(col > 0 && col < 3 && 
				rangeStart != null && rangeEnd != null &&
				Double.parseDouble(rangeStart) < Double.parseDouble(formattedDouble) &&
				Double.parseDouble(rangeEnd) > Double.parseDouble(formattedDouble)) {
			panel.setBackground(Color.BLUE);
			
		}
		else if ((col + row + 2) % 2 == 1){
			panel.setBackground(Color.WHITE);
			label.setForeground(Color.BLACK);
		} else {
			panel.setBackground(Color.BLACK);
			label.setForeground(Color.WHITE);
		}
		return panel;
	}

	public void setRangeStart(String rangeStart) {
		this.rangeStart = rangeStart;
	}

	public void setRangeEnd(String rangeEnd) {
		this.rangeEnd = rangeEnd;
	}

	public void setNeedle(String needle) {
		this.needle = needle;
	}
}
