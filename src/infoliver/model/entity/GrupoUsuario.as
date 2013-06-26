package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.GrupoUsuario",GrupoUsuario);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.GrupoUsuario")]
	[Bindable]
	public class GrupoUsuario{
		public var sequencialGrupo:Object;
		public var sequencialUsuario:Object;
	}
}