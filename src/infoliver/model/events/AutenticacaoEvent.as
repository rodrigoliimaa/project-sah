package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class AutenticacaoEvent extends CairngormEvent{
		public static const AUTENTICAR_USUARIO:String = "AutenticacaoEvent.login";
		//**********************************************************************************
		private var idDestination:String="autenticacaoFacade";
		//**********************************************************************************

		public function AutenticacaoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}