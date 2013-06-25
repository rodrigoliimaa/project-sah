package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.integracao.dao.IMotivoDAO;
import br.com.infoliver.sah.negocio.entity.Motivo;

@Repository("motivoDAO")
@Transactional(readOnly=true)
public class MotivoDAO extends DAOBase implements IMotivoDAO {

	@Override
	public List<Motivo> listar() {
		String sql = "select * from admsah001.sah_motivo" +
				" order by txt_descricao asc";
		
		return (List<Motivo>) jdbcTemplate.query(sql, new ParameterizedRowMapper<Motivo>() {
			@Override
			public Motivo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Motivo motivo = new Motivo();
				motivo.setSequencial(rs.getInt("seq_motivo"));
				motivo.setDescricao(rs.getString("txt_descricao"));
				return motivo;
			}
		});
	}

	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(Motivo motivo) {
		String sql = "update admsah001.sah_motivo set txt_descricao = '" +  motivo.getDescricao() + "'" +
				" where seq_motivo = " + motivo.getSequencial();
		
		jdbcTemplate.update(sql);
	}

	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(Motivo motivo) {
		String sql = "delete from admsah001.sah_motivo" +
				" where seq_motivo = " + motivo.getSequencial();
		
		jdbcTemplate.update(sql);
	}

	@Override	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void inserir(Motivo motivo) {
		String sql = "insert into admsah001.sah_motivo (txt_descricao)" +
				" values('" +  motivo.getDescricao() + "')";
		
		jdbcTemplate.update(sql);
	}

	@Override
	public Motivo recuperarPorDescricao(Motivo motivo) {
		String sql = "select * from admsah001.sah_motivo" +
				" where txt_descricao = '" + motivo.getDescricao() + "'";
		
		List<Motivo> lista = (List<Motivo>) jdbcTemplate.query(sql, new ParameterizedRowMapper<Motivo>() {
			@Override
			public Motivo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Motivo motivo = new Motivo();
				motivo.setSequencial(rs.getInt("seq_motivo"));
				motivo.setDescricao(rs.getString("txt_descricao"));
				return motivo;
			}
		});
		
		if (lista != null && lista.size() > 0) {
			return lista.get(0);
		}
		
		return null;
	}
}
