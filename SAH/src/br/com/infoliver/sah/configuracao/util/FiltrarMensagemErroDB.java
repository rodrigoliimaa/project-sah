package br.com.infoliver.sah.configuracao.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FiltrarMensagemErroDB {

	public static String getConstraintViolada(String mensagem) {
		String[] msgSqlErro = mensagem.split("\\.");
		return msgSqlErro[1].trim();
	}
	
	/**
	 * Retorna a constate
	 * 
	 * @param ex (Exception)
	 * @return Ex:CK_DIFALIQ_MENOR30_TOTAL
	 * @author Igo Cavalcanti
	 * @since 16/11/2009
	 */
	public static String getConstraintVioladaOracle(Exception ex){
		String mensagem = null;
		Pattern pattern = Pattern.compile("\\([\\w]+\\.[\\w]+\\)");
		Matcher matcher = pattern.matcher(ex.getCause().getMessage());
		while(matcher.find())
			mensagem = matcher.group();
		if(mensagem != null)
			mensagem = getConstraintViolada(mensagem.substring(1,(mensagem.length() - 1)));
		return mensagem;
	}

	public static String getConstraintVioladaPostgres(Exception ex){
		String mensagem = "";
		if (ex != null && ex.getCause() != null) {
			mensagem = ex.getCause().getLocalizedMessage();
		}
		String[] msgSqlErro = mensagem.split("\"");
		return msgSqlErro[1].trim();
	}
	
//	public static Boolean getTriggerViolada(Exception ex){
//		Boolean flag = false;
//		String mensagem = ex.getCause().getMessage();
//		int indexInicio = mensagem.indexOf("TG_ALTERADIFALIQ_A_A_R_001");
//		if(indexInicio > 0)
//			flag = true;
//		return flag;
//	}

}
