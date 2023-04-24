package vista.cuentas;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import control.ControlCuenta;
import vista.controlPanel.ControlPanelView;
import vista.tables.CuentasTableModel;
import vista.tables.InfoTableView;

public class CuentaClienteView extends JPanel {
	
	private ControlCuenta _ctrl;
	
	public CuentaClienteView(ControlCuenta ctrl) {
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		setLayout(new BorderLayout());
		
		ControlPanelView controlPanel = new ControlPanelView(_ctrl);
		add(controlPanel, BorderLayout.PAGE_START);
		
		InfoTableView cuentasTable = new InfoTableView("Mis Cuentas", new CuentasTableModel(_ctrl));
		cuentasTable.setPreferredSize(new Dimension(500,250));
		add(cuentasTable, BorderLayout.CENTER);
		
		CuentaStatus status = new CuentaStatus(_ctrl.getTitularID());
		add(status, BorderLayout.PAGE_END);
	}
}
