package infoliver.model.entity {
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Medico",Medico);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Medico")]
	[Bindable]
	public class Medico{
		public var sequencial:Object;
		public var nome:String;
		public var cpf:String;
//		public var rg:String;
//		public var orgaoExpedidor:String;
		public var rg:String;
		public var orgaoEmissor:String;
		public var ufOrgaoEmissor:String;
		public var dataExpedicao:Date;
		public var telefone1:String;
		public var telefone2:String;
		public var telefone3:String;
		public var email:String;
		public var cns:String;
//		public var numeroAtendimentoMes:Object;
		public var ocupacao:Ocupacao=new Ocupacao;
		public var dataCadastro:Date;
		public var indicadorAtivo:String;
		public var usuarioResponsavel:Usuario;
		public var horarios:ArrayCollection;
		public var licencas:ArrayCollection;
	}
}