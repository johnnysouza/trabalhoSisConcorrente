package pkg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
		int tamFamilia = integrantes.size();
		int totalEscolaridade = 0;
		double totalRenda = 0;
		double totalPeso = 0;
		for (int i = 0; i < tamFamilia; i++) {
			Pessoa pessoa = integrantes.get(i);
			totalEscolaridade += pessoa.getEscolaridade().ordinal();
			totalRenda += pessoa.getRenda();
			totalPeso += pessoa.getPeso();
		}
		int mediaEscolaridade = totalEscolaridade / tamFamilia;
		double mediaRenda = totalRenda / tamFamilia;
		double mediaPeso = totalPeso / tamFamilia;
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
}
