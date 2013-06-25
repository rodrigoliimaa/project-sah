package leonelcasado.componentes{
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;
	
	public class Ie extends CustomTextInput{
		
		public function Ie(){
			super();
			addEventListener(KeyboardEvent.KEY_UP, mascara);
			addEventListener(FocusEvent.FOCUS_OUT,valida);

			maxChars=10;
			restrict="0-9";
			maxWidth=100;
			toolTip="Digite uma Inscrição Estadual válida com 09 Dígitos";
			isComponenteValido=false;			
		}

		override public function initialize():void{
			super.initialize();
		}
		
		public function mascara(event:KeyboardEvent):void{
			if(text.length==8){
				text = text + "-";
				setSelection(9,10);
			}
		}

		public function textoComMascara(value:String):void{
			text=CustomViewHelper.mascararCaceal(value);
		}

		public function valida(event:FocusEvent):void{
			if(text.length>0){
				var valor:String=CustomViewHelper.removerMascara(text);
				if(text.substring(0,2)=="24"){
					if(!Validacao.validarCaceal(valor)){
						isComponenteValido=false;
						setSelection(0,10);
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
						setSelection(0,10);
					}										
				}
			}else{
				isComponenteValido=false;
			}
		}				
	}
}