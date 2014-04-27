package BaseDados;

import java.util.Random;

import pkg.Escolaridade;
import pkg.Familia;
import pkg.Pessoa;

public class BaseDados {
	
	public static void main(final String[] args) {
		int TotalPeople = 1;
		Pessoa[] listaPessoas = new Pessoa[TotalPeople];
		Random random = new Random();
		for (int i = 0; i < TotalPeople; i++) {
			Pessoa pessoa = new Pessoa();
			pessoa.setEscolaridade(Escolaridade.values()[random.nextInt(5)]);
			pessoa.setPeso(random.nextDouble() * 200);
			pessoa.setRenda(random.nextFloat() * 20000);
			listaPessoas[i] = pessoa;
		}
		
		int peopleByFamily;
		while ((peopleByFamily = random.nextInt(5)) == 0) {
		}
		int totalFamily = TotalPeople / peopleByFamily;
		Familia[] familias = new Familia[totalFamily];
		
		boolean foundFamily = false;
		for (Pessoa pessoa : listaPessoas) {
			while (!foundFamily) {
				int familyNumber = random.nextInt(totalFamily);
				if (familias[familyNumber] == null) {
					familias[familyNumber] = new Familia();
				}
				if (familias[familyNumber].getPeopleCount() < (peopleByFamily * 1.5)) {
					familias[familyNumber].addPessoa(pessoa);
					foundFamily = true;
				}
			}
			foundFamily = false;
		}
		FamiliasManager.saveFamilys(familias);
	}
}
