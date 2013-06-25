package leonelcasado.componentes{
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;
	
	public class Ie_Cnpj extends CustomTextInput{
		
		public function Ie_Cnpj(){
			super();
			addEventListener(KeyboardEvent.KEY_UP, mascara);
			addEventListener(FocusEvent.FOCUS_OUT,valida);

			maxChars=18;
			restrict="0-9";
			maxWidth=150;
			toolTip="Digite um documento válido";
			isComponenteValido=false;
		}

		override public function initialize():void{
			super.initialize();
		}

		public function mascara(event:KeyboardEvent):void{
			var pos:Number=Number(text.length) + 1;

			if(text.length == 8){
				text = text + "-";
				setSelection(9,10);
			}

			if (text.length > 10){
	 			text=CustomViewHelper.mascararCnpj(text);
				setSelection(pos+1,pos+1);		        	
			}
		}

		public function textoComMascara(value:String):void{
			if(value.length == 9)
				text=CustomViewHelper.mascararCaceal(value);
			else if(value.length == 14)
				text=CustomViewHelper.mascararCnpj(value);
		}

		public function valida(event:FocusEvent):void{
			if(text.length>0){
				var valor:String=CustomViewHelper.removerMascara(text);
				if(valor.length<=9){
					if(text.substring(0,2)=="24"){
						if(!Validacao.validarCaceal(valor)){
							isComponenteValido=false;
							setSelection(0,18);
						}else{
							text=valor.substring(0,8)+"-"+valor.substring(8,9);
							isComponenteValido=true;
						}
					}else{
						if(valor.length==9){
							text=valor.substring(0,8)+"-"+valor.substring(8,9);
							isComponenteValido=true;	
						}else{
							Alerta.error("Inscrição Estadual inválida!");
							isComponenteValido=false;
							setSelection(0,18);
						}										
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