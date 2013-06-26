package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class MedicoEvent extends CairngormEvent{
		public static const ALTERAR:String = "MedicoEvent.alterarMedico";
		public static const EXCLUIR:String = "MedicoEvent.excluirMedico";;
		public static const INSERIR:String = "MedicoEvent.inserirMedico";
		public static const LISTAR:String = "MedicoEvent.listarMedico";
		public static const LISTA_PAGINADO:String = "MedicoEvent.listarMedicoPaginado";
		public static const OBTER:String = "MedicoEvent.obterMedico";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function MedicoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}