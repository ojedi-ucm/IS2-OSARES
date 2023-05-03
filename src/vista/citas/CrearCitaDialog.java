package vista.citas;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import control.ControlCita;
import vista.Utils;

public class CrearCitaDialog extends JDialog {
	
	private ControlCita _ctrl;
	private Calendar _fecha;
	
	private String _formattedDate;
	private JLabel _fechaLabel;
	
	private JComboBox<String> _horaCB;
	private DefaultComboBoxModel<String> _horasModel;
	
	private JComboBox<String> _motivoCB;
	private DefaultComboBoxModel<String> _motivosModel;
	
	public CrearCitaDialog(JFrame parent, ControlCita ctrl, Calendar fecha) {
		super(parent, true);
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Pedir Cita");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		// ---- INIT -----
		_motivosModel = new DefaultComboBoxModel<>();
		_horasModel = new DefaultComboBoxModel<>();
		_fecha = Calendar.getInstance();
		
		loadHoras();
		loadMotivos();
		
		// -------- Form -----------
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Label de Fecha
		_fechaLabel = new JLabel();
		_fechaLabel.setForeground(Color.BLUE);
        formPanel.add(_fechaLabel);
        
        formPanel.add(Box.createVerticalStrut(10));
		
		// Selecctionar Hora
        JLabel horaLabel = new JLabel("Hora");
        horaLabel.setAlignmentX(LEFT_ALIGNMENT);
		
        _horaCB = new JComboBox<String>();
        _horaCB.setModel(_horasModel);
        _horaCB.setSelectedIndex(-1);
        _horaCB.setAlignmentX(LEFT_ALIGNMENT);
        _horaCB.setPreferredSize(new Dimension(300, 30));
		
		formPanel.add(horaLabel);
		formPanel.add(_horaCB);

		// Seleccionar Motivo
		JLabel motivoLabel = new JLabel("Motivo de la Cita");
		motivoLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		_motivoCB = new JComboBox<String>();
		_motivoCB.setModel(_motivosModel);
		_motivoCB.setSelectedIndex(-1);
		_motivoCB.setAlignmentX(LEFT_ALIGNMENT);
		_motivoCB.setPreferredSize(new Dimension(300, 30));
		
		formPanel.add(motivoLabel);
		formPanel.add(_motivoCB);
		
		formPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(formPanel);
		
		
		// -------- Botones ---------
		JPanel btnPanel = new JPanel();
		JButton confirmBtn = new JButton("Confirmar");
		confirmBtn.addActionListener((a) -> {
			try {
				if(_horaCB.getSelectedIndex() == -1 || _motivoCB.getSelectedIndex() == -1)
					Utils.showErrorMsg("Existen campos vacíos.");
				else {
					Calendar hoy = Calendar.getInstance();
					parseFecha((String) _horaCB.getSelectedItem());
					
					if(_fecha.before(hoy))
						Utils.showErrorMsg("No puedes pedir una cita en el pasado.");
					else {
						_ctrl.createCita(_fecha.getTime(), (String) _motivoCB.getSelectedItem());
					
						this.setVisible(false);
						_horaCB.setSelectedIndex(-1);
						_motivoCB.setSelectedIndex(-1);
					}
				}
			} catch(Exception e) {
				Utils.showErrorMsg(e.toString());
			}
		});
		
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener((a) -> {
			this.setVisible(false);
			_horaCB.setSelectedIndex(-1);
			_motivoCB.setSelectedIndex(-1);
		});
		btnPanel.add(confirmBtn);
		btnPanel.add(cancelBtn);
		
		mainPanel.add(btnPanel);
        
		//-----------------------------
        
		pack();
		setResizable(false);
		setVisible(false);
	}
	
	private void loadMotivos() {
		String[] motivos = {
				"Gestión de Mis Cuentas",
				"Créditos e Hipotecas",
				"Asistencia Financiera",
				"Consulta General"
		};
		
		for(String mot: motivos)
			_motivosModel.addElement(mot);
	}
	
	private void loadHoras() {
		String[] horas = {
				"08:00",
				"08:30",
				"09:00",
				"09:30",
				"10:00",
				"10:30",
				"11:00",
				"11:30",
				"12:00",
				"12:30",
				"13:00",
				"13:30",
				"14:00"
		};
		
		for(String hora: horas)
			_horasModel.addElement(hora);
	}
	
	private void parseFecha(String time) throws Exception {
		String[] partes = time.split(":");
		
		int hora = Integer.parseInt(partes[0]);
		int min = Integer.parseInt(partes[1]);
		
		_fecha.set(Calendar.HOUR_OF_DAY, hora);
		_fecha.set(Calendar.MINUTE, min);
	}
	
	
	// ---------------
	
	public void open() {
		setLocationRelativeTo(null);
		
		pack();
		setVisible(true);
	}
	
	public void setFecha(Calendar fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		_fecha = fecha;
		
		_formattedDate = formatter.format(_fecha.getTime());
		_fechaLabel.setText("Nueva cita para el " + _formattedDate);
	}
}
