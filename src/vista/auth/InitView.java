package vista.auth;

import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ControlCliente;
import vista.observers.AuthObserver;

import javax.swing.JButton;

public class InitView extends JPanel {
	
	private ControlCliente _ctrlCliente;
	private AuthObserver _authObs;
	
	public InitView(AuthObserver authObs, ControlCliente ctrlCliente) {
		_ctrlCliente = ctrlCliente;
		_authObs = authObs;
		initGUI();
	}
	
	private void initGUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Header image
		JPanel imgPanel = new JPanel();
        JLabel imgLabel = new JLabel();
        imgLabel.setSize(350, 200);
        
        Image img = new ImageIcon("resources/images/logoOsares.png").getImage().getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(img);
        
        imgLabel.setIcon(imageIcon);
        imgPanel.add(imgLabel);
        
        add(imgPanel);
        
        // Login Dialog
        LoginDialog loginDialog = new LoginDialog(_authObs, _ctrlCliente);
        
        // Register Dialog
        RegisterDialog registerDialog = new RegisterDialog(_authObs, _ctrlCliente);
     
        // Buttons
        JPanel btnPanel = new JPanel();
        JButton login = new JButton("Login");
        login.addActionListener((a) -> loginDialog.open());
        btnPanel.add(login);
        
        JButton register = new JButton("Register");
        register.addActionListener((a) -> registerDialog.open());
        btnPanel.add(register);
        
        add(btnPanel);

	}
}
