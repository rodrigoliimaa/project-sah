package infoliver.model.entity {
	
	import flash.net.registerClassAlias;	
	import mx.collections.ArrayCollection;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.EquipeMedica",EquipeMedica);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.EquipeMedica")]
	[Bindable]
	public class EquipeMedica {
		public var sequencial:Object;
		public var descricao:String;
		public var medicos:ArrayCollection;
		public var horarios:ArrayCollection;
	}
}