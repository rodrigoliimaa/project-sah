package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.integracao.dao.IMedicoDAO;
import br.com.infoliver.sah.negocio.entity.Horario;
import br.com.infoliver.sah.negocio.entity.Licenca;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.entity.Motivo;
import br.com.infoliver.sah.negocio.entity.Ocupacao;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

@Repository("medicoDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes"})
public class MedicoDAO extends DAOBase implements IMedicoDAO {
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(final Medico medico) {
		String sql = "update admsah001.sah_medico set" +
				" txt_cns=?" + // medico.getCns() +
				", txt_cpf=?" + // medico.getCpf() +
				", txt_email=?" + // medico.getEmail() +
				", txt_ind_ativo=?" + // medico.getIndicadorAtivo() +
				", txt_nome=?" + // medico.getNome() +
				", txt_rg=?" + // medico.getRg() +
				", txt_orgao_emissor=?" + // medico.getOrgaoEmissor() +
				", txt_uf_orgao_emissor=?" + // medico.getUfOrgaoEmissor() +
				//", dat_expedicao=?" + // medico.getDataExpedicao() +
				", txt_telefone1=?" + // medico.getTelefone1() +
				", txt_telefone2=?" + // medico.getTelefone2() +
				", txt_telefone3=?" + // medico.getTelefone3() +
				", seq_ocupacao=?" + // medico.getOcupacao().getSequencial() +
				", seq_usuario=?" +
				" where seq_medico = ?";// + medico.getSequencial();
		
		try {
			jdbcTemplate.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement preparedStatement) throws SQLException {
					preparedStatement.setString(1, medico.getCns());
					preparedStatement.setString(2, medico.getCpf());
					preparedStatement.setString(3, medico.getEmail());
					preparedStatement.setString(4, medico.getIndicadorAtivo());
					preparedStatement.setString(5, medico.getNome());
					preparedStatement.setString(6, medico.getRg());
					preparedStatement.setString(7, medico.getOrgaoEmissor());
					preparedStatement.setString(8, medico.getUfOrgaoEmissor());
					//preparedStatement.setDate(9, new Date(medico.getDataExpedicao().getTime()));
					preparedStatement.setString(9, medico.getTelefone1());
					preparedStatement.setString(10, medico.getTelefone2());
					preparedStatement.setString(11, medico.getTelefone3());
					preparedStatement.setInt(12, medico.getOcupacao().getSequencial());
					preparedStatement.setInt(13, medico.getUsuarioResponsavel().getSequencial());
					preparedStatement.setInt(14, medico.getSequencial());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("Não foi possível atualizar o registro");
		}
		
		// deletar horários
		jdbcTemplate.update(
				"delete from admsah001.sah_horario where seq_medico = " + medico.getSequencial()
		);
		
		if (medico.getHorarios() != null) {
			for (final Horario horario : medico.getHorarios()) {
				// inserir horário
				String sqlHorario = "insert into admsah001.sah_horario" +
						" (seq_medico, num_dia_semana, txt_turno, num_maximo_agendamentos)" +
						" values" +
						" (?,?,?,?)";
				
				jdbcTemplate.update(sqlHorario, new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, medico.getSequencial());
						ps.setInt(2, horario.getDiaSemana());
						ps.setString(3, horario.getTurno());
						ps.setInt(4, horario.getNumeroMaximoAgendamentos());
						
					}
				});
			}
		}
	}

	@Override
	public List<Medico> listar() {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_medico",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_medico",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_medico",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Medico>() {
							public Medico mapRow(ResultSet rs, int rowNum)throws SQLException {
								Medico medico = new Medico();
								medico.setSequencial(rs.getInt("seq_medico"));
								medico.setNome(rs.getString("txt_nome"));
								medico.setCns(rs.getString("txt_cns"));
								return medico;
							}
						}));
	
		return (List<Medico>) out.get("p_out_cursor");
	}
	
	@Override
	public List<Medico> listarPaginado(PaginacaoVO medico) {
		String sql = " select admsah001.sah_medico.*, " +
				" 	   admsah001.sah_ocupacao.txt_descricao, " +
				" 	   admsah001.sah_ocupacao.num_codigo_ocupacao, " +
				"      admsah001.sah_usuario.seq_usuario as sah_usuario_seq_usuario, " +
				"      admsah001.sah_usuario.txt_login as sah_usuario_login, " +
				" 	   admsah001.sah_usuario.txt_nome as sah_usuario_nome " +
				" from admsah001.sah_medico " +
				" left join admsah001.sah_usuario on admsah001.sah_medico.seq_usuario = admsah001.sah_usuario.seq_usuario " +
				" join admsah001.sah_ocupacao on admsah001.sah_medico.seq_ocupacao = admsah001.sah_ocupacao.seq_ocupacao " +
				" where 1=1 " +
				criarSQLCondicao((Medico) medico.getEntidade()) +
				" order by sah_medico.txt_nome asc" +
				" " + criarSQLPaginacao(medico);
		
		List<Medico> medicos = jdbcTemplate.query(sql, new MedicoUsuarioOcupacaoRowMapper());
		for (int i = 0; i < medicos.size(); i++) {
			Integer medicoSequencial = medicos.get(i).getSequencial();
			String sqlHorario = "select * from admsah001.sah_horario where seq_medico = " + medicoSequencial;
			RowMapper<Horario> horarioRowMapper = new ParameterizedRowMapper<Horario>() {
				@Override
				public Horario mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					Horario horario = new Horario();
					
					horario.setDiaSemana(rs.getInt("num_dia_semana"));
					horario.setNumeroMaximoAgendamentos(rs.getInt("num_maximo_agendamentos"));
					horario.setTurno(rs.getString("txt_turno"));
					
					return horario;
				}
			}; 
			List<Horario> horariosRecuperados = jdbcTemplate.query(sqlHorario, horarioRowMapper);
			
			medicos.get(i).setHorarios(horariosRecuperados);
		}
		
		for (int i = 0; i < medicos.size(); i++) {
			Integer medicoSequencial = medicos.get(i).getSequencial();
			String sqlLicenca = "select * from admsah001.sah_licenca where seq_medico = " + medicoSequencial;
			RowMapper<Licenca> horarioRowMapper = new ParameterizedRowMapper<Licenca>() {
				@Override
				public Licenca mapRow(ResultSet rs, int rowNum)
						throws SQLException {
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
			}; 
			List<Licenca> licencasRecuperadas = jdbcTemplate.query(sqlLicenca, horarioRowMapper);
			
			medicos.get(i).setLicencas(licencasRecuperadas);
		}
		
		return medicos;
	}

	private String criarSQLPaginacao(PaginacaoVO medico) {
		String sql = "limit " + medico.getQuantidadePaginacao() +
				"offset " + medico.getInicioPaginacao();
		
		return sql;
	}

	@Override
	public Medico consultar(Integer sequencial) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_medico",sequencial);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_medico",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_medico",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Medico>() {
							public Medico mapRow(ResultSet rs, int rowNum)throws SQLException {
								final Medico medico = new Medico();
								medico.setSequencial(rs.getInt("seq_medico"));
								medico.setNome(rs.getString("txt_nome"));
								medico.setCns(rs.getString("txt_cns"));
								
								final Ocupacao ocupacao = new Ocupacao();
								//ocupacao.setSequencial(rs.getInt("seq_ocupacao"));
								medico.setOcupacao(ocupacao);
								
								return medico;
							}
						}));
		List<Medico> lista=(List<Medico>) out.get("p_out_cursor");
		
		return lista.size()>0?lista.get(0):null;
	}
	
	@Override
	public Medico consultarPorCns(String cns) {
		String sql = "select * from admsah001.sah_medico" +
				" where txt_cns = '" + cns + "'";
		
		try {
			List<Medico> lista = jdbcTemplate.query(sql, new MedicoRowMapper());
			if ( lista.size() > 0 ) {
				return lista.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Medico consultarPorCpf(String cpf) {
		String sql = "select * from admsah001.sah_medico" +
				" where txt_cpf = '" + cpf + "'";
		
		try {
			List<Medico> lista = jdbcTemplate.query(sql, new MedicoRowMapper());
			if ( lista.size() > 0 ) {
				return lista.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Medico consultarPorRg(String rg, String orgaoEmissor) {
		//TODO: Revisar esse método (parte do RG foi modificada)
//		String sql = "select * from admsah001.sah_medico" +
//				" where txt_rg = '" + rg + "'" +
//						"and txt_orgao_emissor = '" + orgaoEmissor + "'";
//		
//		try {
//			List<Medico> lista = jdbcTemplate.query(sql, new MedicoRowMapper());
//			if ( lista.size() > 0 ) {
//				return lista.get(0);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return null;
	}
	
	@Override
	public Medico consultarPorSequencial(Integer sequencial) {
		String sql = "select * from admsah001.sah_medico" +
				" where seq_medico = " + sequencial;
		
		return jdbcTemplate.query(sql, new MedicoRowMapper()).get(0);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserir(final Medico medico) throws DAOException {
		final String sql = "insert into admsah001.sah_medico" +
				" (txt_nome, txt_cpf, txt_rg, txt_orgao_emissor, txt_uf_orgao_emissor, dat_expedicao, txt_telefone1, txt_telefone2, txt_telefone3, txt_email," +
				" txt_ind_ativo, txt_cns, seq_ocupacao, seq_usuario)" +
				" values" +
				" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(sql, new String[] {"seq_medico"});
		            ps.setString(1, medico.getNome());
		            ps.setString(2, medico.getCpf());
		            ps.setString(3, medico.getRg());
		            ps.setString(4, medico.getOrgaoEmissor());
		            ps.setString(5, medico.getUfOrgaoEmissor());
		            ps.setDate(6, new Date( medico.getDataExpedicao().getTime() ));
		            ps.setString(7, medico.getTelefone1());
		            ps.setString(8, medico.getTelefone2());
		            ps.setString(9, medico.getTelefone3());
		            ps.setString(10, medico.getEmail());
		            ps.setString(11, medico.getIndicadorAtivo());
		            ps.setString(12, medico.getCns());
		            ps.setInt(13, medico.getOcupacao().getSequencial());
		            ps.setInt(14, medico.getUsuarioResponsavel().getSequencial());
		            return ps;
		        }
		    },
		    keyHolder);
		
		final Integer sequencial = keyHolder.getKey().intValue();
		
		if (medico.getHorarios() != null) {
			for (final Horario horario : medico.getHorarios()) {
				// inserir horário
				String sqlHorario = "insert into admsah001.sah_horario" +
						" (seq_medico, num_dia_semana, txt_turno, num_maximo_agendamentos)" +
						" values" +
						" (?,?,?,?)";
				
				jdbcTemplate.update(sqlHorario, new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, sequencial);
						ps.setInt(2, horario.getDiaSemana());
						ps.setString(3, horario.getTurno());
						ps.setInt(4, horario.getNumeroMaximoAgendamentos());
						
					}
				});
			}
		}
		
		return keyHolder.getKey().intValue();
	}
	
	@Override
	public Integer totalRegitrosParaPaginacao(Medico medico) {
		String sql = "select count(*) from admsah001.sah_medico, admsah001.sah_ocupacao" +
				" where sah_medico.seq_ocupacao = sah_ocupacao.seq_ocupacao"
				+ criarSQLCondicao(medico);
		
		return jdbcTemplate.queryForInt(sql);
	}
	
	private String criarSQLCondicao(Medico medico) {
		Ocupacao ocupacao = medico.getOcupacao();

		String where = "";
		if (medico.getNome() != null) {
			where = where + " and sah_medico.txt_nome ilike '%" + medico.getNome() + "%'";
		} else if (medico.getCpf() != null) {
			where = where + " and sah_medico.txt_cpf = '" + medico.getCpf() + "'";
		} else if (medico.getRg() != null) {
			where = where + " and sah_medico.txt_rg = '" + medico.getRg() + "'";
		} else if (medico.getCns() != null) {
			where = where + " and sah_medico.txt_cns = '" + medico.getCns() + "'";
		} else if (ocupacao != null && ocupacao.getDescricao() != null && ocupacao.getDescricao().trim().equals("") == false) {
			where = where + " and sah_ocupacao.txt_descricao ilike '%" + ocupacao.getDescricao() + "%'";
		}
		
		if (medico.getIndicadorAtivo() != null) {
			where = where + " and txt_ind_ativo = '" +  medico.getIndicadorAtivo() + "'";
		}
		
		return where;
	}
	
	@Override
	public Medico obter(Medico medico) {
		String sql = " select admsah001.sah_medico.*, " +
				" 	   admsah001.sah_ocupacao.txt_descricao, " +
				" 	   admsah001.sah_ocupacao.num_codigo_ocupacao, " +
				"      admsah001.sah_usuario.seq_usuario as sah_usuario_seq_usuario, " +
				"      admsah001.sah_usuario.txt_login as sah_usuario_login, " +
				" 	   admsah001.sah_usuario.txt_nome as sah_usuario_nome " +
				" from admsah001.sah_medico " +
				" left join admsah001.sah_usuario on admsah001.sah_medico.seq_usuario = admsah001.sah_usuario.seq_usuario " +
				" join admsah001.sah_ocupacao on admsah001.sah_medico.seq_ocupacao = admsah001.sah_ocupacao.seq_ocupacao " +
				" where sah_medico.seq_medico = " + medico.getSequencial();
		
		final List<Medico> lista = jdbcTemplate.query(sql, new MedicoOcupacaoRowMapper());
		
		if (lista != null && lista.size() > 0) {
			medico = lista.get(0);
		} else {
			return null;
		}
		
		String sqlHorario = "select * from admsah001.sah_horario where seq_medico = " + medico.getSequencial();
		RowMapper<Horario> horarioRowMapper = new ParameterizedRowMapper<Horario>() {
			@Override
			public Horario mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Horario horario = new Horario();
				
				horario.setDiaSemana(rs.getInt("num_dia_semana"));
				horario.setNumeroMaximoAgendamentos(rs.getInt("num_maximo_agendamentos"));
				horario.setTurno(rs.getString("txt_turno"));
				
				return horario;
			}
		}; 
		List<Horario> horariosRecuperados = jdbcTemplate.query(sqlHorario, horarioRowMapper);
		
		medico.setHorarios(horariosRecuperados);
		
		return medico;
	}
	
	//-----------------------------------------------------------------------
	/*
	 * Faz o mapeamento da consulta em sah_medico
	 */
	private class MedicoRowMapper implements ParameterizedRowMapper<Medico> {
		public Medico mapRow(ResultSet rs, int rowNum) throws SQLException {
			Medico medico = new Medico();
			medico.setSequencial(rs.getInt("seq_medico"));
			medico.setNome(rs.getString("txt_nome"));
			medico.setCpf(rs.getString("txt_cpf"));
			medico.setRg(rs.getString("txt_rg"));
			medico.setOrgaoEmissor(rs.getString("txt_orgao_emissor"));
			medico.setUfOrgaoEmissor(rs.getString("txt_uf_orgao_emissor"));
			medico.setDataExpedicao(rs.getDate("dat_expedicao"));
			medico.setTelefone1(rs.getString("txt_telefone1"));
			medico.setTelefone2(rs.getString("txt_telefone2"));
			medico.setTelefone3(rs.getString("txt_telefone3"));
			medico.setEmail(rs.getString("txt_email"));
			medico.setIndicadorAtivo(rs.getString("txt_ind_ativo"));
			medico.setCns(rs.getString("txt_cns"));

			Ocupacao ocupacao = new Ocupacao();
			ocupacao.setSequencial(rs.getInt("seq_ocupacao"));
			medico.setOcupacao(ocupacao);
			
			Usuario usuario = new Usuario();
			usuario.setSequencial(rs.getInt("seq_usuario"));
			medico.setUsuarioResponsavel(usuario);
			
			medico.setDataCadastro(rs.getDate("dth_cadastro"));
			
			return medico;
		}
	}
	
	/*
	 * Faz o mapeamento da consulta com join em sah_medico com sah_ocupacao
	 */
	private class MedicoOcupacaoRowMapper extends MedicoRowMapper {
		@Override
		public Medico mapRow(ResultSet rs, int rowNum) throws SQLException {
			Medico medico = super.mapRow(rs, rowNum);
			
			if (medico.getOcupacao() != null) {
				medico.getOcupacao().setCodigoOcupacao(rs.getString("num_codigo_ocupacao"));
				medico.getOcupacao().setDescricao(rs.getString("txt_descricao"));
			}
			
			return medico;
		}
	}
	//--------------------------------------------------------------------------------

	/*
	 * Faz o mapeamento da consulta com join em sah_medico com sah_ocupacao
	 */
	private class MedicoUsuarioOcupacaoRowMapper extends MedicoOcupacaoRowMapper {
		@Override
		public Medico mapRow(ResultSet rs, int rowNum) throws SQLException {
			Medico medico = super.mapRow(rs, rowNum);
			
			if (medico.getUsuarioResponsavel() != null) {
				medico.getUsuarioResponsavel().setLogin(rs.getString("sah_usuario_login"));
				medico.getUsuarioResponsavel().setNome(rs.getString("sah_usuario_nome"));
			}
			
			return medico;
		}
	}
	//--------------------------------------------------------------------------------
	
	
	
}