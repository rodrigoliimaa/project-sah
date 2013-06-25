package leonelcasado.componentes{
	import mx.controls.Tree;
	import mx.events.ListEvent;
	
	public class CustomComboBoxTreeRenderer extends Tree{
		[Bindable]
		public var outerDocument:CustomComboBoxTree;
		
		public function CustomComboBoxTreeRenderer(){
			super();
			this.addEventListener(ListEvent.CHANGE, onSelectionChanged);
		}
		
		private function onSelectionChanged(event:ListEvent):void{
			outerDocument.updateLabel(event.currentTarget.selectedItem);
		}
		
		public function expandParents(node:Object):void{
			if (node && !isItemOpen(node)){
				expandItem(node, true);
				expandParents(node.parent());
			}		
		}
		
		public function selectNode(node:Object):void{
			selectedItem = node;
			var idx:int = getItemIndex(selectedItem);
			scrollToIndex(idx);
		}		
	}
}	