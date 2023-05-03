package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.ControlCliente;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import vista.auth.InitView;


public class MainWindow extends JFrame {
	private Clip clip;
	
	public MainWindow() {
		super("OSARES");
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		mainPanel.setPreferredSize(new Dimension(600, 350));
		
		InitView initView = new InitView();
		mainPanel.add(initView, BorderLayout.CENTER);
		
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
		
		mainPanel.add(btnPanel, BorderLayout.NORTH);
		
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
}
