package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.GrupoPermissao",GrupoPermissao);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.GrupoPermissao")]
	[Bindable]
	public class GrupoPermissao{
		public var sequencialGrupo:Object;
		public var sequencialPermissao:Object;
	}
}