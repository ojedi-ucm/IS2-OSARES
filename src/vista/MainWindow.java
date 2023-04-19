package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import vista.cuentas.CuentaClienteView;

public class MainWindow extends JFrame {
	
	public MainWindow() {
		super("OSARES");
		initGUI();
	}
	
	public void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		mainPanel.setPreferredSize(new Dimension(600, 350));
		
		
		CuentaClienteView cuentasClientes = new CuentaClienteView();
		mainPanel.add(cuentasClientes);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null); // Centra la ventana en la pantalla
	    setVisible(true);
	}
}
