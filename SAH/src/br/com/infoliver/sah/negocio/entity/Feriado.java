package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

//@EntityValidador(entidade = "Feriado", genero = GeneroEntity.Masculino)
public class Feriado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//-------------------------------
	public interface AlterarFeriado {}
	public interface ExcluirFeriado {}
	public interface InserirFeriado {}
	public interface ValidarFeriado {}
	//-------------------------------
	
	@NotNull(message = "O código do FERIADO nao deve ser nulo", groups = {AlterarFeriado.class, ExcluirFeriado.class})
	@Null(message = "O código do FERIADO deve ser nulo", groups = {InserirFeriado.class})
	private Integer sequencial;
	
	@NotNull(message = "O campo DESCRIÇÃO é obrigatório", groups = {ValidarFeriado.class})
	@NotBlank(message = "O campo DESCRIÇÃO é obrigatório", groups = {ValidarFeriado.class})
	private String descricao;
	
	@NotNull(message = "O campo DATA DE INÍCIO é obrigatório", groups = {ValidarFeriado.class})
	private Date dataInicio;
	
	private Date dataFim;
	
	@NotNull(message = "O campo SEMPRE NA MESMA DATA é obrigatório", groups = {ValidarFeriado.class})
	@NotBlank(message = "O campo SEMPRE NA MESMA DATA é obrigatório", groups = {InserirFeriado.class})
	private String sempreNaMesmaData;
	
	public Feriado() {

	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	public String getSempreNaMesmaData() {
		return sempreNaMesmaData;
	}
	
	public void setSempreNaMesmaData(String isSempreNaMesmaData) {
		this.sempreNaMesmaData = isSempreNaMesmaData;
	}
	
}
