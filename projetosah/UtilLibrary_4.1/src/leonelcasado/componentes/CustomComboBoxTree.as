package leonelcasado.componentes{
	import mx.controls.ComboBox;
	import mx.core.ClassFactory;
	import mx.events.DropdownEvent;
	
	public class CustomComboBoxTree extends ComboBox{
		private var _ddFactory:ClassFactory;
		private var _treeHeight:Number;
		public var treeSelectedItem:Object;
		
		public function CustomComboBoxTree(){
			super();
			this.dropdownFactory = ddFactory;
			this.addEventListener(DropdownEvent.OPEN,onComboOpen);
		}
		
		override public function initialize():void{
			super.initialize();
		}
		
		override public function set dataProvider(value:Object):void{
			super.dataProvider=value;
			inicializar();			
		}

		private function get ddFactory():ClassFactory{
			if (_ddFactory == null){
				_ddFactory = new ClassFactory();
				_ddFactory.generator = CustomComboBoxTreeRenderer;
				_ddFactory.properties = {
					width:this.width, 
						height:this._treeHeight,
						outerDocument:this
				};
			}
			return _ddFactory;		
		}	
		
		public function get treeHeight():Number{
			return _treeHeight;
		}
		
		public function set treeHeight(value:Number):void{
			this._treeHeight = value;
			ddFactory.properties["height"] = this._treeHeight;
		}
		
		private function onComboOpen(event:DropdownEvent):void{
			var tree:CustomComboBoxTreeRenderer = dropdown as CustomComboBoxTreeRenderer;
			if (treeSelectedItem){
				tree.expandParents(treeSelectedItem);
				tree.selectNode(treeSelectedItem);
			}else{
				if(dataProvider.length>0)
					tree.expandItem(dataProvider.getItemAt(0), true);
			}
		}
		
		public function inicializar():void{
			var tree:CustomComboBoxTreeRenderer = dropdown as CustomComboBoxTreeRenderer;
			if(dataProvider.length>0){
				tree.expandItem(dataProvider.getItemAt(0), true);
				treeSelectedItem=dataProvider.getItemAt(0);
				text = treeSelectedItem[labelField];
				tree.expandParents(treeSelectedItem);
				tree.selectNode(treeSelectedItem);
			}
		}
		
		override protected function updateDisplayList(unscaledWidth:Number,unscaledHeight:Number):void { 
			super.updateDisplayList(unscaledWidth, unscaledHeight);   
			if(dropdown && treeSelectedItem && treeSelectedItem[labelField] != null){   
				text = treeSelectedItem[labelField]; 
			} 
		} 
		
		public function updateLabel(selectedItem:Object):void{
			if (selectedItem){
				treeSelectedItem = selectedItem;
				text = treeSelectedItem[labelField];
			}
		}
		
		override public function get selectedItem():Object {
			return treeSelectedItem;
		}
		
	}	
}