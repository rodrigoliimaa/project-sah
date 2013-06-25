package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.integracao.dao.IOcupacaoDAO;
import br.com.infoliver.sah.negocio.entity.Ocupacao;

@Repository("ocupacaoDAO")
@Transactional(readOnly=true)
public class OcupacaoDAO extends DAOBase implements IOcupacaoDAO {

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Ocupacao> listar() {
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_ocupacao",null,
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Ocupacao>() {
							public Ocupacao mapRow(ResultSet rs, int rowNum)throws SQLException {
								Ocupacao ocupacao = new Ocupacao();
								ocupacao.setSequencial(rs.getInt("seq_ocupacao"));
								ocupacao.setDescricao(rs.getString("txt_descricao"));
								ocupacao.setCodigoOcupacao(rs.getString("num_codigo_ocupacao"));
								return ocupacao;
							}
						}));
	
		return (List<Ocupacao>) out.get("p_out_cursor");
	}
	
	@Override
	public List<Ocupacao> pesquisarPorDescricao(String descricao) {
		String sql = "SELECT * FROM ADMSAH001.SAH_OCUPACAO" +
				" WHERE TXT_DESCRICAO ILIKE '" + "%" + descricao + "%" +
				"'";

		return jdbcTemplate.query(sql, new OcupacaoRowMapper());
	}
	
	private class OcupacaoRowMapper implements ParameterizedRowMapper<Ocupacao> {
		@Override
		public Ocupacao mapRow(ResultSet rs, int rowNum) throws SQLException {
			Ocupacao ocupacao = new Ocupacao();
			ocupacao.setSequencial(rs.getInt("seq_ocupacao"));
			ocupacao.setDescricao(rs.getString("txt_descricao"));
			ocupacao.setCodigoOcupacao(rs.getString("num_codigo_ocupacao"));
			return ocupacao;
		}
	}
	
	@Override
	public Ocupacao consultar(Integer sequencial) {
		String sql = "select * from admsah001.sah_ocupacao" +
				" where seq_ocupacao = " + sequencial;
		
		return jdbcTemplate.query(sql, new OcupacaoRowMapper()).get(0);
	}
	
}