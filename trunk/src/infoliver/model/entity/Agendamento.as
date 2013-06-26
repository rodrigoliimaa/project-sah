package infoliver.model.entity
{
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.infoliver.sah.negocio.entity.Agendamento",Agendamento);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Agendamento")]
	[Bindable]
	public class Agendamento
	{
		public static const SITUACAO_AGENDADO:String = "AGENDADO";
		public static const SITUACAO_ATENDIDO:String = "ATENDIDO";
		public static const SITUACAO_CANCELADO:String = "CANCELADO";
		public static const SITUACAO_FALTA:String = "FALTA";
		public static const SITUACAO_PRESENCA_CONFIRMADA:String = "PRESENCA_CONFIRMADA";
		public static const SITUACAO_REAGENDAMENTO:String = "REAGENDAMENTO";
		
		public static const LISTA_SITUACAO:Array = [
			SITUACAO_AGENDADO, SITUACAO_ATENDIDO, SITUACAO_CANCELADO,
			SITUACAO_FALTA, SITUACAO_PRESENCA_CONFIRMADA, SITUACAO_REAGENDAMENTO
		];
		
		public static const TURNO_MANHA:String = "M";
		public static const TURNO_TARDE:String = "T";
		
		public var sequencial:Object;
		public var medico:Medico;
		public var paciente:Paciente;
		public var data:Date;
		public var turno:String;
		public var situacao:String;
		public var reagendamento:Agendamento;
		public var equipeMedica:EquipeMedica;
		
		public var periodoInicial:Date;
		public var periodoFinal:Date;
		
		// campo usado somente para pesquisa (para compor um intervalo de datas para a listagem de agendamentos)
		public var dataFim:Date;
	}
}