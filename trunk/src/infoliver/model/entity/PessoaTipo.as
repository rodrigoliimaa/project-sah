package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.PessoaTipo",PessoaTipo);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.PessoaTipo")]
	[Bindable]
	public class PessoaTipo{
		public var sequencial:Object;
		public var descricao:String;
	}
}