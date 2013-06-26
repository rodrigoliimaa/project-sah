package infoliver.model.entity
{
	import flash.net.registerClassAlias;
	
	import infoliver.util.DecimalFormatter;

	registerClassAlias("br.com.infoliver.sah.negocio.entity.Fornecedor",Fornecedor);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Fornecedor")]
	[Bindable]
	public class Fornecedor
	{
		public var sequencial:Object;
		
		public var cnpj:String;
		public var dataCadastro:Date;
		public var email:String;
		public var endereco:String; //remover, adicionar os campos abaixo
		public var cep:String;
		public var uf:String;
		public var municipio:String;
		public var codigoIbgeMunicipio:String;
		public var logradouro:String;
		public var numero:String;
		public var complemento:String;
		public var bairro:String;
		public var referencia:String;
		public var indicadorAtivo:String;
		public var inscricaoEstadual:String;
		public var inscricaoMunicipal:String;
		public var nomeFantasia:String;
		public var razaoSocial:String;
		public var tecnicoResponsavel:String;
		public var telefone1:String;
		public var telefone2:String;
		public var telefone3:String;
		public var valorMaximoPedidos:Number;
		public var valorVendasRealizadas:Number;
		
		public function getValorVendasDisponivel():Number
		{
			return (valorMaximoPedidos - valorVendasRealizadas);
		}
		
		public function get valorMaximoPedidosAsString():String
		{
			return DecimalFormatter.instance.format(valorMaximoPedidos / 100);
		}
		
		public function get valorVendasRealizadasAsString():String
		{
			return DecimalFormatter.instance.format(valorVendasRealizadas);
		}
		
		public function getValorVendasDisponivelAsString():String
		{
			return DecimalFormatter.instance.format((valorMaximoPedidos - valorVendasRealizadas) / 100);
		}
	}
}