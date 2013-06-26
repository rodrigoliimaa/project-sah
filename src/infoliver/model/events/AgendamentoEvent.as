package infoliver.model.events
{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;

	public class AgendamentoEvent extends CairngormEvent{
		
		public static const ALTERAR_SITUACAO:String = "AgendamentoEvent.alterarSituacaoAgendamento";
		public static const INSERIR:String = "AgendamentoEvent.inserirAgendamento";
		public static const LISTA_PAGINADO:String = "AgendamentoEvent.listarAgendamentoPaginado";
		public static const REAGENDAR:String = "AgendamentoEvent.reagendarAgendamento";
		
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************
		
		public function AgendamentoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}
}