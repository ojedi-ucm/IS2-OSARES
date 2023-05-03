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

import control.ControlCuenta;
import modelo.Cuenta;
import vista.Utils;
import vista.observers.CuentasObserver;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class AddDineroDialog extends JDialog implements CuentasObserver {
	
	private ControlCuenta _ctrl;
	private DefaultComboBoxModel<String> _cuentasModel;
	private List<String> _ibansList;
	
	private JComboBox<String> _cuentasCB;
	private int _selectedCuentaIndex;
	
	private JTextField _cantidadTF;
	
	
	public AddDineroDialog(JFrame parent, ControlCuenta ctrl) {
		super(parent, true);
		initGUI();
		_ctrl = ctrl;
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
		setTitle("Añadir Dinero");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		// Seleccionar Cuenta
		JLabel cuentaLabel = new JLabel("Cuenta de Destino");
		cuentaLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		_cuentasModel = new DefaultComboBoxModel<>();
		_ibansList = new ArrayList<>();
		
		_cuentasCB = new JComboBox<String>();
		_cuentasCB.setModel(_cuentasModel);
		_cuentasCB.setAlignmentX(LEFT_ALIGNMENT);
		_cuentasCB.setPreferredSize(new Dimension(300, 30));
		_cuentasCB.addActionListener((a) -> {
			_selectedCuentaIndex = _cuentasCB.getSelectedIndex();
		});
		
		formPanel.add(cuentaLabel);
		formPanel.add(_cuentasCB);
		
		formPanel.add(Box.createVerticalStrut(20));
		
		
		// Especificar Cantidad
		JLabel cantidadLabel = new JLabel("Cantidad (€)");
		cantidadLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		_cantidadTF = new JTextField();
		_cantidadTF.setAlignmentX(LEFT_ALIGNMENT);
		_cantidadTF.setPreferredSize(new Dimension(300, 30));
		
		formPanel.add(cantidadLabel);
		formPanel.add(_cantidadTF);
		
		formPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(formPanel);
		
		
		// Botones
		JPanel btnPanel = new JPanel();
		JButton confirmBtn = new JButton("Confirmar");
		confirmBtn.addActionListener((a) -> {
			try {
				if(_selectedCuentaIndex == -1 || _cantidadTF.getText().isBlank())
					Utils.showErrorMsg("Existen campos vacíos.");
				else if(Float.parseFloat(_cantidadTF.getText()) <= 0)
					Utils.showErrorMsg("La cantidad deber ser mayor que 0.");
				else {
					_ctrl.addDinero(_ibansList.get(_selectedCuentaIndex), Float.parseFloat(_cantidadTF.getText()));
					
					this.setVisible(false);
					_cantidadTF.setText("");
				}
			} catch(Exception e) {
				Utils.showErrorMsg(e.toString());
			}
		});
		btnPanel.add(confirmBtn);
		
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener((a) -> {
			this.setVisible(false);
			_cantidadTF.setText("");
		});
		btnPanel.add(cancelBtn);
		
		mainPanel.add(btnPanel);
        
        
		pack();
		setResizable(false);
		setVisible(false);
	}
	
	private void updateModel(Collection<Cuenta> col) {
		try {
			_ibansList.clear();
			_cuentasModel.removeAllElements();
		} catch(Exception e) { }
		
		for(Cuenta c: col) {
			_cuentasModel.addElement(c.getNombre());
			_ibansList.add(c.getIBAN());
		}
		
		_cuentasCB.setSelectedIndex(-1);
	}
	
	public void open() {
		setLocationRelativeTo(null);
		
		pack();
		setVisible(true);
	}
	
	@Override
	public void updateCuentas(Map<String, Cuenta> cuentas) {
		updateModel(cuentas.values());
	}
}
