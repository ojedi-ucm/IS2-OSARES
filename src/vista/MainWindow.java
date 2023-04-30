package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.ControlCuenta;
import vista.cuentas.CuentaClienteView;

public class MainWindow extends JFrame {
	
	private ControlCuenta _ctrl;
	
	public MainWindow(ControlCuenta ctrl) {
		super("OSARES");
		_ctrl = ctrl;
		initGUI();
	}
	
	public void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		mainPanel.setPreferredSize(new Dimension(600, 600));
		
		CuentaClienteView cuentasClientes = new CuentaClienteView(_ctrl);
		mainPanel.add(cuentasClientes);
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null); // Centra la ventana en la pantalla
	    setVisible(true);
	}
}
