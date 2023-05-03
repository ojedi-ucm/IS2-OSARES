package vista.auth;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.zip.DataFormatException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.ControlCliente;
import modelo.Cliente;
import vista.MainWindow;
import vista.Utils;
import vista.observers.AuthObserver;

import javax.swing.JComboBox;

public class RegisterDialog extends JDialog {
	
	private ControlCliente _controler;
	private AuthObserver _authObs;
	
	public RegisterDialog(AuthObserver authObs, ControlCliente ctrl) {
		super(new JFrame(), true);
		_controler = ctrl;
		_authObs = authObs;
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Register");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new GridLayout(4, 2));
		
		// Username
		JLabel userLabel = new JLabel("Username");
		JTextField username = new JTextField();
		username.setPreferredSize(new Dimension(200, 30));
		panelAux.add(userLabel);
		panelAux.add(username);
		
		// telefono
		JLabel tlfLabel = new JLabel("Teléfono");
		JTextField tlf = new JTextField();
		tlf.setPreferredSize(new Dimension(200, 30));
		panelAux.add(tlfLabel);
		panelAux.add(tlf);
		
		// DNI
		JLabel DNILabel = new JLabel("DNI");
		JTextField DNI = new JTextField();
		DNI.setPreferredSize(new Dimension(200, 30));
		panelAux.add(DNILabel);
		panelAux.add(DNI);
		
		// Password
		JLabel passLabel = new JLabel("Password");
		JPasswordField password = new JPasswordField();
		password.setPreferredSize(new Dimension(200, 30));
		panelAux.add(passLabel);
		panelAux.add(password);
		
		mainPanel.add(panelAux);
		
		// Buttons
		JPanel btnPanel = new JPanel();
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener((a) -> {
			int telefono;
			try {
				telefono = Integer.parseInt(tlf.getText());
				
				if(telefono < 100000000 || telefono > 999999999)
					Utils.showErrorMsg("El número de teléfono " + telefono + " no es correcto.");
				
				Cliente nuevoCliente = new Cliente(DNI.getText(), password.getText(), username.getText(), telefono);
				
				if(!_controler.crearCliente(nuevoCliente))
					Utils.showErrorMsg("Ya existe un cliente creado con el DNI " + DNI.getText());
				else {
					username.setText("");
					tlf.setText("");
					DNI.setText("");
					password.setText("");
					setVisible(false);
					_authObs.authSuccess(nuevoCliente);
				}
				
			} catch (Exception e) {
					Utils.showErrorMsg("Los campos introducidos no son correctos.");
			}
		});
		btnPanel.add(registerBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener((a) -> {
			username.setText("");
			tlf.setText("");
			DNI.setText("");
			password.setText("");
			setVisible(false);
		});
		btnPanel.add(cancelBtn);
		
		mainPanel.add(btnPanel);
		
		
		setPreferredSize(new Dimension(350, 200));
		pack();
		setResizable(false);
		setVisible(false);
	}
	
	public void open() {
		setLocationRelativeTo(null);
		
		pack();
		setVisible(true);
	}
}
