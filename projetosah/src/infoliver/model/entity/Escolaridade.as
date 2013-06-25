package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Escolaridade",Escolaridade);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Escolaridade")]
	[Bindable]
	public class Escolaridade{
		public var sequencial:Object;
		public var descricao:String;
	}
}