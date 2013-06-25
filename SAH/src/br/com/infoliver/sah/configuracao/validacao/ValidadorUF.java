package br.com.infoliver.sah.configuracao.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.infoliver.sah.configuracao.annotation.UF;

public class ValidadorUF implements ConstraintValidator<UF, String> {

	@Override
	public void initialize(UF constraintAnnotation) {
	}

	@Override
	public boolean isValid(String uf, ConstraintValidatorContext context) {
		if (uf == null || uf == "")return true;
		if (uf.length() == 2) {
			String[] ufs = { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES",
					"GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR",
					"RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" };
			for (int i = 0; i < ufs.length; i++) {
				if (uf.equals(ufs[i]))return true;
			}
		}
		return false;
	}
}