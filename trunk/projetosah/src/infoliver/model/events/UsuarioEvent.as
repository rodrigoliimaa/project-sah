package infoliver.model.events{
	import leonelcasado.com.adobe.cairngorm.control.CairngormEvent;
	
	public class UsuarioEvent extends CairngormEvent{
		public static const ACESSAR:String = "UsuarioEvent.acessarSistema";
		public static const INSERIR:String = "UsuarioEvent.inserirUsuario";
		public static const ALTERAR:String = "UsuarioEvent.alterarUsuario";
		public static const EXCLUIR:String = "UsuarioEvent.excluirUsuario";
		public static const LISTAR_PAGINADO:String = "UsuarioEvent.listarUsuarioPaginado";
		public static const LISTAR:String = "UsuarioEvent.listarUsuario";
		public static const LISTAR_USUARIO_DO_GRUPO:String = "UsuarioEvent.listarUsuarioDoGrupo";
		public static const LISTAR_USUARIO_DIFERENTE_DO_GRUPO:String = "UsuarioEvent.listarUsuarioDiferenteDoGrupo";
		public static const OBTER:String = "UsuarioEvent.obterUsuario";
		//**********************************************************************************
		private var idDestination:String="facade";
		//**********************************************************************************

		public function UsuarioEvent(pEvent:String,pTelaResult:Object,...args){
			super(pEvent,idDestination,pTelaResult,false,false,false,args);
		}
	}	
}