package br.com.infoliver.sah.configuracao.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.infoliver.sah.configuracao.validacao.ValidadorUF;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {ValidadorUF.class})
public @interface UF {
	String message() default "Valor inválido para a UF.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}