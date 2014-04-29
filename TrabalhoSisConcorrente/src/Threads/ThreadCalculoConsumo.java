package Threads;

public class ThreadCalculoConsumo extends Thread {
	
	public ThreadCalculoConsumo(final String nome) {
		super(nome);
	}
	
	public synchronized void notificar() {
		notify();
	}
	
}
