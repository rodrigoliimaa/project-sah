package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class GrupoEvent extends CairngormEvent{
		public static const INSERIR:String = "GrupoEvent.inserirGrupo";
		public static const ALTERAR:String = "GrupoEvent.alterarGrupo";
		public static const EXCLUIR:String = "GrupoEvent.excluirGrupo";
		public static const LISTAR:String = "GrupoEvent.listarGrupo";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function GrupoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}