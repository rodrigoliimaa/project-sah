package br.com.infoliver.sah.configuracao.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.infoliver.sah.configuracao.annotation.CPF;

public class ValidadorCPF implements ConstraintValidator<CPF, String> {

	@Override
	public void initialize(CPF constraintAnnotation) {
	}

	@Override
	public boolean isValid(String numero, ConstraintValidatorContext context) {
		if(numero==null ||numero =="")return true;
		String cpf = numero.substring(0, 9);
		String digitoVerificador = numero.substring(9, 11);
		int d1 = 0;
		int i;
		if (numero.length() == 11) {
			if (numero == "00000000000" || numero == "11111111111"
					|| numero == "22222222222" || numero == "33333333333"
					|| numero == "44444444444" || numero == "55555555555"
					|| numero == "66666666666" || numero == "77777777777"
					|| numero == "88888888888" || numero == "99999999999") {
				return false;
			}

			for (i = 0; i < 9; i++) {
				d1 += Integer.parseInt(cpf.substring(i,i+1)) * (10 - i);
			}

			if (d1 == 0) {
				return false;
			}

			d1 = 11 - (d1 % 11);

			if (d1 > 9)
				d1 = 0;

			if (Integer.parseInt(digitoVerificador.substring(0,1)) != d1) {
				return false;
			}

			d1 *= 2;

			for (i = 0; i < 9; i++) {
				d1 += Integer.parseInt(cpf.substring(i,i+1)) * (11 - i);
			}

			d1 = 11 - (d1 % 11);

			if (d1 > 9)
				d1 = 0;

			if (Integer.parseInt(digitoVerificador.substring(1,2)) != d1) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
}