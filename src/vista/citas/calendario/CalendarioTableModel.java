package vista.citas.calendario;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class CalendarioTableModel extends AbstractTableModel {
	
	private int _year;
    private int _month;
    //private int[][] _days;
    
    private Object[][][] _data;
    
    private Calendar _calendar;
    
    String[] _headers = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sab", "Dom"};

    public CalendarioTableModel() {
        _calendar = Calendar.getInstance();
        _calendar.setFirstDayOfWeek(Calendar.MONDAY);
        _year = _calendar.get(Calendar.YEAR);
        _month = _calendar.get(Calendar.MONTH);
        //_days = new int[6][7];
        _data = new Object[_calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)][7][2];
        
        updateDays();
    }

	@Override
	public int getRowCount() {
		return _data.length;
	}

	@Override
	public int getColumnCount() {
		return _headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return _data[rowIndex][columnIndex];
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return _headers[columnIndex];
	}
	
	
	public void updateDays() {
        int firstDayOfWeek = _calendar.get(Calendar.DAY_OF_WEEK) - 2;
        int lastDayOfMonth = _calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int row = 0;
        int col = firstDayOfWeek;
        
        for (int i = 1; i <= lastDayOfMonth; i++) {
            //_days[row][col] = i;
            _data[row][col][0] = i;
            _data[row][col][1] = "Hola buenastardes";
            
            col++;
            if (col == 7) {
                row++;
                col = 0;
            }
        }
        
        fireTableDataChanged();
	}
}

// --------------------------------------------------------------------------

class CalendarTableCellRenderer extends JPanel implements TableCellRenderer {

	private JLabel _labelDay;
    private JLabel _labelEvent;

    public CalendarTableCellRenderer() {
    	setOpaque(true);
        setLayout(new GridLayout(2, 1));
        
        _labelDay = new JLabel();
        _labelEvent = new JLabel();

        add(_labelDay);
        add(_labelEvent);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	Object[] values = (Object[]) value;
    	
    	if(values[0] != null && values[1] != null) {
	        _labelDay.setText(values[0].toString());
	        _labelEvent.setText(values[1].toString());
    	} else {
    		_labelDay.setText("");
	        _labelEvent.setText("");
    	}
        
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            _labelDay.setForeground(table.getSelectionForeground());
            _labelEvent.setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            _labelDay.setForeground(table.getForeground());
            _labelEvent.setForeground(table.getForeground());
        }

    	
    	/*int day = (int) value;
        if (day == 0) {
            button.setText("");
            button.setEnabled(false);
        } else {
            button.setText(String.valueOf(day));
            button.setEnabled(true);
        }
        /*if (isSelected) {
            setBackground(table.getSelectionBackground());
            button.setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            button.setForeground(table.getForeground());
        }*/
        
        return this;
    }

}
