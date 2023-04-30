package vista;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ControlCliente;

public class DeleteQuestion extends JDialog{

	private ControlCliente _controler;
	
	public DeleteQuestion(ControlCliente ctrl) {
		super(new JFrame(), true);
		_controler = ctrl;
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JLabel pregunta = new JLabel("¿Estas seguro de que quieres eliminar la cuentas?");
		pregunta.setAlignmentX(LEFT_ALIGNMENT);
		mainPanel.add(pregunta);
		
		JButton si = new JButton("SI");
		si.addActionListener((a) -> {
			_controler.eliminarCliente(_controler.getID());
			setVisible(false);
		});
		JButton no = new JButton("NO");
		no.addActionListener((a) -> {
			setVisible(false);
		});
		JPanel aux = new JPanel();
		aux.add(si);
		aux.add(no);
		mainPanel.add(aux);
		
		setPreferredSize(new Dimension(400, 100));
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
