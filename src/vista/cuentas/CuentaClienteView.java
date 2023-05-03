package vista.cuentas;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import control.ControlCita;
import control.ControlCliente;
import control.ControlCuenta;
import vista.MainWindow;
import vista.controlPanel.ControlPanelView;
import vista.cuentas.tables.CuentasTableModel;
import vista.cuentas.tables.InfoTableView;
import vista.cuentas.tables.TransaccionesTableModel;
import vista.cuentas.tables.TransaccionesTableView;
import vista.observers.AuthObserver;

public class CuentaClienteView extends JPanel {
	
	private ControlCuenta _ctrlCuenta;
	private ControlCliente _ctrlCliente;
	private ControlCita _ctrlCita;
	
	private AuthObserver _authObs;
	
	public CuentaClienteView(ControlCuenta ctrlCuenta, ControlCliente ctrlCliente, ControlCita ctrlCita, AuthObserver authObs) {
		_ctrlCuenta = ctrlCuenta;
		_ctrlCliente = ctrlCliente;
		_ctrlCita = ctrlCita;
		
		_authObs = authObs;
		
		initGUI();
	}
	
	private void initGUI() {
		setLayout(new BorderLayout());
		
		ControlPanelView controlPanel = new ControlPanelView(_ctrlCuenta, _ctrlCliente, _ctrlCita, _authObs);
		add(controlPanel, BorderLayout.PAGE_START);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		InfoTableView cuentasTable = new InfoTableView("Mis Cuentas", new CuentasTableModel(_ctrlCuenta));
		cuentasTable.setPreferredSize(new Dimension(500,250));
		contentPanel.add(cuentasTable, BorderLayout.CENTER);
		
		TransaccionesTableView transaccionesTable = new TransaccionesTableView("Transacciones", new TransaccionesTableModel(_ctrlCuenta), _ctrlCuenta);
		transaccionesTable.setPreferredSize(new Dimension(500,250));
		contentPanel.add(transaccionesTable, BorderLayout.CENTER);
		
		add(contentPanel, BorderLayout.CENTER);
		
		CuentaStatus status = new CuentaStatus(_ctrlCuenta.getTitularID());
		add(status, BorderLayout.PAGE_END);
	}
}
