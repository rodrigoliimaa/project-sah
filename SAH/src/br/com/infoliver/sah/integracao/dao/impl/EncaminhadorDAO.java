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

import br.com.infoliver.sah.integracao.dao.IEncaminhadorDAO;
import br.com.infoliver.sah.negocio.entity.Encaminhador;

@Repository("encaminhadorDAO")
@Transactional(readOnly=true)
public class EncaminhadorDAO extends DAOBase implements IEncaminhadorDAO {

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Encaminhador> listar() {
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_encaminhador",null,
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Encaminhador>() {
							public Encaminhador mapRow(ResultSet rs, int rowNum)throws SQLException {
								Encaminhador encaminhador = new Encaminhador();
								encaminhador.setSequencial(rs.getInt("seq_encaminhador"));
								encaminhador.setDescricao(rs.getString("txt_descricao"));
								return encaminhador;
							}
						}));
	
		return (List<Encaminhador>) out.get("p_out_cursor");
	}
}