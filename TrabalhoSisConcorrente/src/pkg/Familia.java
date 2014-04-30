package pkg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Familia implements Serializable {
	
	private static final long	serialVersionUID	= -5070026948247923813L;
	private Cidade cidade;
	private final List<Pessoa> integrantes = new ArrayList<>();
	
	public int getPeopleCount() {
		return integrantes.size();
	}

	public void addPessoa(final Pessoa pessoa) {
		integrantes.add(pessoa);
	}

	public void addNovoIntegrante() {
		int mediaEscolaridade = 0;
		double mediaRenda = 0;
		double mediaPeso = 0;
		int tamFamilia = integrantes.size();
		if (tamFamilia != 0) {
			int totalEscolaridade = 0;
			double totalRenda = 0;
			double totalPeso = 0;
			for (int i = 0; i < tamFamilia; i++) {
				Pessoa pessoa = integrantes.get(i);
				totalEscolaridade += pessoa.getEscolaridade().ordinal();
				totalRenda += pessoa.getRenda();
				totalPeso += pessoa.getPeso();
			}
			mediaEscolaridade = totalEscolaridade / tamFamilia;
			mediaRenda = totalRenda / tamFamilia;
			mediaPeso = totalPeso / tamFamilia;
			integrantes.add(new Pessoa(Escolaridade.values()[mediaEscolaridade], mediaRenda, mediaPeso));
		} else {
			Random random = new Random();
			mediaEscolaridade = random.nextInt(5);
			mediaRenda = random.nextDouble() * 200;
			mediaPeso = random.nextFloat() * 20000;
		}
		integrantes.add(new Pessoa(Escolaridade.values()[mediaEscolaridade], mediaRenda, mediaPeso));
	}
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Pessoa> getIntegrantes() {
		return integrantes;
	}

	public boolean matarPessoa() {
		if(integrantes.size() == 0){
			return false;
		}
		integrantes.remove(0);
		return true;
	}
}
