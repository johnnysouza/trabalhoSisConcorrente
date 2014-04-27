package Threads;

public class ThreadCalculoConsumo extends Thread {
	
	public ThreadCalculoConsumo(String nome) {
		super(nome);
	}
	
	public synchronized void notificar() {
		notifyAll();
	}

}
