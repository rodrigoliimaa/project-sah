package infoliver.model.events
{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;

	public class EncaminhamentoExternoEvent extends CairngormEvent
	{
		
		public static const LISTAR:String = "EncaminhamentoExternoEvent.listarEncaminhamento";
		public static const INSERIR:String = "EncaminhamentoExternoEvent.inserirEncaminhamento";
		public static const LISTARFILTRO:String = "EncaminhamentoExternoEvent.listarEncaminhamentoFiltro";
		public static const ALTERAR:String = "EncaminhamentoExternoEvent.alterarEncaminhamento";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************
		public function EncaminhamentoExternoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}
}