package Threads;

import pkg.Familia;
import pkg.Pessoa;

public class CalcularConsumoLuz extends ThreadCalculoConsumo {

	private int quantPeriodos;
	private Familia familia;
	private static double LIGHTMAGICNUMBER = 0.04;
	
	public CalcularConsumoLuz(Familia familia, int quantPeriodos) {
		super("Consumo Luz " + Thread.currentThread().getId());
		this.familia = familia;
		this.quantPeriodos = quantPeriodos;
	}
	
	@Override
	public void run() {
		int consumoLuz = 0;
		while (quantPeriodos > 0) {
			for(Pessoa pessoa: familia.getIntegrantes()){
				consumoLuz += pessoa.getRenda() * LIGHTMAGICNUMBER; 
			}
			familia.getCidade().addConsumoLuz(consumoLuz);
			quantPeriodos--;
			try {
				aguardar();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void aguardar() throws InterruptedException {
		wait();
	}
}

