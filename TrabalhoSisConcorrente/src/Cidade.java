import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cidade {

	private long tamPopulacao;
	private long consumoAgua;
	private long consumoAlimentacao;
	private Lock lockAlimentacao;
	private long consumoLuz;

	public Cidade() {
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
	
	
}
