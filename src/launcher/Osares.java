package launcher;

import modelo.Cita;
import modelo.Cliente;
import vista.MainWindow;

import java.util.Date;

import javax.swing.SwingUtilities;

import org.json.JSONObject;

import control.ControlCita;
import control.ControlCuenta;
import control.ControlCliente;

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