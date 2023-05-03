package test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Random;

import modelo.Cliente;
import modelo.Cuenta;

class CuentaTest {

	@Test
	void test() throws Exception {
		JSONObject j1 = new JSONObject();
		j1.put("ss", "ES21");
		j1.put("numCuenta", "589638137127723772094053501024");
		j1.put("dinero", 0);
		j1.put("transacciones", new JSONObject());
		j1.put("nombre", "Cuenta 1");
		j1.put("titular", "001");
		
		JSONObject j2 = new JSONObject();
		j2.put("ss", "ES21");
		j2.put("numCuenta", "411105035808193325315838703453");
		j2.put("dinero", 50000);
		j2.put("transacciones", new JSONObject());
		j2.put("nombre", "Cuenta 2");
		j2.put("titular", "002");
		
		Cliente testCliente = new Cliente("003", "12345", "Fulanito Flores", 60000000);
		
		Random rand1 = new Random();
		Random rand2 = new Random();
		BigInteger numCuenta1 = new BigInteger(20 * 5, rand1);
		BigInteger numCuenta2 = new BigInteger(20 * 5, rand2);
		
		Cuenta c1 = new Cuenta(j1);
		Cuenta c2 = new Cuenta(j2);
		Cuenta c3 = new Cuenta("Cuenta 3", "ES21", numCuenta1, testCliente);
		Cuenta c4 = new Cuenta("Cuenta 4", "ES21", numCuenta2, testCliente);
		
		// IBAN
		assertEquals("ES21589638137127723772094053501024", c1.getIBAN(),
				"c1.getIBAN() ha devuelto un valor incorrecto");
		assertEquals("ES21411105035808193325315838703453", c2.getIBAN(),
				"c2.getIBAN() ha devuelto un valor incorrecto");
		assertEquals("ES21"+numCuenta1, c3.getIBAN(),
				"c3.getIBAN() ha devuelto un valor incorrecto");
		assertEquals("ES21"+numCuenta2, c4.getIBAN(),
				"c4.getIBAN() ha devuelto un valor incorrecto");
		
		// Num Cuenta
		assertEquals(new BigInteger("589638137127723772094053501024"), c1.getNumCuenta(),
				"c1.getNumCuenta() ha devuelto un valor incorrecto");
		assertEquals(new BigInteger("411105035808193325315838703453"), c2.getNumCuenta(),
				"c2.getNumCuenta() ha devuelto un valor incorrecto");
		assertEquals(numCuenta1, c3.getNumCuenta(),
				"c3.getNumCuenta() ha devuelto un valor incorrecto");
		assertEquals(numCuenta2, c4.getNumCuenta(),
				"c4.getNumCuenta() ha devuelto un valor incorrecto");
		
		// SS
		assertEquals("ES21", c1.getSS(),
				"c1.getSS() ha devuelto un valor incorrecto");
		assertEquals("ES21", c2.getSS(),
				"c2.getSS() ha devuelto un valor incorrecto");
		assertEquals("ES21", c3.getSS(),
				"c3.getSS() ha devuelto un valor incorrecto");
		assertEquals("ES21", c4.getSS(),
				"c4.getSS() ha devuelto un valor incorrecto");
		
		// Dinero
		assertEquals(0, c1.getDinero(),
				"c1.getDinero() ha devuelto un valor incorrecto");
		assertEquals(50000, c2.getDinero(),
				"c2.getDinero() ha devuelto un valor incorrecto");
		assertEquals(0, c3.getDinero(),
				"c3.getDinero() ha devuelto un valor incorrecto");
		assertEquals(0, c4.getDinero(),
				"c4.getDinero() ha devuelto un valor incorrecto");
		
		// Nombre
		assertEquals("Cuenta 1", c1.getNombre(),
				"c1.getNombre() ha devuelto un valor incorrecto");
		assertEquals("Cuenta 2", c2.getNombre(),
				"c2.getNombre() ha devuelto un valor incorrecto");
		assertEquals("Cuenta 3", c3.getNombre(),
				"c3.getNombre() ha devuelto un valor incorrecto");
		assertEquals("Cuenta 4", c4.getNombre(),
				"c4.getNombre() ha devuelto un valor incorrecto");
		
		// Titular ID
		assertEquals("001", c1.getTitularID(),
				"c1.getTitularID() ha devuelto un valor incorrecto");
		assertEquals("002", c2.getTitularID(),
				"c2.getTitularID() ha devuelto un valor incorrecto");
		assertEquals("003", c3.getTitularID(),
				"c3.getTitularID() ha devuelto un valor incorrecto");
		assertEquals("003", c4.getTitularID(),
				"c4.getTitularID() ha devuelto un valor incorrecto");
		
		// JSON
		assertEquals("{\"ss\":\"ES21\",\"numCuenta\":589638137127723772094053501024,\"dinero\":0,\"transacciones\":{},\"nombre\":\"Cuenta 1\",\"titular\":\"001\"}", c1.getJSON().toString(),
				"c1.getJSON() ha devuelto un valor incorrecto");
		assertEquals("{\"ss\":\"ES21\",\"numCuenta\":411105035808193325315838703453,\"dinero\":50000,\"transacciones\":{},\"nombre\":\"Cuenta 2\",\"titular\":\"002\"}", c2.getJSON().toString(),
				"c2.getJSON() ha devuelto un valor incorrecto");
		
	}

}