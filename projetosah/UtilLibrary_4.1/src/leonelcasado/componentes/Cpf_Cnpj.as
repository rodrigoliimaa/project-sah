package leonelcasado.componentes{
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;

	public class Cpf_Cnpj extends CustomTextInput{
		
		public function Cpf_Cnpj(){
			super();
			addEventListener(KeyboardEvent.KEY_UP, mascara);
			addEventListener(FocusEvent.FOCUS_OUT,valida);
			maxChars=18;
			restrict="0-9";
			maxWidth=150;
			toolTip="Digite um CPF ou CNPJ válido";
			isComponenteValido=false;
		}

		override public function initialize():void{
			super.initialize();
		}

		public function textoComMascara(value:String):void{
			if(value.length == 11)
				text=CustomViewHelper.mascararCpf(value);
			else if(value.length == 14)
				text=CustomViewHelper.mascararCnpj(value);
		}

		public function mascara(event:KeyboardEvent):void{
			var pos:Number=Number(text.length) + 1;
			if (text.length <= 14){
				if (text.length == 3 || text.length == 7){
		            text = text + ".";
					setSelection(pos,pos);
		  		}
		        if (text.length == 11){
		            text = text + "-";
					setSelection(pos,pos);
		 		}
		 	}if (text.length > 14){
		 		text=CustomViewHelper.removerMascara(text);
				text=text.substring(0,2)+"."+text.substring(2,5)+"."+text.substring(5,8)+"/"+text.substring(8,12)+"-"+text.substring(12,14);
		        if (text.length == 16){
					setSelection(pos,pos);
		        }	
			}
		}

		public function valida(event:FocusEvent):void{
			if(text.length>0){
				var valor:String=CustomViewHelper.removerMascara(text);
				
				if(valor.length<=11){
					if(!Validacao.validarCpf(valor)){
						isComponenteValido=false;
						setSelection(0,18);
					}else{
						text=valor.substring(0,3)+"."+valor.substring(3,6)+"."+valor.substring(6,9)+"-"+valor.substring(9,11);
						isComponenteValido=true;				
					}
				}else{
					if(!Validacao.validarCnpj(valor)){
						isComponenteValido=false;
						setSelection(0,18);
					}else{
						text=valor.substring(0,2)+"."+valor.substring(2,5)+"."+valor.substring(5,8)+"/"+valor.substring(8,12)+"-"+valor.substring(12,14);
						isComponenteValido=true;				
					}					
				}
			}else{
				isComponenteValido=false;
			}			
		}
	}
}