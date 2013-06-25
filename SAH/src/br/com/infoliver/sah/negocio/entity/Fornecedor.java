package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.annotation.TelefoneComDDD;
import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Fornecedor", genero = GeneroEntity.Masculino)
public class Fornecedor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//-------------------------------
	public interface AlterarFornecedor {}
	public interface InserirFornecedor {}
	//-------------------------------
	
	@NotNull(message = "O código do FORNECEDOR não deve ser nulo", groups = {AlterarFornecedor.class})
	private Integer sequencial;
	
	@NotNull(message = "O CNPJ do FORNECEDOR não deve ser nulo", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String cnpj;
	
//	@NotNull(message = "A DATA DE CADASTRO do FORNECEDOR não deve ser nula", groups = {InserirFornecedor.class})
	private Date dataCadastro;
	
//	@NotNull(message = "O ENDEREÇO do FORNECEDOR não deve ser nulo", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String endereco;
	
	@NotNull(message = "O CEP do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	@NotBlank(message = "O CEP do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String cep;
	
	@NotNull(message = "A UF do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	@NotBlank(message = "A UF do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String uf;
	
	@NotNull(message = "O MUNICÍPIO do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	@NotBlank(message = "O MUNICÍPIO do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String municipio;
	
	@NotNull(message = "O CÓDIGO DO IBGE DO MUNÍCIPIO do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	@NotBlank(message = "O CÓDIGO DO IBGE DO MUNICÍPIO do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String codigoIbgeMunicipio;
	
	@NotNull(message = "O LOGRADOURO do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	@NotBlank(message = "O LOGRADOURO do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String logradouro;
	
	@NotNull(message = "O NÚMERO DO LOGRADOURO do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	@NotBlank(message = "O  NÚMERO DO LOGRADOURO do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String numero;
	
	private String complemento;
	
	@NotNull(message = "O BAIRRO do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	@NotBlank(message = "O BAIRRO do FORNECEDOR é obrigatório", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String bairro;
	
	private String referencia;
	
//	@NotNull(message = "O EMAIL do FORNECEDOR não deve ser nulo", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	@Email(message = "O campo EMAIL é inválido", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String email;
	
	private String indicadorAtivo;
	
	//@NotNull(message = "A INSCRIÇÃO ESTADUAL do FORNECEDOR não deve ser nula", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String inscricaoEstadual;
	
	//@NotNull(message = "A INSCRIÇÃO MUNICIPAL do FORNECEDOR não deve ser nula", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String inscricaoMunicipal;
	
	@NotNull(message = "O NOME DE FANTASIA do FORNECEDOR não deve ser nulo", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String nomeFantasia;
	
	@NotNull(message = "A RAZÃO SOCIAL do FORNECEDOR não deve ser nulo", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String razaoSocial;
	
	@NotNull(message = "O TECNICO RESPONSÁVEL do FORNECEDOR não deve ser nulo", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String tecnicoResponsavel;
	
	@TelefoneComDDD(groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String telefone1;
	
	@TelefoneComDDD(groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String telefone2;
	
	@TelefoneComDDD(groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private String telefone3;
	
	@NotNull(message = "O VALOR MÁXIMO DE PEDIDOS do FORNECEDOR não deve ser nulo", groups = {AlterarFornecedor.class, InserirFornecedor.class})
	private BigDecimal valorMaximoPedidos;
	
	private BigDecimal valorVendasRealizadas; // não persistente
	
	public Fornecedor() {

	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getUf() {
		return uf;
	}
	
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public String getMunicipio() {
		return municipio;
	}
	
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	public String getCodigoIbgeMunicipio() {
		return codigoIbgeMunicipio;
	}
	
	public void setCodigoIbgeMunicipio(String codigoIbgeMunicipio) {
		this.codigoIbgeMunicipio = codigoIbgeMunicipio;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getReferencia() {
		return referencia;
	}
	
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public String getIndicadorAtivo() {
		return indicadorAtivo;
	}
	
	public void setIndicadorAtivo(String indicadorAtivo) {
		this.indicadorAtivo = indicadorAtivo;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getTecnicoResponsavel() {
		return tecnicoResponsavel;
	}

	public void setTecnicoResponsavel(String tecnicoResponsavel) {
		this.tecnicoResponsavel = tecnicoResponsavel;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public BigDecimal getValorMaximoPedidos() {
		return valorMaximoPedidos;
	}
	
	public void setValorMaximoPedidos(BigDecimal valorMaximoPedidos) {
		this.valorMaximoPedidos = valorMaximoPedidos;
	}
	
	public BigDecimal getValorVendasRealizadas() {
		return valorVendasRealizadas;
	}
	
	public void setValorVendasRealizadas(BigDecimal valorVendasRealizadas) {
		this.valorVendasRealizadas = valorVendasRealizadas;
	}
	
	// valor ainda disponível para vendas, no mês corrente
	public BigDecimal getValorVendasDisponivel() {
		return (this.valorMaximoPedidos.subtract( this.valorVendasRealizadas ));
	}
	
}
