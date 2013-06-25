package infoliver.model.vo{
	import flash.net.registerClassAlias;
	import mx.collections.ArrayCollection;
	
	registerClassAlias("br.com.infoliver.sah.negocio.vo.ProcedimentoMedicoVO",ProcedimentoMedicoVO);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.vo.ProcedimentoMedicoVO")]
	[Bindable]
	public class ProcedimentoMedicoVO{
		public var relatorioProcedimentoMedicoVO:ArrayCollection=new ArrayCollection;
		public var caminhoRelatorio:String;
	}
}