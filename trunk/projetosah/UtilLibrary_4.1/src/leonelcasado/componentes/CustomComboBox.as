package leonelcasado.componentes{
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.ui.Keyboard;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.controls.ComboBox;

	[Event(name="selecionarItem", type="flash.events.Event")]
	public class CustomComboBox extends ComboBox{
        private var _selectedValue:String;
        private var bSelectedValueSet:Boolean = false;
        private var bDataProviderSet:Boolean = false;

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

		public function CustomComboBox(){
			super();
			addEventListener(KeyboardEvent.KEY_DOWN, mudarFoco);
			addEventListener(Event.CHANGE, textoUpperCase);
			
			//enabled=false;
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

		private function textoUpperCase(event:Event):void{
			if(isUpperCase)text=text.toUpperCase();
			onSelecionarItem();
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
					bDataProviderSet = true;
				}
		    	super.dataProvider = lista;
			}else if(isAddTodos){
				var lista2:Array=[];				
				lista2.push("TODOS");
				for each (var obj2:Object in valueOrdenado){
					lista2.push(obj2);
					bDataProviderSet = true;
				}
		    	super.dataProvider = lista2;
			}else{
				bDataProviderSet = true;
				super.dataProvider = valueOrdenado;
			}
			//enabled=true;
		}

        override protected function commitProperties():void{
            super.commitProperties();
            if (bSelectedValueSet && bDataProviderSet){
                bSelectedValueSet=false;
                var item:String=null;
                for (var i:int=0;i<this.dataProvider.length;i++){
                    if(labelField=="label"){
                    	item = this.dataProvider[i].data;
                    }else{
						if((isAddSelecione==true || isAddTodos==true) && i==0)continue;
                    	item = this.dataProvider[i][labelField];
                    }
                    if(item == _selectedValue){
                        this.selectedIndex = i;
                        break;
                    }
                }
            }
        }

		protected function onSelecionarItem():void{
			this.dispatchEvent(new Event("selecionarItem"));
		}

        public function set selectedValue(s:String):void{
            bSelectedValueSet = true;
            _selectedValue = s;
            invalidateProperties();
        }
	}
}