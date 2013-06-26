package infoliver.model.events
{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class SigtapEvent extends CairngormEvent{
		//		public static const ALTERAR:String = "SigtapEvent.alterarSigtapProcedimento";
		//		public static const EXCLUIR:String = "SigtapEvent.excluirSigtapProcedimento";;
		//		public static const INSERIR:String = "SigtapEvent.inserirSigtapProcedimento";
		//		public static const LISTAR:String = "SigtapEvent.listarSigtapProcedimento";
		public static const LISTA_SIGTAP_CID_PAGINADO:String = "SigtapEvent.listarSigtapCIDPaginado";
		public static const LISTA_SIGTAP_PROCEDIMENTO_PAGINADO:String = "SigtapEvent.listarSigtapProcedimentoPaginado";
		public static const LISTA_SIGTAP_PROCEDIMENTOS:String = "SigtapEvent.listarProcedimentosSigtap";
		public static const OBTER_SIGTAP_CID:String = "SigtapEvent.obterSigtapCID";
		public static const OBTER_SIGTAP_PROCEDIMENTO:String = "SigtapEvent.obterSigtapProcedimento";
		public static const OBTER_SIGTAP_PROCEDIMENTO_PARA_CONSULTA:String = "SigtapEvent.obterSigtapProcedimentoParaConsulta";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************
		
		public function SigtapEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}
}