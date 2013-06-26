package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Ocupacao",Ocupacao);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Ocupacao")]
	[Bindable]
	public class Ocupacao{
		public var sequencial:Object;
		public var descricao:String;
		public var codigoOcupacao:String;
	}
}