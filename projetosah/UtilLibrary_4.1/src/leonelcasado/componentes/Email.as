package leonelcasado.componentes{
	import flash.events.FocusEvent;

	public class Email extends CustomTextInput{
		
		public function Email(){
			super();
			addEventListener(FocusEvent.FOCUS_OUT,valida);
			toolTip="Digite um Email válido";
			isComponenteValido=false;			
		}

		override public function initialize():void{
			super.initialize();
		}
				
		public function valida(event:FocusEvent):void{
			if(text.length>0){
				if(!Validacao.validarEmail(text)){
					isComponenteValido=false;
					setSelection(0,text.length);
				}else{
					isComponenteValido=true;				
				}
			}else{
				isComponenteValido=false;
			}
		}
	}
}