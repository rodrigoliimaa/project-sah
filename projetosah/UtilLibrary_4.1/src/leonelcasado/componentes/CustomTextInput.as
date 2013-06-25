package leonelcasado.componentes{
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	
	import mx.controls.Button;
	import mx.controls.TextInput;
	import mx.events.FlexEvent;
	
	public class CustomTextInput extends TextInput{
		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]
		public var isComponenteValido:Boolean;
		
		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]		
		public var isUpperCase:Boolean=true;
		
		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]		
		public var isClear:Boolean=true;		
		
		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]
		public var isNaoMudarFoco:Boolean;

		private var _texto:String;

		private var clearButton:Button;
		
		/*public var defultTextColor:String = "#FF0000";
		private var _defultText:String = "Please Input Text..."		
		public function set defultText(value:String):void{
			if(value == null || value == ""){
				defultTextColor = new String(this.getStyle("color"));
			}
			_defultText = value;
		}
		public function get defultText():String{
			return _defultText;
		}*/
		
		public function CustomTextInput(){
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, addButton);
			addEventListener(FocusEvent.FOCUS_IN,selecionarDados);
			addEventListener(Event.CHANGE, textoUpperCase);
			addEventListener(Event.CLEAR, textoClear);
			addEventListener(FlexEvent.ENTER, mudarFoco);
			
			//this.addEventListener("textChanged", showClearButton);
			//this.addEventListener(FocusEvent.FOCUS_IN, this_focusInHandler)
			//this.addEventListener(FocusEvent.FOCUS_OUT, this_focusOutHandler)
			
			width=150;
			setStyle("borderStyle","solid");
		}
		
		override public function initialize():void{
			super.initialize();
		}
		
		public function get texto():String{
			return CustomViewHelper.trim(text);
		}
		
		public function textoSemMascara():String{
			return CustomViewHelper.removerMascara(text);
		}
		
		override public function set enabled(value:Boolean):void{	
	        super.enabled = value;
    		if(clearButton != null)
    			clearButton.enabled = value;
    	}

		private function selecionarDados(event:Event):void{
			this.setSelection(0,this.length)
		}

		private function mudarFoco(event:FlexEvent):void{
			if(!isNaoMudarFoco){
				this.focusManager.setFocus(this.focusManager.getNextFocusManagerComponent());
			}
		}
		
		private function textoUpperCase(event:Event):void{
			if(isUpperCase)text=text.toUpperCase();
		}
		
		private function textoClear(event:Event):void{
			if(isClear) text = "";
		}
		
		private function addButton(event:FlexEvent):void{
			if(clearButton == null){
				clearButton = new Button();
				clearButton.width=10;
				clearButton.height=10;
				clearButton.y = (this.height - 10) / 2;
				clearButton.x = this.width - 10 - (this.height - 10) / 2;
				clearButton.focusEnabled=false;
				clearButton.setStyle("upSkin", Imagens.iconWindowCloseButton);
				clearButton.setStyle("overSkin", Imagens.iconWindowCloseButton2);
				clearButton.setStyle("downSkin", Imagens.iconWindowCloseButton2);
				clearButton.addEventListener(MouseEvent.CLICK, clearButton_clickHandler);
				clearButton.visible = false;
				clearButton.buttonMode = true;
				clearButton.useHandCursor = true;
				clearButton.mouseChildren = false;
				this.addChild(clearButton);				
				this.addEventListener("textChanged", showClearButton);
				showClearButton(null);
				if(text == ""){
					setDefaultText();
				}
			}
		}
		
		private function clearButton_clickHandler(event:MouseEvent):void{
			this.text = "";
			clearButton.visible = false;
		}
		
/*		
		private function this_focusInHandler(event:FocusEvent):void{
			if(text == ""){
				this.text = "";
				this.textField.text = "";
				this.textField.textColor = this.getStyle("color");
				clearButton.visible = false;
			}
		}
		
		private function this_focusOutHandler(event:FocusEvent):void{
			if(text == ""){
				setDefaultText();
			}
		}
*/
		private function setDefaultText():void{
			this.text = "";
			//this.textField.text = _defultText;
			//this.textField.textColor = ColorUtils.convertColor(defultTextColor);
			clearButton.visible = false;
		}
		
		
				
		private function showClearButton(event:Event):void{
			if(clearButton){
				if(text != "" && this.enabled){
					clearButton.visible = true;
				}else{
					clearButton.visible = false;
				}
				//this.textField.textColor = this.getStyle("color");
			}
		}
		
		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void{
			super.updateDisplayList(unscaledWidth, unscaledHeight);
			//if(this.textField.text == this._defultText && this.text == ""){
				//this.textField.textColor = ColorUtils.convertColor(defultTextColor);
			//}
			if(clearButton != null){
				clearButton.x = this.width - 10 - (this.height - 10) / 2;
				clearButton.y = (this.height - 10) / 2;
			}
		}
	}
}