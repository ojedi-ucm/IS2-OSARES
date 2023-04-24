package vista.cuentas;

import java.awt.Dimension;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
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

public class CerrarCuentaDialog extends JDialog implements CuentasObserver {
	
	private ControlCuenta _ctrl;
	private DefaultComboBoxModel<String> _cuentasModel;
	private DefaultComboBoxModel<String> _cuentasModifiedModel;
	
	private List<String> _ibansCerrar;
	private List<String> _ibansTrans;
	
	private int _selectedCuentaCerrarIndex;
	private int _selectedCuentaTransIndex;
	
	private JComboBox<String> _cuentasCB;
	private JComboBox<String> _transCBox;
	
	
	public CerrarCuentaDialog(JFrame parent, ControlCuenta ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
		setTitle("Cerrar Cuenta");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		// ------ INIT -------
		_cuentasModel = new DefaultComboBoxModel<>();
		_cuentasModifiedModel = new DefaultComboBoxModel<>();
		_ibansCerrar = new ArrayList<>();
		_ibansTrans = new ArrayList<>();
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		// ------- Seleccionar Cuenta -------
		JLabel cuentaLabel = new JLabel("Cerrar Cuenta");
		cuentaLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		_cuentasCB = new JComboBox<String>();
		_cuentasCB.setModel(_cuentasModel);
		_cuentasCB.setAlignmentX(LEFT_ALIGNMENT);
		_cuentasCB.setPreferredSize(new Dimension(300, 30));
		_cuentasCB.addActionListener((a) -> {
			_selectedCuentaCerrarIndex = _cuentasCB.getSelectedIndex();
			resetModModel();
		});
		
		formPanel.add(cuentaLabel);
		formPanel.add(_cuentasCB);
		
		formPanel.add(Box.createVerticalStrut(20));
		
		// --------- Seleccionar Cuenta para transferir fondos --------
		JLabel transLabel = new JLabel("Transferir fondos a");
		transLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		_transCBox = new JComboBox<String>();
		_transCBox.setAlignmentX(LEFT_ALIGNMENT);
		_transCBox.setPreferredSize(new Dimension(300, 30));
		_transCBox.addActionListener((a) -> {
			_selectedCuentaTransIndex = _transCBox.getSelectedIndex();
		});
		
		formPanel.add(transLabel);
		formPanel.add(_transCBox);
		
		
		formPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(formPanel);
		
		
		// -------- Botones ---------
		JPanel btnPanel = new JPanel();
		JButton confirmBtn = new JButton("Confirmar");
		confirmBtn.addActionListener((a) -> {
			try {
				if(_selectedCuentaCerrarIndex == -1 || _selectedCuentaTransIndex == -1)
					Utils.showErrorMsg("Existen campos vacíos.");
				else {
					_ctrl.cerrarCuenta(_ibansCerrar.get(_selectedCuentaCerrarIndex), _ibansTrans.get(_selectedCuentaTransIndex));
					this.setVisible(false);
				}
			} catch(Exception e) {
				Utils.showErrorMsg(e.toString());
			}
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
		try {
			_ibansTrans.clear();
			_cuentasModifiedModel.removeAllElements();
			
			for(int i = 0; i < _cuentasModel.getSize(); ++i) {
				_cuentasModifiedModel.addElement(_cuentasModel.getElementAt(i));
				_ibansTrans.add(_ibansCerrar.get(i));
			}
			
			_cuentasModifiedModel.removeElementAt(_selectedCuentaCerrarIndex);
			_ibansTrans.remove(_selectedCuentaCerrarIndex);
		} catch(Exception e) {}
			
		_transCBox.setModel(_cuentasModifiedModel);
		_transCBox.setSelectedIndex(-1);
	}
	
	private void updateModels(Collection<Cuenta> col) {
		try {
			_ibansCerrar.clear();
			_ibansTrans.clear();
			_cuentasModel.removeAllElements();
			_cuentasModifiedModel.removeAllElements();
		} catch(Exception e) { }
		
		for(Cuenta c: col) {
			_cuentasModel.addElement(c.getNombre());
			_cuentasModifiedModel.addElement(c.getNombre());
			_ibansCerrar.add(c.getIBAN());
			_ibansTrans.add(c.getIBAN());
		}
		
		_cuentasCB.setSelectedIndex(-1);
		_transCBox.setSelectedIndex(-1);
	}
	
	public void open() {
		setLocationRelativeTo(null);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void updateCuentas(Map<String, Cuenta> cuentas) {
		updateModels(cuentas.values());
	}
}
