package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Pessoa",Pessoa);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Pessoa")]
	[Bindable]
	public class Pessoa{
		public var sequencialPessoa:Object;
		public var nome:String;
		public var documento:String;
		public var sequencialPessoaCadastro:Object;
		public var dataHoraCadastroPessoa:Date;
		public var pessoaTipo:PessoaTipo;
		//public var pessoaEndereco:PessoaEndereco;
		public var pessoaTipoDocumento:PessoaTipoDocumento;
	}
}