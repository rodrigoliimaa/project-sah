package infoliver.model.entity
{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.PessoaTipoDocumento",PessoaTipoDocumento);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.PessoaTipoDocumento")]
	[Bindable]
	public class PessoaTipoDocumento
	{
		
		public var descricao:String;
		public var dataHoraCadastro:Date;
		
	}
}