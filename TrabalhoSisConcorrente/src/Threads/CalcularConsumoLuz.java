package Threads;

import pkg.Familia;
import pkg.Pessoa;

public class CalcularConsumoLuz extends Thread {

	private Familia familia;
	private static double LIGHTMAGICNUMBER = 0.04;
	
	public CalcularConsumoLuz(Familia familia) {
		super("Consumo Luz " + Thread.currentThread().getId());
		this.familia = familia;
	}
	
	@Override
	public void run() {
		int consumoLuz = 0;
		while (true) {
			for(Pessoa pessoa: familia.getIntegrantes()){
				consumoLuz += pessoa.getRenda() * LIGHTMAGICNUMBER; 
			}
			try {
				familia.getCidade().addConsumoLuz(consumoLuz);
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

