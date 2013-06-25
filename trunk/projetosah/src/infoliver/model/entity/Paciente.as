package infoliver.model.entity{
	import flash.net.registerClassAlias;
	import flash.utils.ByteArray;
	
	import mx.collections.ArrayCollection;
	
	registerClassAlias("br.com.infoliver.sah.negocio.entity.Paciente",Paciente);
	[RemoteClass(alias="br.com.infoliver.sah.negocio.entity.Paciente")]
	[Bindable]
	public class Paciente{
		public var sequencial:Object;
		public var sequencialPacienteAnterior:Object;
		public var raca:Raca=new Raca;
		public var ocupacao:Ocupacao=new Ocupacao;
		public var escolaridade:Escolaridade=new Escolaridade;
		public var encaminhador:Encaminhador=new Encaminhador;
		public var tipoResponsavel:TipoResponsavel=new TipoResponsavel;
		public var usuarioCadastro:Usuario=new Usuario;
		public var nome:String;
		public var estadoCivil:String;
		public var sexo:String;
		public var dataNascimento:Date;
		public var tipoSanguineo:String;
		public var ufNaturalidade:String;
		public var municipioNaturalidade:String;
		public var nomeConjuge:String;
		public var nomePai:String;
		public var nomeMae:String;
		public var telefoneMae:String;
		public var cep:String;
		public var logradouro:String;
		public var numeroLogradouro:String;
		public var complementoLogradouro:String;
		public var referenciaLogradouro:String;
		public var bairroLogradouro:String;
		public var municipioLogradouro:String;
		public var codigoIbgeMunicipioLogradouro:String;
		public var ufLogradouro:String;
		public var telefone01:String;
		public var telefone02:String;
		public var telefone03:String;
		public var enderecoEletronico:String;
		public var rg:String;
		public var orgaoEmissor:String;
		public var ufOrgaoEmissor:String;
		public var dataExpedicao:Date;
		public var cpf:String;
		public var cns:String;
		public var nomeCartorio:String;
		public var numeroRegistro:Object;
		public var codigoLivro:String;
		public var numeroFolhaLivro:Object;
		public var dataRegistroLivro:Date;
		public var indicadorAssociado:String;
		public var indicadorEstudo:String;
		public var localEstudo:String;
		public var indicadorTrabalho:String;
		public var localTrabalho:String;
		public var nomeResponsavel:String;
		public var rgResponsavel:String;
		public var cpfResponsavel:String;
		public var telefoneResponsavel:String;
		public var dataHoraCadastro:Date;
		public var indicadorImagemPaciente:String;
		public var imagemPaciente:ByteArray;
		public var indicadorImagemDigital:String;
		public var imagemDigital:ByteArray;
		public var quantidadeArquivo:uint;
		
		public var arquivos:ArrayCollection=new ArrayCollection;
		
	}
}