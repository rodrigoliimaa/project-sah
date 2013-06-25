package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.annotation.CEP;
import br.com.infoliver.sah.configuracao.annotation.CNS;
import br.com.infoliver.sah.configuracao.annotation.CPF;
import br.com.infoliver.sah.configuracao.annotation.TelefoneComDDD;
import br.com.infoliver.sah.configuracao.annotation.UF;
import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;
import br.com.infoliver.sah.negocio.entity.Arquivo.InserirArquivo;

@EntityValidador(entidade = "Paciente", genero = GeneroEntity.Masculino)
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	//----------------------------------------
	public static interface ValidarPaciente{}
	public static interface ValidarPacienteMenor{}
	//----------------------------------------
    public static interface InserirPaciente{}
    public static interface AlterarPaciente{}
    public static interface ExcluirPaciente{}
    //----------------------------------------
    
    
    @Null(message="O código do PACIENTE deve ser nulo.",groups = {InserirPaciente.class})
    @NotNull(message="O código do PACIENTE é obrigatório.",groups = {InserirArquivo.class,AlterarPaciente.class,ExcluirPaciente.class})
	private Integer sequencial;
	
	private Integer sequencialPacienteAnterior;
	
	@Valid
	private Raca raca;
	
	private Ocupacao ocupacao;

	@Valid
	private Escolaridade escolaridade;
	
	private Encaminhador encaminhador;
	
	private TipoResponsavel tipoResponsavel;

	@Valid
	private Usuario usuarioCadastro;
	
	@NotNull(message="O campo NOME é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@NotBlank(message="O campo NOME é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@Length(message = "O campo NOME deve conter no máximo 100 dígitos.", max = 100, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String nome;

	@NotNull(message="O campo ESTADO CIVIL é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@NotBlank(message="O campo ESTADO CIVIL é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	private String estadoCivil;
	
	@NotNull(message="O campo SEXO é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@NotBlank(message="O campo SEXO é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	private String sexo;
	
	@NotNull(message="O campo DATA DE NASCIMENTO é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	private Date dataNascimento;
	
	private String tipoSanguineo;
	
	@UF(groups = {InserirPaciente.class, AlterarPaciente.class})
	private String ufNaturalidade;
	
	@Length(message = "O campo MUNICÍPIO deve conter no máximo 50 dígitos.", max = 50, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String municipioNaturalidade;
	
	@Length(message = "O campo NOME DO CÔNJUGE deve conter no máximo 100 dígitos.", max = 100, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String nomeConjuge;
	
	@Length(message = "O campo NOME DO PAI deve conter no máximo 100 dígitos.", max = 100, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String nomePai;

	@NotNull(message="O campo NOME DA MÃE é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@NotBlank(message="O campo NOME DA MÃE é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@Length(message = "O campo NOME DA MÃE deve conter no máximo 100 dígitos.", max = 100, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String nomeMae;

	@TelefoneComDDD(groups = {InserirPaciente.class, AlterarPaciente.class})
	private String telefoneMae;

	@NotNull(message="O campo CEP é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@NotBlank(message="O campo CEP é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@Length(message = "O campo CEP deve conter no máximo 08 dígitos.", max = 8, groups = {InserirPaciente.class, AlterarPaciente.class})
	@CEP(groups = {InserirPaciente.class, AlterarPaciente.class})
	private String cep;

	@NotNull(message="O campo ENDEREÇO é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@NotBlank(message="O campo ENDEREÇO é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@Length(message = "O campo ENDEREÇO deve conter no máximo 100 dígitos.", max = 100, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String logradouro;

	@NotNull(message="O campo NÚMERO é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@NotBlank(message="O campo NÚMERO é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@Length(message = "O campo NÚMERO deve conter no máximo 10 dígitos.", max = 10, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String numeroLogradouro;
	
	@Length(message = "O campo COMPLEMENTO deve conter no máximo 100 dígitos.", max = 100, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String complementoLogradouro;
	
	@Length(message = "O campo REFERÊNCIAL deve conter no máximo 100 dígitos.", max = 100, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String referenciaLogradouro;
	
	@NotNull(message="O campo BAIRRO é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@NotBlank(message="O campo BAIRRO é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@Length(message = "O campo BAIRRO deve conter no máximo 50 dígitos.", max = 50, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String bairroLogradouro;

	@NotNull(message="O campo MUNICÍPIO é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@NotBlank(message="O campo MUNICÍPIO é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@Length(message = "O campo MUNICÍPIO deve conter no máximo 50 dígitos.", max = 50, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String municipioLogradouro;

	@NotNull(message="O campo CÓDIGO IBGE é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@NotBlank(message="O campo CÓDIGO IBGE é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@Length(message = "O campo CÓDIGO IBGE deve conter no máximo 08 dígitos.", max = 8, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String codigoIbgeMunicipioLogradouro;

	@NotNull(message="O campo UF é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@NotBlank(message="O campo UF é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@UF(groups = {InserirPaciente.class, AlterarPaciente.class})
	private String ufLogradouro;
	
	@TelefoneComDDD(groups = {InserirPaciente.class, AlterarPaciente.class})
	private String telefone01;
	
	@TelefoneComDDD(groups = {InserirPaciente.class, AlterarPaciente.class})
	private String telefone02;
	
	@TelefoneComDDD(groups = {InserirPaciente.class, AlterarPaciente.class})
	private String telefone03;
	
	private String enderecoEletronico;
		
	@Length(message = "O campo RG deve conter no máximo 30 dígitos.", max = 30, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String rg;
	
	@Length(message = "O campo ÓRGÃO EMISSOR deve conter no máximo 04 dígitos.", max = 4, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String orgaoEmissor;
	
	@UF(groups = {InserirPaciente.class, AlterarPaciente.class})
	private String ufOrgaoEmissor;
	
	private Date dataExpedicao;
	
	@NotNull(message="O campo CPF é obrigatório.",groups = {ValidarPaciente.class})
	@CPF(groups = {ValidarPaciente.class})
	private String cpf;

	@NotNull(message="O campo CNS é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@NotBlank(message="O campo CNS é obrigatório.",groups = {InserirPaciente.class,AlterarPaciente.class})
	@Length(message = "O campo CNS deve conter no máximo 15 dígitos.", max = 15, groups = {InserirPaciente.class, AlterarPaciente.class})
	@CNS(groups = {InserirPaciente.class,AlterarPaciente.class})
	private String cns;
	
	@NotNull(message="O campo NOME DO CARTÓRIO é obrigatório.",groups = {ValidarPacienteMenor.class})
	@NotBlank(message="O campo NOME DO CARTÓRIO é obrigatório.",groups = {ValidarPacienteMenor.class})
	@Length(message = "O campo NOME DO CARTÓRIO deve conter no máximo 100 dígitos.", max = 100, groups = {ValidarPacienteMenor.class})
	private String nomeCartorio;
	
	@NotNull(message="O campo Nº REGISTRO é obrigatório.",groups = {ValidarPacienteMenor.class})
	@Min(message="O campo Nº REGISTRO deve ser maior que zero.",value=1,groups = {ValidarPacienteMenor.class})
	private Integer numeroRegistro;
	
	@NotNull(message="O campo CÓDIGO DO LIVRO é obrigatório.",groups = {ValidarPacienteMenor.class})
	@NotBlank(message="O campo CÓDIGO DO LIVRO é obrigatório.",groups = {ValidarPacienteMenor.class})
	@Length(message = "O campo CÓDIGO DO LIVRO deve conter no máximo 10 dígitos.", max = 10, groups = {ValidarPacienteMenor.class})
	private String codigoLivro;
	
	@NotNull(message="O campo Nº FOLHA é obrigatório.",groups = {ValidarPacienteMenor.class})
	@Min(message="O campo Nº FOLHA deve ser maior que zero.",value=1,groups = {ValidarPacienteMenor.class})
	private Integer numeroFolhaLivro;
	
	@NotNull(message="O campo DATA DE REGISTRO é obrigatório.",groups = {ValidarPacienteMenor.class})
	private Date dataRegistroLivro;

	@NotNull(message="O campo NOME DO RESPONSÁVEL é obrigatório.",groups = {ValidarPacienteMenor.class})
	@NotBlank(message="O campo NOME DO RESPONSÁVEL é obrigatório.",groups = {ValidarPacienteMenor.class})		
	@Length(message = "O campo NOME DO RESPONSÁVEL deve conter no máximo 100 dígitos.", max = 100, groups = {ValidarPacienteMenor.class})
	private String nomeResponsavel;
	
	@Length(message = "O campo RG DO RESPONSÁVEL deve conter no máximo 30 dígitos.", max = 30, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String rgResponsavel;
	
	@CPF(groups = {InserirPaciente.class, AlterarPaciente.class})
	private String cpfResponsavel;
	
	@TelefoneComDDD(groups = {InserirPaciente.class, AlterarPaciente.class})
	private String telefoneResponsavel;

	private String indicadorAssociado;
	
	private String indicadorEstudo;
	
	@Length(message = "O campo LOCAL DE ESTUDO deve conter no máximo 100 dígitos.", max = 100, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String localEstudo;
	
	@Length(message = "O campo LOCAL DE TRABALHO deve conter no máximo 100 dígitos.", max = 100, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String indicadorTrabalho;
	
	@Length(message = "O campo NOME DO RESPONSÁVEL deve conter no máximo 100 dígitos.", max = 100, groups = {InserirPaciente.class, AlterarPaciente.class})
	private String localTrabalho;
	
	private String indicadorImagemPaciente;

	private byte[] imagemPaciente;

	private String indicadorImagemDigital;

	private byte[] imagemDigital;
	
	private Date dataHoraCadastro;

	private Integer quantidadeArquivo;

	private List<Arquivo> arquivos;
	
	public Paciente() {
	}

	public Paciente(Integer sequencial) {
		this.sequencial=sequencial;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public Integer getSequencialPacienteAnterior() {
		return sequencialPacienteAnterior;
	}

	public void setSequencialPacienteAnterior(Integer sequencialPacienteAnterior) {
		this.sequencialPacienteAnterior = sequencialPacienteAnterior;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public Ocupacao getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(Ocupacao ocupacao) {
		this.ocupacao = ocupacao;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Encaminhador getEncaminhador() {
		return encaminhador;
	}

	public void setEncaminhador(Encaminhador encaminhador) {
		this.encaminhador = encaminhador;
	}

	public TipoResponsavel getTipoResponsavel() {
		return tipoResponsavel;
	}

	public void setTipoResponsavel(TipoResponsavel tipoResponsavel) {
		this.tipoResponsavel = tipoResponsavel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public String getUfNaturalidade() {
		return ufNaturalidade;
	}

	public void setUfNaturalidade(String ufNaturalidade) {
		this.ufNaturalidade = ufNaturalidade;
	}

	public String getMunicipioNaturalidade() {
		return municipioNaturalidade;
	}

	public void setMunicipioNaturalidade(String municipioNaturalidade) {
		this.municipioNaturalidade = municipioNaturalidade;
	}

	public String getNomeConjuge() {
		return nomeConjuge;
	}

	public void setNomeConjuge(String nomeConjuge) {
		this.nomeConjuge = nomeConjuge;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getTelefoneMae() {
		return telefoneMae;
	}

	public void setTelefoneMae(String telefoneMae) {
		this.telefoneMae = telefoneMae;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(String numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	public String getComplementoLogradouro() {
		return complementoLogradouro;
	}

	public void setComplementoLogradouro(String complementoLogradouro) {
		this.complementoLogradouro = complementoLogradouro;
	}

	public String getReferenciaLogradouro() {
		return referenciaLogradouro;
	}

	public void setReferenciaLogradouro(String referenciaLogradouro) {
		this.referenciaLogradouro = referenciaLogradouro;
	}

	public String getBairroLogradouro() {
		return bairroLogradouro;
	}

	public void setBairroLogradouro(String bairroLogradouro) {
		this.bairroLogradouro = bairroLogradouro;
	}

	public String getMunicipioLogradouro() {
		return municipioLogradouro;
	}

	public void setMunicipioLogradouro(String municipioLogradouro) {
		this.municipioLogradouro = municipioLogradouro;
	}

	public String getCodigoIbgeMunicipioLogradouro() {
		return codigoIbgeMunicipioLogradouro;
	}

	public void setCodigoIbgeMunicipioLogradouro(
			String codigoIbgeMunicipioLogradouro) {
		this.codigoIbgeMunicipioLogradouro = codigoIbgeMunicipioLogradouro;
	}

	public String getUfLogradouro() {
		return ufLogradouro;
	}

	public void setUfLogradouro(String ufLogradouro) {
		this.ufLogradouro = ufLogradouro;
	}
	
	public String getTelefone01() {
		return telefone01;
	}

	public void setTelefone01(String telefone01) {
		this.telefone01 = telefone01;
	}

	public String getTelefone02() {
		return telefone02;
	}

	public void setTelefone02(String telefone02) {
		this.telefone02 = telefone02;
	}

	public String getTelefone03() {
		return telefone03;
	}

	public void setTelefone03(String telefone03) {
		this.telefone03 = telefone03;
	}

	public String getEnderecoEletronico() {
		return enderecoEletronico;
	}

	public void setEnderecoEletronico(String enderecoEletronico) {
		this.enderecoEletronico = enderecoEletronico;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public String getUfOrgaoEmissor() {
		return ufOrgaoEmissor;
	}

	public void setUfOrgaoEmissor(String ufOrgaoEmissor) {
		this.ufOrgaoEmissor = ufOrgaoEmissor;
	}

	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}

	public String getNomeCartorio() {
		return nomeCartorio;
	}

	public void setNomeCartorio(String nomeCartorio) {
		this.nomeCartorio = nomeCartorio;
	}

	public Integer getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(Integer numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public String getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(String codigoLivro) {
		this.codigoLivro = codigoLivro;
	}

	public Integer getNumeroFolhaLivro() {
		return numeroFolhaLivro;
	}

	public void setNumeroFolhaLivro(Integer numeroFolhaLivro) {
		this.numeroFolhaLivro = numeroFolhaLivro;
	}

	public Date getDataRegistroLivro() {
		return dataRegistroLivro;
	}

	public void setDataRegistroLivro(Date dataRegistroLivro) {
		this.dataRegistroLivro = dataRegistroLivro;
	}

	public String getIndicadorAssociado() {
		return indicadorAssociado;
	}

	public void setIndicadorAssociado(String indicadorAssociado) {
		this.indicadorAssociado = indicadorAssociado;
	}

	public String getIndicadorEstudo() {
		return indicadorEstudo;
	}

	public void setIndicadorEstudo(String indicadorEstudo) {
		this.indicadorEstudo = indicadorEstudo;
	}

	public String getLocalEstudo() {
		return localEstudo;
	}

	public void setLocalEstudo(String localEstudo) {
		this.localEstudo = localEstudo;
	}

	public String getIndicadorTrabalho() {
		return indicadorTrabalho;
	}

	public void setIndicadorTrabalho(String indicadorTrabalho) {
		this.indicadorTrabalho = indicadorTrabalho;
	}

	public String getLocalTrabalho() {
		return localTrabalho;
	}

	public void setLocalTrabalho(String localTrabalho) {
		this.localTrabalho = localTrabalho;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public String getRgResponsavel() {
		return rgResponsavel;
	}

	public void setRgResponsavel(String rgResponsavel) {
		this.rgResponsavel = rgResponsavel;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getTelefoneResponsavel() {
		return telefoneResponsavel;
	}

	public void setTelefoneResponsavel(String telefoneResponsavel) {
		this.telefoneResponsavel = telefoneResponsavel;
	}
	
	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getIndicadorImagemPaciente() {
		return indicadorImagemPaciente;
	}

	public void setIndicadorImagemPaciente(String indicadorImagemPaciente) {
		this.indicadorImagemPaciente = indicadorImagemPaciente;
	}

	public byte[] getImagemPaciente() {
		return imagemPaciente;
	}

	public void setImagemPaciente(byte[] imagemPaciente) {
		this.imagemPaciente = imagemPaciente;
	}

	public String getIndicadorImagemDigital() {
		return indicadorImagemDigital;
	}

	public void setIndicadorImagemDigital(String indicadorImagemDigital) {
		this.indicadorImagemDigital = indicadorImagemDigital;
	}

	public byte[] getImagemDigital() {
		return imagemDigital;
	}

	public void setImagemDigital(byte[] imagemDigital) {
		this.imagemDigital = imagemDigital;
	}

	public Date getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public List<Arquivo> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}

	public Integer getQuantidadeArquivo() {
		return quantidadeArquivo;
	}

	public void setQuantidadeArquivo(Integer quantidadeArquivo) {
		this.quantidadeArquivo = quantidadeArquivo;
	}
}