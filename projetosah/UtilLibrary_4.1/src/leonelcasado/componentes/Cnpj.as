package leonelcasado.componentes{
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;

	public class Cnpj extends CustomTextInput{
		
		public function Cnpj(){
			super();
			addEventListener(KeyboardEvent.KEY_UP, mascara);
			addEventListener(FocusEvent.FOCUS_OUT,valida);
			maxChars=18;
			restrict="0-9";
			maxWidth=150;
			toolTip="Digite um CNPJ válido com 14 Dígitos";
			isComponenteValido=false;
		}

		override public function initialize():void{
			super.initialize();
		}

		public function textoComMascara(value:String):void{
			text=CustomViewHelper.mascararCnpj(value);
		}

		public function mascara(event:KeyboardEvent):void{
			var pos:Number=Number(text.length) + 1;
			if (text.length == 2 || text.length == 6){
	            text = text + ".";
				setSelection(pos,pos);
	  		}	
	        if (text.length == 10){
	            text = text + "/";
				setSelection(pos,pos);
	 		}	
	        if (text.length == 15){
	            text = text + "-";
				setSelection(pos,pos);
	        }	
		}
		
		public function valida(event:FocusEvent):void{
			if(text.length>0){
				var valor:String=CustomViewHelper.removerMascara(text);
				if(!Validacao.validarCnpj(valor)){
					isComponenteValido=false;
					setSelection(0,18);
				}else{
					text=valor.substring(0,2)+"."+valor.substring(2,5)+"."+valor.substring(5,8)+"/"+valor.substring(8,12)+"-"+valor.substring(12,14);
					isComponenteValido=true;				
				}	
			}else{
				isComponenteValido=false;
			}
		}		
	}
}