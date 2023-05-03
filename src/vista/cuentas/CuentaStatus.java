package vista.cuentas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import vista.Utils;

public class CuentaStatus extends JPanel {
	
	private String _idTitular;
	
	private JLabel _usuarioLabel;
	
	public CuentaStatus(String idTitular) {
		_idTitular = idTitular;
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		
		JPanel usuarioPanel = new JPanel();
		JLabel userTitleLabel = new JLabel(Utils.resizedIcon("user.png", 20, 20));
		userTitleLabel.setForeground(Color.GRAY);
		
		_usuarioLabel = new JLabel(_idTitular);
		_usuarioLabel.setForeground(Color.DARK_GRAY);
		_usuarioLabel.setFont(new Font("", Font.ITALIC, 13));
		usuarioPanel.add(userTitleLabel);
		usuarioPanel.add(_usuarioLabel);
		this.add(usuarioPanel);
		
		JSeparator sep = new JSeparator(JSeparator.VERTICAL);
		sep.setPreferredSize(new Dimension(10, 20));
		this.add(sep);
	}
}
