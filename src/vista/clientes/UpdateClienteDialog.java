package vista.clientes;

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
import modelo.Cliente;
import vista.Utils;

public class UpdateClienteDialog extends JDialog {

private ControlCliente _controler;
	
	public UpdateClienteDialog(ControlCliente ctrl) {
		super(new JFrame(), true);
		_controler = ctrl;
		initGUI();
		
	}
	
	private void initGUI() {
		setTitle("Update");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JPanel panelAux = new JPanel();
		panelAux.setLayout(new GridLayout(4, 2));
		
		// DNI
		JLabel DNILabel = new JLabel("DNI");
		JLabel DNI = new JLabel();
		DNI.setPreferredSize(new Dimension(200, 30));
		DNI.setText(_controler._cliente.getId());
		panelAux.add(DNILabel);
		panelAux.add(DNI);
		
		// Username
		JLabel userLabel = new JLabel("Username");
		JTextField username = new JTextField();
		username.setPreferredSize(new Dimension(200, 30));
		username.setText(_controler._cliente.getName());
		panelAux.add(userLabel);
		panelAux.add(username);
		
		// telefono
		JLabel tlfLabel = new JLabel("telofono");
		JTextField tlf = new JTextField();
		tlf.setPreferredSize(new Dimension(200, 30));
		tlf.setText(String.valueOf(_controler._cliente.getTlf()));
		panelAux.add(tlfLabel);
		panelAux.add(tlf);
		
		// Password
		JLabel passLabel = new JLabel("Password");
		JTextField password = new JTextField();
		password.setPreferredSize(new Dimension(200, 30));
		password.setText(_controler._cliente.getPassword());
		panelAux.add(passLabel);
		panelAux.add(password);
		
		mainPanel.add(panelAux);
		
		// Buttons
		JPanel btnPanel = new JPanel();
		
		JButton registerBtn = new JButton("Actualizar");
		registerBtn.addActionListener((a) -> {
			int telefono;
			String contra;
			String nombre;
			try {
				contra = password.getText();
				nombre = username.getText();
				telefono = Integer.parseInt(tlf.getText());
				if(telefono < 100000000 || telefono > 999999999 || contra.equals("") || contra.equals(null)
						|| nombre.equals("") || nombre.equals(null))
					throw new Exception();
				_controler._cliente.setPhone(telefono);
				_controler._cliente.setPassword(contra);
				_controler._cliente.setName(nombre);
				
				_controler.actualizarCliete(_controler._cliente);
				
				setVisible(false);
				
			} catch (Exception e) {
					Utils.showErrorMsg("Existe algun error en los campos introducidos, Por favor revileso e intentelo de nuevo");
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
