package vista.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import modelo.Cuenta;


public class CuentasTableModel extends AbstractTableModel {
	
	String[] _header = { "Nombre", "Cantidad Total", "IBAN" };
	List<Cuenta> _cuentas;
	
	public CuentasTableModel() {
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
}
