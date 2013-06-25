package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Raca",Raca);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Raca")]
	[Bindable]
	public class Raca{
		public var sequencial:Object;
		public var descricao:String;
	}
}