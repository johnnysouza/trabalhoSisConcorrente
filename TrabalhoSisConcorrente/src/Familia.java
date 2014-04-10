import java.util.List;

public class Familia extends Thread {

	private Cidade cidade;
	private List<Pessoa> integrantes;

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
	
	

}
