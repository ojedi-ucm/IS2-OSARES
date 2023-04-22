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
import vista.observers.CuentasObserver;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class CerrarCuentaDialog extends JDialog implements CuentasObserver {
	
	private ControlCuenta _ctrl;
	private List<Cuenta> _cuentas;
	private DefaultComboBoxModel<String> _cuentasModel;
	private DefaultComboBoxModel<String> _cuentasModifiedModel;
	
	private int _selectedIndexCuenta;
	
	private JComboBox<String> _transCBox;
	
	
	public CerrarCuentaDialog(JFrame parent, ControlCuenta ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Cerrar Cuenta");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		// ------ INIT -------
		_cuentas = _ctrl.getCuentas();
		_cuentasModel = new DefaultComboBoxModel<>();
		_cuentasModifiedModel = new DefaultComboBoxModel<>();
		
		for(Cuenta c: _cuentas) {
			_cuentasModel.addElement(c.getNombre());
			_cuentasModifiedModel.addElement(c.getNombre());
		}

		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		// ------- Seleccionar Cuenta -------
		JLabel cuentaLabel = new JLabel("Cerrar Cuenta");
		cuentaLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		JComboBox<String> cuentas = new JComboBox<String>();
		cuentas.setModel(_cuentasModel);
		cuentas.setSelectedIndex(-1);
		cuentas.setAlignmentX(LEFT_ALIGNMENT);
		cuentas.setPreferredSize(new Dimension(300, 30));
		cuentas.addActionListener((a) -> {
			_selectedIndexCuenta = cuentas.getSelectedIndex();
			resetModModel();
		});
		
		formPanel.add(cuentaLabel);
		formPanel.add(cuentas);
		
		formPanel.add(Box.createVerticalStrut(20));
		
		// --------- Seleccionar Cuenta para transferir fondos --------
		JLabel transLabel = new JLabel("Transferir fondos a");
		transLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		_transCBox = new JComboBox<String>();
		_transCBox.setSelectedIndex(-1);
		_transCBox.setAlignmentX(LEFT_ALIGNMENT);
		_transCBox.setPreferredSize(new Dimension(300, 30));
		
		formPanel.add(transLabel);
		formPanel.add(_transCBox);
		
		
		formPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(formPanel);
		
		
		// -------- Botones ---------
		JPanel btnPanel = new JPanel();
		JButton confirmBtn = new JButton("Confirmar");
		confirmBtn.addActionListener((a) -> {
			
		});
		btnPanel.add(confirmBtn);
		
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener((a) -> {
			this.setVisible(false);
		});
		btnPanel.add(cancelBtn);
		
		// ------------------------
		
		mainPanel.add(btnPanel);
        
        
		pack();
		setResizable(false);
		setVisible(false);
	}
	
	private void resetModModel() {
		_cuentasModifiedModel.removeAllElements();
		
		for(Cuenta c: _cuentas) {
			_cuentasModifiedModel.addElement(c.getNombre());
		}
		
		_cuentasModifiedModel.removeElementAt(_selectedIndexCuenta);
		_transCBox.setModel(_cuentasModifiedModel);
	}
	
	public void open() {
		setLocationRelativeTo(null);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void updateCuentas(Map<String, Cuenta> cuentas) {
		_cuentas.clear();
		
		for(Cuenta c: cuentas.values())
			_cuentas.add(c);
	}
}
