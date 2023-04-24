package vista.cuentas;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vista.Utils;

import control.ControlCuenta;
import modelo.Cuenta;
import vista.observers.CuentasObserver;

public class TransferenciaDialog extends JDialog implements CuentasObserver {
	
	private final String OTRA_CUENTA = "Otra cuenta (IBAN)";
	
	private ControlCuenta _ctrl;
	private List<String> _ibansDesde;
	private List<String> _ibansTrans;
	private DefaultComboBoxModel<String> _cuentasModel;
	private DefaultComboBoxModel<String> _cuentasYOtrosModel;
	
	private JComboBox<String> _cuentasSalida;
	private JComboBox<String> _cuentasLlegada;
	
	private JTextField _cantidadTF;
	
	private int _selectedIndexSalida;
	private int _selectedIndexLlegada;
	
	private JTextField _ibanTF;
	
	public TransferenciaDialog(JFrame parent, ControlCuenta ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
		setTitle("Nueva Transferencia");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		_cuentasModel = new DefaultComboBoxModel<>();
		_cuentasYOtrosModel = new DefaultComboBoxModel<>();
		_ibansDesde = new ArrayList<>();
		_ibansTrans = new ArrayList<>();
		
		
		// ------- Form Panel --------
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		// -------- Seleccionar Cuenta Salida ---------
		JLabel salidaLabel = new JLabel("Desde");
		salidaLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		_cuentasSalida = new JComboBox<String>();
		_cuentasSalida.setModel(_cuentasModel);
		_cuentasSalida.setAlignmentX(LEFT_ALIGNMENT);
		_cuentasSalida.setPreferredSize(new Dimension(300, 30));
		_cuentasSalida.addActionListener((a) -> {
			_selectedIndexSalida = _cuentasSalida.getSelectedIndex();
			resetModModel();
		});
		
		formPanel.add(salidaLabel);
		formPanel.add(_cuentasSalida);
		
		
		formPanel.add(Box.createVerticalStrut(15));
		
		
		// -------- Seleccionar Cuenta Llegada ----------
		JLabel llegadaLabel = new JLabel("Transferir a");
		llegadaLabel.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(llegadaLabel);
		
		_cuentasLlegada = new JComboBox<String>();
		_cuentasLlegada.setModel(_cuentasYOtrosModel);
		_cuentasLlegada.setAlignmentX(LEFT_ALIGNMENT);
		_cuentasLlegada.setPreferredSize(new Dimension(300, 30));
		_cuentasLlegada.addActionListener((a) -> {
			_selectedIndexLlegada = _cuentasLlegada.getSelectedIndex();
			
			if(_cuentasLlegada.getSelectedItem() != null) {
				if(_cuentasLlegada.getSelectedItem().equals(OTRA_CUENTA)) {
					_ibanTF.setText("ES21");
					_ibanTF.setEnabled(true);
				}
				else if(!_ibansTrans.isEmpty()) {
					_ibanTF.setText(_ibansTrans.get(_selectedIndexLlegada));
					_ibanTF.setEnabled(false);
				}
			}
		});
		
		formPanel.add(llegadaLabel);
		formPanel.add(_cuentasLlegada);
		
		// ------- Especificar IBAN ---------
		_ibanTF = new JTextField();
		_ibanTF.setAlignmentX(LEFT_ALIGNMENT);
		_ibanTF.setPreferredSize(new Dimension(300, 30));
		_ibanTF.setEnabled(false);
		
		formPanel.add(_ibanTF);
		
		
		formPanel.add(Box.createVerticalStrut(20));
		
		
		// ------- Especificar Cantidad ---------
		JLabel cantidadLabel = new JLabel("Cantidad (€)");
		cantidadLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		_cantidadTF = new JTextField();
		_cantidadTF.setAlignmentX(LEFT_ALIGNMENT);
		_cantidadTF.setPreferredSize(new Dimension(300, 30));
		
		formPanel.add(cantidadLabel);
		formPanel.add(_cantidadTF);
		
		formPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		
		mainPanel.add(formPanel);
		
		
		// ----- Botones ------
		JPanel btnPanel = new JPanel();
		JButton confirmBtn = new JButton("Confirmar"); // Confirmar transferencia
		confirmBtn.addActionListener((a) -> {
			try {
				if(_selectedIndexSalida == -1 || _ibanTF.getText().isBlank() || _cantidadTF.getText().isBlank())
					Utils.showErrorMsg("Existen campos vacíos.");
				else {
					_ctrl.transferirDinero(_ibansDesde.get(_selectedIndexSalida), _ibanTF.getText(), Float.parseFloat(_cantidadTF.getText()));
					
					this.setVisible(false);
					_cantidadTF.setText("");
				}
			} catch(Exception e) {
				Utils.showErrorMsg(e.toString());
			}
		});
		btnPanel.add(confirmBtn);
		
		JButton cancelBtn = new JButton("Cancelar"); // Cancelar
		cancelBtn.addActionListener((a) -> {
			this.setVisible(false);
			_cantidadTF.setText("");
			_cuentasSalida.setSelectedIndex(-1);
			_cuentasLlegada.setSelectedIndex(-1);
		});
		btnPanel.add(cancelBtn);
		
		mainPanel.add(btnPanel);
        
        // ---------------------
		
		pack();
		setResizable(false);
		setVisible(false);
	}
	
	private void resetModModel() {
		try {
			_ibansTrans.clear();
			_cuentasYOtrosModel.removeAllElements();
			
			for(int i = 0; i < _cuentasModel.getSize(); ++i) {
				_cuentasYOtrosModel.addElement(_cuentasModel.getElementAt(i));
				_ibansTrans.add(_ibansDesde.get(i));
			}
			
			_cuentasYOtrosModel.removeElementAt(_selectedIndexSalida);
			_ibansTrans.remove(_selectedIndexSalida);
		} catch(Exception e) {}
		
		_cuentasYOtrosModel.addElement(OTRA_CUENTA);
		_cuentasLlegada.setModel(_cuentasYOtrosModel);
		_cuentasLlegada.setSelectedIndex(-1);
		_ibanTF.setText("");
		_ibanTF.setEnabled(false);
	}
	
	private void updateModels(Collection<Cuenta> col) {
		try {
			_ibansDesde.clear();
			_ibansTrans.clear();
			_cuentasModel.removeAllElements();
			_cuentasYOtrosModel.removeAllElements();
		} catch(Exception e) {}
		
		for(Cuenta c: col) {
			_cuentasModel.addElement(c.getNombre());
			_cuentasYOtrosModel.addElement(c.getNombre());
			_ibansDesde.add(c.getIBAN());
			_ibansTrans.add(c.getIBAN());
		}
		
		_cuentasYOtrosModel.addElement(OTRA_CUENTA);
		_cuentasSalida.setSelectedIndex(-1);
		_cuentasLlegada.setSelectedIndex(-1);
		_ibanTF.setText("");
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
