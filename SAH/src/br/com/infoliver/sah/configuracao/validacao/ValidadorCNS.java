package br.com.infoliver.sah.configuracao.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.infoliver.sah.configuracao.annotation.CNS;

public class ValidadorCNS implements ConstraintValidator<CNS, String> {

	@Override
	public void initialize(CNS constraintAnnotation) {
	}

	@Override
	public boolean isValid(String numero, ConstraintValidatorContext context) {
		/*if(numero==null ||numero =="")return true;
		if (numero.length() == 15) {
			String primeiroNumeroCNS=numero.substring(0,1);			
			if(primeiroNumeroCNS=="8" || primeiroNumeroCNS=="9"){
				return validarCnsProvisorio(numero);
			}
			
			int soma=0;
			int resto=0;
			int dv=0;
			String pis;
			String resultado;

			pis = numero.substring(0,11);
			soma = (((Integer.parseInt(pis.substring(0,1))) * 15) +   
				((Integer.parseInt(pis.substring(1,2))) * 14) +
				((Integer.parseInt(pis.substring(2,3))) * 13) +
				((Integer.parseInt(pis.substring(3,4))) * 12) +
				((Integer.parseInt(pis.substring(4,5))) * 11) +
				((Integer.parseInt(pis.substring(5,6))) * 10) +
				((Integer.parseInt(pis.substring(6,7))) * 9) +
				((Integer.parseInt(pis.substring(7,8))) * 8) +
				((Integer.parseInt(pis.substring(8,9))) * 7) +
				((Integer.parseInt(pis.substring(9,10))) * 6) +
				((Integer.parseInt(pis.substring(10,11))) * 5));
			resto = soma % 11;
			dv = 11 - resto;
			if (dv == 11)dv=0;
			
			if (dv == 10) {
				soma = (((Integer.parseInt(pis.substring(0,1))) * 15) +   
					((Integer.parseInt(pis.substring(1,2))) * 14) +
					((Integer.parseInt(pis.substring(2,3))) * 13) +
					((Integer.parseInt(pis.substring(3,4))) * 12) +
					((Integer.parseInt(pis.substring(4,5))) * 11) +
					((Integer.parseInt(pis.substring(5,6))) * 10) +
					((Integer.parseInt(pis.substring(6,7))) * 9) +
					((Integer.parseInt(pis.substring(7,8))) * 8) +
					((Integer.parseInt(pis.substring(8,9))) * 7) +
					((Integer.parseInt(pis.substring(9,10))) * 6) +
					((Integer.parseInt(pis.substring(10,11))) * 5) + 2);
				resto = soma % 11;
				dv = 11 - resto;
				resultado = pis + "001" + String.valueOf(dv);
			} else { 
				resultado = pis + "000" + String.valueOf(dv);
			}
			
			if (numero != resultado) {
				return false;
			}
		}else{
			return false;
		}
*/		return true;
	}
	
	
	private Boolean validarCnsProvisorio(String numero){
		String pis=numero.substring(0,15);
		int soma=0;
		int resto=0;
		
		soma = ((Integer.parseInt(pis.substring( 0, 1),10)) * 15)
			+ ((Integer.parseInt(pis.substring( 1, 2),10)) * 14)
			+ ((Integer.parseInt(pis.substring( 2, 3),10)) * 13)
			+ ((Integer.parseInt(pis.substring( 3, 4),10)) * 12)
			+ ((Integer.parseInt(pis.substring( 4, 5),10)) * 11)
			+ ((Integer.parseInt(pis.substring( 5, 6),10)) * 10)
			+ ((Integer.parseInt(pis.substring( 6, 7),10)) * 9)
			+ ((Integer.parseInt(pis.substring( 7, 8),10)) * 8)
			+ ((Integer.parseInt(pis.substring( 8, 9),10)) * 7)
			+ ((Integer.parseInt(pis.substring( 9,10),10)) * 6)
			+ ((Integer.parseInt(pis.substring(10,11),10)) * 5)
			+ ((Integer.parseInt(pis.substring(11,12),10)) * 4)
			+ ((Integer.parseInt(pis.substring(12,13),10)) * 3)
			+ ((Integer.parseInt(pis.substring(13,14),10)) * 2)
			+ ((Integer.parseInt(pis.substring(14,15),10)) * 1);
		
		resto = soma % 11;
		
		if (resto == 0)
			return true;
		else{
			return false;  
		}   
	}
}