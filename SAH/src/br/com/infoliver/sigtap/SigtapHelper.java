package br.com.infoliver.sigtap;

import java.util.HashMap;
import java.util.Set;

public class SigtapHelper {
	
	public static final String ISO_8859_1 = "ISO-8859-1";
	public static final String UTF_8 = "UTF-8";
	
	public static final String SIGTAP_DIR = "sigtap";
	public static final String NOME_ARQUIVO_SIGTAP_SQL = "sigtap.sql";
	public static final String NOME_SCHEMA = "sigtap";
	public static final String NOME_SCHEMA_E_PONTO = NOME_SCHEMA + ".";
	public static final String PONTO_E_VIRGULA = ";";
	public static final String PONTO_TXT = ".txt";
	public static final String VIRGULA = ",";
	
	public static final String TB_UNDERLINE_LOWER_CASE = "tb_";
	public static final String RL_UNDERLINE_LOWER_CASE = "rl_";
	public static final String TB_UNDERLINE_UPPER_CASE = "TB_";
	public static final String RL_UNDERLINE_UPPER_CASE = "RL_";
	
	public static final String CREATE_SCHEMA = "create schema " + NOME_SCHEMA + PONTO_E_VIRGULA;
	public static final String DROP_SCHEMA_IF_EXISTS = "drop schema if not exists " + NOME_SCHEMA + PONTO_E_VIRGULA;
	public static final String DROP_TABLE_IF_EXISTS = "drop table if exists ";
	
//	public static final String URL_FTP = "ftp2.datasus.gov.br";

	
	public static final HashMap<String, String> SIGTAP_POSTGRES_DATA_TYPES_MAP;
	static {
		SIGTAP_POSTGRES_DATA_TYPES_MAP = new HashMap<String, String>();
		SIGTAP_POSTGRES_DATA_TYPES_MAP.put("VARCHAR2", "CHARACTER VARYING");
		SIGTAP_POSTGRES_DATA_TYPES_MAP.put("NUMBER", "NUMERIC");
	}
	
	public static String getDatatype(String datatype) {
		Set<String> keys = SIGTAP_POSTGRES_DATA_TYPES_MAP.keySet();
		for (String key : keys) {
			if (key.equalsIgnoreCase(datatype)) {
				return SIGTAP_POSTGRES_DATA_TYPES_MAP.get(key);
			}
		}
		
		return datatype;
	}
	
	
	public static final String MENSAGEM_ERRO_EXTRAIR_TABELAS_ARQUIVO_LAYOUT = "NÃO FOI POSSÍVEL EXTRAIR AS TABELAS DO ARQUIVO DE LAYOUT";
	
}
