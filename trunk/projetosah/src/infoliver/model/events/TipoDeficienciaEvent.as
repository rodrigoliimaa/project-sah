package infoliver.model.events
{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;

	public class TipoDeficienciaEvent extends CairngormEvent
	{
		public static const LISTAR:String = "TipoDeficienciaEvent.listarDeficiencia";
		public static const INSERIR:String = "TipoDeficienciaEvent.inserirTipoDeficiencia";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************
		
		public function TipoDeficienciaEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		
		
	}
}
}	