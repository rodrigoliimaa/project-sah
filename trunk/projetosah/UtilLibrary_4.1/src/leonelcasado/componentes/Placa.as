package leonelcasado.componentes{
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;
	
	public class Placa extends CustomTextInput{
		private var numeros:String = "0123456789";
		private var caracteres:String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		public function Placa(){
			super();
			addEventListener(KeyboardEvent.KEY_UP, mascara);
			addEventListener(FocusEvent.FOCUS_OUT,valida);

			maxChars=8;
			restrict="A-Z,0-9";
			maxWidth=150;
			toolTip="Digite uma Placa válida";
			isComponenteValido=false;
		}

		override public function initialize():void{
			super.initialize();
		}

		public function textoComMascara(value:String):void{
			text=CustomViewHelper.mascararPlaca(value);
		}

		private var tam:Number;
		public function mascara(event:KeyboardEvent):void{
			var pos:Number=Number(text.length) + 1;		
			tam=Number(text.length);
			var digito:String=text.substring(tam-1,tam);
			if(text.length <= 3){
				if (caracteres.indexOf(digito) != -1) {
					text=text;
					setSelection(pos,pos);	
				}else{
					text=text.replace(digito,"");
					setSelection(pos,pos+1);
				}
			} 
			
			if(text.length == 3){
				text = text + "-";
				setSelection(pos,pos+1);
			}

			if(text.length > 4){
				if (numeros.indexOf(digito) != -1) {
					text=text;
					setSelection(pos,pos);	
				}else{
					text=text.replace(digito,"");
					setSelection(pos,pos+1);
				}
			}
		}

		public function valida(event:FocusEvent):void{
			if(text.length>0){	
				var valor:String=CustomViewHelper.removerMascara(text);
				if(!Validacao.validarPlaca(valor)){
					isComponenteValido=false;
					setSelection(0,8);
				}else{
					text=valor.substring(0,3)+"-"+valor.substring(3,7);
					isComponenteValido=true;				
				}
			}else{
				isComponenteValido=false;
			}		
		}
	}
}