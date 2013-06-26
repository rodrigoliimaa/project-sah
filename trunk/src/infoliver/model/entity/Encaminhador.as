package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Encaminhador",Encaminhador);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Encaminhador")]
	[Bindable]
	public class Encaminhador{
		public var sequencial:Object;
		public var descricao:String;
	}
}