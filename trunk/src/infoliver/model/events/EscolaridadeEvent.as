package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class EscolaridadeEvent extends CairngormEvent{
		public static const LISTAR:String = "EscolaridadeEvent.listarEscolaridade";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function EscolaridadeEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}