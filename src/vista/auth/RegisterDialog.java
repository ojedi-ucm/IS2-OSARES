package vista.auth;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.zip.DataFormatException;

import javax.swing.BorderFactory;
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
		setTitle("Nuevo Usuario");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Username
		JLabel userLabel = new JLabel("Nombre y Apellidos");
		JTextField username = new JTextField();
		username.setPreferredSize(new Dimension(300, 30));
		formPanel.add(userLabel);
		formPanel.add(username);
		
		// telefono
		JLabel tlfLabel = new JLabel("Teléfono");
		JTextField tlf = new JTextField();
		tlf.setPreferredSize(new Dimension(300, 30));
		formPanel.add(tlfLabel);
		formPanel.add(tlf);
		
		// DNI
		JLabel DNILabel = new JLabel("DNI/NIF");
		JTextField DNI = new JTextField();
		DNI.setPreferredSize(new Dimension(300, 30));
		formPanel.add(DNILabel);
		formPanel.add(DNI);
		
		// Password
		JLabel passLabel = new JLabel("Contraseña");
		JPasswordField password = new JPasswordField();
		password.setPreferredSize(new Dimension(300, 30));
		formPanel.add(passLabel);
		formPanel.add(password);
		
		formPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(formPanel);
		
		// Buttons
		JPanel btnPanel = new JPanel();
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener((a) -> {
			int telefono;
			try {
				telefono = Integer.parseInt(tlf.getText());
				
				if(telefono < 100000000 || telefono > 999999999)
					Utils.showErrorMsg("El número de teléfono " + telefono + " no es correcto.");
				
				Cliente nuevoCliente = new Cliente(DNI.getText(), new String(password.getPassword()), username.getText(), telefono);
				
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
