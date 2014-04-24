package pkg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Threads.CalcularConsumoAgua;
import Threads.CalcularConsumoAlimentacao;
import Threads.CalcularConsumoLuz;
import BaseDados.FamiliasManager;

public class Cidade extends Thread {
	
	private final List<Familia>	familias	= new ArrayList<>();
	private long consumoAgua;
	private long consumoAlimentacao;
	private Lock lockAlimentacao;
	private long consumoLuz;
	private int finalizedThreads = 0;
	
	public Cidade() {
		Familia[] loadFamilys = FamiliasManager.loadFamilys();
		for (Familia familia : loadFamilys) {
			if (familia != null) {
				familia.setCidade(this);
				familias.add(familia);
			}
		}
		lockAlimentacao = new ReentrantLock();
	}
	
	@Override
	public void run() {
		while(true){
			startThreads();
			try {
				startGrowing();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	private void startGrowing() throws InterruptedException {
		while(finalizedThreads < (familias.size() * 3)){
			sleep(50);
		}
		finalizedThreads = 0;
		addPopulacao();
	}


	private void startThreads() {
		for (Familia familia: familias){
			new CalcularConsumoAgua(familia).start();
			new CalcularConsumoAlimentacao(familia).start();
			new CalcularConsumoLuz(familia).start();
		}
	}


	public synchronized void addConsumoAgua(long consumoAgua) {
		this.consumoAgua += consumoAgua;
		finalizedThreads++;
	}

	public void addConsumoAlimentacao(long consumoAlimentos) {
		try {
			lockAlimentacao.lock();
			this.consumoAlimentacao += consumoAlimentos;
			finalizedThreads++;
		} finally {
			lockAlimentacao.unlock();
		}
	}

	public synchronized void addConsumoLuz(long consumoLuz) throws InterruptedException {
			this.consumoLuz += consumoLuz;
			finalizedThreads++;
	}
	
	public synchronized void addPopulacao() {
		int tamPopulacao = getTamanhoPopulacao();
		int cresimentoPop = (int) (tamPopulacao * 0.03);
		Random familyRandom = new Random();
		for (int i = 0; i < cresimentoPop; i++) {
			Familia familia = familias.get(familyRandom.nextInt(familias.size()));
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
