package leonelcasado.componentes{
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;
	
	import mx.controls.DateField;
	import mx.events.FlexEvent;

	public class CustomDateField extends DateField{
		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]
		public var isComponenteValido:Boolean;
		
		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]
		public var isNaoMudarFoco:Boolean;
		
		public function CustomDateField(){
			super();
			addEventListener(FlexEvent.ENTER, mudarFoco);
			addEventListener(FocusEvent.FOCUS_OUT,valida);
			restrict="0-9";			
			toolTip="Digite uma Data válida";
			setStyle("borderStyle","solid");
			setStyle("textAlign","center");
			editable=true;
			width=125;
			isComponenteValido=false;
			formatString="DD/MM/YYYY";
			dayNames = ["D","S","T","Q","Q","S","S"];
			monthNames=["Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"];
		}
		
		override public function initialize():void{
			super.initialize();
		}

		private function mascara(event:KeyboardEvent):void{
			var pos:Number=Number(text.length) + 1;			
			if (text.length == 2 || text.length == 5){
	            text = text + "/";
	            //setSelection(pos,pos);
	  		}
		}
		
		public function valida(event:FocusEvent):void{
			if(text.length>0){
				var valor:String=CustomViewHelper.removerMascara(text);
				if(!Validacao.validarData(valor)){
					isComponenteValido=false;
					//setSelection(0,10);
				}else{					
					//Valida intervalo informado.				
					if(selectableRange!=null){
						var dia:Number = Number(valor.substring(0,2));
						var mes:Number = Number(valor.substring(2,4));
						var ano:Number = Number(valor.substring(4,8));
						var data:Date=new Date(ano,mes-1,dia);
						if((data>=selectableRange.rangeStart) &&
							(data<=selectableRange.rangeEnd)){
							//não faz nada.
						}else{	
							Alerta.error("Data inválida!");
						}
					}
					text=valor.substring(0,2)+"/"+valor.substring(2,4)+"/"+valor.substring(4,8);
					isComponenteValido=true;				
				}				
			}else{
				isComponenteValido=false;
			}			
		}

		private function mudarFoco(event:FlexEvent):void{
			if(!isNaoMudarFoco){
				this.focusManager.setFocus(this.focusManager.getNextFocusManagerComponent());
			}
		}
	}
}