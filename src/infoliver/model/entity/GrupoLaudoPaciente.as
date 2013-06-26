package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.GrupoLaudoPaciente",GrupoLaudoPaciente);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.GrupoLaudoPaciente")]
	[Bindable]
	public class GrupoLaudoPaciente{
		public var sequencialGrupo:Object;
		public var sequencialPaciente:Object;
	}
}