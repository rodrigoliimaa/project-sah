package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Parametro",Parametro);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Parametro")]
	[Bindable]
	public class Parametro{
		public var sequencial:Object;
		public var chave:String;
		public var valor:String;
	}
}