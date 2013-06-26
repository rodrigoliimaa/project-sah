package infoliver.model.entity
{
	import flash.net.registerClassAlias;
	
	

	registerClassAlias("br.com.infoliver.sah.negocio.entity.SetorEncaminhamentoExterno",SetorEncaminhamentoExterno);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.SetorEncaminhamentoExterno")]
	[Bindable]
	public class SetorEncaminhamentoExterno
	{
		public var sequencial:Object;
		public var descricao:String;
		public var dataCadastro:Date;
		
	}
}