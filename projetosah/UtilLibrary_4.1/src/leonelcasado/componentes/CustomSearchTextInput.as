package leonelcasado.componentes{
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.ui.Keyboard;
	
	import mx.containers.Canvas;
	import mx.controls.Image;

	/**
     *  @events
     */
	[Event( name="changeSearchTerm", type="flash.events.Event" )]
	public class CustomSearchTextInput extends Canvas {
		private var imageClear:Image;
		public var searchTextInput:PromptingTextInput;
		public var _prompt:String = "Pesquisar...";
		public var onSearchRequest:Function;
		
		public function CustomSearchTextInput() {
			super();
		}
		
		public function get text():String {
			if(searchTextInput == null) {
				return "";
			}
			return searchTextInput.text;
		}
		
		public function set text(text:String):void {
			searchTextInput.text = text;
		}

		public function get prompt():String {
			if(searchTextInput == null) {
				return "";
			}
			return searchTextInput.prompt;
		}
		
		public function set prompt(prompt:String):void {
			_prompt = prompt;
		}
		
		override protected final function createChildren():void {
			super.createChildren();
			
			searchTextInput = new PromptingTextInput();
			searchTextInput.setStyle("left", "0");
			searchTextInput.setStyle("top", "0");
			searchTextInput.setStyle("right", "0");
			searchTextInput.setStyle("paddingLeft", "20");
			searchTextInput.setStyle("paddingLeft", "20");
			searchTextInput.prompt = _prompt;
			searchTextInput.addEventListener(KeyboardEvent.KEY_UP, searchTextInputKeyUp);
			addChild(searchTextInput);
			
			var imageLupa:Image = new Image();
			imageLupa.source = Imagens.iconBackgroundTextFind;
			imageLupa.setStyle("left", "5");
			imageLupa.setStyle("verticalCenter", "0");
			imageLupa.addEventListener(MouseEvent.CLICK, searchImageClick);
			addChild(imageLupa);

			imageClear = new Image();
			imageClear.visible = false;
			imageClear.source = Imagens.iconClear;
			imageClear.setStyle("right", "5");
			imageClear.setStyle("verticalCenter", "0");
			imageClear.addEventListener(MouseEvent.CLICK, clearImageClick);
			addChild(imageClear);
		}
		
		private function searchTextInputKeyUp(event:KeyboardEvent):void {
			// Dispatch CHANGE Event
            var newEvent:Event = new Event("changeSearchTerm", false);
            dispatchEvent(newEvent);
			
			if(event.keyCode == Keyboard.ENTER) {
				callOnSearchRequest();
			}
			if (searchTextInput.text.length > 0){
				imageClear.visible = true;	
			}
			if (searchTextInput.text.length == 0){
				imageClear.visible = false;	
			}
		}
		
		private function searchImageClick(event:MouseEvent):void {
			callOnSearchRequest();
		}

		private function clearImageClick(event:MouseEvent):void {
			searchTextInput.text = "";
			searchTextInput.setFocus();
            imageClear.visible = false;	
			// Dispatch CHANGE Event
            var newEvent:Event = new Event("changeSearchTerm", false);
            dispatchEvent(newEvent);
		}
		
		private function callOnSearchRequest():void {
			if(onSearchRequest != null) {
				onSearchRequest();
			}
		}
	}
}