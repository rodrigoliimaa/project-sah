package br.com.infoliver.sah.configuracao.exception;

import br.com.infoliver.sah.configuracao.util.FiltrarMensagemErroDB;
import br.com.infoliver.sah.negocio.enumeration.DBErroEnum;

@SuppressWarnings("unused")
public class DBException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DBException(Exception ex) throws BOException {
		String constraintViolada = FiltrarMensagemErroDB.getConstraintVioladaPostgres(ex);
		
		if(constraintViolada != null)
			lancarExcecaoDao(constraintViolada);
		
//		if(getTriggerViolada(ex))
//				throw new DAOException(mensagemErroViolacaoTgAlteradifaliqAAR001);
	}

	public DBException(Exception ex,String msg) throws BOException {
		String constraintViolada = FiltrarMensagemErroDB.getConstraintVioladaPostgres(ex);
		
		if(constraintViolada != null)
			lancarExcecaoDao(constraintViolada,msg);
		
//		if(getTriggerViolada(ex))
//				throw new DAOException(mensagemErroViolacaoTgAlteradifaliqAAR001);
	}
	
	private void lancarExcecaoDao(String constraintViolada) throws BOException{
		String mensagem = DBErroEnum.builder(constraintViolada);
		if(mensagem == null)			
			throw new DAOException(constraintViolada);
		else
			throw new DAOException(mensagem);
	}

	private void lancarExcecaoDao(String constraintViolada,String msg) throws BOException{
		String mensagem = DBErroEnum.builder(constraintViolada);
		String msgParametrizada= mensagem.replace("#", msg);
		if(mensagem == null)
			throw new DAOException(constraintViolada);
		else
			throw new DAOException(msgParametrizada);
	}
}