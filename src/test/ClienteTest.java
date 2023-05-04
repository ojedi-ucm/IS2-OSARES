package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import modelo.Cita;
import modelo.Cliente;
import modelo.Cuenta;

class ClienteTest {

	@Test
	void test() throws Exception {
		
  	    List<String> listaCuentas1 = new ArrayList<String>();
	    List<String> listaCitas1 = new ArrayList<String>();
	    List<String> listaCuentas2 = new ArrayList<String>();
	    List<String> listaCitas2 = new ArrayList<String>();
	    List<String> listaCuentas3 = new ArrayList<String>();
	    List<String> listaCitas3 = new ArrayList<String>();
	    List<String> listaCuentas4 = new ArrayList<String>();
	    List<String> listaCitas4 = new ArrayList<String>();
		
		JSONObject aux1 = new JSONObject();
		aux1.put("id", "003");
		aux1.put("name", "alberto");
		aux1.put("password", "d");
		aux1.put("tlf", 003);
		
		JSONObject j1 = new JSONObject();
		j1.put("ss", "ES21");
		j1.put("numCuenta", "589638137127723772094053501024");
		j1.put("dinero", 0);
		j1.put("transacciones", new JSONObject());
		j1.put("nombre", "Cuenta 4");
		j1.put("titular", "004");
		
		Cuenta cuenta = new Cuenta(j1);
		
		listaCuentas4.add(cuenta.getIBAN());
		
		Date d1 = new Date(2023, 4, 1);
		
		JSONObject j2 = new JSONObject();
		j2.put("fecha", d1.getTime());
		j2.put("titular", "6");
		j2.put("motivo", "crear una cuenta nueva");
		
		Cita cita = new Cita(j2);
		
		listaCitas4.add(cita.getIdCita());

		JSONObject aux2 = new JSONObject();
		aux2.put("id", "004");
		aux2.put("name", "luis");
		aux2.put("password", "x");
		aux2.put("tlf", 004);
		JSONArray Array_Cuentas = new JSONArray();
		Array_Cuentas.put(cuenta.getIBAN());
		aux2.put("listaCuentas", Array_Cuentas);
		JSONArray Array_Citas = new JSONArray();
		Array_Citas.put(cita.getIdCita());
		aux2.put("listaCitas", Array_Citas);
		
		Cliente c1 = new Cliente("001", "si", "juan", 001);
		Cliente c2 = new Cliente("002", "no", "pepe", 002);
		Cliente c3 = new Cliente(aux1);
		Cliente c4 = new Cliente(aux2);
		
		//ID
		assertEquals("001", c1.getId(),
				"c1.getId() ha devuelto un valor incorrecto");
		assertEquals("002", c2.getId(),
				"c2.getId() ha devuelto un valor incorrecto");
		assertEquals("003", c3.getId(),
				"c3.getId() ha devuelto un valor incorrecto");
		assertEquals("004", c4.getId(),
				"c4.getId() ha devuelto un valor incorrecto");
		
		//NAME
		assertEquals("juan", c1.getName(),
				"c1.getName() ha devuelto un valor incorrecto");
		assertEquals("pepe", c2.getName(),
				"c2.getName() ha devuelto un valor incorrecto");
		assertEquals("alberto", c3.getName(),
				"c3.getName() ha devuelto un valor incorrecto");
		assertEquals("luis", c4.getName(),
				"c4.getName() ha devuelto un valor incorrecto");
		
		//TELEFONO
		assertEquals(001, c1.getTlf(),
				"c1.getTlf() ha devuelto un valor incorrecto");
		assertEquals(002, c2.getTlf(),
				"c2.getTlf() ha devuelto un valor incorrecto");
		assertEquals(003, c3.getTlf(),
				"c3.getTlf() ha devuelto un valor incorrecto");
		assertEquals(004, c4.getTlf(),
				"c4.getTlf() ha devuelto un valor incorrecto");
		
		//PASSWORD
		assertEquals("si", c1.getPassword(),
				"c1.getPassword() ha devuelto un valor incorrecto");
		assertEquals("no", c2.getPassword(),
				"c2.getPassword() ha devuelto un valor incorrecto");
		assertEquals("d", c3.getPassword(),
				"c3.getPassword() ha devuelto un valor incorrecto");
		assertEquals("x", c4.getPassword(),
				"c4.getPassword() ha devuelto un valor incorrecto");
		
		//CITAS
		assertEquals(listaCitas1, c1.getCitas(),
				"c1.getCitas() ha devuelto un valor incorrecto");
		assertEquals(listaCitas2, c2.getCitas(),
				"c2.getCitas() ha devuelto un valor incorrecto");
		assertEquals(listaCitas3, c3.getCitas(),
				"c3.getCitas() ha devuelto un valor incorrecto");
		assertEquals(listaCitas4, c4.getCitas(),
				"c4.getCitas() ha devuelto un valor incorrecto");
		
		//CUENTAS
		assertEquals(listaCuentas1, c1.getCuentas(),
				"c1.getCuentas() ha devuelto un valor incorrecto");
		assertEquals(listaCuentas2, c2.getCuentas(),
				"c2.getCuentas() ha devuelto un valor incorrecto");
		assertEquals(listaCuentas3, c3.getCuentas(),
				"c3.getCuentas() ha devuelto un valor incorrecto");
		assertEquals(listaCuentas4, c4.getCuentas(),
				"c4.getCuentas() ha devuelto un valor incorrecto");
		
		//SETTERS
		c1.setName("yo");
		c1.setPassword("yo");
		c1.setPhone(000000001);
		assertEquals("yo", c1.getName(),
				"c1.getName() ha devuelto un valor incorrecto");
		assertEquals(000000001, c1.getTlf(),
				"c1.getTlf() ha devuelto un valor incorrecto");
		assertEquals("yo", c1.getPassword(),
				"c1.getPassword() ha devuelto un valor incorrecto");
		
		//ADD CITAS Y CUENTAS
		JSONObject a2 = new JSONObject();
		j2.put("ss", "ES21");
		j2.put("numCuenta", "411105035808193325315838703453");
		j2.put("dinero", 50000);
		j2.put("transacciones", new JSONObject());
		j2.put("nombre", "Cuenta 2");
		j2.put("titular", "002");
		Cuenta cuenta2 = new Cuenta(a2);
		
		Date d5 = new Date(2023, 12, 20);
		JSONObject a1 = new JSONObject();
		j1.put("fecha", d5.getTime());
		j1.put("titular", "5");
		j1.put("motivo", "crear una cuenta nueva");
		Cita cita2 = new Cita(a1);
		
		c2.addCita(cita2.getIdCita());
		c2.addCuenta(cuenta2.getIBAN());
		listaCitas2.add(cita2.getIdCita());
		listaCuentas2.add(cuenta2.getIBAN());
		
		assertEquals(listaCitas2, c2.getCitas(),
				"c2.getCitas() ha devuelto un valor incorrecto");
		assertEquals(listaCuentas2, c2.getCuentas(),
				"c2.getCuentas() ha devuelto un valor incorrecto");
		
		//REMOVE CITAS Y CUENTAS
		c2.removeCita(cita2.getIdCita());
		c2.removeCuenta(cuenta2.getIBAN());
		listaCitas2.remove(cita2.getIdCita());
		listaCuentas2.remove(cuenta2.getIBAN());
		
		assertEquals(listaCitas2, c2.getCitas(),
				"c2.getCitas() ha devuelto un valor incorrecto");
		assertEquals(listaCuentas2, c2.getCuentas(),
				"c2.getCuentas() ha devuelto un valor incorrecto");
		
		//JSON
		String texto = "{\"password\":\"yo\",\"listaCitas\":[],\"listaCuentas\":[],\"name\":\"yo\",\"id\":\"001\",\"tlf\":1}";
		String texto2 = "{\"password\":\"x\",\"listaCitas\":[\"661641036000000\"],\"listaCuentas\":[\"ES21589638137127723772094053501024\"],\"name\":\"luis\",\"id\":\"004\",\"tlf\":4}";
		
		assertEquals(texto, c1.toString(),
				"c1.toString() ha devuelto un valor incorrecto");
		assertEquals(texto2, c4.toString(),
				"c4.toString() ha devuelto un valor incorrecto");
	}

}