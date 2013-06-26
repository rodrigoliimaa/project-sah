package infoliver.model.entity
{
	import flash.net.registerClassAlias;
	
	import infoliver.util.DecimalFormatter;
	
	import mx.collections.ArrayCollection;

	registerClassAlias("br.com.infoliver.sah.negocio.entity.MovimentacaoProcedimento",MovimentacaoProcedimento);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.MovimentacaoProcedimento")]
	[Bindable]
	public class MovimentacaoProcedimento
	{
		public static const LISTA_CATEGORIA:Array = ["A","B","C"];
		public static const LISTA_TIPO:Array = ["UNILATERAL","BILATERAL"];
		
		public var sequencial:Object;
		
		public var quantidade:int;
		public var categoriaTipo:String;
		
		public var sigtap_co_procedimento:String;
		public var sigtap_no_procedimento:String; // não persistir
		public var sigtap_vl_sa:Number;
		public var sigtap_vl_sh:Number;
		public var sigtap_vl_sp:Number;
		public var sigtap_qt_maxima_execucao:int;
		public var sigtap_vl_idade_minima:int;
		public var sigtap_vl_idade_maxima:int;
		public var sigtap_tp_sexo:String;
		public var sigtap_dt_competencia:String;
		public var sigtap_dt_procedimento:String;
		
		public var cid:MovimentacaoCID;
		public var equipamento:Equipamento;
		
		public function get valorTotal():Number{
			return (sigtap_vl_sa + sigtap_vl_sh + sigtap_vl_sp);
		}
		
		public function get valorTotalAsString():String{
			return DecimalFormatter.instance.format(valorTotal / 100);
		}
		
		public static function fromSigtapProcedimento(sigtapProcedimento:SigtapProcedimento):MovimentacaoProcedimento {
			var procedimento:MovimentacaoProcedimento = new MovimentacaoProcedimento();
			procedimento.sigtap_co_procedimento = sigtapProcedimento.co_procedimento;
			procedimento.sigtap_no_procedimento = sigtapProcedimento.no_procedimento;
			procedimento.sigtap_vl_sa = sigtapProcedimento.vl_sa;
			procedimento.sigtap_vl_sh = sigtapProcedimento.vl_sh;
			procedimento.sigtap_vl_sp = sigtapProcedimento.vl_sp;
			procedimento.sigtap_qt_maxima_execucao = sigtapProcedimento.qt_maxima_execucao;
			procedimento.sigtap_vl_idade_minima = sigtapProcedimento.vl_idade_minima;
			procedimento.sigtap_vl_idade_maxima = sigtapProcedimento.vl_idade_maxima;
			procedimento.sigtap_tp_sexo = sigtapProcedimento.tp_sexo;
			procedimento.sigtap_dt_competencia = sigtapProcedimento.dt_competencia;
			procedimento.sigtap_dt_procedimento = sigtapProcedimento.dt_procedimento;
			return procedimento;
		}
		
		public static function fromSigtapProcedimentos(sigtapProcedimentos:ArrayCollection):ArrayCollection {
			var procedimentos:ArrayCollection = new ArrayCollection();
			for each (var sigtapProcedimento:SigtapProcedimento in sigtapProcedimentos) {
				procedimentos.addItem(fromSigtapProcedimento(sigtapProcedimento));
			}
			return procedimentos;
		}
	}
}