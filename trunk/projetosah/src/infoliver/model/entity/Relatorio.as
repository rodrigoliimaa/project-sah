package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Relatorio",Relatorio);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Relatorio")]
	[Bindable]
	public class Relatorio{
		public var sequencial:Object;
		public var nome:String;
		public var nomeArquivo:String;
		public var indicadorProcedimento:String;
	}
}