package br.com.infoliver.sah.configuracao.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.infoliver.sah.configuracao.annotation.CNPJ;

public class ValidadorCNPJ implements ConstraintValidator<CNPJ, String> {

	@Override
	public void initialize(CNPJ constraintAnnotation) {
	}

	@Override
	public boolean isValid(String numero, ConstraintValidatorContext context) {
		if(numero==null ||numero =="")return true;
		if (numero.length() == 14) {
			String cnpj=numero.substring(0,12);
			String digitoVerificador=numero.substring(12,14);
			int soma = 0;
			int resultado1 = 0;
			int resultado2 = 0;
				
			soma = (5 * Integer.parseInt(cnpj.substring(0,1))) + 
			(4 * Integer.parseInt(cnpj.substring(1,2))) + 
			(3 * Integer.parseInt(cnpj.substring(2,3))) + 
			(2 * Integer.parseInt(cnpj.substring(3,4))) + 
			(9 * Integer.parseInt(cnpj.substring(4,5))) + 
			(8 * Integer.parseInt(cnpj.substring(5,6))) + 
			(7 * Integer.parseInt(cnpj.substring(6,7))) + 
			(6 * Integer.parseInt(cnpj.substring(7,8))) +
			(5 * Integer.parseInt(cnpj.substring(8,9))) +
			(4 * Integer.parseInt(cnpj.substring(9,10))) +
			(3 * Integer.parseInt(cnpj.substring(10,11))) +
			(2 * Integer.parseInt(cnpj.substring(11,12)));

	        soma = soma - (11 * (soma / 11));
	        
	        if (soma == 0 || soma == 1){
	            resultado1 = 0;
	        }else{
	            resultado1 = 11 - soma;
	   		}
			//Valida o primeiro digito verificador
	        if (resultado1 == Integer.parseInt(digitoVerificador.substring(0,1))){
				soma = (6 * Integer.parseInt(cnpj.substring(0,1))) + 
				(5 * Integer.parseInt(cnpj.substring(1,2))) + 
				(4 * Integer.parseInt(cnpj.substring(2,3))) + 
				(3 * Integer.parseInt(cnpj.substring(3,4))) + 
				(2 * Integer.parseInt(cnpj.substring(4,5))) + 
				(9 * Integer.parseInt(cnpj.substring(5,6))) + 
				(8 * Integer.parseInt(cnpj.substring(6,7))) + 
				(7 * Integer.parseInt(cnpj.substring(7,8))) +
				(6 * Integer.parseInt(cnpj.substring(8,9))) +
				(5 * Integer.parseInt(cnpj.substring(9,10))) +
				(4 * Integer.parseInt(cnpj.substring(10,11))) +
				(3 * Integer.parseInt(cnpj.substring(11,12))) +
				(2 * (Integer.parseInt(digitoVerificador.substring(0,1))));
			            
	            soma = soma - (11 * (soma / 11));
	            if (soma == 0 || soma == 1){
	                resultado2 = 0;
	            }else{
	                resultado2 = 11 - soma;
	            }
				
				//Valida o segundo digito verificador
	            if (resultado2 == Integer.parseInt(digitoVerificador.substring(1,2))){
	                return true;
	            }else{
	        		return false;
	        	}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}