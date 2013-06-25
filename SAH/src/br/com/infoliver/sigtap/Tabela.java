package br.com.infoliver.sigtap;

import java.util.ArrayList;
import java.util.List;

public class Tabela {

	private String nome;
	private List<Coluna> colunas;
	
	public Tabela(String nome) {
		this.nome = nome;
		this.colunas = new ArrayList<Coluna>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Coluna> getColunas() {
		return colunas;
	}

	public void setColunas(List<Coluna> colunas) {
		this.colunas = colunas;
	}
	
	public void adicionarColuna(Coluna coluna) {
		this.colunas.add(coluna);
	}
	
	public String toSQL() {
		StringBuilder sb = new StringBuilder("");
		
		sb.append("create table ");
		sb.append(SigtapHelper.NOME_SCHEMA_E_PONTO);
		sb.append(this.nome);
		sb.append("( ");
		for (int i = 0; i < this.colunas.size(); i++) {
			sb.append(colunas.get(i).toSQL());
			if (i < (this.colunas.size() - 1)) {
				sb.append(", ");
			}
		}
		sb.append(" );");
		
		return sb.toString();
	}
	
//	public String prepareInsertSQL() {
//		int numeroColunas = this.colunas.size();
//		
//		StringBuilder sb = new StringBuilder("");
//		
//		sb.append("insert into ");
//		sb.append(NOME_SCHEMA_E_PONTO);
//		sb.append(this.nome);
//		
//		// colunas
//		sb.append("( ");
//		for (int i = 0; i < numeroColunas; i++) {
//			sb.append(colunas.get(i).getColuna());
//			if (i < (numeroColunas - 1)) {
//				sb.append(", ");
//			}
//		}
//		sb.append(" )");
//		
//		// valores
//		sb.append(" values ( ");
//		for (int i = 0; i < numeroColunas; i++) {
//			sb.append("?");
//			if (i < (numeroColunas - 1)) {
//				sb.append(", ");
//			}
//		}
//		sb.append(" );");
//		
//		return sb.toString();
//	}
	
	public String prepareInsertSQL(String registro) {
		int numeroColunas = this.colunas.size();
		
		StringBuilder sbInsert = new StringBuilder("");
		StringBuilder sbValues = new StringBuilder("");
		
		sbInsert.append("insert into ");
		sbInsert.append(SigtapHelper.NOME_SCHEMA_E_PONTO);
		sbInsert.append(this.nome);
		
		sbInsert.append("( ");
		sbValues.append(" values ( ");
		for (int i = 0; i < numeroColunas; i++) {
			Coluna coluna = colunas.get(i);
			sbInsert.append(coluna.getColuna());
			if (coluna.getTipo().equalsIgnoreCase(SigtapHelper.getDatatype("varchar2")) || coluna.getTipo().equalsIgnoreCase("char")) {
				if (registro.substring(coluna.getInicio() - 1, coluna.getFim()).contains("\'")) {
					sbValues.append("E'" + registro.substring(coluna.getInicio() - 1, coluna.getFim()).trim().replace("\'", "\\\'") + "'");
				} else {
					sbValues.append("'" + registro.substring(coluna.getInicio() - 1, coluna.getFim()).trim() + "'");
				}
			} else {
				sbValues.append(registro.substring(coluna.getInicio() - 1, coluna.getFim()).trim());
			}
			if (i < (numeroColunas - 1)) {
				sbInsert.append(", ");
				sbValues.append(", ");
			}
		}
		sbInsert.append(" )");
		sbValues.append(" );");
		
		return sbInsert.toString() + sbValues.toString();
	}
	
}
