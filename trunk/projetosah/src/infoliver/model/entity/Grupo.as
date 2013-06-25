package infoliver.model.entity{
	import flash.net.registerClassAlias;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Grupo",Grupo);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Grupo")]
	[Bindable]
	public class Grupo{
		public var sequencial:Object;
		public var descricao:String;

		public var isSelecionado:Boolean=false;
	}
}