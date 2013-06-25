package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class PermissaoEvent extends CairngormEvent{
		public static const LISTAR_PERMISSAO_DO_GRUPO:String = "PermissaoEvent.listarPermissaoDoGrupo";
		public static const LISTAR_PERMISSAO_DIFERENTE_DO_GRUPO:String = "PermissaoEvent.listarPermissaoDiferenteDoGrupo";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function PermissaoEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}