package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class PacienteEvent extends CairngormEvent{
		public static const INSERIR:String = "PacienteEvent.inserirPaciente";
		public static const ALTERAR:String = "PacienteEvent.alterarPaciente";
		public static const EXCLUIR:String = "PacienteEvent.excluirPaciente";
		public static const LISTAR_PAGINADO:String = "PacienteEvent.listarPacientePaginado";
		public static const LISTAR:String = "PacienteEvent.listarPaciente";
		public static const LISTAR_PAGINADO_RELATORIO:String = "PacienteEvent.listarPacienteRelatorio";
		public static const OBTER:String = "PacienteEvent.obterPaciente";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function PacienteEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}