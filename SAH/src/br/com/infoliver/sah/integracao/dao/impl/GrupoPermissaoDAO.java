package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.Types;
import java.util.Map;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.integracao.dao.IGrupoPermissaoDAO;
import br.com.infoliver.sah.negocio.entity.GrupoPermissao;

@Repository("grupoPermissaoDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"rawtypes"})
public class GrupoPermissaoDAO extends DAOBase implements IGrupoPermissaoDAO {

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserir(GrupoPermissao grupoPermissao) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_grupo",grupoPermissao.getSequencialGrupo());
		params.addValue("p_in_seq_permissao",grupoPermissao.getSequencialPermissao());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_permissao_manter",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),	
				new SqlParameter("p_in_seq_permissao",Types.INTEGER),	
				new SqlOutParameter("p_out_retorno", Types.INTEGER));
		
		return (Integer) out.get("p_out_retorno");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer excluir(GrupoPermissao grupoPermissao) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",2);
		params.addValue("p_in_seq_grupo",grupoPermissao.getSequencialGrupo());
		params.addValue("p_in_seq_permissao",grupoPermissao.getSequencialPermissao());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_permissao_manter",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),	
				new SqlParameter("p_in_seq_permissao",Types.INTEGER),	
				new SqlOutParameter("p_out_retorno", Types.INTEGER));
		
		return (Integer) out.get("p_out_retorno");
	}

}