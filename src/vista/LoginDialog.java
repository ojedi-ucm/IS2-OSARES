package vista;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.ControlCliente;

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
		
		// Username
		JPanel userPanel = new JPanel();
		JLabel userLabel = new JLabel("DNI");
		JTextField username = new JTextField();
		username.setPreferredSize(new Dimension(200, 30));
		userPanel.add(userLabel);
		userPanel.add(username);
		mainPanel.add(userPanel);
		
		// Password
		JPanel passPanel = new JPanel();
		JLabel passLabel = new JLabel("Password");
		JPasswordField password = new JPasswordField();
		password.setPreferredSize(new Dimension(200, 30));
		passPanel.add(passLabel);
		passPanel.add(password);
		mainPanel.add(passPanel);
		
		
		// Buttons
		JPanel btnPanel = new JPanel();
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener((a) -> {
			if(_controler.iniSesion(username.getText(), password.getText()) == null)
				Utils.showErrorMsg("DNI o Contraseña incorrecta");
			else {
				setVisible(false);
			}
		});
		btnPanel.add(loginBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener((a) -> {
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