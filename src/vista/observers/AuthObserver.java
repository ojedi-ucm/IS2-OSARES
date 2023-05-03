package vista.observers;

import modelo.Cliente;

public interface AuthObserver {
	public void authSuccess(Cliente cliente);
	public void closeSession(Cliente cliente);
}
