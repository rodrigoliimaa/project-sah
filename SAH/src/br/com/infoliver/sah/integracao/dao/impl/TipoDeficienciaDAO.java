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

import br.com.infoliver.sah.integracao.dao.ITipoDeficienciaDAO;
import br.com.infoliver.sah.negocio.entity.TipoDeficiencia;

@Repository("tipoDeficienciaDAO")
@Transactional(readOnly=true)
public class TipoDeficienciaDAO extends DAOBase implements ITipoDeficienciaDAO {

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TipoDeficiencia> listar() {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",2);
		params.addValue("p_in_seq_encaminhamento_externo",null);
		params.addValue("p_in_txt_descricao",null);
		params.addValue("p_in_dth_cadastro",null);
		params.addValue("p_out_cursor",Types.OTHER);
		Map out = callProcedureUsingOutResultSet("admsah001", null, "sp_tipo_deficiencia", null,
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<TipoDeficiencia>(){
							public TipoDeficiencia mapRow(ResultSet rs, int rowNum) throws SQLException {
								TipoDeficiencia tipoDeficiencia = new TipoDeficiencia();
								tipoDeficiencia.setSequencial(rs.getInt("seq_tipo_deficiencia"));
								tipoDeficiencia.setDescricao(rs.getString("txt_descricao"));
								tipoDeficiencia.setDataHoraCadastro(rs.getDate("dth_cadastro"));
								return tipoDeficiencia;
							}
						}));
		
		return (List<TipoDeficiencia>) out.get("p_out_cursor");
	}

	@Override
	@Transactional(readOnly=false,propagation = Propagation.REQUIRED)
	public void inserir(TipoDeficiencia tipoDeficiencia) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_encaminhamento_externo",null);
		params.addValue("p_in_txt_descricao",tipoDeficiencia.getDescricao());
		params.addValue("p_in_dth_cadastro",null);
		params.addValue("p_out_cursor",Types.OTHER);
		//----------------------------------------------------------	
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_tipo_deficiencia",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhamento_externo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),
				new SqlParameter("p_in_dth_cadastro",Types.DATE));	
				new SqlOutParameter("p_out_cursor", Types.INTEGER);
		
	}
	
}
