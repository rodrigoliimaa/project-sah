package br.com.infoliver.sigtap;

public class Coluna {
	
	private String coluna;
	private int tamanho;
	private int inicio;
	private int fim;
	private String tipo;
	
	public Coluna() {
		
	}

	public String getColuna() {
		return coluna;
	}

	public void setColuna(String coluna) {
		this.coluna = coluna;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFim() {
		return fim;
	}

	public void setFim(int fim) {
		this.fim = fim;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String toSQL() {
		StringBuilder sb = new StringBuilder("");
		
		sb.append(this.coluna);
		sb.append(" ");
		
		String datatype = SigtapHelper.getDatatype(this.tipo);
		sb.append(datatype);
		
		sb.append("(" + this.tamanho + ")");
		
		return sb.toString();
	}	

}
