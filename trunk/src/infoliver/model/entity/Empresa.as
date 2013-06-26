package infoliver.model.entity{
	import flash.net.registerClassAlias;
	import flash.utils.ByteArray;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Empresa",Empresa);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Empresa")]
	[Bindable]
	public class Empresa{
		public var sequencial:Object;
		public var razaoSocial:String;
		public var nomeFantasia:String;
		public var cnpj:String;
		public var cnss:String;
		public var ie:String;
		public var endereco:String;
		public var telefone:String;
		public var nomePresidente:String;
		public var rgPresidente:String;
		public var cpfPresidente:String;
		public var imagemLogo:ByteArray;
	}
}