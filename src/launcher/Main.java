package launcher;

import modelo.Cita;
import modelo.Cliente;
import vista.MainWindow;

import java.util.Date;

import javax.swing.SwingUtilities;

import control.ControlCita;

public class Main {
//Atributos
	
//Metodos auxiliares
	
//Main
	public static void main(String[] args) {
		Cliente tit = new Cliente();
		ControlCita ctrl = new ControlCita(tit);
		
		try {
			SwingUtilities.invokeAndWait(() -> new MainWindow(ctrl));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}