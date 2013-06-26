package infoliver.model.entity
{
	import flash.net.registerClassAlias;
	
	import mx.formatters.DateFormatter;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Licenca",Licenca);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Licenca")]
	[Bindable]
	public class Licenca{
		public var sequencial:Object;
		public var motivo:Motivo;
		public var dataInicio:Date;
		public var dataFim:Date;
		public var observacao:String;
		public var medico:Medico;
		
		public var df:DateFormatter = new DateFormatter();
		
		public function get getDataInicio():String{
			df.formatString = "DD/MM/YYYY";
			return df.format(dataInicio);
		}
		
		public function get getDataFim():String{
			df.formatString = "DD/MM/YYYY";
			return df.format(dataFim);
		}
	}
}