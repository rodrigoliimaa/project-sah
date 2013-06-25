package infoliver.model.entity
{
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.infoliver.sah.negocio.entity.Setor",Setor);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Setor")]
	[Bindable]
	public class Setor
	{
		public var sequencial:Object;
		public var descricao:String;
		
	}
}