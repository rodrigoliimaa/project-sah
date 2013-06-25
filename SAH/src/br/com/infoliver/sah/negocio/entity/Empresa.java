package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.annotation.CNPJ;
import br.com.infoliver.sah.configuracao.annotation.TelefoneComDDD;
import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Empresa", genero = GeneroEntity.Feminino)
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

    public static interface InserirEmpresa{}
    public static interface AlterarEmpresa{}
    public static interface ExcluirEmpresa{}

    @Null(message="O código da EMPRESA deve ser nulo.",groups = {InserirEmpresa.class})
    @NotNull(message="O código da EMPRESA é obrigatório.",groups = {AlterarEmpresa.class,ExcluirEmpresa.class})
	private Integer sequencial;
    
    @NotNull(message="O campo RAZÃO SOCIAL é obrigatório.",groups = {InserirEmpresa.class,AlterarEmpresa.class})
    @NotBlank(message="O campo RAZÃO SOCIAL é obrigatório.",groups = {InserirEmpresa.class,AlterarEmpresa.class})
    @Length(message="O campo RAZÃO SOCIAL deve conter no máximo 100 dígitos.",max = 100, groups = {InserirEmpresa.class,AlterarEmpresa.class})
	private String razaoSocial;

    @Length(message="O campo NOME FANTASIA deve conter no máximo 50 dígitos.",max = 50, groups = {InserirEmpresa.class,AlterarEmpresa.class})
    private String nomeFantasia;
    
    @NotNull(message="O campo CNPJ é obrigatório.",groups = {InserirEmpresa.class,AlterarEmpresa.class})
    @NotBlank(message="O campo CNPJ é obrigatório.",groups = {InserirEmpresa.class,AlterarEmpresa.class})
    @CNPJ(groups = {InserirEmpresa.class,AlterarEmpresa.class})
    private String cnpj;
    
    @Length(message="O campo CNSS deve conter no máximo 15 dígitos.",max = 15, groups = {InserirEmpresa.class,AlterarEmpresa.class})
    private String cnss;
    
    @Length(message="O campo IE deve conter no máximo 9 dígitos.",max = 9, groups = {InserirEmpresa.class,AlterarEmpresa.class})
    private String ie;
	
    @Length(message="O campo ENDEREÇO deve conter no máximo 40 dígitos.",max = 40, groups = {InserirEmpresa.class,AlterarEmpresa.class})
    private String endereco;
	
    @TelefoneComDDD(groups = {InserirEmpresa.class, AlterarEmpresa.class})
    @Length(message="O campo TELEFONE deve conter no máximo 10 dígitos.",max = 10, groups = {InserirEmpresa.class,AlterarEmpresa.class})
	private String telefone;
	
    @Length(message="O campo NOME DO PRESIDENTE deve conter no máximo 50 dígitos.",max = 50, groups = {InserirEmpresa.class,AlterarEmpresa.class})
	private String nomePresidente;

    @Length(message="O campo RG DO PRESIDENTE deve conter no máximo 30 dígitos.",max = 30, groups = {InserirEmpresa.class,AlterarEmpresa.class})
    private String rgPresidente;

    @Length(message="O campo CPF DO PRESIDENTE deve conter no máximo 11 dígitos.",max = 11, groups = {InserirEmpresa.class,AlterarEmpresa.class})
    private String cpfPresidente;
	
	private byte[] imagemLogo;

	public Empresa() {
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCnss() {
		return cnss;
	}

	public void setCnss(String cnss) {
		this.cnss = cnss;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomePresidente() {
		return nomePresidente;
	}

	public void setNomePresidente(String nomePresidente) {
		this.nomePresidente = nomePresidente;
	}

	public String getRgPresidente() {
		return rgPresidente;
	}

	public void setRgPresidente(String rgPresidente) {
		this.rgPresidente = rgPresidente;
	}

	public String getCpfPresidente() {
		return cpfPresidente;
	}

	public void setCpfPresidente(String cpfPresidente) {
		this.cpfPresidente = cpfPresidente;
	}

	public byte[] getImagemLogo() {
		return imagemLogo;
	}

	public void setImagemLogo(byte[] imagemLogo) {
		this.imagemLogo = imagemLogo;
	}

}