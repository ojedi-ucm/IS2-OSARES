package vista.observers;

import java.util.Collection;
import java.util.Map;

import modelo.Cuenta;

public interface CuentasObserver {
	public default void updateCuentas(Map<String, Cuenta> cuentas) { };
	public default void updateDinero(float dineroTotal) { };
}
