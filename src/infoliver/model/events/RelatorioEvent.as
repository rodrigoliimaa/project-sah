package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class RelatorioEvent extends CairngormEvent{
		public static const LISTAR_RELATORIO_PROCEDIMENTO:String = "RelatorioEvent.listarRelatorioProcedimento";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function RelatorioEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}