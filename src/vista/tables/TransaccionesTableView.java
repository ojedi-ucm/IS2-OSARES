package vista.tables;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.table.TableModel;

import control.ControlCuenta;
import modelo.Cuenta;
import vista.observers.CuentasObserver;

public class TransaccionesTableView extends JPanel implements CuentasObserver {
	String _title;
	TableModel _tableModel;
	ControlCuenta _ctrl;
	
	DefaultComboBoxModel<String> _cuentasModel;
	JComboBox<String> _selectCuenta;
	int _selectedIndex;
	
	List<String> _ibans;
	
	public TransaccionesTableView(String title, TableModel tableModel, ControlCuenta ctrl) {
		_title = title;
		_tableModel = tableModel;
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		
		Border border = BorderFactory.createTitledBorder(_title);
		setBorder(border);
		
		_cuentasModel = new DefaultComboBoxModel<>();
		_ibans = new ArrayList<>();
		
		_selectCuenta = new JComboBox<String>();
		_selectCuenta.setModel(_cuentasModel);
		_selectCuenta.setSelectedIndex(-1);
		_selectCuenta.setAlignmentX(LEFT_ALIGNMENT);
		_selectCuenta.setPreferredSize(new Dimension(300, 30));
		_selectCuenta.addActionListener((a) -> {
			_selectedIndex = _selectCuenta.getSelectedIndex();
			
			if(_selectedIndex != -1 && !_ibans.isEmpty())
				_ctrl.updateSelTransacciones(_ibans.get(_selectedIndex));
		});
		add(_selectCuenta, BorderLayout.NORTH);
		
		
		JTable table = new JTable(_tableModel);
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		

		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void updateModels(Collection<Cuenta> col) {
		try {
			_ibans.clear();
			_cuentasModel.removeAllElements();
		} catch(Exception e) {}
		
		for(Cuenta c: col) {
			_cuentasModel.addElement(c.getNombre());
			_ibans.add(c.getIBAN());
		}
		
		if(_selectCuenta.getSelectedIndex() != -1)
			_ctrl.updateSelTransacciones(_ibans.get(_selectedIndex));
	}
	
	@Override
	public void updateCuentas(Map<String, Cuenta> cuentas) {
		updateModels(cuentas.values());
	}
}
