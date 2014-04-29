package gui;

import java.util.Scanner;

import pkg.Cidade;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		try {
			System.out.print("Digite a quantidade de anos a serem simulados: ");
			int quantAnos = Integer.parseInt(scan.nextLine());
			System.out.print("Digite o valor do intervalo (ms) entre cada ano: ");
			long intervalo = Integer.parseInt(scan.nextLine());
			Cidade cidade = new Cidade(quantAnos, intervalo);
			cidade.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			scan.close();
		}
	}
	
}
