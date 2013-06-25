package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;
import br.com.infoliver.sah.negocio.entity.Arquivo.InserirArquivo;
import br.com.infoliver.sah.negocio.entity.Paciente.AlterarPaciente;
import br.com.infoliver.sah.negocio.entity.Paciente.InserirPaciente;

@EntityValidador(entidade = "Usuário", genero = GeneroEntity.Masculino)
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	public static interface LogarUsuario{}
    public static interface AltearSenhaUsuario{}
    public static interface InserirUsuario{}
    public static interface AlterarUsuario{}
    public static interface ExcluirUsuario{}

	@Null(message="O código do USUÁRIO deve ser nulo.",groups = {InserirUsuario.class})
	@NotNull(message="O código do USUÁRIO é obrigatório.",groups = {InserirArquivo.class,AlterarUsuario.class,ExcluirUsuario.class,InserirPaciente.class,AlterarPaciente.class})
    private Integer sequencial;

    @NotNull(message="O campo NOME é obrigatório.",groups = {InserirUsuario.class,AlterarUsuario.class})
    @NotBlank(message="O campo NOME é obrigatório.",groups = {InserirUsuario.class,AlterarUsuario.class})
	@Length(message = "O campo NOME deve conter no máximo 100 dígitos.", max = 100, groups = {InserirUsuario.class, AlterarUsuario.class})
    private String nome;

    @NotNull(message="O campo LOGIN é obrigatório.",groups = {LogarUsuario.class,InserirUsuario.class,AlterarUsuario.class})
    @NotBlank(message="O campo LOGIN é obrigatório.",groups = {LogarUsuario.class,InserirUsuario.class,AlterarUsuario.class})
	@Length(message = "O campo LOGIN deve conter no máximo 10 dígitos.", max = 10, groups = {InserirUsuario.class, AlterarUsuario.class})
    private String login;

    @NotNull(message="O campo SENHA é obrigatório.",groups = {LogarUsuario.class})
    @NotBlank(message="O campo SENHA é obrigatório.",groups = {LogarUsuario.class})
	@Length(message = "O campo SENHA deve conter no máximo 32 dígitos.", max = 32, groups = {InserirUsuario.class, AlterarUsuario.class})
    private String senha;

    @NotNull(message="O campo NOVA SENHA é obrigatório.",groups = {AlterarUsuario.class})
    @NotBlank(message="O campo NOVA SENHA é obrigatório.",groups = {AlterarUsuario.class})
    private String novaSenha;    
    
	private Date dataBloqueio;

	@Length(message = "O campo MOTIVO DO BLOQUEIO deve conter no máximo 100 dígitos.", max = 100, groups = {InserirUsuario.class, AlterarUsuario.class})
	private String motivoBloqueio;

    @NotNull(message="O campo INDICADOR DE PRIMEIRO ACESSO é obrigatório.",groups = {InserirUsuario.class,AlterarUsuario.class})
    @NotBlank(message="O campo INDICADOR DE PRIMEIRO ACESSO é obrigatório.",groups = {InserirUsuario.class,AlterarUsuario.class})
	private String indicadorPrimeiroAcesso;

	@NotNull(message="O campo USUÁRIO DO CADASTRO é obrigatório.",groups = {InserirUsuario.class,AlterarUsuario.class})
	@NotBlank(message="O campo USUÁRIO DO CADASTRO é obrigatório.",groups = {InserirUsuario.class,AlterarUsuario.class})
	private Integer sequencialUsuarioCadastro;

	private Date dataHoraCadastro;

    public Usuario() {
    }
    
    public Usuario(Integer sequencial) {
    	this.sequencial=sequencial;
    }
    
    public Usuario(String nome) {
    	this.nome=nome;
    }

    public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getIndicadorPrimeiroAcesso() {
		return indicadorPrimeiroAcesso;
	}

	public void setIndicadorPrimeiroAcesso(String indicadorPrimeiroAcesso) {
		this.indicadorPrimeiroAcesso = indicadorPrimeiroAcesso;
	}

	public Date getDataBloqueio() {
		return dataBloqueio;
	}

	public void setDataBloqueio(Date dataBloqueio) {
		this.dataBloqueio = dataBloqueio;
	}

	public String getMotivoBloqueio() {
		return motivoBloqueio;
	}

	public void setMotivoBloqueio(String motivoBloqueio) {
		this.motivoBloqueio = motivoBloqueio;
	}

	public Date getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public Integer getSequencialUsuarioCadastro() {
		return sequencialUsuarioCadastro;
	}

	public void setSequencialUsuarioCadastro(Integer sequencialUsuarioCadastro) {
		this.sequencialUsuarioCadastro = sequencialUsuarioCadastro;
	}

}