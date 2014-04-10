package pkg;

import java.util.ArrayList;
import java.util.List;

import BaseDados.FamiliasManager;

public class Cidade extends Thread {
	
	private final List<Familia>	familias	= new ArrayList<>();
	
	public Cidade() {
		Familia[] loadFamilys = FamiliasManager.loadFamilys();
		for (Familia familia : loadFamilys) {
			if (familia != null) {
				familias.add(familia);
				System.out.println(familia.getPeopleCount());
			}
		}
	}
	
	public static void main(final String[] args) {
		new Cidade();
	}
	
}
