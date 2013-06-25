package infoliver.model.entity
{
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.infoliver.sah.negocio.entity.Horario",Horario);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Horario")]
	[Bindable]
	public class Horario
	{
		public static const DOMINGO:String = "DOMINGO";
		public static const SEGUNDA:String = "SEGUNDA";
		public static const TERCA:String = "TERÇA";
		public static const QUARTA:String = "QUARTA";
		public static const QUINTA:String = "QUINTA";
		public static const SEXTA:String = "SEXTA";
		public static const SABADO:String = "SABADO";
		
		public static const DIAS_DA_SEMANA:Array = [
			DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO
		];
		
		public static const MAP_DIA_SEMANA_INT_TO_STRING:Object = {
			0:DOMINGO, 1:SEGUNDA, 2:TERCA, 3:QUARTA, 4:QUINTA, 5:SEXTA, 6:SABADO
		}
			
		public static const MAP_DIA_SEMANA_STRING_TO_INT:Object = {
			DOMINGO:0, SEGUNDA:1, TERCA:2, QUARTA:3, QUINTA:4, SEXTA:5, SABADO:6
		}
		
		public static const COMBO_BOX_DATA_PROVIDER_DIAS_DA_SEMANA:Array = [
			{label: DOMINGO, data: 0},
			{label: SEGUNDA, data: 1},
			{label: TERCA, data: 2},
			{label: QUARTA, data: 3},
			{label: QUINTA, data: 4},
			{label: SEXTA, data: 5},
			{label: SABADO, data: 6}
		];
		
		public static const TURNO_MANHA:String = "M";
		public static const TURNO_TARDE:String = "T";
		
		public static const COMBO_BOX_DATA_PROVIDER_TURNOS:Array = [
			{label: "MANHÃ", data: TURNO_MANHA},
			{label: "TARDE", data: TURNO_TARDE}
		];
		
		public var medico:Medico;
		public var diaSemana:int;
		public var turno:String;
		public var numeroMaximoAgendamentos:int;
		
		public function get getDiaSemana():String{
			return MAP_DIA_SEMANA_INT_TO_STRING[diaSemana];
		}
		
		public function setDiaSemana(value:String):void{
			diaSemana = MAP_DIA_SEMANA_STRING_TO_INT[value];
		}
		
		public function get getTurno():String{
			if (turno == TURNO_MANHA) {
				return "MANHÃ";
			} else {
				return "TARDE";
			}
		}
	}
}