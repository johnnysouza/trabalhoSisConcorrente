package Threads;

import pkg.Familia;

public class CalcularConsumoAgua extends ThreadCalculoConsumo {

		private int quantPeriodos;
		private Familia familia;
		private static int AGUAPORDIA = 2;
		
		public CalcularConsumoAgua(Familia familia, int quantPeriodos) {
			super("Consumo Agua " + Thread.currentThread().getId());
			this.familia = familia;
			this.quantPeriodos = quantPeriodos;
		}
		
		@Override
		public void run() {
			while (quantPeriodos > 0) {
				familia.getCidade().addConsumoAgua(familia.getIntegrantes().size() * AGUAPORDIA * 30);
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
