package leonelcasado.componentes{
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.ui.Keyboard;
	
	import mx.controls.TextInput;
	import mx.events.FlexEvent;
	
	public class Numero extends TextInput{
		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]
		public var isNaoMudarFoco:Boolean;

		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]
		public var isFormatarNumero:Boolean=false;

		private var _texto:String;

		public function Numero(){
			super();
			addEventListener(KeyboardEvent.KEY_UP, formataNumero);
			addEventListener(MouseEvent.CLICK, selecionarTexto);
			addEventListener(FlexEvent.ENTER, mudarFoco);			

			maxChars=20;
			restrict="0-9";
			maxWidth=150;
			toolTip="Digite um número";
			setStyle("borderStyle","solid");
			setStyle("textAlign","right");
		}

		override public function initialize():void{
			super.initialize();
		}

		public function get texto():String{
			return CustomViewHelper.trim(text);
		}

		private function mudarFoco(event:FlexEvent):void{
			if(!isNaoMudarFoco){
				this.focusManager.setFocus(this.focusManager.getNextFocusManagerComponent());
			}
		}			

		public function textoSemMascara():String{
			return CustomViewHelper.removerMascara(text);
		}

		private function selecionarTexto(event:MouseEvent):void{
			if( text.length > 0 )
				setSelection(0,text.length);
		}

		private function formataNumero_backspace():void{
			var numDig:String = text;
			var tamDig:int = numDig.length;
			var contador:int = 0;
			var i:int;
			tamDig--;
			
			if ( tamDig >= 0 ){
				var numero:String = "";
				
				for ( i = tamDig; (i >= 0); i-- ){
					if ( (parseInt(numDig.substr(i, 1)) >= 0) && (parseInt(numDig.substr(i, 1)) <= 9) ){
						contador++;
						if ( (contador == 2) && ((numero.length) > 3) ){
							numero = "." + numero;
							contador = 0;
						}
						numero = numDig.substr(i, 1) + numero;
					}
				}
				text = numero;
			}
		}
		
		private function formataNumero(event:KeyboardEvent):Boolean{
			if(isFormatarNumero){
				if(event.keyCode != Keyboard.TAB && event.keyCode != Keyboard.SHIFT && event.keyCode != Keyboard.ENTER ){
					var sep:int = 0;
					var key:String = '';
					var len:int = 0;
					var len2:int = 0;
					var aux:String = "";
					var aux2:String= "";
					var strCheck:String = '0123456789';
					var milSep:String=".";
					var i:Number;
					var j:Number;
					
					formataNumero_backspace();
					
					len = text.length;
					for (i = 0; i < len; i++){
						if (text.charAt(i) != '0'){
							break;
						}
					}
					
					aux = '';
					for (; i < len; i++){
						if ( strCheck.indexOf(text.charAt(i)) != -1 ){
							aux += text.charAt(i);
						}
					}
					len = aux.length;
					
					if (text == ''){
						text = '';
					}else{
						if (len == 0)text = '0';
						if (len>0){
							aux2 = '';
							for (j = 0, i = len-1; i >= 0; i--){
								if (j == 3){
									aux2 += milSep;
									j = 0;
								}
								aux2 += aux.charAt(i);
								j++;
							}
							
							text = '';
							len2 = aux2.length;
							for (  i = len2 - 1; i >= 0; i-- ){
								text += aux2.charAt(i);
							}
						}
					}
					setSelection(text.length,text.length);
				}
			}	
			return false;
		}
	}
}