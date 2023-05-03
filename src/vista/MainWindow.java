package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.ControlCita;
import control.ControlCliente;
import control.ControlCuenta;
import modelo.Cliente;

import javax.swing.JButton;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import vista.auth.InitView;
import vista.observers.AuthObserver;
import vista.cuentas.*;


public class MainWindow extends JFrame implements AuthObserver {
	private Clip clip;
	
	private ControlCliente _ctrlCliente;
	private ControlCuenta _ctrlCuenta;
	private ControlCita _ctrlCita;
	
	private JPanel _mainPanel;
	
	public MainWindow(ControlCliente ctrlCliente) {
		super("OSARES");
		
		_ctrlCliente = ctrlCliente;
		
		initGUI();
	}
	
	private void initGUI() {
		_mainPanel = new JPanel(new BorderLayout());
		setContentPane(_mainPanel);
		_mainPanel.setPreferredSize(new Dimension(600, 350));
		
		InitView initView = new InitView(this, _ctrlCliente);
		_mainPanel.add(initView, BorderLayout.CENTER);
		
		JPanel btnPanel = new JPanel();
		JButton playBtn = new JButton("Play");
		playBtn.addActionListener((a) -> {
			if(!clip.isActive())
				clip.start();
		});
		btnPanel.add(playBtn);
		
		JButton stopBtn = new JButton("Stop");
		stopBtn.addActionListener((a) -> {
			clip.stop();
		});
		btnPanel.add(stopBtn);
		
		_mainPanel.add(btnPanel, BorderLayout.NORTH);
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				try {
		            File audioFile = new File("resources/audio.wav");
		            clip = AudioSystem.getClip();
		            clip.open(AudioSystem.getAudioInputStream(audioFile));
		            clip.start();
		        } catch (Exception ex) {
		            System.out.println("Error al reproducir el audio: " + ex.getMessage());
		        }
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null); // Centra la ventana en la pantalla
	    setVisible(true);
	}
	
	

	@Override
	public void authSuccess(Cliente cliente) {
		_ctrlCuenta = new ControlCuenta(cliente, _ctrlCliente);
		_ctrlCita = new ControlCita(cliente, _ctrlCliente);
		
		_mainPanel = new JPanel(new BorderLayout());
		setContentPane(_mainPanel);
		_mainPanel.setPreferredSize(new Dimension(600, 600));
		
		CuentaClienteView cuentasClientes = new CuentaClienteView(_ctrlCuenta, _ctrlCliente, _ctrlCita, this);
		_mainPanel.add(cuentasClientes);
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    pack();
	    setLocationRelativeTo(null); // Centra la ventana en la pantalla
	    setVisible(true);
	}

	@Override
	public void closeSession(Cliente cliente) {
		initGUI();
	}
}
