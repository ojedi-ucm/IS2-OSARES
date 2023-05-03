package launcher;

import javax.swing.SwingUtilities;

import vista.MainWindow;
import control.ControlCuenta;

import modelo.Cliente;

public class Osares {
//Atributos
	
//Metodos auxiliares
	
//Main
	public static void main(String[] args) {
		Cliente tit = new Cliente();
		ControlCuenta ctrl = new ControlCuenta(tit);
		
		try {
			SwingUtilities.invokeAndWait(() -> new MainWindow(ctrl));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}