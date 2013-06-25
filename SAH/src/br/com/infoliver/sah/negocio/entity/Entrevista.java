package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade="Entrevista",genero = GeneroEntity.Masculino)
public class Entrevista implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//-------------------------------
	public interface AlterarEntrevista {}
	public interface InserirEntrevista {}
	//-------------------------------
	@NotNull(message = "O código da Entrevista deve ser nulo", groups = {AlterarEntrevista.class})
	private Integer sequencialEntrevista;
	
	@NotNull(message = "O código da Entrevista deve ser nulo", groups={InserirEntrevista.class})
	private String nomePaciente;
	
	private String documento;
	
	private String tipoDocumento;
	
	private Integer idade;
	
	@NotNull(message = "O campo Entidade Encaminhamento é Obrigatório", groups={InserirEntrevista.class})
	private String entidadeEncaminhado;
	
	private String logradouro;
	
	private String bairroLogradouro;
	private String cidadeLogradouro;
	private String numeroLogradouro;
	private String referenciaLogradouro;
	private String objetivo;
	private String relato;
	private Date dthCadastro;
	private String nomeAssistente;
	
	public Integer getSequencialEntrevista() {
		return sequencialEntrevista;
	}
	public void setSequencialEntrevista(Integer sequencialEntrevista) {
		this.sequencialEntrevista = sequencialEntrevista;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getEntidadeEncaminhado() {
		return entidadeEncaminhado;
	}
	public void setEntidadeEncaminhado(String entidadeEncaminhado) {
		this.entidadeEncaminhado = entidadeEncaminhado;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairroLogradouro() {
		return bairroLogradouro;
	}
	public void setBairroLogradouro(String bairroLogradouro) {
		this.bairroLogradouro = bairroLogradouro;
	}
	public String getCidadeLogradouro() {
		return cidadeLogradouro;
	}
	public void setCidadeLogradouro(String cidadeLogradouro) {
		this.cidadeLogradouro = cidadeLogradouro;
	}
	public String getNumeroLogradouro() {
		return numeroLogradouro;
	}
	public void setNumeroLogradouro(String numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}
	public String getReferenciaLogradouro() {
		return referenciaLogradouro;
	}
	public void setReferenciaLogradouro(String referenciaLogradouro) {
		this.referenciaLogradouro = referenciaLogradouro;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String getRelato() {
		return relato;
	}
	public void setRelato(String relato) {
		this.relato = relato;
	}
	public Date getDthCadastro() {
		return dthCadastro;
	}
	public void setDthCadastro(Date dthCadastro) {
		this.dthCadastro = dthCadastro;
	}
	public String getNomeAssistente() {
		return nomeAssistente;
	}
	public void setNomeAssistente(String nomeAssistente) {
		this.nomeAssistente = nomeAssistente;
	}
	
	
	
	
	
}
