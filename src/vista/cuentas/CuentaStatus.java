package vista.cuentas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

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
		JLabel userTitleLabel = new JLabel(resizedIcon("user.png"));
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
	
	private ImageIcon resizedIcon(String fileName) {
		File sourceimage = new File("resources/icons/" + fileName);
		Image img = null;
		
		try {
			img = ImageIO.read(sourceimage);
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return new ImageIcon(img.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
	}
}
