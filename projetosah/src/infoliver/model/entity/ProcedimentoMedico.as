package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.ProcedimentoMedico",ProcedimentoMedico);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.ProcedimentoMedico")]
	[Bindable]
	public class ProcedimentoMedico{
		public var sequencial:Object;
		public var tipoProcedimento:String;
		public var quantidadeLinha:uint;
		public var codigoPrincipal:String;
		public var descricaoPrincipal:String;
		public var codigoSecundario:String;
		public var descricaoSecundario:String;
		public var sequencialProcedimentoPai:Object;
	}
}