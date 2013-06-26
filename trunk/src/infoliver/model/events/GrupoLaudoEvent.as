package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class GrupoLaudoEvent extends CairngormEvent{
		public static const INSERIR:String = "GrupoLaudoEvent.inserirGrupoLaudo";
		public static const ALTERAR:String = "GrupoLaudoEvent.alterarGrupoLaudo";
		public static const EXCLUIR:String = "GrupoLaudoEvent.excluirGrupoLaudo";
		public static const LISTAR:String = "GrupoLaudoEvent.listarGrupoLaudo";
		public static const LISTAR_PACIENTE_GRUPO:String = "GrupoLaudoEvent.listarPacienteGrupoLaudo";
		
		public static const INSERIR_GRUPO_LAUDO_PACIENTE:String = "GrupoLaudoEvent.inserirGrupoLaudoPaciente";
		public static const EXCLUIR_GRUPO_LAUDO_PACIENTE:String = "GrupoLaudoEvent.excluirGrupoLaudoPaciente";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function GrupoLaudoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}