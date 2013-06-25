package leonelcasado.componentes {
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.controls.CheckBox;
	import mx.events.FlexEvent;
	
	[Event(name="comboChecked", type="com.arcadiocarballares.ComboCheckEvent")]
	
	public class ComboCheckItemRenderer extends CheckBox {
		
		public function ComboCheckItemRenderer() {
			super();
			addEventListener(FlexEvent.CREATION_COMPLETE, onCreationComplete);
			addEventListener(MouseEvent.CLICK,onClick);
		}

		private function onCreationComplete(event:Event):void {
			try{
				if (data!=null && data.isSelecionado==true) {
					selected=true;
					var cck:CustomComboBoxCheck=CustomComboBoxCheck(ComboCheckDropDownFactory(owner).owner);
					var index:int=cck.selectedItems.getItemIndex(data);
	        		if (index==-1) {
						cck.selectedItems.addItem(data);
	        		}
				}
			}catch(e:Error){}
		}

        private function onClick(event:Event):void {
			super.data.isSelecionado=selected;
			var myComboCheckEvent:ComboCheckEvent=new ComboCheckEvent(ComboCheckEvent.COMBO_CHECKED);
	        myComboCheckEvent.obj=data;
	        owner.dispatchEvent(myComboCheckEvent);
        }
	}
}