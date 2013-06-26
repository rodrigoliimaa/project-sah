package infoliver.model.entity
{
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.infoliver.sah.negocio.entity.EncaminhamentoExterno",EncaminhamentoExterno);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.EncaminhamentoExterno")]
	public class EncaminhamentoExterno
	{
		public var sequencial:Object;
		public var descricao:String;
		public var dataHoraCadastro:Date;
		public var logradouro:String;
		public var numero:String;
		public var bairro:String;
		public var cidade:String;
		public var referencia:String;
		public var responsavel:String;
		
	}
}