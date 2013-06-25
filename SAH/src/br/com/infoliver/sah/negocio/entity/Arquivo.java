package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Arquivo", genero = GeneroEntity.Masculino)
public class Arquivo implements Serializable {
	private static final long serialVersionUID = 1L;

    public static interface InserirArquivo{}
    public static interface AlterarArquivo{}
    public static interface ExcluirArquivo{}

    @Null(message="O código do ARQUIVO deve ser nulo.",groups = {InserirArquivo.class})
    @NotNull(message="O código do ARQUIVO é obrigatório.",groups = {AlterarArquivo.class,ExcluirArquivo.class})
	private Integer sequencial;
	
	@Valid
	private Paciente paciente;
	
	@Valid
	private Usuario usuario;
	
	@NotNull(message="O campo NOME é obrigatório.",groups = {InserirArquivo.class,AlterarArquivo.class})
	@NotBlank(message="O campo NOME é obrigatório.",groups = {InserirArquivo.class,AlterarArquivo.class})
	@Length(message = "O campo NOME deve conter no máximo 100 dígitos.", max = 100, groups = {InserirArquivo.class, AlterarArquivo.class})
	private String nome;

	@NotNull(message="O campo TAMANHO é obrigatório.",groups = {InserirArquivo.class,AlterarArquivo.class})
	private Integer tamanho;
	
	@NotNull(message="O campo IMAGEM DO ARQUIVO é obrigatório.",groups = {InserirArquivo.class,AlterarArquivo.class})
	private byte[] imagemArquivo;

	@NotNull(message="O campo CÓDIGO HASH é obrigatório.",groups = {InserirArquivo.class,AlterarArquivo.class})
	@NotBlank(message="O campo CÓDIGO HASH é obrigatório.",groups = {InserirArquivo.class,AlterarArquivo.class})
	@Length(message = "O campo CÓDIGO HASH deve conter no máximo 32 dígitos.", max = 32, groups = {InserirArquivo.class, AlterarArquivo.class})
	private String codigoHash;

	public Arquivo() {
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public byte[] getImagemArquivo() {
		return imagemArquivo;
	}

	public void setImagemArquivo(byte[] imagemArquivo) {
		this.imagemArquivo = imagemArquivo;
	}

	public String getCodigoHash() {
		return codigoHash;
	}

	public void setCodigoHash(String codigoHash) {
		this.codigoHash = codigoHash;
	}
}