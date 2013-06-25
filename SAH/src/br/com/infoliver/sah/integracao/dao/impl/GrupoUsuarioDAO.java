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
import br.com.infoliver.sah.integracao.dao.IGrupoUsuarioDAO;
import br.com.infoliver.sah.negocio.entity.GrupoUsuario;

@Repository("grupoUsuarioDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"rawtypes"})
public class GrupoUsuarioDAO extends DAOBase implements IGrupoUsuarioDAO {

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserir(GrupoUsuario grupoUsuario) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_grupo",grupoUsuario.getSequencialGrupo());
		params.addValue("p_in_seq_usuario",grupoUsuario.getSequencialUsuario());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_usuario_manter",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),	
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),	
				new SqlOutParameter("p_out_retorno", Types.INTEGER));
		
		return (Integer) out.get("p_out_retorno");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer excluir(GrupoUsuario grupoUsuario) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",2);
		params.addValue("p_in_seq_grupo",grupoUsuario.getSequencialGrupo());
		params.addValue("p_in_seq_usuario",grupoUsuario.getSequencialUsuario());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_usuario_manter",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),	
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),	
				new SqlOutParameter("p_out_retorno", Types.INTEGER));
		
		return (Integer) out.get("p_out_retorno");
	}

}
