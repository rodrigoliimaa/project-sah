package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class ProcedimentoMedicoEvent extends CairngormEvent{
		public static const LISTAR_TIPO_PROCEDIMENTO:String = "ProcedimentoMedicoEvent.listarTipoProcedimentoMedico";
		public static const LISTAR_PROCEDIMENTO_PRINCIPAL:String = "ProcedimentoMedicoEvent.listarProcedimentoMedicoPrincipal";
		public static const LISTAR_PROCEDIMENTO_PRINCIPAL_POLICLINICA:String = "ProcedimentoMedicoEvent.listarProcedimentoMedicoPrincipalPoliclinica";
		public static const LISTAR_PROCEDIMENTO_SECUNDARIO:String = "ProcedimentoMedicoEvent.listarProcedimentoMedicoSecundario";
		public static const LISTAR_PROCEDIMENTOS:String = "ProcedimentoMedicoEvent.listarProcedimentos";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function ProcedimentoMedicoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}