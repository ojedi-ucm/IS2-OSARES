package vista.clientes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.ControlCliente;
import modelo.Cliente;
import vista.Utils;
import vista.observers.AuthObserver;

public class ClienteDialog extends JDialog {

private ControlCliente _controler;

	AuthObserver _authObs;

	JTextField _userTF;
	JTextField _tlfTF;
	JPasswordField _changePass;
	JPasswordField _password;
	
	public ClienteDialog(ControlCliente ctrl, AuthObserver authObs) {
		super(new JFrame(), true);
		_controler = ctrl;
		_authObs = authObs;
		initGUI();
		
	}
	
	private void initGUI() {
		setTitle("Información de Usuario");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		// DNI
		JPanel dniPanel = new JPanel();
		dniPanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel dniIcon = new JLabel(Utils.resizedIcon("user.png", 20, 20));
		JLabel dniLabel = new JLabel(_controler._cliente.getId());
		dniLabel.setForeground(Color.BLUE);
		dniPanel.add(dniIcon);
		dniPanel.add(dniLabel);
		mainPanel.add(dniPanel);
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// ------- Nombre y Apellido -------
		JLabel userLabel = new JLabel("Nombre y Apellidos");
		userLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel userPanel = new JPanel();
		userPanel.setAlignmentX(LEFT_ALIGNMENT);
		_userTF = new JTextField();
		_userTF.setEnabled(false);
		_userTF.setPreferredSize(new Dimension(300, 30));
		JButton modUsername = new JButton("Cambiar");
		modUsername.addActionListener((a) -> {
			_userTF.setEnabled(true);
		});
		
		userPanel.add(_userTF);
		userPanel.add(modUsername);
		
		formPanel.add(userLabel);
		formPanel.add(userPanel);
		
		
		// ------ Telefono ------
		JLabel tlfLabel = new JLabel("Teléfono");
		tlfLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel tlfPanel = new JPanel();
		tlfPanel.setAlignmentX(LEFT_ALIGNMENT);
		_tlfTF = new JTextField();
		_tlfTF.setEnabled(false);
		_tlfTF.setPreferredSize(new Dimension(300, 30));
		JButton modTlf = new JButton("Cambiar");
		modTlf.addActionListener((a) -> {
			_tlfTF.setEnabled(true);
		});
		
		tlfPanel.add(_tlfTF);
		tlfPanel.add(modTlf);
		
		formPanel.add(tlfLabel);
		formPanel.add(tlfPanel);
		
		// Cambiar Contraseña
		JLabel changePassLabel = new JLabel("Nueva Contraseña");
		changePassLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel changePassPanel = new JPanel();
		changePassPanel.setAlignmentX(LEFT_ALIGNMENT);
		_changePass = new JPasswordField();
		_changePass.setEnabled(false);
		_changePass.setPreferredSize(new Dimension(300, 30));
		JButton modContra = new JButton("Cambiar");
		modContra.addActionListener((a) -> {
			_changePass.setEnabled(true);
		});
		
		changePassPanel.add(_changePass);
		changePassPanel.add(modContra);
		
		formPanel.add(changePassLabel);
		formPanel.add(changePassPanel);
		
		formPanel.add(Box.createVerticalStrut(10));
		
		// ------ Contraseña Actual------
		JLabel passLabel = new JLabel("Contraseña Actual");
		passLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		_password = new JPasswordField();
		_password.setAlignmentX(LEFT_ALIGNMENT);
		_password.setPreferredSize(new Dimension(300, 30));
		
		formPanel.add(passLabel);
		formPanel.add(_password);
		
		
		formPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(formPanel);
		
		// Buttons
		JPanel btnPanel = new JPanel();
		
		JButton registerBtn = new JButton("Guardar");
		registerBtn.setForeground(Color.GREEN);
		registerBtn.addActionListener((a) -> {
			try {
				String inPassword = new String(_password.getPassword());
				
				if(_controler._cliente.getPassword().equals(inPassword)) {
					String newUser = _userTF.getText();
					String newPass = new String(_changePass.getPassword());
					int telefono = Integer.parseInt(_tlfTF.getText());
					
					if(telefono < 100000000 || telefono > 999999999 || inPassword.isBlank() || newUser.isBlank())
						throw new Exception();
					
					if(_userTF.isEnabled())
						_controler._cliente.setName(newUser);
					
					if(_tlfTF.isEnabled())
						_controler._cliente.setPhone(telefono);
					
					if(_changePass.isEnabled() && !newPass.isBlank())
						_controler._cliente.setPassword(newPass);
					
					_controler.actualizarCliente(_controler._cliente);
					
					setVisible(false);
					_userTF.setEnabled(false);
					_tlfTF.setEnabled(false);
					_changePass.setEnabled(false);
					_changePass.setText("");
					_password.setText("");
				} else
					Utils.showErrorMsg("No se pueden realizar los cambios. La contraseña es incorrecta.");
			} catch (Exception e) {
					Utils.showErrorMsg("Existe algun error en los campos introducidos. Por favor revíleso e inténtelo de nuevo.");
			}
		});
		btnPanel.add(registerBtn);
		
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener((a) -> {
			setVisible(false);
			
			_userTF.setEnabled(false);
			_tlfTF.setEnabled(false);
			_changePass.setEnabled(false);
			_changePass.setText("");
			_password.setText("");
		});
		btnPanel.add(cancelBtn);
		
		JButton eliminarBtn = new JButton("Eliminar Usuario");
		eliminarBtn.setForeground(Color.RED);
		eliminarBtn.addActionListener((a) -> {
			int n = JOptionPane.showOptionDialog(this, "¿Seguro que quieres eliminar tu usuario?", "Eliminar Usuario",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			if (n == 0) {
				setVisible(false);
				_controler.eliminarCliente();
				_authObs.closeSession();
			}
		});
		btnPanel.add(eliminarBtn);
		
		mainPanel.add(btnPanel);
		
		
		pack();
		setResizable(false);
		setVisible(false);
	}
	
	public void open() {
		setLocationRelativeTo(null);
		
		_userTF.setText(_controler._cliente.getName());
		_tlfTF.setText(String.valueOf(_controler._cliente.getTlf()));
		_changePass.setText("");
		_password.setText("");
		
		pack();
		setVisible(true);
	}
}
