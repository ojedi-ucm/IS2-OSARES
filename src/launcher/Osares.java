package launcher;

import vista.MainWindow;

import javax.swing.SwingUtilities;

import control.ControlCliente;

public class Osares {
	public static void main(String[] args) {
		try {
			ControlCliente ctrlCliente = new ControlCliente();
			SwingUtilities.invokeAndWait(() -> new MainWindow(ctrlCliente));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}