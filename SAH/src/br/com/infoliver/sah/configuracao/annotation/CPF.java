package br.com.infoliver.sah.configuracao.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.infoliver.sah.configuracao.validacao.ValidadorCPF;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidadorCPF.class)
public @interface CPF {
	String message() default "Valor inválido para o CPF.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}