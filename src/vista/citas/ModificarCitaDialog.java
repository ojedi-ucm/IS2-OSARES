package vista.citas;

import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.ControlCita;
import modelo.Cita;
import vista.Utils;

public class ModificarCitaDialog extends JDialog {
	
	private ControlCita _ctrl;
	private Cita _cita;
	private Calendar _fecha;
	
	private JLabel _fechaLabel;
	
	private JComboBox<Integer> _diaCB;
	private DefaultComboBoxModel<Integer> _diasModel;
	
	private JComboBox<String> _mesCB;
	private DefaultComboBoxModel<String> _mesesModel;
	
	private JComboBox<Integer> _yearCB;
	private DefaultComboBoxModel<Integer> _yearsModel;
	
	private JComboBox<String> _horaCB;
	private DefaultComboBoxModel<String> _horasModel;
	
	private JComboBox<String> _motivoCB;
	private DefaultComboBoxModel<String> _motivosModel;
	
	public ModificarCitaDialog(JFrame parent, ControlCita ctrl, Cita cita) {
		super(parent, true);
		_ctrl = ctrl;
		_cita = cita;
		
		 if(_cita != null) {
			_fecha = Calendar.getInstance();
			_fecha.setTime(_cita.getFecha());
			initGUI();
		}
	}
	
