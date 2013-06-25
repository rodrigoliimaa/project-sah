package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.integracao.dao.IFeriadoDAO;
import br.com.infoliver.sah.negocio.entity.Feriado;

@Repository("feriadoDAO")
@Transactional(readOnly=true)
public class FeriadoDAO extends DAOBase implements IFeriadoDAO {
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(final Feriado feriado) {
		String sql = "update admsah001.sah_feriado set" +
				" txt_descricao = ?" +
				", dth_inicio = ?" +
				", dth_fim = ?" +
				", txt_sempre_na_mesma_data = ?" +
				" where seq_feriado = ?";
		
		try {
			jdbcTemplate.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps)
						throws SQLException {
			            ps.setString(1, feriado.getDescricao());
			            ps.setDate(2, new Date(feriado.getDataInicio().getTime()));
			            ps.setDate(3, new Date(feriado.getDataFim().getTime()));
			            ps.setString(4, feriado.getSempreNaMesmaData());
			            ps.setInt(5, feriado.getSequencial());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Nao foi possivel atualizar o registro");
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(final Feriado feriado) {
		String sql = "delete from admsah001.sah_feriado" +
				" where seq_feriado = ?";
		
		try {
			jdbcTemplate.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps)
						throws SQLException {
			            ps.setInt(1, feriado.getSequencial());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Nao foi possivel excluir o registro");
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserir(final Feriado feriado) {
		final String sql = "insert into admsah001.sah_feriado" +
				" (txt_descricao, dth_inicio, dth_fim, txt_sempre_na_mesma_data)" +
				" values" +
				" (?,?,?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(sql, new String[] {"seq_feriado"});
		            ps.setString(1, feriado.getDescricao());
		            ps.setDate(2, new Date(feriado.getDataInicio().getTime()));
		            ps.setDate(3, new Date(feriado.getDataFim().getTime()));
		            ps.setString(4, feriado.getSempreNaMesmaData());
		            return ps;
		        }
		    },
		    keyHolder);
		
		return keyHolder.getKey().intValue();
	}

	@Override
	public List<Feriado> listar() {
		String sql = "select * from admsah001.sah_feriado order by dth_inicio asc, dth_fim asc";
		
		return jdbcTemplate.query(sql, new FeriadoRowMapper());
	}
	
	//--------------------------------------------------------------------------
	private class FeriadoRowMapper implements ParameterizedRowMapper<Feriado> {
		@Override
		public Feriado mapRow(ResultSet rs, int rowNum) throws SQLException {
			Feriado feriado = new Feriado();
			
			feriado.setSequencial(rs.getInt("seq_feriado"));
			feriado.setDescricao(rs.getString("txt_descricao"));
			feriado.setDataInicio(rs.getDate("dth_inicio"));
			feriado.setDataFim(rs.getDate("dth_fim"));
			feriado.setSempreNaMesmaData(rs.getString("txt_sempre_na_mesma_data"));
			
			return feriado;
		}
	}
	//--------------------------------------------------------------------------

}
