package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class LogAcessoEvent extends CairngormEvent{
		public static const LISTAR_PAGINADO:String = "LogAcessoEvent.listarLogAcessoPaginado";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function LogAcessoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}