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

import br.com.infoliver.sah.integracao.dao.IEscolaridadeDAO;
import br.com.infoliver.sah.negocio.entity.Escolaridade;

@Repository("escolaridadeDAO")
@Transactional(readOnly=true)
public class EscolaridadeDAO extends DAOBase implements IEscolaridadeDAO {

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Escolaridade> listar() {
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_escolaridade",null,
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Escolaridade>() {
							public Escolaridade mapRow(ResultSet rs, int rowNum)throws SQLException {
								Escolaridade escolaridade = new Escolaridade();
								escolaridade.setSequencial(rs.getInt("seq_escolaridade"));
								escolaridade.setDescricao(rs.getString("txt_descricao"));
								return escolaridade;
							}
						}));
	
		return (List<Escolaridade>) out.get("p_out_cursor");
	}
}