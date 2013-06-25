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

import br.com.infoliver.sah.integracao.dao.IParametroDAO;
import br.com.infoliver.sah.negocio.entity.Parametro;

@Repository("parametroDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"rawtypes"})
public class ParametroDAO extends DAOBase implements IParametroDAO {

	@Override
	public List listar() {
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_parametro_listar",null,
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Parametro>() {
							public Parametro mapRow(ResultSet rs, int rowNum)throws SQLException {
								Parametro parametro=new Parametro();
								parametro.setSequencial(rs.getInt("seq_parametro"));
								parametro.setChave(rs.getString("txt_chave"));
								parametro.setValor(rs.getString("txt_valor"));
								return parametro;
							}
						}));
		return (List) out.get("p_out_cursor");
	}
}