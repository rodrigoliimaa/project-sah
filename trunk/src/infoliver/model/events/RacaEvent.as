package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class RacaEvent extends CairngormEvent{
		public static const LISTAR:String = "RacaEvent.listarRaca";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function RacaEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}