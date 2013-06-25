package infoliver.model.events
{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class MovimentacaoEvent extends CairngormEvent{
		public static const ALTERAR:String = "MovimentacaoEvent.alterarMovimentacao";
		public static const EXCLUIR:String = "MovimentacaoEvent.excluirMovimentacao";;
		public static const INSERIR:String = "MovimentacaoEvent.inserirMovimentacao";
		public static const LISTAR_RELATORIO:String = "MovimentacaoEvent.listarRelatorioMapa";
		public static const LISTA_PAGINADO:String = "MovimentacaoEvent.listarMovimentacaoPaginado";
		public static const LISTA_PAGINANDO:String = "MovimentacaoEvent.listarPaginandoAuditivo";
		public static const LISTA_PAGINADO_PARA_RELATORIO:String = "MovimentacaoEvent.listarMovimentacaoPaginadoParaRelatorio";
		public static const LISTA_PAGINADO_COM_DATA_AUTORIZACAO:String = "MovimentacaoEvent.listarMovimentacaoPaginadoComDataAutorizacaoPreenchida";
		public static const LISTAR_COMPROVANTE:String = "MovimentacaoEvent.listarRelatorioEncaminhamento";
		public static const LISTAR_TERMO_COMPROMISSO:String = "MovimentacaoEvent.listarTermoCompromisso";
		public static const LISTAR_RELATORIO_BPI:String = "MovimentacaoEvent.listarRelatorioMapaBpi";
		public static const LISTAR_RELATORIO_EQUIPAMENTOS:String = "MovimentacaoEvent.listarRelatorioEquipamentos";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************
		
		public function MovimentacaoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}
}