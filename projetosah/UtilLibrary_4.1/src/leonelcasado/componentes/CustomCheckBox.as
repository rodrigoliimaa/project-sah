package leonelcasado.componentes{
	import flash.events.KeyboardEvent;
	import flash.ui.Keyboard;
	
	import mx.controls.CheckBox;
	
	public class CustomCheckBox extends CheckBox{

		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]
		public var isNaoMudarFoco:Boolean;

		public function CustomCheckBox(){
			super();
			addEventListener(KeyboardEvent.KEY_DOWN, mudarFoco);
			useHandCursor=true;
			buttonMode=true;
			setStyle("borderStyle","solid");
		}

		override public function initialize():void{
			super.initialize();
		}

		private function mudarFoco(event:KeyboardEvent):void{
			if(!isNaoMudarFoco){
				if(event.keyCode==Keyboard.ENTER){
					this.focusManager.setFocus(this.focusManager.getNextFocusManagerComponent());
				}
			}
		}
	}
}