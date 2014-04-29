package pkg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import BaseDados.FamiliasManager;
import Threads.CalcularConsumoAgua;
import Threads.CalcularConsumoAlimentacao;
import Threads.CalcularConsumoLuz;
import Threads.ThreadCalculoConsumo;

public class Cidade extends Thread {

	private static final Semaphore semaforo = new Semaphore(3, true);
	public long intervalo;
	public int quantMeses;
	private List<ThreadCalculoConsumo> threadsConsumo = new ArrayList<>();
	private final List<Familia> familias = new ArrayList<>();
	private long consumoAgua;
	private long consumoAlimentacao;
	private long consumoLuz;
	private Lock lockAgua;
	private Lock lockAlimentacao;
	private Lock lockLuz;
	private Lock lockThreads;
	private int finalizedThreads = 0;
	
	public Cidade(int quantMeses, long intervalo, int id) {
		super("Cidade" + id);
		this.quantMeses = quantMeses;
		this.intervalo = intervalo;
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

	public Cidade() {
		this(10, 1000, 1);
	}

	@Override
	public void run() {
		try {
            semaforo.acquire();
            startThreads();
            while (quantMeses > 0) {
                startGrowing();
                sleep(intervalo);
                quantMeses--;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
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
			CalcularConsumoAgua agua = new CalcularConsumoAgua(familia, quantMeses);
			threadsConsumo.add(agua);
			agua.start();
			CalcularConsumoAlimentacao alimentacao = new CalcularConsumoAlimentacao(familia, quantMeses);
			threadsConsumo.add(alimentacao);
			alimentacao.start();
			
			CalcularConsumoLuz luz = new CalcularConsumoLuz(familia, quantMeses);
			threadsConsumo.add(luz);
			luz.start();
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

	public void addConsumoLuz(long consumoLuz) {
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
		if (cresimentoPop == 0) {
			cresimentoPop = 1;
		}
		Random familyRandom = new Random();
		for (int i = 0; i < cresimentoPop; i++) {
			Familia familia = familias
					.get(familyRandom.nextInt(familias.size()));
			familia.addNovoIntegrante();
		}
		System.out.println("Tamanho da população na cidade " + this.getName() + ": " + getTamanhoPopulacao());
		notificarThreadsConsumo();
	}
	
	private void notificarThreadsConsumo() {
		for (ThreadCalculoConsumo thread : threadsConsumo) {
			thread.notificar();
		}
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
