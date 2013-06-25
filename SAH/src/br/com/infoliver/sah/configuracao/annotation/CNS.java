package br.com.infoliver.sah.configuracao.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.infoliver.sah.configuracao.validacao.ValidadorCNS;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidadorCNS.class)
public @interface CNS {
	String message() default "Valor inválido para o CNS.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}