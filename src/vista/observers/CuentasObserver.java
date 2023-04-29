package vista.observers;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import modelo.Cuenta;

public interface CuentasObserver {
	public default void updateCuentas(Map<String, Cuenta> cuentas) { };
	public default void updateTransacciones(List<JSONObject> transacciones) { };
	public default void updateDinero(float dineroTotal) { };
}
