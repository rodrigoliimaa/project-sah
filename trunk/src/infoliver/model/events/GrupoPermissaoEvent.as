package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class GrupoPermissaoEvent extends CairngormEvent{
		public static const INSERIR:String = "GrupoPermissaoEvent.inserirGrupoPermissao";
		public static const EXCLUIR:String = "GrupoPermissaoEvent.excluirGrupoPermissao";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function GrupoPermissaoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}