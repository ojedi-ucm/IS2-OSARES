package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import modelo.Cita;

class CitaTest {

	@Test
	void test() {
		
		Date d1 = new Date(2023, 4, 1);
		Date d2 = new Date(2023, 8, 25);
		Date d3 = new Date(2023, 9, 13);
		Date d4 = new Date(2023, 1, 30);
		Date d5 = new Date(2023, 12, 20);
		Date d6 = new Date(2023, 8, 19);
		
		JSONObject j1 = new JSONObject();
		j1.put("fecha", d5.getTime());
		j1.put("titular", "5");
		j1.put("motivo", "crear una cuenta nueva");
		
		JSONObject j2 = new JSONObject();
		j2.put("fecha", d6.getTime());
		j2.put("titular", "6");
		j2.put("motivo", "crear una cuenta nueva");
		
		Cita c1 = new Cita(d1, "1", "revisión cartilla");
		Cita c2 = new Cita(d2, "2", "petición préstamo");
		Cita c3 = new Cita(d3, "3", "petición hipoteca");
		Cita c4 = new Cita(d4, "4", "actualizar cartilla");
		Cita c5 = new Cita(j1);
		Cita c6 = new Cita(j2);
		
		assertEquals(new Date(2023, 4, 1), c1.getFecha(),
				"c1.getFecha ha devuelto un valor incorrecto");
		assertEquals(new Date(2023, 8, 25), c2.getFecha(),
				"c2.getFecha ha devuelto un valor incorrecto");
		assertEquals(new Date(2023, 9, 13), c3.getFecha(),
				"c3.getFecha ha devuelto un valor incorrecto");
		assertEquals(new Date(2023, 1, 30), c4.getFecha(),
				"c4.getFecha ha devuelto un valor incorrecto");
		assertEquals(new Date(2023, 12, 20), c5.getFecha(),
				"c5.getFecha ha devuelto un valor incorrecto");
		assertEquals(new Date(2023, 8, 19), c6.getFecha(),
				"c6.getFecha ha devuelto un valor incorrecto");
		
		assertEquals("1", c1.getTitularID(),
				"c1.getIdCita() ha devuelto un valor incorrecto");
		assertEquals("2", c2.getTitularID(),
				"c2.getIdCita() ha devuelto un valor incorrecto");
		assertEquals("3", c3.getTitularID(),
				"c3.getIdCita() ha devuelto un valor incorrecto");
		assertEquals("4", c4.getTitularID(),
				"c4.getIdCita() ha devuelto un valor incorrecto");
		assertEquals("5", c5.getTitularID(),
				"c5.getIdCita() ha devuelto un valor incorrecto");
		assertEquals("6", c6.getTitularID(),
				"c6.getIdCita() ha devuelto un valor incorrecto");
		
		assertEquals("revisión cartilla", c1.getMotivo(),
				"c1.getMotivo() ha devuelto un valor incorrecto");
		assertEquals("petición préstamo", c2.getMotivo(),
				"c2.getMotivo() ha devuelto un valor incorrecto");
		assertEquals("petición hipoteca", c3.getMotivo(),
				"c3.getMotivo() ha devuelto un valor incorrecto");
		assertEquals("actualizar cartilla", c4.getMotivo(),
				"c4.getMotivo() ha devuelto un valor incorrecto");
		assertEquals("crear una cuenta nueva", c5.getMotivo(),
				"c5.getMotivo() ha devuelto un valor incorrecto");
		assertEquals("crear una cuenta nueva", c6.getMotivo(),
				"c6.getMotivo() ha devuelto un valor incorrecto");

		assertEquals("1" + d1.getTime(), c1.getIdCita(),
				"c1.getIdCita() ha devuelto un valor incorrecto");
		assertEquals("2" + d2.getTime(), c2.getIdCita(),
				"c2.getIdCita() ha devuelto un valor incorrecto");
		assertEquals("3" + d3.getTime(), c3.getIdCita(),
				"c3.getIdCita() ha devuelto un valor incorrecto");
		assertEquals("4" + d4.getTime(), c4.getIdCita(),
				"c4.getIdCita() ha devuelto un valor incorrecto");
		assertEquals("5" + d5.getTime(), c5.getIdCita(),
				"c5.getIdCita() ha devuelto un valor incorrecto");
		assertEquals("6" + d6.getTime(), c6.getIdCita(),
				"c6.getIdCita() ha devuelto un valor incorrecto");
		
		assertEquals("{\"161641036000000\":{\"fecha\":61641036000000,\"motivo\":\"revisión cartilla\",\"titular\":\"1\"}}",
				c1.toString(), "c1.toString() ha devuelto un valor incorrecto");
		assertEquals("{\"261653736800000\":{\"fecha\":61653736800000,\"motivo\":\"petición préstamo\",\"titular\":\"2\"}}",
				c2.toString(), "c2.toString() ha devuelto un valor incorrecto");
		assertEquals("{\"361655292000000\":{\"fecha\":61655292000000,\"motivo\":\"petición hipoteca\",\"titular\":\"3\"}}",
				c3.toString(), "c3.toString() ha devuelto un valor incorrecto");
		assertEquals("{\"461635855600000\":{\"fecha\":61635855600000,\"motivo\":\"actualizar cartilla\",\"titular\":\"4\"}}",
				c4.toString(), "c4.toString() ha devuelto un valor incorrecto");
		assertEquals("{\"561663849200000\":{\"fecha\":61663849200000,\"motivo\":\"crear una cuenta nueva\",\"titular\":\"5\"}}",
				c5.toString(), "c5.toString() ha devuelto un valor incorrecto");
		
	}

}