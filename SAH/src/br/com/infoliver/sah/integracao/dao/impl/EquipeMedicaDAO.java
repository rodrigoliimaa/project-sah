package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.integracao.dao.IEquipeMedicaDAO;
import br.com.infoliver.sah.integracao.dao.IMedicoDAO;
import br.com.infoliver.sah.negocio.entity.EquipeMedica;
import br.com.infoliver.sah.negocio.entity.Horario;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;
import flex.messaging.io.ArrayCollection;
import flex.messaging.io.ArrayList;

@Repository("equipeMedicaDAO")
@Transactional(readOnly=true)
public class EquipeMedicaDAO extends DAOBase implements IEquipeMedicaDAO {

	@Autowired IMedicoDAO medicoDAO;
	
	@Override
	public void alterar(final EquipeMedica equipeMedica) {
		String sql = " update admsah001.sah_equipe_medica " +
					 " set " +
					 " descricao = ? " +
					 " where seq_equipe_medica = ? ";
		
		try {
			jdbcTemplate.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps)
						throws SQLException {
			            ps.setString(1, equipeMedica.getDescricao());
			            ps.setInt(2, equipeMedica.getSequencial());
				}
			});
			
			// deletar medicos da equipe
			jdbcTemplate.update(
				"delete from admsah001.sah_medico_equipe_medica where seq_equipe_medica = " + equipeMedica.getSequencial()
			);
			
			// deletar horarios
			jdbcTemplate.update(
				"delete from admsah001.sah_horario where seq_equipe_medica = " + equipeMedica.getSequencial()
			);
			
			// insert dos medicos da equipe
			for(final Medico medico : equipeMedica.getMedicos()) {
				 String sqlMedico = "insert into admsah001.sah_medico_equipe_medica" +
									" (seq_medico, seq_equipe_medica)" +
									" values" +
									" (?,?)";
				
				 jdbcTemplate.update(sqlMedico, new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setInt(1, medico.getSequencial());
							ps.setInt(2, equipeMedica.getSequencial());
						}
					});
			}
			
			// insert dos horarios
			for(final Horario horario : equipeMedica.getHorarios()) {
				 String sqlMedico = "insert into admsah001.sah_horario" +
									" (num_dia_semana, txt_turno, num_maximo_agendamentos, seq_equipe_medica)" +
									" values" +
									" (?,?,?,?)";
				
				 jdbcTemplate.update(sqlMedico, new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
							ps.setInt(1, horario.getDiaSemana());
							ps.setString(2, horario.getTurno());
							ps.setInt(3, horario.getNumeroMaximoAgendamentos());
							ps.setInt(4, equipeMedica.getSequencial());
						}
					});
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Nao foi possivel atualizar o registro");
		}
		
	}
	
	@Override
	public List<EquipeMedica> listarPaginado(PaginacaoVO equipeMedica) {
		
		EquipeMedica equipeMedicaVO = (EquipeMedica) equipeMedica.getEntidade();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from admsah001.sah_equipe_medica ");
		sql.append("where 1=1 ");
		
		if(equipeMedicaVO.getDescricao() != null && !equipeMedicaVO.getDescricao().equals("")) {
			sql.append("and descricao like ");
			sql.append("'%"+equipeMedicaVO.getDescricao()+"%'");
		}
		criarSQLPaginacao(equipeMedica);
		List<EquipeMedica> equipesMedica = jdbcTemplate.query(sql.toString(), new EquipeMedicaRowMapper());
		List<EquipeMedica> listaEquipeMedicaNova = new ArrayCollection();
		
		for(EquipeMedica equipeMedicas : equipesMedica) {
			sql = new StringBuffer();
			sql.append(" select * from admsah001.sah_medico_equipe_medica ");
			sql.append(" where seq_equipe_medica = ");
			sql.append(equipeMedicas.getSequencial());
			
			List<Medico> listaMedico = jdbcTemplate.query(sql.toString(), new MedicoRowMapper());
			equipeMedicas.setMedicos(listaMedico);
			
			sql = new StringBuffer();
			sql.append(" select * from admsah001.sah_horario ");
			sql.append(" where seq_equipe_medica = ");
			sql.append(equipeMedicas.getSequencial());
			
			List<Horario> listaHorarios = jdbcTemplate.query(sql.toString(), new HorarioRowMapper());
			equipeMedicas.setHorarios(listaHorarios);
			
			listaEquipeMedicaNova.add(equipeMedicas);
		}
		
		return listaEquipeMedicaNova;
	}
	
	
	
	private String criarSQLPaginacao(PaginacaoVO equipeMedica) {
		String sql = " limit " + equipeMedica.getQuantidadePaginacao() +
				" offset " + equipeMedica.getInicioPaginacao();
		
		return sql;
	}
	
	@Override
	public void excluir(final EquipeMedica equipeMedica) {
		String sqlEquipeMedica = "delete from admsah001.sah_equipe_medica" +
					 			 " where seq_equipe_medica = ?";
		
		String sqlHorarios = "delete from admsah001.sah_horarios" +
							 " where seq_equipe_medica = ?";
		
		String sqlMedicoEquipeMedica = "delete from admsah001.sah_medico_equipe_medica" +
				 			           " where seq_equipe_medica = ?";
		
		try {
			jdbcTemplate.update(sqlEquipeMedica, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps)
						throws SQLException {
			            ps.setInt(1, equipeMedica.getSequencial());
				}
			});
			
			jdbcTemplate.update(sqlHorarios, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps)
						throws SQLException {
			            ps.setInt(1, equipeMedica.getSequencial());
				}
			});
			
			jdbcTemplate.update(sqlMedicoEquipeMedica, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps)
						throws SQLException {
			            ps.setInt(1, equipeMedica.getSequencial());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Nao foi possivel excluir o registro");
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserir(final EquipeMedica equipeMedica) {
	final String sql = "insert into admsah001.sah_equipe_medica" +
	" (descricao)" +
	" values" +
	" (?)";

	KeyHolder keyHolder = new GeneratedKeyHolder();

	jdbcTemplate.update(
	new PreparedStatementCreator() {
	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	PreparedStatement ps =
	connection.prepareStatement(sql, new String[] {"seq_equipe_medica"});
	ps.setString(1, equipeMedica.getDescricao());
	return ps;
	}
	},
	keyHolder);

	final Integer sequencial = keyHolder.getKey().intValue();

	// insert dos medicos da equipe
	for(final Medico medico : equipeMedica.getMedicos()) {
	String sqlMedico = "insert into admsah001.sah_medico_equipe_medica" +
	" (seq_medico, seq_equipe_medica)" +
	" values" +
	" (?,?)";

	jdbcTemplate.update(sqlMedico, new PreparedStatementSetter() {
	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
	ps.setInt(1, medico.getSequencial());
	ps.setInt(2, sequencial);
	}
	});
	}

	// insert dos horarios
	for(final Horario horario : equipeMedica.getHorarios()) {
	String sqlMedico = "insert into admsah001.sah_horario" +
	" (num_dia_semana, txt_turno, num_maximo_agendamentos, seq_equipe_medica)" +
	" values" +
	" (?,?,?,?)";

	jdbcTemplate.update(sqlMedico, new PreparedStatementSetter() {
	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
	ps.setInt(1, horario.getDiaSemana());
	ps.setString(2, horario.getTurno());
	ps.setInt(3, horario.getNumeroMaximoAgendamentos());
	ps.setInt(4, sequencial);
	}
	});
	}

	return keyHolder.getKey().intValue();
	}
	

	@Override
	public List<EquipeMedica> listar() {
		
		StringBuffer sql = new StringBuffer(); 
		sql.append("select * from admsah001.sah_equipe_medica order by descricao asc");
		
		List<EquipeMedica> listaEquipeMedica = jdbcTemplate.query(sql.toString(), new EquipeMedicaRowMapper());
		
		List<EquipeMedica> listaEquipeMedicaNova = new ArrayList();
		
		for(EquipeMedica equipeMedica : listaEquipeMedica) {
			sql = new StringBuffer();
			sql.append(" select * from admsah001.sah_medico_equipe_medica ");
			sql.append(" where sequencial_equipe_medica = ");
			sql.append(equipeMedica.getSequencial());
			
			List<Medico> listaMedico = jdbcTemplate.query(sql.toString(), new MedicoRowMapper());
			equipeMedica.setMedicos(listaMedico);
			
			sql = new StringBuffer();
			sql.append(" select * from admsah001.sah_horario ");
			sql.append(" where sequencial_equipe_medica = ");
			sql.append(equipeMedica.getSequencial());
			
			List<Horario> listaHorarios = jdbcTemplate.query(sql.toString(), new HorarioRowMapper());
			equipeMedica.setHorarios(listaHorarios);
			
			listaEquipeMedicaNova.add(equipeMedica);
		}
		
		return listaEquipeMedica;
	}
	
	
	private class EquipeMedicaRowMapper implements ParameterizedRowMapper<EquipeMedica> {
		@Override
		public EquipeMedica mapRow(ResultSet rs, int rowNum) throws SQLException {
			EquipeMedica equipeMedica = new EquipeMedica();
			
			equipeMedica.setSequencial(rs.getInt("seq_equipe_medica"));
			equipeMedica.setDescricao(rs.getString("descricao"));
			
			return equipeMedica;
		}
	}
	
	private class MedicoRowMapper implements ParameterizedRowMapper<Medico> {
		@Override
		public Medico mapRow(ResultSet rs, int rowNum) throws SQLException {
			Medico medico = medicoDAO.consultar(rs.getInt("seq_medico"));
			return medico;
		}
	}
	
	private class HorarioRowMapper implements ParameterizedRowMapper<Horario> {
		@Override
		public Horario mapRow(ResultSet rs, int rowNum) throws SQLException {
			Horario horario = new Horario();
			
			horario.setDiaSemana(rs.getInt("num_dia_semana"));
			horario.setNumeroMaximoAgendamentos(rs.getInt("num_maximo_agendamentos"));
			horario.setTurno(rs.getString("txt_turno"));
			
			return horario;
		}
	}

	@Override
	public EquipeMedica obter(EquipeMedica equipeMedica) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from admsah001.sah_equipe_medica ");
		sql.append("where seq_equipe_medica = ");
		sql.append(equipeMedica.getSequencial());
		
		List<EquipeMedica> equipesMedica = jdbcTemplate.query(sql.toString(), new EquipeMedicaRowMapper());
		
		equipeMedica = equipesMedica.get(0);
		
		sql = new StringBuffer();
		sql.append(" select * from admsah001.sah_medico_equipe_medica ");
		sql.append(" where seq_equipe_medica = ");
		sql.append(equipeMedica.getSequencial());
			
		List<Medico> listaMedico = jdbcTemplate.query(sql.toString(), new MedicoRowMapper());
		equipeMedica.setMedicos(listaMedico);
			
		sql = new StringBuffer();
		sql.append(" select * from admsah001.sah_horario ");
		sql.append(" where seq_equipe_medica = ");
		sql.append(equipeMedica.getSequencial());
			
		List<Horario> listaHorarios = jdbcTemplate.query(sql.toString(), new HorarioRowMapper());
		equipeMedica.setHorarios(listaHorarios);
		
		return equipeMedica;
	}
	
}
