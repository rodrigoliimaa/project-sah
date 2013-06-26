package infoliver.model.vo{
	import flash.net.registerClassAlias;
	import infoliver.model.entity.Medico;
	import infoliver.model.entity.Paciente;
	import infoliver.model.entity.ProcedimentoMedico;
	
	registerClassAlias("br.com.infoliver.sah.negocio.vo.RelatorioProcedimentoMedicoVO",RelatorioProcedimentoMedicoVO);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.vo.RelatorioProcedimentoMedicoVO")]
	[Bindable]
	public class RelatorioProcedimentoMedicoVO{
		public var paciente:Paciente=new Paciente;
		public var procedimentoMedico:ProcedimentoMedico=new ProcedimentoMedico;
		public var medico:Medico=new Medico;
	}
}