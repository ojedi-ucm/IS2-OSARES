package vista.observers;

import java.util.List;
import java.util.Map;

import modelo.Cuenta;

public interface CuentasObserver {
	public default void updateCuentas(Map<String, Cuenta> cuentas) { };
	public default void updateTransacciones(List<Map<String, Object>> transacciones) { };
	public default void updateDinero(float dineroTotal) { };
}
