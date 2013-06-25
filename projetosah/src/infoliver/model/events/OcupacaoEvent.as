package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class OcupacaoEvent extends CairngormEvent{
		public static const LISTAR:String = "OcupacaoEvent.listarOcupacao";
		public static const PESQUISAR_POR_DESCRICAO:String = "OcupacaoEvent.pesquisarOcupacaoPorDescricao";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function OcupacaoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}