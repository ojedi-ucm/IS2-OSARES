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
import modelo.Cliente;

import javax.swing.JComboBox;

public class RegisterDialog extends JDialog {
	
	private ControlCliente _controler;
	
	public RegisterDialog(ControlCliente ctrl) {
		super(new JFrame(), true);
		_controler = ctrl;
		initGUI();
		
	}
	
	private void initGUI() {
		setTitle("Register");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		// Username
		JPanel userPanel = new JPanel();
		JLabel userLabel = new JLabel("Username");
		JTextField username = new JTextField();
		username.setPreferredSize(new Dimension(200, 30));
		userPanel.add(userLabel);
		userPanel.add(username);
		mainPanel.add(userPanel);
		
		// telefono
		JPanel tlfPanel = new JPanel();
		JLabel tlfLabel = new JLabel("telofono");
		JTextField tlf = new JTextField();
		tlf.setPreferredSize(new Dimension(200, 30));
		tlfPanel.add(tlfLabel);
		tlfPanel.add(tlf);
		mainPanel.add(tlfPanel);
		
		// DNI
		JPanel DNIPanel = new JPanel();
		JLabel DNILabel = new JLabel("DNI");
		JTextField DNI = new JTextField();
		DNI.setPreferredSize(new Dimension(200, 30));
		DNIPanel.add(DNILabel);
		DNIPanel.add(DNI);
		mainPanel.add(DNIPanel);
		
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
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener((a) -> {
			Cliente aux = new Cliente(DNI.getText(), password.getText(), username.getText(), Integer.parseInt(tlf.getText()));
			if(!_controler.crearCliente(aux))
				Utils.showErrorMsg("Ya hay un cliente creado con ese DNI");
			else
				setVisible(false);
		});
		btnPanel.add(registerBtn);
		
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