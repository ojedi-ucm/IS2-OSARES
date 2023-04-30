package vista.citas;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import control.ControlCita;
import vista.citas.calendario.CalendarioView;

public class CitasDialog extends JDialog {
	
	private ControlCita _ctrl;
	
	
	public CitasDialog(JFrame parent, ControlCita ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		setTitle("Gestión de Citas");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		CalendarioView calendario = new CalendarioView("Mis Citas");
		calendario.setPreferredSize(new Dimension(500,400));
		add(calendario);
		
		JPanel btnsPanel = new JPanel();
		JButton cerrar = new JButton("Cerrar");
		cerrar.addActionListener((a) -> {
			setVisible(false);
		});
		btnsPanel.add(cerrar);
		mainPanel.add(btnsPanel);
		
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
