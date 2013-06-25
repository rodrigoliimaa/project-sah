package leonelcasado.componentes{
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.ui.Keyboard;
	
	import mx.controls.Button;
	import mx.events.FlexEvent;

	[Event(name="enter", type="flash.events.Event")]
	public class CustomButton extends Button{

		private var _enterOrClick:Function;
		
		public function CustomButton(){
			super();
			useHandCursor=true;
			buttonMode=true;
			//addEventListener(KeyboardEvent.KEY_DOWN, keyDown);
		}

		override public function initialize():void{
			super.initialize();
		}
		
		override protected function keyDownHandler(event:KeyboardEvent):void{
			if(event.keyCode == Keyboard.ENTER) {
				onEnter();
			}
		}

		protected function onEnter():void{
			this.dispatchEvent(new Event("enter"));
		}

		private function keyDown(event:KeyboardEvent):void {
			if(event.keyCode == Keyboard.ENTER) {
				this.validaEExecutaMetodo();
			}
		}
		
		override protected function clickHandler(event:MouseEvent):void {
			super.clickHandler(event);
			this.validaEExecutaMetodo();
		}
		
		private function validaEExecutaMetodo():void {
			if(_enterOrClick != null) {
				enterOrClick();
			}	
		}
		
		public function set enterOrClick(_enterOrClick:Function):void {
			this._enterOrClick = _enterOrClick;
		}
		
		public function get enterOrClick():Function {
			return this._enterOrClick;
		}		
	}
}