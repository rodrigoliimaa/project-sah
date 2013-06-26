package infoliver.model.entity
{
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Auditivo",Auditivo);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Auditivo")]
	[Bindable]
	public class Auditivo
	{
		public var sequencial:Object;
		
		public var apacBpi:String;
		public var dataAutorizacao:Date;
		public var dataEncaminhamento:Date;
		public var dataEntrada:Date;
		public var dataEntrega:Date;
		public var dataSolicitacao:Date;
		public var dataVencimento:Date;
		public var numeroNota:String;
		public var observacao:String;
		
		public var sigtap_dt_competencia:String;
		
		public var cidPrincipal:MovimentacaoCID;
		public var cidSecundario:MovimentacaoCID;
		public var cidCausasAssociadas:MovimentacaoCID;
		public var fornecedor:Fornecedor;
		public var medico:Medico;
		public var paciente:Paciente;
		public var programa:Programa;
		
		// campos usados somente para pesquisa
		public var dataInicioSolicitacao:Date;
		public var dataFimSolicitacao:Date;
		public var situacao:String;
		public var dataInicioSituacao:Date;
		public var dataFimSituacao:Date; // entrada, encaminhado, entregue
	}
}