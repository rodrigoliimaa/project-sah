package infoliver.model.events
{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;

	public class PreAtendimentoEvent extends CairngormEvent
	{
		public static const INSERIR:String = "PreAtendimentoEvent.inserirPreAtendimento";
		public static const LISTAR:String = "PreAtendimentoEvent.listarPreAtendimentoPaginado";
		public static const LISTARTODOS:String = "PreAtendimentoEvent.listar";
		public static const EXCLUIR:String = "PreAtendimentoEvent.excluirPreAtendimento";
		public static const LISTARCOMFILTRO:String = "PreAtendimentoEvent.listarPreAtendimentoPaginadoFiltro";
		public static const LISTARATENDIDOS:String = "PreAtendimentoEvent.listarPreAtendimentoAtendidos";
		public static const ALTERAR:String = "PreAtendimentoEvent.alterarPreAtendimento";
		
		private var idDestination:String="facade";
		//**********************************************************************************
		
		public function PreAtendimentoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
			
	}
}