package infoliver.model.events
{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;

	public class FornecedorEvent extends CairngormEvent{
		public static const ALTERAR:String = "FornecedorEvent.alterarFornecedor";
//		public static const EXCLUIR:String = "FornecedorEvent.excluirFornecedor";;
		public static const INSERIR:String = "FornecedorEvent.inserirFornecedor";
		public static const LISTAR_FORNECEDORES:String = "FornecedorEvent.listarFornecedores";
		public static const LISTA_PAGINADO:String = "FornecedorEvent.listarFornecedorPaginado";
		public static const OBTER:String = "FornecedorEvent.obterFornecedor";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************
		
		public function FornecedorEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}
}