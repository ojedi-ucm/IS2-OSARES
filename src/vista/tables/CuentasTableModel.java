package vista.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import control.ControlCuenta;
import modelo.Cuenta;
import vista.observers.CuentasObserver;


public class CuentasTableModel extends AbstractTableModel implements CuentasObserver {
	
	String[] _header = { "Nombre", "Cantidad Total", "IBAN" };
	List<Cuenta> _cuentas;
	
	public CuentasTableModel(ControlCuenta ctrl) {
		ctrl.addObserver(this);
		_cuentas = new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		return _cuentas.size();
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
				return _cuentas.get(rowIndex).getNombre();
			case 1:
				return _cuentas.get(rowIndex).getDinero();
			case 2:
				return _cuentas.get(rowIndex).getIBAN();
		}
		
		return 0;
	}
	
	@Override
	public void updateCuentas(Map<String, Cuenta> cuentas) {
		_cuentas.clear();
		
		for(Cuenta c: cuentas.values())
			_cuentas.add(c);
	}
}
