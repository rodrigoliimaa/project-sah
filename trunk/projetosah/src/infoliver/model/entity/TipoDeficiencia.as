package infoliver.model.entity
{
	import flash.net.registerClassAlias;
	
	

	registerClassAlias("br.com.infoliver.sah.negocio.entity.TipoDeficiencia",TipoDeficiencia);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.TipoDeficiencia")]
	[Bindable]
	public class TipoDeficiencia
	{
		public var sequencial:Object;
		public var descricao:String;
		public var dataHoraCadastro:Date;
	}
}