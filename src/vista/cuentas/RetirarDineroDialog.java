package vista.cuentas;

import java.awt.Dimension;
import java.util.List;

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

import javax.swing.JButton;
import javax.swing.JComboBox;

public class RetirarDineroDialog extends JDialog {
	
	private ControlCuenta _ctrl;
	private List<JSONObject> _cuentas;
	private DefaultComboBoxModel<String> _cuentasModel;
	
	
	public RetirarDineroDialog(JFrame parent, ControlCuenta ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Retirar Dinero");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		
		
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		// Seleccionar Cuenta
		JLabel cuentaLabel = new JLabel("Cuenta de Retirada");
		cuentaLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		_cuentas = _ctrl.getCuentas();
		_cuentasModel = new DefaultComboBoxModel<>();
		
		for(JSONObject s: _cuentas)
			_cuentasModel.addElement(s.getString("nombre"));
		
		JComboBox<String> cuentas = new JComboBox<String>();
		cuentas.setModel(_cuentasModel);
		cuentas.setAlignmentX(LEFT_ALIGNMENT);
		cuentas.setPreferredSize(new Dimension(300, 30));
		
		formPanel.add(cuentaLabel);
		formPanel.add(cuentas);
		
		formPanel.add(Box.createVerticalStrut(20));
		
		
		// Especificar Cantidad
		JLabel cantidadLabel = new JLabel("Cantidad (€)");
		cantidadLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		JTextField cantidad = new JTextField();
		cantidad.setAlignmentX(LEFT_ALIGNMENT);
		cantidad.setPreferredSize(new Dimension(300, 30));
		
		formPanel.add(cantidadLabel);
		formPanel.add(cantidad);
		
		formPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(formPanel);
		
		
		// Botones
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
