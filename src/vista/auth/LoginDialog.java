package vista.auth;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.ControlCliente;
import vista.Utils;
import vista.clientes.UpdateClienteDialog;

public class LoginDialog extends JDialog {
	
	private ControlCliente _controler;
	
	public LoginDialog(ControlCliente ctrl) {
		super(new JFrame(), true);
		_controler = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Login");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new GridLayout(2, 2));
		
		// Username
		JLabel userLabel = new JLabel("DNI");
		JTextField username = new JTextField();
		username.setPreferredSize(new Dimension(200, 30));
		panelAux.add(userLabel);
		panelAux.add(username);
		//mainPanel.add(userPanel);
		
		// Password
		JLabel passLabel = new JLabel("Password");
		JPasswordField password = new JPasswordField();
		password.setPreferredSize(new Dimension(200, 30));
		panelAux.add(passLabel);
		panelAux.add(password);
		//mainPanel.add(passPanel);
		
		mainPanel.add(panelAux);
		
		// Buttons
		JPanel btnPanel = new JPanel();
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener((a) -> {
			if(_controler.iniSesion(username.getText(), password.getText()) == null)
				Utils.showErrorMsg("DNI o Contrase�a incorrecta");
			else {
				password.setText("");
				username.setText("");
				setVisible(false);
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