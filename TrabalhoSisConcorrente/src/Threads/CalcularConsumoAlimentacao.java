package Threads;

import pkg.Familia;
import pkg.Pessoa;

public class CalcularConsumoAlimentacao extends ThreadCalculoConsumo {

	private int quantPeriodos;
	private Familia familia;
	private static double FOODMAGICNUMBER = 0.0255;
	
	public CalcularConsumoAlimentacao(Familia familia, int quantPeriodos) {
		super("Consumo Alimentação " + Thread.currentThread().getId());
		this.familia = familia;
		this.quantPeriodos = quantPeriodos;
	}
	
	@Override
	public void run() {
		long consumoAlimentacao = 0;
		while (quantPeriodos > 0) {
			for(Pessoa pessoa: familia.getIntegrantes()){
				consumoAlimentacao += (long) (pessoa.getPeso() * FOODMAGICNUMBER * 30);
			}
			familia.getCidade().addConsumoAlimentacao(consumoAlimentacao);
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
