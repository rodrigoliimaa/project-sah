package infoliver.model.entity
{
	import flash.net.registerClassAlias;

	
		registerClassAlias("br.com.infoliver.sah.negocio.entity.PreAtendimento",PreAtendimento);
		[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.PreAtendimento")]
		[Bindable]
		public class PreAtendimento{
			
			public var setor:Setor;
			public var usuario:Usuario;			
			public var tipoStatusAtendimento:TipoStatusAtendimento;			
			public var dataEncaminhamentoExterno:Date;
			public var dataCadastro:Date;
			public var relato:String;
			public var nome:String;
			public var tipoDocumento:String;
			public var documento:String;
			public var tipoPaciente:String;
			public var sequencial:Object;
			public var logradouro:String;
			public var cidade:String;
			public var bairro:String;
			public var numero:String;
			public var referencia:String;
			public var idade:String;
			public var encaminhamentoExterno:EncaminhamentoExterno;
			public var setorEncaminhamentoInterno:Object;
			
			
			
		}
		
		
	
}