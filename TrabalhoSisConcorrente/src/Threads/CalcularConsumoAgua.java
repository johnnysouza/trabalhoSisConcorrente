package Threads;

import pkg.Familia;

public class CalcularConsumoAgua extends Thread {

		private Familia familia;
		private static int AGUAPORDIA = 2;
		
		public CalcularConsumoAgua(Familia familia) {
			this.familia = familia;
		}
		
		@Override
		public void run() {
			while (true) {
				familia.getCidade().addConsumoAgua(familia.getIntegrantes().size() * AGUAPORDIA * 30);
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}		
	}
