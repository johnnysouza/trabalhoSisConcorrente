package pkg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import BaseDados.FamiliasManager;

public class Cidade extends Thread {
	
	private final List<Familia>	familias	= new ArrayList<>();
	private long consumoAgua;
	private long consumoAlimentacao;
	private Lock lockAlimentacao;
	private long consumoLuz;
	
	public Cidade() {
		Familia[] loadFamilys = FamiliasManager.loadFamilys();
		for (Familia familia : loadFamilys) {
			if (familia != null) {
				familias.add(familia);
				System.out.println(familia.getPeopleCount());
			}
		}
		lockAlimentacao = new ReentrantLock();
	}
	

	public synchronized void addConsumoAgua(int consumoAgua) {
		this.consumoAgua += consumoAgua;
	}

	public void addConsumoAlimentacao(int consumoAlimentos) {
		try {
			lockAlimentacao.lock();
			this.consumoAlimentacao += consumoAlimentos;
		} finally {
			lockAlimentacao.unlock();
		}
	}

	public synchronized void addConsumoLuz(int consumoLuz) throws InterruptedException {
			this.consumoLuz += consumoLuz;
	}
	
	public synchronized void addPopulacao() {
		int tamPopulacao = getTamanhoPopulacao();
		int cresimentoPop = (int) (tamPopulacao * 0.03);
		Random familyRandom = new Random(familias.size());
		for (int i = 0; i < cresimentoPop; i++) {
			Familia familia = familias.get(familyRandom.nextInt());
			familia.addNovoIntegrante();
		}
		notifyAll();
	}
	
	private int getTamanhoPopulacao() {
		int tamPopulacao = 0;
		for (Familia familia: familias) {
			tamPopulacao += familia.getPeopleCount();
		}
		return tamPopulacao;
	}
	
	public static void main(final String[] args) {
		new Cidade();
	}
	
}
