package leonelcasado.componentes{
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.text.TextLineMetrics;
	import flash.ui.Keyboard;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.controls.ComboBox;
	import mx.events.ListEvent;
	
	public class CustomComboBoxMultSelection extends ComboBox{

		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]		
		public var isUpperCase:Boolean=true;

		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]
		public var isAddSelecione:Boolean;

		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]
		public var isAddTodos:Boolean;

		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]
		public var isNaoMudarFoco:Boolean;
		
		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]
		public var isOrdenarLista:Boolean=false;

		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]
		public var isOrdenacaoDesc:Boolean=false;

		private var ctrlKey:Boolean = false;
		private const multiplaSelecao:String = 'MÚLTIPLA SELEÇÃO';
 		private var _itensSelecionados:Array;

		public function CustomComboBoxMultSelection(){
			super();
			addEventListener(KeyboardEvent.KEY_DOWN, mudarFoco);
			addEventListener(Event.CHANGE, textoUpperCase);
			
			//enabled=false;
			useHandCursor=true;
			buttonMode=true;
			setStyle("borderStyle","solid");
		}
				
		private function mudarFoco(event:KeyboardEvent):void{
			if(!isNaoMudarFoco){
				if(event.keyCode==Keyboard.ENTER){
					this.focusManager.setFocus(this.focusManager.getNextFocusManagerComponent());
				}
			}
		}

		private function textoUpperCase(event:Event):void{
			if(isUpperCase)text=text.toUpperCase();
		}

		override public function initialize():void{
			super.initialize();
		}

		override public function set initialized(value:Boolean):void{
			super.initialized = value;
			if(value)
            	resize();
		}
 
		override protected function keyDownHandler(event:KeyboardEvent):void{
			super.keyDownHandler(event);
			ctrlKey = event.ctrlKey;
			if(ctrlKey)
				dropdown.allowMultipleSelection = true;
		}
 
		override protected function keyUpHandler(event:KeyboardEvent):void{
			super.keyUpHandler(event);
			ctrlKey = event.ctrlKey;
			if(!ctrlKey){
				close(); 
				var changeEvent:ListEvent = new ListEvent(ListEvent.CHANGE)
				dispatchEvent(changeEvent);
			}
		}
 
		override public function close(trigger:Event=null):void{
			if(!ctrlKey){
				super.close(trigger);
				if(dropdown.selectedItems.length > 1)
					textInput.text = multiplaSelecao;				
			}
		}
 
		private function resize():void{
			var lineMetrics:TextLineMetrics;			
 			lineMetrics = measureText(multiplaSelecao);
 			var newWidth:Number = lineMetrics.width;
 			newWidth += getStyle("arrowButtonWidth") + getStyle("paddingLeft") + getStyle("paddingRight") + 8;
		    this.width = Math.max(this.width, newWidth);
		}
 
		public function set selectedItems(value:Array):void{
			if(dropdown)
				dropdown.selectedItems = value;
		}
 
		[Bindable("change")]
		public function get selectedItems():Array{
			if(dropdown)
				return dropdown.selectedItems;
			else
				return null;
		}
 
		public function set selectedIndices(value:Array):void{
			if(dropdown)
				dropdown.selectedIndices = value;
		}
 
		[Bindable("change")]
		public function get selectedIndices():Array{
			if(dropdown)
				return dropdown.selectedIndices;
			else
				return null;
		}
 		
 		/*
 		 * Remover esta função e usar selectedItems() para retornar o objeto completo
 		 */
		public static function getListaSelecaoComboBox(listaSelecionada:Array):Array{
			var lista:Array=[];
			for each (var obj:Object in listaSelecionada){
				lista.push(obj);
			}
			return lista;
		}
		
		override public function set dataProvider(value:Object):void{
			if(value==null || value.length==0){
				super.dataProvider = value; 
				return;
			}
			//-------------------------------------
			var valueOrdenado:Object;
			if(isOrdenarLista)
				valueOrdenado=CustomViewHelper.ordenarArrayCollection(labelField,value as ArrayCollection,isOrdenacaoDesc);
			else
				valueOrdenado=value;
			//-------------------------------------
			if(isAddSelecione){
				var lista:Array=[];				
				lista.push("SELECIONE");
				for each (var obj:Object in valueOrdenado){
					lista.push(obj);
				}
		    	super.dataProvider = lista;
		    	this.selectedIndex=0;
			}else if(isAddTodos){
				var lista2:Array=[];				
				lista2.push("TODOS");
				for each (var obj2:Object in valueOrdenado){
					lista2.push(obj2);
				}
		    	super.dataProvider = lista2;
			}else{
				super.dataProvider = valueOrdenado;
			}
			//enabled=true;
		}
	}
}