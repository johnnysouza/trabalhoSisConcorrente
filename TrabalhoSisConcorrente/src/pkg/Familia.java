package pkg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Familia extends Thread implements Serializable {
	
	private static final long	serialVersionUID	= -5070026948247923813L;
	
	private final List<Pessoa>	integrantes			= new ArrayList<>();
	
	public float calcularConsumo¡gua() {
		// TODO
		return 0;
	}
	
	public float calcularAlimentacao() {
		// TODO
		return 0;
	}
	
	public float calcularConsumoLuz() {
		// TODO
		return 0;
	}
	
	public int getPeopleCount() {
		return integrantes.size();
	}
	
	public void addPessoa(final Pessoa pessoa) {
		integrantes.add(pessoa);
	}
}
