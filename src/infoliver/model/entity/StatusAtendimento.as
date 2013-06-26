package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.StatusAtendimento",StatusAtendimento);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.StatusAtendimento")]
	[Bindable]
	public class StatusAtendimento{
		public var sequencial:Object;
		public var descricao:String;
	}
}