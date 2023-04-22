package vista.cuentas;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

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
import javax.swing.JRadioButton;
import javax.swing.text.*;

import org.json.JSONObject;

import control.ControlCuenta;
import modelo.Cuenta;
import vista.observers.CuentasObserver;

public class TransferenciaDialog extends JDialog implements CuentasObserver {
	
	private final String OTRA_CUENTA = "Otra cuenta (IBAN)";
	
	private ControlCuenta _ctrl;
	private List<Cuenta> _cuentas;
	private List<String> _ibans;
	private DefaultComboBoxModel<String> _cuentasModel;
	private DefaultComboBoxModel<String> _cuentasYOtrosModel;
	
	private int _selectedIndexSalida;
	private int _selectedIndexLlegada;
	
	private JTextField _ibanTF;
	
	public TransferenciaDialog(JFrame parent, ControlCuenta ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Nueva Transferencia");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		_cuentas = _ctrl.getCuentas();
		_cuentasModel = new DefaultComboBoxModel<>();
		_cuentasYOtrosModel = new DefaultComboBoxModel<>();
		_ibans = new ArrayList<>();
		
		for(Cuenta c: _cuentas) {
			_cuentasModel.addElement(c.getNombre());
			_cuentasYOtrosModel.addElement(c.getNombre());
			_ibans.add(c.getIBAN());
		}
			
		_cuentasYOtrosModel.addElement(OTRA_CUENTA);
		
		
		// ------- Form Panel --------
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		// -------- Seleccionar Cuenta Salida ---------
		JLabel salidaLabel = new JLabel("Desde");
		salidaLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		JComboBox<String> cuentasSalida = new JComboBox<String>();
		cuentasSalida.setModel(_cuentasModel);
		cuentasSalida.setSelectedIndex(-1);
		cuentasSalida.setAlignmentX(LEFT_ALIGNMENT);
		cuentasSalida.setPreferredSize(new Dimension(300, 30));
		cuentasSalida.addActionListener((a) -> {
			_selectedIndexSalida = cuentasSalida.getSelectedIndex();
		});
		
		formPanel.add(salidaLabel);
		formPanel.add(cuentasSalida);
		
		
		formPanel.add(Box.createVerticalStrut(15));
		
		
		// -------- Seleccionar Cuenta Llegada ----------
		JLabel llegadaLabel = new JLabel("Transferir a");
		llegadaLabel.setAlignmentX(LEFT_ALIGNMENT);
		formPanel.add(llegadaLabel);
		
		JComboBox<String> cuentasLlegada = new JComboBox<String>();
		cuentasLlegada.setModel(_cuentasYOtrosModel);
		cuentasLlegada.setSelectedIndex(-1);
		cuentasLlegada.setAlignmentX(LEFT_ALIGNMENT);
		cuentasLlegada.setPreferredSize(new Dimension(300, 30));
		cuentasLlegada.addActionListener((a) -> {
			_selectedIndexLlegada = cuentasLlegada.getSelectedIndex();
			
			if(cuentasLlegada.getSelectedItem().equals(OTRA_CUENTA)) {
				_ibanTF.setText("ES21");
				_ibanTF.setEnabled(true);
			}
			else {
				_ibanTF.setText(_ibans.get(_selectedIndexLlegada));
				_ibanTF.setEnabled(false);
			}
		});
		
		formPanel.add(llegadaLabel);
		formPanel.add(cuentasLlegada);
		
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
		
		JTextField cantidad = new JTextField();
		cantidad.setAlignmentX(LEFT_ALIGNMENT);
		cantidad.setPreferredSize(new Dimension(300, 30));
		
		formPanel.add(cantidadLabel);
		formPanel.add(cantidad);
		
		formPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		
		mainPanel.add(formPanel);
		
		
		// ----- Botones ------
		JPanel btnPanel = new JPanel();
		JButton confirmBtn = new JButton("Confirmar"); // Confirmar cambios
		confirmBtn.addActionListener((a) -> {
			
		});
		btnPanel.add(confirmBtn);
		
		JButton cancelBtn = new JButton("Cancelar"); // Cancelar
		cancelBtn.addActionListener((a) -> {
			this.setVisible(false);
		});
		btnPanel.add(cancelBtn);
		
		mainPanel.add(btnPanel);
        
        // ---------------------
		
		pack();
		setResizable(false);
		setVisible(false);
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
