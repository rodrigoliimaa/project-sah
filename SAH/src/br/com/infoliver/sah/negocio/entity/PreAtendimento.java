package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade="PreAtendimento", genero = GeneroEntity.Masculino)
public class PreAtendimento implements Serializable {

	private static final long serialVersionUID = 1L;
	public static interface ValidarPreAtendimento{}
	public static interface InserirPreAtendimento{};
	public static interface AlterarPreAtendimento{};
	public static interface ExcluirPreAtendimento{};
	
	@Null(message="O campo PreAtendimento deve ser nulo.", groups = {InserirPreAtendimento.class})
	@NotNull(message="O campo PreAtendimento é obrigatorio.", groups = {AlterarPreAtendimento.class})
	private Integer sequencial;
	
	@Valid
	private Setor setor;
	
	@Valid
	private Usuario usuario;
	
	//@Valid
	private EncaminhamentoExterno encaminhamentoExterno;
	
	@Valid
	private TipoStatusAtendimento tipoStatusAtendimento;
	
	@Valid
	private Integer setorEncaminhamentoInterno;
	
	
	//private Pessoa pessoaEncaminhamentoRecebidoExterno;
	//private Setor setorEncaminhamentoRecebidoExterno;
	private Date dataEncaminhamentoRecebidoExterno;
	private String nome;
	private String tipoDocumento;
	private String documento;
	private String tipoPaciente;
	
	
	

	//@Length(message="O campo RELATO deve conter no máximo 400 digitos.", max = 400, groups = {InserirPreAtendimento.class, AlterarPreAtendimento.class})
	//@NotBlank(message="O campo RELATO é obrigatorio.",groups = {InserirPreAtendimento.class, AlterarPreAtendimento.class})
	//@NotNull(message="O campo PreAtendimento é obrigatorio.", groups = {AlterarPreAtendimento.class,InserirPreAtendimento.class,})
	private String relato;
	//private Pessoa pessoaEncaminhamentoExterno;
	private Date dataHoraCadastro;
	private String logradouro;
	private String cidade;
	private String bairro;
	private String numero;
	private String referencia;
	private String idade;
	

	public PreAtendimento() {}
	
	public Integer getSequencial() {
		return sequencial;
	}
	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
/*	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}**/
	public TipoStatusAtendimento getTipoStatusAtendimento() {
		return tipoStatusAtendimento;
	}
	public void setTipoStatusAtendimento(TipoStatusAtendimento tipoStatusAtendimento) {
		this.tipoStatusAtendimento = tipoStatusAtendimento;
	}
/*	public Pessoa getPessoaEncaminhamentoRecebidoExterno() {
		return pessoaEncaminhamentoRecebidoExterno;
	}
	public void setPessoaEncaminhamentoRecebidoExterno(
			Pessoa pessoaEncaminhamentoRecebidoExterno) {
		this.pessoaEncaminhamentoRecebidoExterno = pessoaEncaminhamentoRecebidoExterno;
	}**/
	/*public Setor getSetorEncaminhamentoRecebidoExterno() {
		return setorEncaminhamentoRecebidoExterno;
	}
	public void setSetorEncaminhamentoRecebidoExterno(
			Setor setorEncaminhamentoRecebidoExterno) {
		this.setorEncaminhamentoRecebidoExterno = setorEncaminhamentoRecebidoExterno;
	}*/
	public Date getDataEncaminhamentoRecebidoExterno() {
		return dataEncaminhamentoRecebidoExterno;
	}
	public void setDataEncaminhamentoRecebidoExterno(
			Date dataEncaminhamentoRecebidoExterno) {
		this.dataEncaminhamentoRecebidoExterno = dataEncaminhamentoRecebidoExterno;
	}
	public String getRelato() {
		return relato;
	}
	public void setRelato(String relato) {
		this.relato = relato;
	}

	public Date getDataHoraCadastro() {
		return dataHoraCadastro;
	}
	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getTipoPaciente() {
		return tipoPaciente;
	}

	public void setTipoPaciente(String tipoPaciente) {
		this.tipoPaciente = tipoPaciente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public EncaminhamentoExterno getEncaminhamentoExterno() {
		return encaminhamentoExterno;
	}

	public void setEncaminhamentoExterno(EncaminhamentoExterno encaminhamentoExterno) {
		this.encaminhamentoExterno = encaminhamentoExterno;
	}

	public Integer getSetorEncaminhamentoInterno() {
		return setorEncaminhamentoInterno;
	}

	public void setSetorEncaminhamentoInterno(Integer setorEncaminhamentoInterno) {
		this.setorEncaminhamentoInterno = setorEncaminhamentoInterno;
	}
	
	
}
