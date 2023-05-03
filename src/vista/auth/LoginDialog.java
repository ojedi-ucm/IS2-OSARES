package vista.auth;

import java.awt.Dimension;

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
import vista.Utils;
import vista.observers.AuthObserver;

public class LoginDialog extends JDialog {
	
	private ControlCliente _controler;
	private AuthObserver _authObs;
	
	public LoginDialog(AuthObserver authObs, ControlCliente ctrl) {
		super(new JFrame(), true);
		_controler = ctrl;
		_authObs = authObs;
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Login");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Username
		JLabel userLabel = new JLabel("DNI/NIF");
		userLabel.setAlignmentX(LEFT_ALIGNMENT);
		JTextField username = new JTextField();
		username.setAlignmentX(LEFT_ALIGNMENT);
		username.setPreferredSize(new Dimension(300, 30));
		formPanel.add(userLabel);
		formPanel.add(username);
		
		// Password
		JLabel passLabel = new JLabel("Contraseña");
		passLabel.setAlignmentX(LEFT_ALIGNMENT);
		JPasswordField password = new JPasswordField();
		password.setAlignmentX(LEFT_ALIGNMENT);
		password.setPreferredSize(new Dimension(300, 30));
		formPanel.add(passLabel);
		formPanel.add(password);
		
		formPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(formPanel);
		
		// Buttons
		JPanel btnPanel = new JPanel();
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener((a) -> {
			Cliente authCliente = _controler.iniSesion(username.getText(), new String(password.getPassword()));
			
			if(authCliente == null)
				Utils.showErrorMsg("DNI o Contraseña incorrecta");
			else {
				password.setText("");
				username.setText("");
				setVisible(false);
				_authObs.authSuccess(authCliente);
			}
		});
		btnPanel.add(loginBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener((a) -> {
			password.setText("");
			username.setText("");
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