package Threads;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import pkg.Cidade;
import pkg.Familia;

public class Mortalidade extends Thread {

	private Lock lock;
	private Condition condicaoMorte;
	private Cidade cidade;

	public Mortalidade(Cidade cidade) {
		this.cidade = cidade;
		lock = new ReentrantLock();
		condicaoMorte = lock.newCondition();
	}

	@Override
	public void run() {
		try {
			lock.lock();
			condicaoMorte.await();
			while (cidade.isAlive() && cidade.getQuantMeses() > 0) {
				executarPessoas();
				condicaoMorte.await();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void executarPessoas() {
		Random random = new Random();
		int tamanhaPopulacao = cidade.getTamanhoPopulacao();
		List<Familia> familias = cidade.getFamilias();
		int quantMortes = 0;
		while (quantMortes == 0) {
			quantMortes = random.nextInt((int) (tamanhaPopulacao * 0.05));
		}
		while (quantMortes > 0) {
			boolean matou = false;
			while (!matou) {
				int quantFamilias = random.nextInt(familias.size());
				matou = familias.get(quantFamilias).matarPessoa();
			}
			quantMortes--;
		}
	}

	public Condition getCondicaoMorte() {
		return condicaoMorte;
	}

	public Lock getLock() {
		return lock;
	}
}
