package vista.controlPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import control.ControlCuenta;
import vista.cuentas.*;
import vista.observers.CuentasObserver;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ControlPanelView extends JPanel implements CuentasObserver {
	// Constantes
	private final String ICON_PATH = "resources/icons/";
	
	// Atributos
	private ControlCuenta _ctrlCuenta;
	
	private float _dineroTotal;
	
	private JToolBar _toolBar;
	
	private JButton _addDineroBtn;
	private JButton _retirarDineroBtn;
	private JButton _transBtn;
	private JButton _abrirCuentaBtn;
	private JButton _cerrarCuentaBtn;
	private JButton _configUsuarioBtn;
	private JButton _citasBtn;
	private JButton _cerrarSesBtn;
	
	private JLabel _dinTotalLbl;
	
	private boolean _isDineroHidden = false;
	
	
	
	public ControlPanelView(ControlCuenta ctrlCuenta) {
		_ctrlCuenta = ctrlCuenta;
		initGUI();
		_ctrlCuenta.addObserver(this);
	}
	
	private void initGUI() {
		setLayout(new BorderLayout());
		_toolBar = new JToolBar();
		_toolBar.setFloatable(false);
		add(_toolBar, BorderLayout.PAGE_START);
		
		// ------ INIT --------
		
		AddDineroDialog addDialog = new AddDineroDialog(new JFrame(), _ctrlCuenta);
		RetirarDineroDialog retirarDialog = new RetirarDineroDialog(new JFrame(), _ctrlCuenta);
		TransferenciaDialog transDialog = new TransferenciaDialog(new JFrame(), _ctrlCuenta);
		AbrirCuentaDialog abrirCuentaDialog = new AbrirCuentaDialog(new JFrame(), _ctrlCuenta);
		CerrarCuentaDialog cerrarCuentaDialog = new CerrarCuentaDialog(new JFrame(), _ctrlCuenta);
		// FALTAN Dialogos de citas y config de usuario
		
		// ------ Añadir Dinero ------ 
		_addDineroBtn = new JButton();
		btnConfig(_addDineroBtn);
		_addDineroBtn.setToolTipText("Añadir dinero");
		_addDineroBtn.setIcon(resizedIcon("plus.png"));
		_addDineroBtn.addActionListener((a) -> {
			addDialog.open();
		});
		_toolBar.add(_addDineroBtn);
		
		// ------ Retirar Dinero ------ 
		_retirarDineroBtn = new JButton();
		btnConfig(_retirarDineroBtn);
		_retirarDineroBtn.setToolTipText("Retirar dinero");
		_retirarDineroBtn.setIcon(resizedIcon("minus.png"));
		_retirarDineroBtn.addActionListener((a) -> {
			retirarDialog.open();
		});
		_toolBar.add(_retirarDineroBtn);
		_toolBar.addSeparator();
		
		// ------ Transferir Dinero ------ 
		_transBtn = new JButton();
		btnConfig(_transBtn);
		_transBtn.setToolTipText("Transferir dinero");
		_transBtn.setIcon(resizedIcon("trans.png"));
		_transBtn.addActionListener((a) -> {
			transDialog.open();
		});
		_toolBar.add(_transBtn);
		_toolBar.addSeparator();
		
		
		// ------ Panel de cuentas ------ 
		JPanel cuentasPanel = new JPanel();
		cuentasPanel.setLayout(new BoxLayout(cuentasPanel, BoxLayout.Y_AXIS));
		// Crear Cuenta
		_abrirCuentaBtn = new JButton("Abrir Cuenta");
		btnConfig(_abrirCuentaBtn);
		_abrirCuentaBtn.setForeground(Color.GRAY);
		_abrirCuentaBtn.addActionListener((a) -> {
			abrirCuentaDialog.open();
		});
		cuentasPanel.add(_abrirCuentaBtn);
		
		// Cerrar Cuenta
		_cerrarCuentaBtn = new JButton("Cerrar Cuenta");
		btnConfig(_cerrarCuentaBtn);
		_cerrarCuentaBtn.setForeground(Color.GRAY);
		_cerrarCuentaBtn.addActionListener((a) -> {
			cerrarCuentaDialog.open();
		});
		cuentasPanel.add(_cerrarCuentaBtn);
		_toolBar.add(cuentasPanel);
		
		
		// ------  Mostrar Dinero Total ------ 
		JPanel dinTotalPanel = new JPanel();
		JPanel dinBox = new JPanel();
		dinBox.setLayout(new BoxLayout(dinBox, BoxLayout.Y_AXIS));
		JLabel dinTotalLabel = new JLabel("Patrimonio Neto");
		_dinTotalLbl = new JLabel("0.0 €");
		_dinTotalLbl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	_isDineroHidden = !_isDineroHidden;
            	updateDineroTotal(_dineroTotal);
            }
        });
		
		_dinTotalLbl.setFont(new Font("Monospaced", Font.PLAIN, 16));
		
		dinTotalLabel.setAlignmentX(CENTER_ALIGNMENT);
		_dinTotalLbl.setAlignmentX(CENTER_ALIGNMENT);
		
		dinBox.add(dinTotalLabel);
		dinBox.add(_dinTotalLbl);
		dinTotalPanel.add(dinBox);
		_toolBar.add(dinTotalPanel);
		
		// ------ Pedir Cita ------ 
		_citasBtn = new JButton();
		btnConfig(_citasBtn);
		_citasBtn.setToolTipText("Gestionar Citas");
		_citasBtn.setIcon(resizedIcon("calendar.png"));
		_citasBtn.addActionListener((a) -> {
			
		});
		_toolBar.add(_citasBtn);
		
		// ------ Configurar Usuario ------ 
		_configUsuarioBtn = new JButton();
		btnConfig(_configUsuarioBtn);
		_configUsuarioBtn.setToolTipText("Configuración de Usuario");
		_configUsuarioBtn.setIcon(resizedIcon("config.png"));
		_configUsuarioBtn.addActionListener((a) -> {
			
		});
		_toolBar.add(_configUsuarioBtn);
		_toolBar.addSeparator();
		
		// ------ Cerrar Sesión ------
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
	
	private void updateDineroTotal(float dineroTotal) {
		_dineroTotal = dineroTotal;
		
		DecimalFormat df = new DecimalFormat("#,###.##");
		
		if(_isDineroHidden) {
			_dinTotalLbl.setText("******");
            _dinTotalLbl.setForeground(Color.DARK_GRAY);
		} else {
			_dinTotalLbl.setText(df.format(_dineroTotal) + " €");
			_dinTotalLbl.setForeground(Color.BLUE);
		}
			
		
	}

	@Override
	public void updateDinero(float dineroTotal) {
		updateDineroTotal(dineroTotal);
	}
}
