package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.TipoResponsavel",TipoResponsavel);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.TipoResponsavel")]
	[Bindable]
	public class TipoResponsavel{
		public var sequencial:Object;
		public var descricao:String;
	}
}