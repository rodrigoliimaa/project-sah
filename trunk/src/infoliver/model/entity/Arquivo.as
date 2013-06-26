package infoliver.model.entity{
	import flash.net.registerClassAlias;
	import flash.utils.ByteArray;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Arquivo",Arquivo);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Arquivo")]
	[Bindable]
	public class Arquivo{
		public var sequencial:Object;
		public var paciente:Paciente=new Paciente;
		public var usuario:Usuario=new Usuario;
		public var nome:String;
		public var tamanho:uint;
		public var imagemArquivo:ByteArray;
		public var codigoHash:String;
		
		public var caminhoLocalArquivo:String;
		public var isNovoArquivo:Boolean=false;
	}
}