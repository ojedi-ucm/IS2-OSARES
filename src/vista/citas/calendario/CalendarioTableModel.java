package vista.citas.calendario;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import control.ControlCita;
import modelo.Cita;
import vista.observers.CitasObserver;

public class CalendarioTableModel extends AbstractTableModel implements CitasObserver {
	
	private int _year;
    private int _month;
    
    private List<Cita> _citas;
    
    private Object[][][] _data;
    
    private Calendar _calendar;
    
    String[] _headers = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};

    public CalendarioTableModel(ControlCita ctrl) {
    	
        _calendar = Calendar.getInstance();
        
        _year = _calendar.get(Calendar.YEAR);
        _month = _calendar.get(Calendar.MONTH);
        
        _citas = new ArrayList<>();
        
        ctrl.addObserver(this);
        
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
		_data = new Object[_calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)][7][4];
		
		Calendar selMes = Calendar.getInstance();
		selMes.set(_year, _month, 1);
		
		int firstDayOfWeek = selMes.get(Calendar.DAY_OF_WEEK) - 2;
		
		if(firstDayOfWeek < 0) {
			firstDayOfWeek = 6;
			_data = new Object[_calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)+1][7][4];
		}
			
        int lastDayOfMonth = _calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int row = 0;
        int col = firstDayOfWeek;
        
        for (int i = 1; i <= lastDayOfMonth && row < _calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)+1; i++) {
            
            
            Calendar dia = Calendar.getInstance();
            dia.set(Calendar.YEAR, _year);
            dia.set(Calendar.MONTH, _month);
            dia.set(Calendar.DAY_OF_MONTH, i);
            
            Cita cita = searchCita(dia.getTime());
            
            if(cita != null)
            	_data[row][col][0] = cita;
            else
            	_data[row][col][0] = null;
            
            _data[row][col][1] = i;
            _data[row][col][2] = _month;
            _data[row][col][3] = _year;
            
            col++;
            if (col == 7) {
                row++;
                col = 0;
            }
        }
        
        fireTableDataChanged();
	}
	
	public void updateDays(Calendar fecha) {
		_calendar.set(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), fecha.get(Calendar.DAY_OF_MONTH));
		_month = fecha.get(Calendar.MONTH);
		_year = fecha.get(Calendar.YEAR);
		updateDays();
	}
	
	private Cita searchCita(Date fecha) {
		LocalDate in = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		for(Cita c: _citas) {
			LocalDate fCita = c.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if(fCita.equals(in))
				return c;
		}
		
		return null;
	}

	@Override
	public void updateCitas(Map<String, Cita> citas) {
		_citas.clear();
		
		for(Cita c: citas.values())
			_citas.add(c);
		
		updateDays();
		fireTableDataChanged();
	}
	
	public int getYear() {
		return _year;
	}
	
	public int getMonth() {
		return _month;
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
        
        _labelDay.setForeground(Color.BLUE);
        _labelEvent.setForeground(Color.BLUE);

        add(_labelDay);
        add(_labelEvent);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	Object[] values = (Object[]) value;
    	Calendar today = Calendar.getInstance();
    	Cita cita;
    	
    	if(values[1] != null) {
	        _labelDay.setText(values[1].toString());
	        if(values[0] != null) {
	        	cita = (Cita) values[0];
	        	_labelEvent.setText(cita.getMotivo());
	        } else {
	        	_labelEvent.setText("");
	        }
    	} else {
    		_labelDay.setText("");
	        _labelEvent.setText("");
    	}
        
        if (values[1] != null) {
        	if(isSelected && column < 5) {
	            setBackground(table.getSelectionBackground());
	            _labelDay.setForeground(table.getSelectionForeground());
	            _labelEvent.setForeground(table.getSelectionForeground());
        	} else {
        		if(values[1].equals(today.get(Calendar.DAY_OF_MONTH)) && values[2].equals(today.get(Calendar.MONTH)) && values[3].equals(today.get(Calendar.YEAR))) {
        			_labelDay.setForeground(Color.RED);
        		} else {
	        		if(column >= 5)
	        			_labelDay.setForeground(Color.GRAY);
	            	else
	            		_labelDay.setForeground(Color.BLACK);
        		}
        		
        		setBackground(table.getBackground());
                _labelEvent.setForeground(Color.BLUE);
        	}
        } else {
        	setBackground(table.getBackground());
        }
        
        return this;
    }

}
