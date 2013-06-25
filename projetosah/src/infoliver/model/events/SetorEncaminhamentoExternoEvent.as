package infoliver.model.events
{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;

	public class SetorEncaminhamentoExternoEvent extends CairngormEvent{
		public static const LISTAR:String = "SetorEncaminhamentoExternoEvent.listarSetorEncaminhamentoExterno";
		public static const INSERIR:String = "SetorEncaminhamentoExternoEvent.inserirSetorEncaminhamentoExterno";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************
		
		public function SetorEncaminhamentoExternoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}