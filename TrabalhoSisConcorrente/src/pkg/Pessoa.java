package pkg;

import java.io.Serializable;

public class Pessoa implements Serializable {
	
	private static final long	serialVersionUID	= -6465542907105863520L;
	private Escolaridade		escolaridade;
	private double				renda;
	private double				peso;
	
	/**
	 * @return the escolaridade
	 */
	public Escolaridade getEscolaridade() {
		return escolaridade;
	}
	
	/**
	 * @param escolaridade
	 *            the escolaridade to set
	 */
	public void setEscolaridade(final Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}
	
	/**
	 * @return the renda
	 */
	public double getRenda() {
		return renda;
	}
	
	/**
	 * @param renda
	 *            the renda to set
	 */
	public void setRenda(final float renda) {
		this.renda = renda;
	}
	
	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}
	
	/**
	 * @param d
	 *            the peso to set
	 */
	public void setPeso(final double d) {
		peso = d;
	}
	
}
