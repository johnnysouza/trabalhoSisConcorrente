package pkg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Familia extends Thread implements Serializable {
	
	private static final long	serialVersionUID	= -5070026948247923813L;
	
	
	private Cidade cidade;
	private final List<Pessoa>	integrantes			= new ArrayList<>();
	
	public void calcularConsumo¡gua() throws InterruptedException {
		int consumoAgua = 0;
		while (true) {
			// TODO calculo
			cidade.addConsumoAgua(consumoAgua);
			wait();
		}
	}

	public void calcularAlimentacao() throws InterruptedException {
		int consumoAlimentacao = 0;
		while (true) {
			// TODO calculo
			cidade.addConsumoAlimentacao(consumoAlimentacao);
			wait();
		}
	}

	public void calcularConsumoLuz() throws InterruptedException {
		int consumoLuz = 0;
		while (true) {
			// TODO calculo
			cidade.addConsumoLuz(consumoLuz);
			wait();
		}
	}
	
	public int getPeopleCount() {
		return integrantes.size();
	}
	
	public void addPessoa(final Pessoa pessoa) {
		integrantes.add(pessoa);
	}
}
