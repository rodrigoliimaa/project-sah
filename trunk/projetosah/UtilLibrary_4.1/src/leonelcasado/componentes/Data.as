package leonelcasado.componentes{
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;
		
	public class Data extends CustomTextInput{
		
		public function Data(){
			super();
			addEventListener(KeyboardEvent.KEY_UP, mascara);
			addEventListener(FocusEvent.FOCUS_OUT,valida);
			maxChars=10;
			restrict="0-9";
			maxWidth=120;
			width=120;
			toolTip="Digite uma Data válida";
			isComponenteValido=false;
		}

		override public function initialize():void{
			super.initialize();
		}
		
		private function mascara(event:KeyboardEvent):void{
			var pos:Number=Number(text.length) + 1;			
			if (text.length == 2 || text.length == 5){
	            text = text + "/";
				setSelection(pos,pos);
	  		}
		}

		public function valida(event:FocusEvent):void{
			if(text.length>0){
				var valor:String=CustomViewHelper.removerMascara(text);
				if(!Validacao.validarData(valor)){
					isComponenteValido=false;
					setSelection(0,10);
				}else{
					text=valor.substring(0,2)+"/"+valor.substring(2,4)+"/"+valor.substring(4,8);
					isComponenteValido=true;				
				}
			}else{
				isComponenteValido=false;
			}
		}	
	}
}