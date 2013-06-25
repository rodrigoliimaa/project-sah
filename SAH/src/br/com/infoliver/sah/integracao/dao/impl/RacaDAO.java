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

import br.com.infoliver.sah.integracao.dao.IRacaDAO;
import br.com.infoliver.sah.negocio.entity.Raca;

@Repository("racaDAO")
@Transactional(readOnly=true)
public class RacaDAO extends DAOBase implements IRacaDAO {

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Raca> listar() {
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_raca",null,
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Raca>() {
							public Raca mapRow(ResultSet rs, int rowNum)throws SQLException {
								Raca raca = new Raca();
								raca.setSequencial(rs.getInt("seq_raca"));
								raca.setDescricao(rs.getString("txt_descricao"));
								return raca;
							}
						}));
	
		return (List<Raca>) out.get("p_out_cursor");
	}
}