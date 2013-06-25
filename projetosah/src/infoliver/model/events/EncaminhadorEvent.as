package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class EncaminhadorEvent extends CairngormEvent{
		public static const LISTAR:String = "EncaminhadorEvent.listarEncaminhador";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function EncaminhadorEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}