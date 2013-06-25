package infoliver.model.entity
{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Programa",Programa);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Programa")]
	[Bindable]
	public class Programa
	{
		public var sequencial:Object;
		public var descricao:String;
	}
}