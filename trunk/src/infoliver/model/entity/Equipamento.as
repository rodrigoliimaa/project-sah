package infoliver.model.entity
{
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.infoliver.sah.negocio.entity.Equipamento",Equipamento);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Equipamento")]
	[Bindable]
	public class Equipamento
	{
		public var sequencial:Object;
		public var descricao:String;
	}
}