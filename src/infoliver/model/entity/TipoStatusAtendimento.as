package infoliver.model.entity
{
	import flash.net.registerClassAlias;	
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.TipoStatusAtendimento",TipoStatusAtendimento);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.TipoStatusAtendimento")]
	[Bindable]
	public class TipoStatusAtendimento
	{
		public var sequencial:Object;
		public var descricao:String;
		public var dataHoraCadastro:Date;
		
	}
}