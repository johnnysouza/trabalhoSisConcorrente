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

	public static final int WAIT_TIME = 1000;
	private final List<Familia> familias = new ArrayList<>();
	private long consumoAgua;
	private long consumoAlimentacao;
	private long consumoLuz;
	private Lock lockAgua;
	private Lock lockAlimentacao;
	private Lock lockLuz;
	private Lock lockThreads;
	private int finalizedThreads = 0;

	public Cidade() {
		super("Cidade");
		Familia[] loadFamilys = FamiliasManager.loadFamilys();
		for (Familia familia : loadFamilys) {
			if (familia != null) {
				familia.setCidade(this);
				familias.add(familia);
			}
		}
		lockAgua = new ReentrantLock();
		lockAlimentacao = new ReentrantLock();
		lockLuz = new ReentrantLock();
		lockThreads = new ReentrantLock();
	}

	@Override
	public void run() {
		startThreads();
		while (true) {
			try {
				startGrowing();
				sleep(WAIT_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void startGrowing() throws InterruptedException {
		while (finalizedThreads < (familias.size() * 3)) {
			sleep(50);
		}
		finalizedThreads = 0;
		addPopulacao();
	}

	private void startThreads() {
		for (Familia familia : familias) {
			new CalcularConsumoAgua(familia).start();
			new CalcularConsumoAlimentacao(familia).start();
			new CalcularConsumoLuz(familia).start();
		}
	}

	public void addConsumoAgua(long consumoAgua) {
		try {
			lockAgua.lock();
			this.consumoAgua += consumoAgua;
			lockThreads.lock();
			finalizedThreads++;
		} finally {
			lockAgua.unlock();
			lockThreads.unlock();
		}
	}

	public void addConsumoAlimentacao(long consumoAlimentos) {
		try {
			lockAlimentacao.lock();
			lockThreads.lock();
			this.consumoAlimentacao += consumoAlimentos;
			finalizedThreads++;
		} finally {
			lockAlimentacao.unlock();
			lockThreads.unlock();
		}
	}

	public void addConsumoLuz(long consumoLuz) throws InterruptedException {
		try {
			lockLuz.lock();
			lockThreads.lock();
			this.consumoLuz += consumoLuz;
			finalizedThreads++;
		} finally {
			lockLuz.unlock();
			lockThreads.unlock();
		}
	}

	public synchronized void addPopulacao() {
		int cresimentoPop = (int) (getTamanhoPopulacao() * 0.03);
		Random familyRandom = new Random();
		for (int i = 0; i < cresimentoPop; i++) {
			Familia familia = familias
					.get(familyRandom.nextInt(familias.size()));
			familia.addNovoIntegrante();
		}
		System.out.println("Tamanho da população: " + getTamanhoPopulacao());
		notifyAll();
	}

	private int getTamanhoPopulacao() {
		int tamPopulacao = 0;
		for (Familia familia : familias) {
			tamPopulacao += familia.getPeopleCount();
		}
		return tamPopulacao;
	}

	public static void main(final String[] args) {
		new Cidade();
	}

}
