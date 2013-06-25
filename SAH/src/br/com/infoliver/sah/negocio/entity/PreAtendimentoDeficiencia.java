package br.com.infoliver.sah.negocio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.Valid;

import br.com.infoliver.sah.configuracao.validacao.EntityValidador;
import br.com.infoliver.sah.configuracao.validacao.GeneroEntity;

@EntityValidador(entidade = "Pre Atendimento Deficiencia", genero = GeneroEntity.Feminino)
public class PreAtendimentoDeficiencia implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static interface InserirPreAtendimentoDeficiencia{}
    public static interface AlterarPreAtendimentoDeficiencia{}
    public static interface ExcluirPreAtendimentoDeficiencia{}
	
    @Valid
    private PreAtendimento preAtendimento;
    
    @Valid
    private TipoDeficiencia tipoDeficiencia;
    
    private Date dataHoraCadastro;

	public PreAtendimento getPreAtendimento() {
		return preAtendimento;
	}
	
	public void setPreAtendimento(PreAtendimento preAtendimento) {
		this.preAtendimento = preAtendimento;
	}

	public TipoDeficiencia getTipoDeficiencia() {
		return tipoDeficiencia;
	}

	public void setTipoDeficiencia(TipoDeficiencia tipoDeficiencia) {
		this.tipoDeficiencia = tipoDeficiencia;
	}

	public Date getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	public void setDataHoraCadastro(Date dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}
    
}
