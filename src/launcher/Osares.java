package launcher;

import javax.swing.SwingUtilities;

import control.ControlCliente;
import vista.MainWindow;

public class Osares {
//Atributos
	
//Metodos auxiliares
	
//Main
	public static void main(String[] args) throws Exception {
		
		ControlCliente ctrl = new ControlCliente();
		
		try {
			SwingUtilities.invokeAndWait(() -> new MainWindow(ctrl));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}