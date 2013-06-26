package infoliver.model.vo{
	import flash.net.registerClassAlias;
	import infoliver.model.entity.Empresa;
	import infoliver.model.entity.Usuario;
	import mx.collections.ArrayCollection;
	
	registerClassAlias("br.com.infoliver.sah.negocio.vo.LoginVO",LoginVO);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.vo.LoginVO")]
	[Bindable]
	public class LoginVO{
		public var empresa:Empresa=new Empresa;
		public var usuario:Usuario=new Usuario;
		public var parametro:ArrayCollection;
		public var permissao:ArrayCollection;
	}
}