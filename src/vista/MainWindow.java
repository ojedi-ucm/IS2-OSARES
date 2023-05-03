package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.ControlCita;
import control.ControlCliente;
import control.ControlCuenta;
import modelo.Cliente;

import vista.auth.InitView;
import vista.observers.AuthObserver;
import vista.cuentas.*;

public class MainWindow extends JFrame implements AuthObserver {
	
	private ControlCliente _ctrlCliente;
	private ControlCuenta _ctrlCuenta;
	private ControlCita _ctrlCita;
	
	private JPanel _mainPanel;
	
	public MainWindow(ControlCliente ctrlCliente) {
		super("Osares");
		
		_ctrlCliente = ctrlCliente;
		
		initGUI();
	}
	
	private void initGUI() {
		_mainPanel = new JPanel(new BorderLayout());
		setContentPane(_mainPanel);
		_mainPanel.setPreferredSize(new Dimension(600, 350));
		
		InitView initView = new InitView(this, _ctrlCliente);
		_mainPanel.add(initView, BorderLayout.CENTER);
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}
			
		});
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null); // Centra la ventana en la pantalla
	    setVisible(true);
	}
	
	

	@Override
	public void authSuccess(Cliente cliente) {
		_ctrlCuenta = new ControlCuenta(cliente, _ctrlCliente);
		_ctrlCita = new ControlCita(cliente, _ctrlCliente);
		
		_mainPanel = new JPanel(new BorderLayout());
		setContentPane(_mainPanel);
		_mainPanel.setPreferredSize(new Dimension(1000, 600));
		
		CuentaClienteView cuentasClientes = new CuentaClienteView(_ctrlCuenta, _ctrlCliente, _ctrlCita, this);
		_mainPanel.add(cuentasClientes);
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}
			
		});
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null); // Centra la ventana en la pantalla
	    setVisible(true);
	}

	@Override
	public void closeSession() {
		initGUI();
	}
}
