package br.com.infoliver.sah.negocio.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Component;

import br.com.infoliver.sah.configuracao.exception.DBException;

/**
 * @author Igo Cavalcanti 
 * @since 17/11/2009
 * @version 1.0
 */
@Aspect
@Order(1)
@Component("tratarExcecaoAspect")
public class TratarExcecaoAspect {

	public TratarExcecaoAspect(){}
	
	@Pointcut("execution(* *..*BO.*(..))")
	public void tratarExcecaoAspect(){}
	
	@AfterThrowing(pointcut="tratarExcecaoAspect()", throwing="ex" )
	public void validatar(Exception ex) throws Exception {
		if(ex instanceof DataIntegrityViolationException ^ ex instanceof UncategorizedSQLException )
			new DBException(ex);
	}
}