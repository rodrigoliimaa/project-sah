package leonelcasado.componentes{
	import flash.events.FocusEvent;
	
	public class Danfe extends CustomTextInput{
		
		public function Danfe(){
			super();
			addEventListener(FocusEvent.FOCUS_OUT,valida);
			restrict="0-9";			
			maxChars=44;
			maxWidth=330;
			width=330;
			toolTip="Digite um DANFE válido com 44 Dígitos";
			isComponenteValido=false;
		}
		
		override public function initialize():void{
			super.initialize();
		}
		
		public function valida(event:FocusEvent):void{
			if(text.length>0){
				if(text.length==44){
					if(Validacao.validarDanfe(text)){
						isComponenteValido=true;						
					}else{
						isComponenteValido=false;
						setSelection(0,44);
					}
				}else{
					Alerta.error("Formatação de DANFE inválida!");
					isComponenteValido=false;
					setSelection(0,44);					
				}
			}else{
				isComponenteValido=false;
			}
		}		
	}
}