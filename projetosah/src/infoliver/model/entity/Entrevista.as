package infoliver.model.entity
{
	import flash.net.registerClassAlias;

	registerClassAlias("br.com.infoliver.sah.negocio.entity.Entrevista",Entrevista);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Entrevista")]
	[Bindable]
	public class Entrevista
	{
		public var sequencialEntrevista:Object;		
		public var nomePaciente:String;			
		public var documento:String;			
		public var tipoDocumento:String;			
		public var idade;			
		public var entidadeEncaminhado:String;				
		public var logradouro:String;				
		public var bairroLogradouro:String;
		public var cidadeLogradouro:String;
		public var numeroLogradouro:String;
		public var referenciaLogradouro:String;
		public var objetivo:String;
		public var relato:String;
		public var dthCadastro:String;
		public var nomeAssistente:String;
	}
}