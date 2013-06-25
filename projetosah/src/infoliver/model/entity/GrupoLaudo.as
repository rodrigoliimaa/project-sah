package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.GrupoLaudo",GrupoLaudo);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.GrupoLaudo")]
	[Bindable]
	public class GrupoLaudo{
		public var sequencial:Object;
		public var descricao:String;
		
		public var isSelecionado:Boolean=false;
	}
}