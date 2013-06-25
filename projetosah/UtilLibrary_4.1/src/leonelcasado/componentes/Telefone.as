package leonelcasado.componentes{
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;

	public class Telefone extends CustomTextInput{
		
		public function Telefone(){
			super();
			addEventListener(KeyboardEvent.KEY_UP, mascara);
			addEventListener(FocusEvent.FOCUS_OUT,valida);
			maxChars=13;
			restrict="0-9";
			maxWidth=100;
			width=100;
			toolTip="Digite um Telefone válido";
			isComponenteValido=false;			
		}

		override public function initialize():void{
			super.initialize();
		}
		
		private function mascara(event:KeyboardEvent):void{
			var pos:Number=Number(text.length) + 1;
	        if (text.length == 1){
	            text = "("+text;
				setSelection(pos,pos);
	 		}else if (text.length == 3){
				text = text+")";
				setSelection(pos,pos);
	 		}else if (text.length == 8){
				text = text+"-";
				setSelection(pos,pos);
			}
		}
		
		public function textoComMascara(value:String):void{
			text=CustomViewHelper.mascararDDDTelefone(value);
		}

		public function valida(event:FocusEvent):void{
			if(text.length>0){
				var valor:String=CustomViewHelper.removerMascara(text);
				if(!Validacao.validarDDDTelefone(valor)){
					isComponenteValido=false;
					setSelection(0,13);
				}else{
					text="("+valor.substring(0,2)+")"+valor.substring(2,6)+"-"+valor.substring(6,10);
					isComponenteValido=true;				
				}
			}else{
				isComponenteValido=false;
			}
		}
	}
}