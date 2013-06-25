package leonelcasado.componentes{
	import flash.events.Event;
	import flash.events.FocusEvent;
	
	import mx.controls.TextArea;
	import mx.utils.StringUtil;

	public class CustomTextArea extends TextArea{
		[Inspectable(category="General", enumeration="true,false", defaultValue="false")]		
		public var isUpperCase:Boolean=true;

		private var _texto:String;

		public function CustomTextArea(){
			super();
			addEventListener(FocusEvent.FOCUS_IN,setarPrimeiraLinha);
			addEventListener(Event.CHANGE, textoUpperCase);
			setStyle("borderStyle","solid");
		}

		override public function initialize():void{
			super.initialize();
		}

		public function get texto():String{
			return CustomViewHelper.trim(text);
		}

		private function setarPrimeiraLinha(event:FocusEvent):void{
			StringUtil.trim(text);
			setSelection(0,0);
		}

		private function textoUpperCase(event:Event):void{
			if(isUpperCase)text=text.toUpperCase();
		}
	}
}