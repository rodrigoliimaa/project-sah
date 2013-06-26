package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class SetorEvent extends CairngormEvent{
		public static const LISTAR:String = "SetorEvent.listar";
		public static const INSERIR:String = "SetorEvent.inserir";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************
		
		public function SetorEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}