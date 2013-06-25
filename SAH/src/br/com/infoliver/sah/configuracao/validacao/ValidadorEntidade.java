package br.com.infoliver.sah.configuracao.validacao;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.configuracao.exception.ValidadorException;

@Component("validadorEntidade")
public class ValidadorEntidade {

	@Autowired
	private Validator validator;

	private Set<ConstraintViolation<Object>> violacoes;
	
	public ValidadorEntidade validar(Class<?> clazz,Object entidade, Class<?>... classes) throws BOException {
		Validador.assertNotNullObject(clazz,entidade);
		violacoes = validator.validate(entidade,classes);
		return this;
	}

	public boolean isValido() {
		return violacoes.isEmpty();
	}

	public void lancarExcecao()throws ValidadorException {
		if (!isValido())
			throw new ValidadorException(formatarMensagem(),violacoes);
	}

	private String formatarMensagem(){
		Iterator<ConstraintViolation<Object>> iterator = violacoes.iterator();
		StringBuilder builder = new StringBuilder();
		while (iterator.hasNext()) {
			ConstraintViolation<Object> constraintViolation = iterator.next();
			if(builder.length()>0)builder.append("\n");
			builder.append(constraintViolation.getMessage());
		}
		return builder.toString();
	}
}