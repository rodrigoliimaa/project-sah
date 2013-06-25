package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.integracao.dao.IEquipamentoDAO;
import br.com.infoliver.sah.negocio.entity.Equipamento;

@Repository("equipamentoDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes"})
public class EquipamentoDAO extends DAOBase implements IEquipamentoDAO {

	@Override
	public List<Equipamento> listar() {
		String sql = "select * from admsah001.sah_equipamento" +
				" order by txt_descricao asc";
		
		return jdbcTemplate.query(sql, new EquipamentoRowMapper());
	}
	
	private class EquipamentoRowMapper implements ParameterizedRowMapper<Equipamento> {
		@Override
		public Equipamento mapRow(ResultSet rs, int rowNum) throws SQLException {
			Equipamento equipamento = new Equipamento();
			equipamento.setSequencial(rs.getInt("seq_equipamento"));
			equipamento.setDescricao(rs.getString("txt_descricao"));
			return equipamento;
		}
	}

}
