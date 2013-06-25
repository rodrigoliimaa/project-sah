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

import br.com.infoliver.sah.integracao.dao.ITipoResponsavelDAO;
import br.com.infoliver.sah.negocio.entity.TipoResponsavel;

@Repository("tipoResponsavelDAO")
@Transactional(readOnly=true)
public class TipoResponsavelDAO extends DAOBase implements ITipoResponsavelDAO {

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TipoResponsavel> listar() {
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_tipo_responsavel",null,
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<TipoResponsavel>() {
							public TipoResponsavel mapRow(ResultSet rs, int rowNum)throws SQLException {
								TipoResponsavel tipoResponsavel = new TipoResponsavel();
								tipoResponsavel.setSequencial(rs.getInt("seq_tipo_responsavel"));
								tipoResponsavel.setDescricao(rs.getString("txt_descricao"));
								return tipoResponsavel;
							}
						}));
	
		return (List<TipoResponsavel>) out.get("p_out_cursor");
	}
}