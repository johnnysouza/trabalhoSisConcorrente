package Threads;

import pkg.Familia;

public class CalcularConsumoAgua extends Thread {

		private Familia familia;
		private static int AGUAPORDIA = 2;
		
		public CalcularConsumoAgua(Familia familia) {
			super("Consumo Agua " + Thread.currentThread().getId());
			this.familia = familia;
		}
		
		@Override
		public void run() {
			while (true) {
				familia.getCidade().addConsumoAgua(familia.getIntegrantes().size() * AGUAPORDIA * 30);
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
