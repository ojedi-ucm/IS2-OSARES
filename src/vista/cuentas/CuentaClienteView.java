package vista.cuentas;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import control.ControlCuenta;
import vista.controlPanel.ControlPanelView;
import vista.tables.*;

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
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		InfoTableView cuentasTable = new InfoTableView("Mis Cuentas", new CuentasTableModel(_ctrl));
		cuentasTable.setPreferredSize(new Dimension(500,250));
		contentPanel.add(cuentasTable, BorderLayout.CENTER);
		
		TransaccionesTableView transaccionesTable = new TransaccionesTableView("Transacciones", new TransaccionesTableModel(_ctrl), _ctrl);
		transaccionesTable.setPreferredSize(new Dimension(500,250));
		contentPanel.add(transaccionesTable, BorderLayout.CENTER);
		
		add(contentPanel, BorderLayout.CENTER);
		
		CuentaStatus status = new CuentaStatus(_ctrl.getTitularID());
		add(status, BorderLayout.PAGE_END);
	}
}
