package launcher;

import javax.swing.SwingUtilities;

import vista.MainWindow;

public class Main {
//Atributos
	
//Metodos auxiliares
	
//Main
	public static void main(String[] args) {
		try {
			SwingUtilities.invokeAndWait(() -> new MainWindow());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}