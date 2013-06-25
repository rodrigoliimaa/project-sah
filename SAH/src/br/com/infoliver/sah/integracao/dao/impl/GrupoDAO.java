package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.integracao.dao.IGrupoDAO;
import br.com.infoliver.sah.negocio.entity.Grupo;

@Repository("grupoDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes"})
public class GrupoDAO extends DAOBase implements IGrupoDAO {

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserir(Grupo grupo) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_grupo",null);
		params.addValue("p_in_txt_descricao",grupo.getDescricao());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_manter",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),	
				new SqlOutParameter("p_out_retorno", Types.INTEGER));
		
		return (Integer) out.get("p_out_retorno");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer alterar(Grupo grupo) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",2);
		params.addValue("p_in_seq_grupo",grupo.getSequencial());
		params.addValue("p_in_txt_descricao",grupo.getDescricao());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_manter",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),	
				new SqlOutParameter("p_out_retorno", Types.INTEGER));
		
		return (Integer) out.get("p_out_retorno");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer excluir(Grupo grupo) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",3);
		params.addValue("p_in_seq_grupo",grupo.getSequencial());
		params.addValue("p_in_txt_descricao",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_manter",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),	
				new SqlOutParameter("p_out_retorno", Types.INTEGER));
		
		return (Integer) out.get("p_out_retorno");
	}

	@Override
	public List<Grupo> listar() {
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_listar",null,
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Grupo>() {
							public Grupo mapRow(ResultSet rs, int rowNum)throws SQLException {
								Grupo grupo = new Grupo();
								grupo.setSequencial(rs.getInt("seq_grupo"));
								grupo.setDescricao(rs.getString("txt_descricao"));
								return grupo;
							}
						}));
	
		return (List<Grupo>) out.get("p_out_cursor");
	}
}