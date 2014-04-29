package gui;

import java.util.Scanner;

import pkg.Cidade;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		try {
			System.out.print("Digite a quantidade de meses a serem simulados: ");
			int quantMeses = Integer.parseInt(scan.nextLine());
			System.out.print("Digite o valor do intervalo (ms) entre cada mês: ");
			long intervalo = Integer.parseInt(scan.nextLine());
			Cidade cidade = new Cidade(quantMeses, intervalo);
			cidade.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			scan.close();
		}
	}
	
}
