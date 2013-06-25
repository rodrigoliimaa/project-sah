package leonelcasado.componentes{
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;

	public class Cns extends CustomTextInput{
		
		public function Cns(){
			super();
			addEventListener(FocusEvent.FOCUS_OUT,valida);
			maxChars=15;
			restrict="0-9";
			maxWidth=120;
			width=120;
			toolTip="Digite um CNS válido com 15 Dígitos";
			isComponenteValido=false;			
		}

		override public function initialize():void{
			super.initialize();
		}
		
		public function valida(event:FocusEvent):void{
			// Retirada Validação a pedido de Rogerio 27/02/2012			
			/*if(text.length>0){
				var valor:String=text;
				if(!Validacao.validarCns(valor)){
					isComponenteValido=false;
					setSelection(0,15);
				}else{
					isComponenteValido=true;				
				}
			}else{
				isComponenteValido=false;
			}*/
			isComponenteValido=true;
			
		}
	}
}