	private void initGUI() {
		setTitle("Mi Cita");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		// ---- INIT -----
		_motivosModel = new DefaultComboBoxModel<>();
		_diasModel = new DefaultComboBoxModel<>();
		_mesesModel = new DefaultComboBoxModel<>();
		_yearsModel = new DefaultComboBoxModel<>();
		_horasModel = new DefaultComboBoxModel<>();
		
		// -------- Form -----------
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Label de Fecha
		_fechaLabel = new JLabel("Fecha");
		_fechaLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		
		JPanel fechaPanel = new JPanel();
		fechaPanel.setAlignmentX(LEFT_ALIGNMENT);
		
		// Dia
		_diaCB = new JComboBox<Integer>();
		_diaCB.setModel(_diasModel);
		_diaCB.setEnabled(false);
		_diaCB.setAlignmentX(LEFT_ALIGNMENT);
		
		// Mes
		_mesCB = new JComboBox<String>();
		_mesCB.setModel(_mesesModel);
		_mesCB.setEnabled(false);
		_mesCB.setAlignmentX(LEFT_ALIGNMENT);
		
		// Año
		_yearCB = new JComboBox<Integer>();
		_yearCB.setModel(_yearsModel);
		_yearCB.setEnabled(false);
		_yearCB.setAlignmentX(LEFT_ALIGNMENT);
		
		// Cambiar BTN
		JButton modFecha = new JButton("Cambiar");
		modFecha.addActionListener((a)-> {
			_diaCB.setEnabled(true);
			_mesCB.setEnabled(true);
			_yearCB.setEnabled(true);
		});
		
		fechaPanel.add(_diaCB);
		fechaPanel.add(_mesCB);
		fechaPanel.add(_yearCB);
		fechaPanel.add(modFecha);
		
        formPanel.add(_fechaLabel);
        formPanel.add(fechaPanel);
		
		// Seleccionar Hora
        JLabel horaLabel = new JLabel("Hora");
        horaLabel.setAlignmentX(LEFT_ALIGNMENT);
		
        JPanel horaPanel = new JPanel();
        horaPanel.setAlignmentX(LEFT_ALIGNMENT);
        _horaCB = new JComboBox<String>();
        _horaCB.setModel(_horasModel);
        _horaCB.setEnabled(false);
        _horaCB.setAlignmentX(LEFT_ALIGNMENT);
        _horaCB.setPreferredSize(new Dimension(300, 30));
        
        JButton modHora = new JButton("Cambiar");
        modHora.addActionListener((a) -> {
        	_horaCB.setEnabled(true);
        });
		
        horaPanel.add(_horaCB);
        horaPanel.add(modHora);
		formPanel.add(horaLabel);
		formPanel.add(horaPanel);

		// Seleccionar Motivo
		JLabel motivoLabel = new JLabel("Motivo de la Cita");
		motivoLabel.setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel motivoPanel = new JPanel();
		motivoPanel.setAlignmentX(LEFT_ALIGNMENT);
		_motivoCB = new JComboBox<String>();
		_motivoCB.setModel(_motivosModel);
		_motivoCB.setEnabled(false);
		_motivoCB.setAlignmentX(LEFT_ALIGNMENT);
		_motivoCB.setPreferredSize(new Dimension(300, 30));
		
		JButton modMotivo = new JButton("Cambiar");
		modMotivo.addActionListener((a) -> {
			_motivoCB.setEnabled(true);
		});
		
		motivoPanel.add(_motivoCB);
		motivoPanel.add(modMotivo);
		formPanel.add(motivoLabel);
		formPanel.add(motivoPanel);
		
		formPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(formPanel);
		
		
		// -------- Botones ---------
		JPanel btnPanel = new JPanel();
		JButton confirmBtn = new JButton("Guardar");
		confirmBtn.setForeground(Color.GREEN);
		confirmBtn.addActionListener((a) -> {
			try {
				if(_horaCB.getSelectedIndex() == -1 || _motivoCB.getSelectedIndex() == -1)
					Utils.showErrorMsg("Existen campos vacíos.");
				else {
					Calendar hoy = Calendar.getInstance();
					parseFecha();
					
					if(_fecha.before(hoy))
						Utils.showErrorMsg("No puedes pedir una cita en el pasado.");
					else if(_fecha.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || _fecha.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
						Utils.showErrorMsg("No puedes pedir una cita el fin de semana. La oficina está cerrada.");
					else {
						_ctrl.updateCita(_cita, _fecha.getTime(), (String) _motivoCB.getSelectedItem());
					
						this.setVisible(false);
						_horaCB.setSelectedIndex(-1);
						_motivoCB.setSelectedIndex(-1);
					}
				}
			} catch(Exception e) {
				Utils.showErrorMsg(e.toString());
			}
		});
		
		JButton cerrarBtn = new JButton("Cerrar");
		cerrarBtn.addActionListener((a) -> {
			this.setVisible(false);
		});
		
		JButton cancelCitaBtn = new JButton("Cancelar Cita");
		cancelCitaBtn.setForeground(Color.RED);
		cancelCitaBtn.addActionListener((a) -> {
			
			int n = JOptionPane.showOptionDialog(this, "¿Seguro que quieres cancelar la cita?", "Cancelar Cita",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			if (n == 0) {
				_ctrl.deleteCita(_cita);
				this.setVisible(false);
			}
		});
		
		btnPanel.add(confirmBtn);
		btnPanel.add(cerrarBtn);
		btnPanel.add(cancelCitaBtn);
		
		mainPanel.add(btnPanel);
        
		//-----------------------------
		
		loadHoras();
		loadMotivos();
		loadDias();
		loadMeses();
		loadYears();
        
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
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
		
		_motivoCB.setSelectedItem(_cita.getMotivo());
	}
	
	private void loadDias() {
		for(int i = 1; i <= 31; i++)
			_diasModel.addElement(i);
		
		_diaCB.setSelectedItem(_fecha.get(Calendar.DAY_OF_MONTH));
	}
	
	private void loadMeses() {
		String[] meses = {
				"Enero",
				"Febrero",
				"Marzo",
				"Abril",
				"Mayo",
				"Junio",
				"Julio",
				"Agosto",
				"Septiembre",
				"Octubre",
				"Noviembre",
				"Diciembre"
		};
		
		for(String mes: meses)
			_mesesModel.addElement(mes);
		
		_mesCB.setSelectedIndex(_fecha.get(Calendar.MONTH));
	}
	
	private void loadYears() {
		for(int i = _fecha.get(Calendar.YEAR); i <= _fecha.get(Calendar.YEAR) + 5; i++)
			_yearsModel.addElement(i);
		
		_yearCB.setSelectedItem(_fecha.get(Calendar.YEAR));
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
		
		_horaCB.setSelectedItem(formattedTime());
	}
	
	private void parseFecha() throws Exception {
		String selectedHora = (String) _horaCB.getSelectedItem();
		String[] partes = selectedHora.split(":");
		
		int hora = Integer.parseInt(partes[0]);
		int min = Integer.parseInt(partes[1]);
		
		_fecha.set(Calendar.DAY_OF_MONTH, (int) _diaCB.getSelectedItem());
		_fecha.set(Calendar.MONTH, _mesCB.getSelectedIndex());
		_fecha.set(Calendar.YEAR, (int) _yearCB.getSelectedItem());
		
		_fecha.set(Calendar.HOUR_OF_DAY, hora);
		_fecha.set(Calendar.MINUTE, min);
	}
	
	private String formattedTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		
		return formatter.format(_fecha.getTime());
	}
}
