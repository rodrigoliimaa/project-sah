package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import br.com.infoliver.sah.integracao.dao.ILicencaDAO;
import br.com.infoliver.sah.negocio.entity.Licenca;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.entity.Motivo;
import br.com.infoliver.sah.negocio.entity.Ocupacao;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

@Repository("licencaDAO")
@Transactional(readOnly=true)
public class LicencaDAO extends DAOBase implements ILicencaDAO {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(final Licenca licenca) {
		final String sql = "update admsah001.sah_licenca set" +
				" seq_motivo = ?," +
				" dth_inicio = ?," +
				" dth_fim = ?," +
				" txt_observacao = ?," +
				" seq_medico = ?" +
				" where seq_licenca = ?" ;
		
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(sql, new String[] {"seq_licenca"});
		            ps.setInt(1, licenca.getMotivo().getSequencial());
		            ps.setDate(2, new Date(licenca.getDataInicio().getTime()));
		            ps.setDate(3, new Date(licenca.getDataFim().getTime()));
		            ps.setString(4, licenca.getObservacao());
		            ps.setInt(5, licenca.getMedico().getSequencial());
		            ps.setInt(6, licenca.getSequencial());
		            return ps;
		        }
		    });
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(final Licenca licenca) {
		String sql = "delete from admsah001.sah_licenca" +
				" where seq_licenca = ?";
		
		try {
			jdbcTemplate.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps)
						throws SQLException {
			            ps.setInt(1, licenca.getSequencial());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Nao foi possivel excluir o registro");
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserir(final Licenca licenca) {
		final String sql = "insert into admsah001.sah_licenca" +
				" (seq_motivo, dth_inicio, dth_fim, txt_observacao, seq_medico)" +
				" values" +
				" (?, ?, ?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(sql, new String[] {"seq_licenca"});
		            ps.setInt(1, licenca.getMotivo().getSequencial());
		            ps.setDate(2, new Date(licenca.getDataInicio().getTime()));
		            ps.setDate(3, new Date(licenca.getDataFim().getTime()));
		            ps.setString(4, licenca.getObservacao());
		            ps.setInt(5, licenca.getMedico().getSequencial());
		            return ps;
		        }
		    },
		    keyHolder);
		
		return keyHolder.getKey().intValue();
	}

	@Override
	public List<Licenca> listarPaginado(PaginacaoVO licenca) {
		String sql = "select * from admsah001.sah_licenca, admsah001.sah_medico, admsah001.sah_ocupacao" +
				" where sah_licenca.seq_medico = sah_medico.seq_medico" +
				" and sah_medico.seq_ocupacao = sah_ocupacao.seq_ocupacao" +
				" " + criarSQLCondicao((Licenca) licenca.getEntidade()) +
				" order by dth_inicio asc, dth_fim asc" +
				" " + criarSQLPaginacao(licenca);
		
		return jdbcTemplate.query(sql, new LicencaMedicoOcupacaoRowMapper());
	}
	
	@Override
	public List<Licenca> listarPorMedico(Medico medico) {
		String sqlHorario = "select * from admsah001.sah_licenca where seq_medico = " + medico.getSequencial();
		
		return jdbcTemplate.query(sqlHorario, new LicencaRowMapper());
	}
	
	private String criarSQLCondicao(Licenca licenca) {
		String where = "";
		
		if (licenca != null) {
			if (licenca.getObservacao() != null) {
				where = where + " and sah_licenca.txt_observacao ilike '%" + licenca.getObservacao() + "%'";
			}
			
			if (licenca.getDataInicio() != null) {
				where = where + " and sah_licenca.dth_inicio >= '" + sdf.format(licenca.getDataInicio()) + "'";
			}
			
			if (licenca.getDataFim() != null) {
				where = where + " and sah_licenca.dth_fim <= '" + sdf.format(licenca.getDataFim()) + "'";
			}
			
			if (licenca.getMotivo() != null
					&& licenca.getMotivo().getSequencial() != null
					&& licenca.getMotivo().getSequencial() > 0) {
				where = where + " and sah_licenca.seq_motivo = " + licenca.getMotivo().getSequencial();
			}
			
			if (licenca.getMedico() != null
					&& licenca.getMedico().getSequencial() != null
					&& licenca.getMedico().getSequencial() > 0) {
				where = where + " and sah_licenca.seq_medico = " + licenca.getMedico().getSequencial();
			}
		}
		
		return where;
	}
	
	private String criarSQLPaginacao(PaginacaoVO licenca) {
		String sql = "limit " + licenca.getQuantidadePaginacao() +
				"offset " + licenca.getInicioPaginacao();
		
		return sql;
	}

	@Override
	public Integer totalRegitrosParaPaginacao(Licenca licenca) {
//		String sql = "select count(*) from admsah001.sah_licenca";
//				criarSQLCondicao((Medico) licenca.getEntidade()) +
		String sql = "select count(*) from admsah001.sah_licenca, admsah001.sah_medico, admsah001.sah_ocupacao" +
				" where sah_licenca.seq_medico = sah_medico.seq_medico" +
				" and sah_medico.seq_ocupacao = sah_ocupacao.seq_ocupacao" +
				" " + criarSQLCondicao(licenca);
		
		return jdbcTemplate.queryForInt(sql);
	}
	
	//-------------------------------------------------------------------------
	private class LicencaRowMapper implements ParameterizedRowMapper<Licenca> {
		@Override
		public Licenca mapRow(ResultSet rs, int rowNum) throws SQLException {
			Licenca licenca = new Licenca();
			
			licenca.setDataFim(rs.getDate("dth_fim"));
			licenca.setDataInicio(rs.getDate("dth_inicio"));
			licenca.setObservacao(rs.getString("txt_observacao"));
			licenca.setSequencial(rs.getInt("seq_licenca"));
			
			Motivo motivo = new Motivo();
			motivo.setSequencial(rs.getInt("seq_motivo"));
			licenca.setMotivo(motivo);
			
			return licenca;
		}
	}
	
	private class LicencaMedicoOcupacaoRowMapper implements ParameterizedRowMapper<Licenca> {
		@Override
		public Licenca mapRow(ResultSet rs, int rowNum) throws SQLException {
			Licenca licenca = new Licenca();
			
			licenca.setDataFim(rs.getDate("dth_fim"));
			licenca.setDataInicio(rs.getDate("dth_inicio"));
			licenca.setObservacao(rs.getString("txt_observacao"));
			licenca.setSequencial(rs.getInt("seq_licenca"));
			
			Motivo motivo = new Motivo();
			motivo.setSequencial(rs.getInt("seq_motivo"));
			licenca.setMotivo(motivo);
			
			Medico medico = new Medico();
			medico.setSequencial(rs.getInt("seq_medico"));
			medico.setNome(rs.getString("txt_nome"));
			medico.setCns(rs.getString("txt_cns"));
			medico.setIndicadorAtivo(rs.getString("txt_ind_ativo"));
			licenca.setMedico(medico);
			
			Ocupacao ocupacao = new Ocupacao();
			ocupacao.setCodigoOcupacao(rs.getString("num_codigo_ocupacao"));
			ocupacao.setDescricao(rs.getString("txt_descricao"));
			ocupacao.setSequencial(rs.getInt("seq_ocupacao"));
			medico.setOcupacao(ocupacao);
			
			return licenca;
		}
	}
	//-------------------------------------------------------------------------

}
