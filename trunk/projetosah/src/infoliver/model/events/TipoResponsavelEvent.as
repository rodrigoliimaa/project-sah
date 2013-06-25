package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class TipoResponsavelEvent extends CairngormEvent{
		public static const LISTAR:String = "TipoResponsavelEvent.listarTipoResponsavel";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function TipoResponsavelEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}