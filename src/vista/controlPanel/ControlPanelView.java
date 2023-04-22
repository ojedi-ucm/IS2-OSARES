package vista.controlPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;

import java.text.DecimalFormat;

import java.io.File;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import control.ControlCuenta;
import vista.cuentas.*;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ControlPanelView extends JPanel {
	// Constantes
	private final String ICON_PATH = "resources/icons/";
	
	// Atributos
	private ControlCuenta _ctrlCuenta;
	
	private float _dineroTotal;
	private String _dineroTotalStr;
	
	private JToolBar _toolBar;
	
	private JButton _addDineroBtn;
	private JButton _retirarDineroBtn;
	private JButton _transBtn;
	private JButton _abrirCuentaBtn;
	private JButton _cerrarCuentaBtn;
	private JButton _configUsuarioBtn;
	private JButton _cerrarSesBtn;
	
	
	
	public ControlPanelView(ControlCuenta ctrlCuenta) {
		_ctrlCuenta = ctrlCuenta;
		initGUI();
	}
	
	private void initGUI() {
		setLayout(new BorderLayout());
		_toolBar = new JToolBar();
		_toolBar.setFloatable(false);
		add(_toolBar, BorderLayout.PAGE_START);
		
		//INIT
		updateDineroTotal();
		
		AddDineroDialog addDialog = new AddDineroDialog(new JFrame(), _ctrlCuenta);
		RetirarDineroDialog retirarDialog = new RetirarDineroDialog(new JFrame(), _ctrlCuenta);
		
		// Añadir Dinero
		_addDineroBtn = new JButton();
		btnConfig(_addDineroBtn);
		_addDineroBtn.setToolTipText("Añadir dinero");
		_addDineroBtn.setIcon(resizedIcon("plus.png"));
		_addDineroBtn.addActionListener((a) -> {
			addDialog.open();
		});
		_toolBar.add(_addDineroBtn);
		
		// Retirar Dinero
		_retirarDineroBtn = new JButton();
		btnConfig(_retirarDineroBtn);
		_retirarDineroBtn.setToolTipText("Retirar dinero");
		_retirarDineroBtn.setIcon(resizedIcon("minus.png"));
		_retirarDineroBtn.addActionListener((a) -> {
			retirarDialog.open();
		});
		_toolBar.add(_retirarDineroBtn);
		_toolBar.addSeparator();
		
		// Transferir Dinero
		_transBtn = new JButton();
		btnConfig(_transBtn);
		_transBtn.setToolTipText("Transferir dinero");
		_transBtn.setIcon(resizedIcon("trans.png"));
		_transBtn.addActionListener((a) -> {
			
		});
		_toolBar.add(_transBtn);
		_toolBar.addSeparator();
		
		
		JPanel cuentasPanel = new JPanel();
		cuentasPanel.setLayout(new BoxLayout(cuentasPanel, BoxLayout.Y_AXIS));
		// Crear Cuenta
		_abrirCuentaBtn = new JButton("Abrir Cuenta");
		btnConfig(_abrirCuentaBtn);
		_abrirCuentaBtn.setForeground(Color.GRAY);
		_abrirCuentaBtn.addActionListener((a) -> {
			
		});
		cuentasPanel.add(_abrirCuentaBtn);
		
		// Cerrar Cuenta
		_cerrarCuentaBtn = new JButton("Cerrar Cuenta");
		btnConfig(_cerrarCuentaBtn);
		_cerrarCuentaBtn.setForeground(Color.GRAY);
		_cerrarCuentaBtn.addActionListener((a) -> {
			
		});
		cuentasPanel.add(_cerrarCuentaBtn);
		_toolBar.add(cuentasPanel);
		
		
		// Mostrar Dinero Total
		JPanel dinTotalPanel = new JPanel();
		JPanel dinBox = new JPanel();
		dinBox.setLayout(new BoxLayout(dinBox, BoxLayout.Y_AXIS));
		JLabel dinTotalLabel = new JLabel("Patrimonio Neto");
		JLabel dinTotal = new JLabel(_dineroTotalStr + " €");
		
		dinTotal.setFont(new Font("Monospaced", Font.PLAIN, 16));
		dinTotal.setForeground(Color.BLUE);
		
		dinTotalLabel.setAlignmentX(CENTER_ALIGNMENT);
		dinTotal.setAlignmentX(CENTER_ALIGNMENT);
		
		dinBox.add(dinTotalLabel);
		dinBox.add(dinTotal);
		dinTotalPanel.add(dinBox);
		_toolBar.add(dinTotalPanel);
		
		
		// Configurar Usuario
		_configUsuarioBtn = new JButton();
		btnConfig(_configUsuarioBtn);
		_configUsuarioBtn.setToolTipText("Configuración de Usuario");
		_configUsuarioBtn.setIcon(resizedIcon("config.png"));
		_configUsuarioBtn.addActionListener((a) -> {
			
		});
		_toolBar.add(_configUsuarioBtn);
		
		// Cerrar Sesión
		_cerrarSesBtn = new JButton();
		btnConfig(_cerrarSesBtn);
		_cerrarSesBtn.setToolTipText("Cerrar Sesión");
		_cerrarSesBtn.setIcon(resizedIcon("close.png"));
		_cerrarSesBtn.addActionListener((a) -> {
			
		});
		_toolBar.add(_cerrarSesBtn);
	}
	
	
	// ------ Métodos secundarios -------
	
	private ImageIcon resizedIcon(String fileName) {
		File sourceimage = new File(ICON_PATH + fileName);
		Image img = null;
		
		try {
			img = ImageIO.read(sourceimage);
		} catch (Exception e){
			System.out.println(e.toString());
		}
		
		return new ImageIcon(img.getScaledInstance(24, 24, Image.SCALE_SMOOTH));
	}
	
	private void btnConfig(JButton btn) {
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		
	}
	
	private void updateDineroTotal() {
		_dineroTotal = _ctrlCuenta.getDineroTotal();
		
		DecimalFormat df = new DecimalFormat("#,###.##");
		_dineroTotalStr = df.format(_dineroTotal);
	}
}
