package infoliver.model.entity
{
	import flash.net.registerClassAlias;
	
	import mx.collections.ArrayCollection;

	registerClassAlias("br.com.infoliver.sah.negocio.entity.SigtapProcedimento",SigtapProcedimento);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.SigtapProcedimento")]
	[Bindable]
	public class SigtapProcedimento
	{
		public var co_procedimento:String;
		public var no_procedimento:String;
		public var tp_complexidade:String;
		public var tp_sexo:String;
		public var qt_maxima_execucao:int;
		public var qt_dias_permanencia:int;
		public var qt_pontos:int;
		public var vl_idade_minima:int;
		public var vl_idade_maxima:int;
		public var vl_sh:Number;
		public var vl_sa:Number;
		public var vl_sp:Number;
		public var co_financiamento:String;
		public var co_rubrica:String;
		public var dt_competencia:String;
		public var dt_procedimento:String;
		
		public var sigtapCIDs:ArrayCollection;
		public var sigtapOcupacoes:ArrayCollection;
	}
}