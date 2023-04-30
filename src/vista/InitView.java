package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ControlCliente;

import javax.swing.JButton;

public class InitView extends JPanel {
	
	private ControlCliente _controler;
	
	public InitView(ControlCliente ctrl) {
		_controler = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Header image
		JPanel imgPanel = new JPanel();
        JLabel imgLabel = new JLabel();
        imgLabel.setSize(350, 250);
        
        Image img = new ImageIcon("resources/images/espana.png").getImage().getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(img);
        
        imgLabel.setIcon(imageIcon);
        imgPanel.add(imgLabel);
        
        add(imgPanel);
        
        // Login Dialog
        LoginDialog loginDialog = new LoginDialog(_controler);
        
        // Register Dialog
        RegisterDialog registerDialog = new RegisterDialog(_controler);
     
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