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
		Date d1 = new Date(2023, 4, 1);
		Date d2 = new Date(2023, 8, 25);
		Date d3 = new Date(2023, 9, 13);
		Date d4 = new Date(2023, 1, 30);
		
		Cita c1 = new Cita(d1, "1", "revisión cartilla");
		Cita c2 = new Cita(d2, "2", "petición préstamo");
		Cita c3 = new Cita(d3, "3", "petición hipoteca");
		Cita c4 = new Cita(d4, "4", "actualizar cartilla");
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		System.out.println(c3.toString());
		System.out.println(c4.toString());
		try {
			SwingUtilities.invokeAndWait(() -> new MainWindow(ctrl));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}