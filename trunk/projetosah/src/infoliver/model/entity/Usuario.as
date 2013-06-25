package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Usuario",Usuario);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Usuario")]
	[Bindable]
	public class Usuario{
		public var sequencial:Object;
		public var nome:String;
		public var login:String;
		public var senha:String;
		public var novaSenha:String;
		public var dataBloqueio:Date;
		public var motivoBloqueio:String;
		public var indicadorPrimeiroAcesso:String;
		public var sequencialUsuarioCadastro:Object;
		public var dataHoraCadastro:Date;
	}
}