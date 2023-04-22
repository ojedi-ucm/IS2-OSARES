package launcher;

import javax.swing.SwingUtilities;

import vista.MainWindow;
import control.ControlCuenta;

public class Main {
//Atributos
	
//Metodos auxiliares
	
//Main
	public static void main(String[] args) {
		ControlCuenta ctrl = new ControlCuenta();
		
		try {
			SwingUtilities.invokeAndWait(() -> new MainWindow(ctrl));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}