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
import br.com.infoliver.sah.integracao.dao.ISetorDAO;
import br.com.infoliver.sah.negocio.entity.Grupo;
import br.com.infoliver.sah.negocio.entity.Setor;

@Repository("setorDAO")
@Transactional(readOnly=true)
public class SetorDAO extends DAOBase implements ISetorDAO {

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Setor> listar() {
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_setor",null,
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Setor>() {
							public Setor mapRow(ResultSet rs, int rowNum)throws SQLException {
								Setor setor = new Setor();
								setor.setSequencial(rs.getInt("seq_setor"));
								setor.setDescricao(rs.getString("txt_descricao"));
								return setor;
							}
						}));
	
		return (List<Setor>) out.get("p_out_cursor");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserirSetor(Setor setor) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_grupo",null);
		params.addValue("p_in_txt_descricao",setor.getDescricao());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_setor",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),	
				new SqlOutParameter("p_out_retorno", Types.INTEGER));
		
		return (Integer) out.get("p_out_retorno");
	}
}