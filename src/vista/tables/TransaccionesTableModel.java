package vista.tables;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import control.ControlCuenta;
import vista.observers.CuentasObserver;


public class TransaccionesTableModel extends AbstractTableModel implements CuentasObserver {
	
	String[] _header = { "Desde", "Hasta", "Cantidad Transferida" };
	List<Map<String, Object>> _transacciones;
	
	ControlCuenta _ctrl;
	
	public TransaccionesTableModel(ControlCuenta ctrl) {
		_transacciones = new ArrayList<>();
		_ctrl = ctrl;
		_ctrl.addObserver(this);
	}

	@Override
	public int getRowCount() {
		return _transacciones.size();
	}

	@Override
	public int getColumnCount() {
		return _header.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return _header[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:		
				return _ctrl.getNombre((String) _transacciones.get(rowIndex).get("desde"));
			case 1:
				return _ctrl.getNombre((String) _transacciones.get(rowIndex).get("hasta"));
			case 2:
				DecimalFormat df = new DecimalFormat("#,###.##");
				return df.format(_transacciones.get(rowIndex).get("cantidad")) + " €";
		}
		
		return 0;
	}
	
	@Override
	public void updateTransacciones(List<Map<String, Object>> transacciones) {
		_transacciones.clear();
		
		for(Map<String, Object> map: transacciones) {
			_transacciones.add(map);
		}
		
		fireTableStructureChanged();
	}
}
