package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import br.com.infoliver.sah.configuracao.annotation.TelefoneComDDD;
import br.com.infoliver.sah.configuracao.annotation.UF;
import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Médico", genero = GeneroEntity.Masculino)
public class Medico implements Serializable {

	private static final long serialVersionUID = 1L;

	//-------------------------------
	public interface AlterarMedico {}
	public interface InserirMedico {}
	//-------------------------------

	@NotNull(message = "O código do MÉDICO deve ser nulo", groups = {AlterarMedico.class})
	private Integer sequencial;
	
	@NotNull(message = "O campo NOME é obrigatório", groups = {InserirMedico.class})
	private String nome;
	
	@NotNull(message = "O campo CPF é obrigatório", groups = {InserirMedico.class})
//	@CPF(groups = {InserirMedico.class})
	private String cpf;
	
//	@NotNull(message = "O campo RG é obrigatório", groups = {InserirMedico.class})
	@Length(message = "O campo RG deve conter no máximo 30 dígitos.", max = 30, groups = {InserirMedico.class})
	private String rg;
	
//	@NotNull(message = "O campo ORGÃO EXPEDIDOR é obrigatório", groups = {InserirMedico.class})
//	private String orgaoExpedidor;
	
//	@NotNull(message = "O campo ORGÃO EXPEDIDOR é obrigatório", groups = {InserirMedico.class})
	@Length(message = "O campo ÓRGÃO EMISSOR deve conter no máximo 04 dígitos.", max = 4, groups = {InserirMedico.class})
	private String orgaoEmissor;
	
	@UF(groups = {InserirMedico.class})
	private String ufOrgaoEmissor;
	
	private Date dataExpedicao;
	
	@TelefoneComDDD(groups = {InserirMedico.class})
	private String telefone1;
	
	@TelefoneComDDD(groups = {InserirMedico.class})
	private String telefone2;
	
	@TelefoneComDDD(groups = {InserirMedico.class})
	private String telefone3;

	@Email(message = "O campo EMAIL é inválido", groups = {InserirMedico.class})
	private String email;
	
	@NotNull(message = "O campo CNS é obrigatório", groups = {InserirMedico.class})
//	@CNS(groups = {InserirPaciente.class})
	private String cns;
	
//	@NotNull(message = "O número de atendimento por mês é obrigatório", groups = {InserirMedico.class})
//	private Integer numeroAtendimentoMes;
	
	@NotNull(message = "O campo OCUPAÇÃO é obrigatório", groups = {InserirMedico.class})
	@Valid
	private Ocupacao ocupacao;
	
	private Date dataCadastro;
	private String indicadorAtivo;
	
	private Usuario usuarioResponsavel;
	
	private List<Horario> horarios;
	private List<Licenca> licencas;
	
	public Medico() {
		
	}
	
	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}
	
	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
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

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}

//	public Integer getNumeroAtendimentoMes() {
//		return numeroAtendimentoMes;
//	}
//
//	public void setNumeroAtendimentoMes(Integer numeroAtendimentoMes) {
//		this.numeroAtendimentoMes = numeroAtendimentoMes;
//	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getIndicadorAtivo() {
		return indicadorAtivo;
	}

	public void setIndicadorAtivo(String indicadorAtivo) {
		this.indicadorAtivo = indicadorAtivo;
	}

	public Ocupacao getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(Ocupacao ocupacao) {
		this.ocupacao = ocupacao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

//	public String getOrgaoExpedidor() {
//		return orgaoExpedidor;
//	}
	
	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

//	public void setOrgaoExpedidor(String orgaoExpedidor) {
//		this.orgaoExpedidor = orgaoExpedidor;
//	}
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<Horario> getHorarios() {
		return horarios;
	}
	
	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	
	public List<Licenca> getLicencas() {
		return licencas;
	}
	
	public void setLicencas(List<Licenca> licencas) {
		this.licencas = licencas;
	}
	
}