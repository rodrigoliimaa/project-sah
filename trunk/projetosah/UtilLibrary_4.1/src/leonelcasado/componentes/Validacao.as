package leonelcasado.componentes{	
	
	public class Validacao{
		
		public function Validacao(){
		}

		public static function validarCep(cep:String):Boolean{
			if(cep.length==8){
				return true;	
			}else{
				Alerta.error("CEP inválido!");
				return false;
			}
		}

		public static function validarEmail(email:String):Boolean{
			var posicao_arroba:uint = email.indexOf('@');
			var nome:String = email.substring(0, posicao_arroba);
			var dominio:String = email.substring(posicao_arroba+1);
			var posicao_ponto:uint = dominio.lastIndexOf('.');
			var texto_anterior:String = dominio.substring(0, posicao_ponto);
			var texto_posterior:String = dominio.substring(posicao_ponto+1);
			if (nome.length<=0 || texto_anterior.length<=0 || texto_posterior.length<=0) {
				Alerta.error("Email inválido!");
				return false;
			}else{
				return true;
			}
		}

		public static function validarDDDTelefone(DDDTelefone:String):Boolean{
			if(DDDTelefone.length==10){
				return true;	
			}else{
				Alerta.error("Telefone inválido!");
				return false;
			}
		}

		public static function validarCaceal(numero:String):Boolean{
			var caceal:String=numero.substring(0,8);
			var digitoVerificador:String=numero.substring(8,9);
			var soma:Number = 0;			
			if (caceal.length == 8) {		
				soma = (2 * Number(caceal.charAt(7))) + 
				(3 * Number(caceal.charAt(6))) + 
				(4 * Number(caceal.charAt(5))) + 
				(5 * Number(caceal.charAt(4))) + 
				(6 * Number(caceal.charAt(3))) + 
				(7 * Number(caceal.charAt(2))) + 
				(8 * Number(caceal.charAt(1))) + 
				(9 * Number(caceal.charAt(0)));
				
				soma = soma * 10;				
				var dvCalculado:int = soma - (int(soma /11)* 11);
				if (dvCalculado == int(digitoVerificador))return true;					
				if (dvCalculado == 10){
					if(int(digitoVerificador)==0)return true;
				}
				Alerta.error("CACEAL inválido!");				
			}else{
				Alerta.error("CACEAL inválido!");
			}
			return false;
		}
		
		public static function validarCpf(numero:String):Boolean{
			var cpf:String=numero.substring(0,9);
			var digitoVerificador:String=numero.substring(9,11);
			var d1:int = 0;
			var i:int;
			
			if (numero.length == 11) {
		
				if (numero == "00000000000" || numero == "11111111111" || numero == "22222222222" ||
					numero == "33333333333" || numero == "44444444444" || numero == "55555555555" || 
					numero == "66666666666" || numero == "77777777777" || numero == "88888888888" || 
					numero == "99999999999"){
					Alerta.error("CPF inválido!");
					return false;
				}
		
		
				for (i = 0; i < 9; i++){
					d1 += int(cpf.charAt(i))*(10-i);
				}
			
				if (d1 == 0){
					Alerta.error("CPF inválido!");
					return false;
				}
				
				d1 = 11 - (d1 % 11);
				
				if (d1 > 9) d1 = 0;
			
				if (int(digitoVerificador.charAt(0)) != d1){
					Alerta.error("CPF inválido!");
					return false;
				}
		
				d1 *= 2;
		  
				for (i = 0; i < 9; i++){
					d1 += int(cpf.charAt(i))*(11-i);
				}
		  
				d1 = 11 - (d1 % 11); 
		  
				if (d1 > 9) d1 = 0; 
		  
				if (int(digitoVerificador.charAt(1)) != d1){ 
					Alerta.error("CPF inválido!");
					return false;
				}
			}else{
				Alerta.error("CPF inválido!");
				return false;				
			}
			return true;
   		}

		public static function validarCnpj(numero:String):Boolean{
			var cnpj:String=numero.substring(0,12);
			var digitoVerificador:String=numero.substring(12,14);
			var soma:Number = 0;
			var resultado1:Number = 0;
			var resultado2:Number = 0;
			if (numero.length == 14) {		
				soma = (5 * Number(cnpj.charAt(0))) + 
				(4 * Number(cnpj.charAt(1))) + 
				(3 * Number(cnpj.charAt(2))) + 
				(2 * Number(cnpj.charAt(3))) + 
				(9 * Number(cnpj.charAt(4))) + 
				(8 * Number(cnpj.charAt(5))) + 
				(7 * Number(cnpj.charAt(6))) + 
				(6 * Number(cnpj.charAt(7))) +
				(5 * Number(cnpj.charAt(8))) +
				(4 * Number(cnpj.charAt(9))) +
				(3 * Number(cnpj.charAt(10))) +
				(2 * Number(cnpj.charAt(11)));

		        soma = soma - (11 * (int(soma / 11)));
		        
		        if (soma == 0 || soma == 1){
		            resultado1 = 0;
		        }else{
		            resultado1 = 11 - soma;
		   		}
				//Valida o primeiro digito verificador
		        if (resultado1 == int(digitoVerificador.charAt(0))){
					soma = (6 * Number(cnpj.charAt(0))) + 
					(5 * Number(cnpj.charAt(1))) + 
					(4 * Number(cnpj.charAt(2))) + 
					(3 * Number(cnpj.charAt(3))) + 
					(2 * Number(cnpj.charAt(4))) + 
					(9 * Number(cnpj.charAt(5))) + 
					(8 * Number(cnpj.charAt(6))) + 
					(7 * Number(cnpj.charAt(7))) +
					(6 * Number(cnpj.charAt(8))) +
					(5 * Number(cnpj.charAt(9))) +
					(4 * Number(cnpj.charAt(10))) +
					(3 * Number(cnpj.charAt(11))) +
					(2 * Number(digitoVerificador.charAt(0)));
				            
		            soma = soma - (11 * (int(soma / 11)))
		            if (soma == 0 || soma == 1){
		                resultado2 = 0;
		            }else{
		                resultado2 = 11 - soma;
		            }
					
					//Valida o segundo digito verificador
		            if (resultado2 == int(digitoVerificador.charAt(1))){
		                return true;
		            }else{
		            	Alerta.error("CNPJ inválido!");
		        	}
				}else{
					Alerta.error("CNPJ inválido!");				
				}
			}else{
				Alerta.error("CNPJ inválido!");				
			}
			return false;
   		}		

		public static function validarDanfe(chaveDanfe:String):Boolean{
	        var digitoVerificadorAtual:Number = Number(chaveDanfe.substring(43,44));
	        var chave:String = chaveDanfe.substring(0,43); // Retira o DV
	        var tamanhoChaveDanfe:int = chave.length;
	        var somatorio:int = 0;
	        var multiplicador:int = 2;
	        var digitoVerificadorGerado:Number;
	        
	        if(chaveDanfe.length==44){
		        for (var i:int=0;i<=tamanhoChaveDanfe;i++){
		            var caracter:Number = Number(chave.substring(tamanhoChaveDanfe-i-1,tamanhoChaveDanfe-i));
		            if (multiplicador==10){
		            	multiplicador=2;
		            }
		            somatorio += caracter * multiplicador++;
		        }
		        var restoDivisao:Number = somatorio % 11;
		        if(restoDivisao==0 || restoDivisao==1){
		        	digitoVerificadorGerado=0;
		        }else{
		        	digitoVerificadorGerado = 11 - restoDivisao;
		        }
	
				if (digitoVerificadorAtual != digitoVerificadorGerado){ 
					Alerta.error("DANFE inválido!");
					return false;
				}
	        }else{
				Alerta.error("DANFE inválido!");
				return false;				
			}			
			return true;
		}

		private static var numeros:String = "0123456789";
		private static var caracteres:String = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
		public static function validarPlaca(placa:String):Boolean{
			var vCampo:String = placa;
			var isTipoValido:Boolean = true;
			var c:String;
			var i:int;

			//Valida os 3 primeiros digitos da placa como Alfabeticos.
			if (vCampo.length == 7){	
				// Testa se os 3 primeiros dígitos são caracteres válidos.
				for (i = 0; i < 3 && isTipoValido; i++) {
					c = vCampo.charAt(i);
					if (caracteres.indexOf(c) == -1) {
						isTipoValido = false;
					}
				}
				// Testa se os 4 ultimos dígitos são números válidos.
				for (i = 3; i < 7 && isTipoValido; i++) {
					c = vCampo.charAt(i);
					if (numeros.indexOf(c) == -1) {
						isTipoValido = false;
					}
				}
		
				if (!isTipoValido) {
					isTipoValido = false;
					Alerta.error("Placa inválida!");						
				}
			} else {
				isTipoValido = false;
				Alerta.error("Placa inválida!");
			}
			return isTipoValido;
		}

		public static function validarData(data:String):Boolean{
			var vCampo:String = data;
			var dia:Number = Number(vCampo.substring(0,2));
			var mes:Number = Number(vCampo.substring(2,4));
			var ano:Number = Number(vCampo.substring(4,8));
	
			if (vCampo.length!=8){
				Alerta.error("Data inválida!");
				return false;
			}
				
			if ((mes==0)||(ano==0))	{
				Alerta.error("Data inválida!");
				return false;
			}
		
			if (mes > 12) {
				Alerta.error("Data inválida!");
				return false;
			}
			
			if (mes==2){		
				if ( ((ano%4 != 0)||(ano%100==0 && ano%400!=0)) && dia > 28 || dia > 29 ){
					Alerta.error("Data inválida!");
					return false;
				}		
			}
			else {
				if (dia > 31) {
					Alerta.error("Data inválida!");
					return false;
				}
				else{	
					if ((dia==31)&&((mes==4)||(mes==6)||(mes==9)||(mes==11))){
						Alerta.error("Data inválida!");
						return false;
					}
				}
			}
			return true;
		}
		
		public static function validarCns(numero:String):Boolean{
			var primeiroNumeroCNS:String=numero.substring(0,1);
			var soma:Number=new Number;
			var resto:Number=new Number;
			var dv:Number=new Number
			var pis:String=new String;
			var resultado:String=new String;
			
			if (numero.length == 15) {				
				if(primeiroNumeroCNS=="8" || primeiroNumeroCNS=="9"){
					return validarCnsProvisorio(numero);
				}
				
				pis = numero.substring(0,11);
				soma = (((Number(pis.substring(0,1))) * 15) +   
					((Number(pis.substring(1,2))) * 14) +
					((Number(pis.substring(2,3))) * 13) +
					((Number(pis.substring(3,4))) * 12) +
					((Number(pis.substring(4,5))) * 11) +
					((Number(pis.substring(5,6))) * 10) +
					((Number(pis.substring(6,7))) * 9) +
					((Number(pis.substring(7,8))) * 8) +
					((Number(pis.substring(8,9))) * 7) +
					((Number(pis.substring(9,10))) * 6) +
					((Number(pis.substring(10,11))) * 5));
				resto = soma % 11;
				dv = 11 - resto;
				if (dv == 11)dv=0;
				
				if (dv == 10) {
					soma = (((Number(pis.substring(0,1))) * 15) +   
						((Number(pis.substring(1,2))) * 14) +
						((Number(pis.substring(2,3))) * 13) +
						((Number(pis.substring(3,4))) * 12) +
						((Number(pis.substring(4,5))) * 11) +
						((Number(pis.substring(5,6))) * 10) +
						((Number(pis.substring(6,7))) * 9) +
						((Number(pis.substring(7,8))) * 8) +
						((Number(pis.substring(8,9))) * 7) +
						((Number(pis.substring(9,10))) * 6) +
						((Number(pis.substring(10,11))) * 5) + 2);
					resto = soma % 11;
					dv = 11 - resto;
					resultado = pis + "001" + String(dv);
				} else { 
					resultado = pis + "000" + String(dv);
				}
				
				if (numero != resultado) {
					Alerta.error("CNS invalido!");
					return false;
				}
			}else{
				Alerta.error("CNS inválido!");
				return false;				
			}
			return true;
		}

		private static function validarCnsProvisorio(numero:String):Boolean{
			var pis:String=numero.substring(0,15);
			var soma:Number=new Number;
			var resto:Number=new Number;
			
			soma = ((parseInt(pis.substring( 0, 1),10)) * 15)
				+ ((parseInt(pis.substring( 1, 2),10)) * 14)
				+ ((parseInt(pis.substring( 2, 3),10)) * 13)
				+ ((parseInt(pis.substring( 3, 4),10)) * 12)
				+ ((parseInt(pis.substring( 4, 5),10)) * 11)
				+ ((parseInt(pis.substring( 5, 6),10)) * 10)
				+ ((parseInt(pis.substring( 6, 7),10)) * 9)
				+ ((parseInt(pis.substring( 7, 8),10)) * 8)
				+ ((parseInt(pis.substring( 8, 9),10)) * 7)
				+ ((parseInt(pis.substring( 9,10),10)) * 6)
				+ ((parseInt(pis.substring(10,11),10)) * 5)
				+ ((parseInt(pis.substring(11,12),10)) * 4)
				+ ((parseInt(pis.substring(12,13),10)) * 3)
				+ ((parseInt(pis.substring(13,14),10)) * 2)
				+ ((parseInt(pis.substring(14,15),10)) * 1);
			
			resto = soma % 11;
			
			if (resto == 0)
				return true;
			else{
				Alerta.error("CNS provisório inválido!");
				return false;  
			}   
		}
	}
}