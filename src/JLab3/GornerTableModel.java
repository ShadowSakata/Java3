package JLab3;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {

	private Double[] coefficients;
	private Double from;
	private Double to;
	private Double step;

	public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
		this.from = from;
		this.to = to;
		this.step = step;
		this.coefficients = coefficients;
	}

	public Double getFrom() {
		return from;
	}

	public Double getTo() {
		return to;
	}

	public Double getStep() {
		return step;
	}

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return (int) (Math.ceil((to - from) / step)) + 1;
	}

	public Object getValueAt(int row, int col) {
		double x = from + step * row;
		switch(col) {
		default:
		case 0:
			return x;
		case 1:
			Double result = 0.0;
			for (int i = 0; i < coefficients.length; i++) {
				result += x * result + coefficients[coefficients.length - i - 1];
			}
			return result;
		case 2:
			Double antiresult = 0.0;
			for (int i = 0; i < coefficients.length; i++) {
				antiresult += x * antiresult + coefficients[i];
			}
			return antiresult;
		case 3:
			result = 0.0;
			antiresult = 0.0;
			for (int i = 0; i < coefficients.length; i++) {
				result += x * result + coefficients[coefficients.length - i - 1];
			}
			for (int i = 0; i < coefficients.length; i++) {
				antiresult += x * antiresult + coefficients[i];
			}
			return result - antiresult;
		}
	}

	public String getColumnName(int col) {
		switch (col) {
		case 0:
			return "Значение X";
		default:
		case 1:
			return "Значение многочлена";
		case 2:
			return "Зеркальные коэффициенты";
		case 3:
			return "Разность";
		}
	}

	public Class<?> getColumnClass(int col) {
		return Double.class;
	}
}
