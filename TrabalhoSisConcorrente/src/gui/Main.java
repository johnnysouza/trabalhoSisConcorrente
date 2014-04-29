package gui;

import java.util.Scanner;

import pkg.Cidade;

public class Main {

	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		int quantCidades = 0;
		try {
			System.out.print("Digite a quantidade de cidades a serem simuladas: ");
			quantCidades = Integer.parseInt(scan.nextLine());
			while (quantCidades > 100 || quantCidades <= 0) {
				System.out.println("Quantidade de cidades não pode ser menor ou igual a 0 nem maior que 100");
				System.out.print("Digite a quantidade de cidades a serem simuladas: ");
				quantCidades = Integer.parseInt(scan.nextLine());
			}			
			
			Cidade[] cidades = new Cidade[quantCidades];
			for (int i = 0; i < quantCidades; i++) {
				System.out.print("Digite a quantidade de meses a serem simulados: ");
				int quantMeses = Integer.parseInt(scan.nextLine());
				System.out.print("Digite o valor do intervalo (ms) entre cada mês: ");
				long intervalo = Integer.parseInt(scan.nextLine());
				cidades[i] = new Cidade(quantMeses, intervalo, i + 1);
			}
			for (Cidade cidade : cidades) {
				cidade.start();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			scan.close();
		}
	}
	
}
