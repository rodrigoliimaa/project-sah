package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;


	@EntityValidador(entidade = "Encaminhamento Externo", genero = GeneroEntity.Masculino)
	public class EncaminhamentoExterno implements Serializable {

		private static final long serialVersionUID = 1L;
		
		public static interface InserirEncaminhamentoExterno{}
		public static interface AlterarEncaminhamentoExterno{}
		public static interface ExcluirEncaminhamentoExterno{}
		
		@Null(message="O campo TIPO DEFICIÊNCIA deve ser nulo.",groups = {InserirEncaminhamentoExterno.class})
	    @NotNull(message="O campo TIPO DEFICIÊNCIA é obrigatório.",groups = {AlterarEncaminhamentoExterno.class,ExcluirEncaminhamentoExterno.class})
		private Integer sequencial;
		
		@NotNull(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirEncaminhamentoExterno.class,AlterarEncaminhamentoExterno.class})
		@NotBlank(message="O campo DESCRIÇÃO é obrigatório.",groups = {InserirEncaminhamentoExterno.class,AlterarEncaminhamentoExterno.class})
		@Length(message = "O campo DESCRIÇÃO deve conter no máximo 50 dígitos.", max = 50, groups = {InserirEncaminhamentoExterno.class, AlterarEncaminhamentoExterno.class})
		private String descricao;
		
		private Date dataHoraCadastro;
		
		private String logradouro;
		private String numero;
		private String bairro;
		private String cidade;
		private String referencia;
		private String responsavel;
		

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

		public Date getDataHoraCadastro() {
			return dataHoraCadastro;
		}

		public void setDataHoraCadastro(Date dataHoraCadastro) {
			this.dataHoraCadastro = dataHoraCadastro;
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

		public String getBairro() {
			return bairro;
		}

		public void setBairro(String bairro) {
			this.bairro = bairro;
		}

		public String getCidade() {
			return cidade;
		}

		public void setCidade(String cidade) {
			this.cidade = cidade;
		}

		public String getReferencia() {
			return referencia;
		}

		public void setReferencia(String referencia) {
			this.referencia = referencia;
		}

		public String getResponsavel() {
			return responsavel;
		}

		public void setResponsavel(String responsavel) {
			this.responsavel = responsavel;
		}


}