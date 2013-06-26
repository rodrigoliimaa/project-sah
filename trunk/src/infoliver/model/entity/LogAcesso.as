package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.LogAcesso",LogAcesso);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.LogAcesso")]
	[Bindable]
	public class LogAcesso{
		public var sequencial:Object;
		public var sequencialUsuario:Object;
		public var nomeUsuario:String;
		public var dataInicial:Date;
		public var dataFinal:Date;
		public var dataHoraCadastro:Date;
	}
}