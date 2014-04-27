package Threads;

import pkg.Familia;
import pkg.Pessoa;

public class CalcularConsumoAlimentacao extends Thread {

	private Familia familia;
	private static double FOODMAGICNUMBER = 0.0255;
	
	public CalcularConsumoAlimentacao(Familia familia) {
		super("Consumo Alimentação " + Thread.currentThread().getId());
		this.familia = familia;
	}
	
	@Override
	public void run() {
		long consumoAlimentacao = 0;
		while (true) {
			for(Pessoa pessoa: familia.getIntegrantes()){
				consumoAlimentacao += (long) (pessoa.getPeso() * FOODMAGICNUMBER * 30);
			}
			familia.getCidade().addConsumoAlimentacao(consumoAlimentacao);
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
