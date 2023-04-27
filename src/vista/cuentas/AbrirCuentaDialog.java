package vista.cuentas;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONObject;

import control.ControlCuenta;
import modelo.Cuenta;
import vista.Utils;
import vista.observers.CuentasObserver;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class AbrirCuentaDialog extends JDialog {
	
	private ControlCuenta _ctrl;
	private JTextField _nombreTF;
	
	
	public AbrirCuentaDialog(JFrame parent, ControlCuenta ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Abrir Nueva Cuenta");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		// Especificar Cantidad
		JLabel nombreLabel = new JLabel("Nombre de la Cuenta");
		nombreLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		_nombreTF = new JTextField();
		_nombreTF.setAlignmentX(LEFT_ALIGNMENT);
		_nombreTF.setPreferredSize(new Dimension(300, 30));
		
		formPanel.add(nombreLabel);
		formPanel.add(_nombreTF);
		
		formPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(formPanel);
		
		
		// Botones
		JPanel btnPanel = new JPanel();
		JButton confirmBtn = new JButton("Confirmar");
		confirmBtn.addActionListener((a) -> {
			try {
				_ctrl.abrirCuenta(_nombreTF.getText());
				setVisible(false);
				_nombreTF.setText("");
			} catch(Exception e) {
				e.printStackTrace();
				Utils.showErrorMsg(e.toString());
			}
		});
		btnPanel.add(confirmBtn);
		
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener((a) -> {
			this.setVisible(false);
			_nombreTF.setText("");
		});
		btnPanel.add(cancelBtn);
		
		mainPanel.add(btnPanel);
        
        
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
