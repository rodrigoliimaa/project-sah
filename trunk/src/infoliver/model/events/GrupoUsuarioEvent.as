package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class GrupoUsuarioEvent extends CairngormEvent{
		public static const INSERIR:String = "GrupoUsuarioEvent.inserirGrupoUsuario";
		public static const EXCLUIR:String = "GrupoUsuarioEvent.excluirGrupoUsuario";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function GrupoUsuarioEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}