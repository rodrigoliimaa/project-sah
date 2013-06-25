package leonelcasado.componentes{
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;

	public class Cpf extends CustomTextInput{
		
		public function Cpf(){
			super();
			addEventListener(KeyboardEvent.KEY_UP, mascara);
			addEventListener(FocusEvent.FOCUS_OUT,valida);
			maxChars=14;
			restrict="0-9";
			maxWidth=120;
			width=120;
			toolTip="Digite um CPF válido com 11 Dígitos";
			isComponenteValido=false;			
		}

		override public function initialize():void{
			super.initialize();
		}

		public function textoComMascara(value:String):void{
			text=CustomViewHelper.mascararCpf(value);
		}

		private function mascara(event:KeyboardEvent):void{
			var pos:Number=Number(text.length) + 1;
			if (text.length == 3 || text.length == 7){
	            text = text + ".";
				setSelection(pos,pos);
	  		}

	        if (text.length == 11){
	            text = text + "-";
				setSelection(pos,pos);
	 		}	
		}
		
		public function valida(event:FocusEvent):void{
			if(text.length>0){
				var valor:String=CustomViewHelper.removerMascara(text);
				if(!Validacao.validarCpf(valor)){
					isComponenteValido=false;
					setSelection(0,14);
				}else{
					text=valor.substring(0,3)+"."+valor.substring(3,6)+"."+valor.substring(6,9)+"-"+valor.substring(9,11);
					isComponenteValido=true;				
				}
			}else{
				isComponenteValido=false;
			}
		}
	}
}