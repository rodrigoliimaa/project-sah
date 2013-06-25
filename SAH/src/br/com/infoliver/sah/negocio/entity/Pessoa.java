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
import br.com.infoliver.sah.negocio.entity.Usuario.AlterarUsuario;
import br.com.infoliver.sah.negocio.entity.Usuario.InserirUsuario;

@EntityValidador(entidade = "Pessoa", genero = GeneroEntity.Feminino)
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

    public static interface InserirPessoa{}
    public static interface AlterarPessoa{}
    public static interface ExcluirPessoa{}

    @Null(message="O campo PESSOA deve ser nulo.",groups = {InserirPessoa.class})
    @NotNull(message="O campo PESSOA é obrigatório.",groups = {AlterarPessoa.class,ExcluirPessoa.class})
	private Integer sequencialPessoa;
    
	@NotNull(message="O campo NOME é obrigatório.",
		groups = {InserirPessoa.class,AlterarPessoa.class,
		InserirUsuario.class,AlterarUsuario.class})
	@NotBlank(message="O campo NOME é obrigatório.",
		groups = {InserirPessoa.class,AlterarPessoa.class,
			InserirUsuario.class,AlterarUsuario.class})
	@Length(message = "O campo NOME deve conter no máximo 100 dígitos.", max = 100, 
		groups = {InserirPessoa.class, AlterarPessoa.class,
			InserirUsuario.class,AlterarUsuario.class})
	private String nome;

	@Length(message = "O campo DOCUMENTO deve conter no máximo 100 dígitos.", max = 100, groups = {InserirPessoa.class, AlterarPessoa.class})
	private String documento;

	@Valid
	private PessoaTipoDocumento pessoaTipoDocumento;
	
    private Integer sequencialPessoaCadastro;

	private Date dataHoraCadastroPessoa;

    private PessoaTipo pessoaTipo;
    
   
	public Pessoa() {
	}

	public Integer getSequencialPessoa() {
		return sequencialPessoa;
	}

	public void setSequencialPessoa(Integer sequencialPessoa) {
		this.sequencialPessoa = sequencialPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public PessoaTipoDocumento getPessoaTipoDocumento() {
		return pessoaTipoDocumento;
	}
	
	public void setPessoaTipoDocumento(PessoaTipoDocumento pessoaTipoDocumento) {
		this.pessoaTipoDocumento = pessoaTipoDocumento;
	}
	
	public Integer getSequencialPessoaCadastro() {
		return sequencialPessoaCadastro;
	}

	public void setSequencialPessoaCadastro(Integer sequencialPessoaCadastro) {
		this.sequencialPessoaCadastro = sequencialPessoaCadastro;
	}

	public Date getDataHoraCadastroPessoa() {
		return dataHoraCadastroPessoa;
	}

	public void setDataHoraCadastroPessoa(Date dataHoraCadastroPessoa) {
		this.dataHoraCadastroPessoa = dataHoraCadastroPessoa;
	}

	public PessoaTipo getPessoaTipo() {
		return pessoaTipo;
	}

	public void setPessoaTipo(PessoaTipo pessoaTipo) {
		this.pessoaTipo = pessoaTipo;
	}

	
}