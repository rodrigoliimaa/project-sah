package infoliver.model.entity
{
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.infoliver.sah.negocio.entity.Feriado",Feriado);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Feriado")]
	[Bindable]
	public class Feriado
	{
		public var sequencial:Object;
		public var descricao:String;
		public var dataInicio:Date;
		public var dataFim:Date;
		public var sempreNaMesmaData:String; // "S" ou "N"
	}
}