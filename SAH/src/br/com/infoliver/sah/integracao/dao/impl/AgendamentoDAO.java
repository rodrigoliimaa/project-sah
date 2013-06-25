package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
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

import br.com.infoliver.sah.integracao.dao.IAgendamentoDAO;
import br.com.infoliver.sah.negocio.entity.Agendamento;
import br.com.infoliver.sah.negocio.entity.EquipeMedica;
import br.com.infoliver.sah.negocio.entity.Horario;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.entity.Ocupacao;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

@Repository("agendamentoDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes"})
public class AgendamentoDAO extends DAOBase implements IAgendamentoDAO {
	public String sqlRelatorio;
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterarSituacao(Agendamento agendamento) {
		String clausulaReagendamento = "";
		if (agendamento.getSituacao().equals(Agendamento.SITUACAO_REAGENDAMENTO)) {
			clausulaReagendamento = ", seq_reagendamento = " + agendamento.getReagendamento().getSequencial();
		}
		
		String sql = "update admsah001.sah_agendamento" +
				" set txt_situacao = '" + agendamento.getSituacao() + "'" +
				clausulaReagendamento  +
				" where seq_agendamento = " + agendamento.getSequencial();
		
		jdbcTemplate.update(sql);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserir(final Agendamento agendamento) {
		
		
		if(agendamento.getMedico().getSequencial() != null) {
			final String sql = "insert into admsah001.sah_agendamento" +
					" (dth_agendamento, txt_turno, txt_situacao, seq_medico, seq_paciente, dth_periodo_ini, dth_periodo_fim)" +
					" values" +
					" (?,?,?,?,?,?,?)";
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(sql, new String[] {"seq_agendamento"});
			            ps.setDate(1, new Date(agendamento.getData().getTime()));
			            ps.setString(2, agendamento.getTurno());
			            ps.setString(3, agendamento.getSituacao());
			            ps.setInt(4, agendamento.getMedico().getSequencial());
			            ps.setInt(5, agendamento.getPaciente().getSequencial());
			            ps.setDate(6, new Date(agendamento.getPeriodoInicial().getTime()));
			            ps.setDate(7, new Date(agendamento.getPeriodoFinal().getTime()));
			            
			            return ps;
			        }
			    },
			    keyHolder);
			
			return keyHolder.getKey().intValue();
			
		} else {
			final String sql = "insert into admsah001.sah_agendamento" +
					" (dth_agendamento, txt_turno, txt_situacao, seq_equipe_medica, seq_paciente, dth_periodo_ini, dth_periodo_fim)" +
					" values" +
					" (?,?,?,?,?,?,?)";
			
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(sql, new String[] {"seq_agendamento"});
			            ps.setDate(1, new Date(agendamento.getData().getTime()));
			            ps.setString(2, agendamento.getTurno());
			            ps.setString(3, agendamento.getSituacao());
			            ps.setInt(4, agendamento.getEquipeMedica().getSequencial());
			            ps.setInt(5, agendamento.getPaciente().getSequencial());
			            
			            return ps;
			        }
			    },
			    keyHolder);
			
			return keyHolder.getKey().intValue();
		}
		
	}
	
	@Override
	public List<Agendamento> listarPaginado(PaginacaoVO agendamento) {
		sqlRelatorio = " select " +
				" admsah001.sah_agendamento.*, admsah001.sah_medico.*, admsah001.sah_ocupacao.* " +
				" , admsah001.sah_paciente.seq_paciente " +
				" , admsah001.sah_paciente.txt_nome as sah_paciente_txt_nome " +
				" , admsah001.sah_paciente.dat_nascimento as sah_paciente_dat_nascimento " +
				" , admsah001.sah_paciente.txt_cns as sah_paciente_txt_cns " +
				" , admsah001.sah_paciente.seq_ocupacao as sah_paciente_seq_ocupacao " +
				" , admsah001.sah_equipe_medica.seq_equipe_medica as sah_equipe_medica_seq_equipe_medica " +
				" , admsah001.sah_equipe_medica.descricao as sah_equipe_medica_descricao " +
				"  from admsah001.sah_agendamento " +
				" left join admsah001.sah_medico on admsah001.sah_medico.seq_medico = admsah001.sah_agendamento.seq_medico " +
				" left join admsah001.sah_equipe_medica on admsah001.sah_equipe_medica.seq_equipe_medica = admsah001.sah_agendamento.seq_equipe_medica " +
				" left join admsah001.sah_ocupacao on admsah001.sah_medico.seq_ocupacao = admsah001.sah_ocupacao.seq_ocupacao " +
				" join admsah001.sah_paciente on admsah001.sah_paciente.seq_paciente = admsah001.sah_agendamento.seq_paciente " +
				" where 1=1 " +
				criarSQLCondicao((Agendamento) agendamento.getEntidade()) +
				" order by sah_agendamento.dth_agendamento asc, sah_agendamento.txt_turno asc, sah_medico.txt_nome asc" +
				" " + criarSQLPaginacao(agendamento);
		
		return jdbcTemplate.query(sqlRelatorio, new AgendamentoMedicoPacienteRowMapper());
	}
	
	private String criarSQLCondicao(Agendamento agendamento) {
		String where = "";
		
		if (agendamento.getData() != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			where = where + " and sah_agendamento.dth_agendamento >= '" + simpleDateFormat.format(agendamento.getData())  + "'";
		}
		if (agendamento.getDataFim() != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			where = where + " and sah_agendamento.dth_agendamento <= '" + simpleDateFormat.format(agendamento.getDataFim())  + "'";
		}
		
		if (agendamento.getTurno() != null) {
			where = where + " and sah_agendamento.txt_turno = '" + agendamento.getTurno()  + "'";
		}
		
		if (agendamento.getSituacao() != null) {
			where = where + " and sah_agendamento.txt_situacao = '" + agendamento.getSituacao() + "'";
		}
		
		if (agendamento.getMedico() != null) {
			Medico medico = agendamento.getMedico();
			
			if (medico.getNome() != null) {
				
				where = where + " and sah_medico.txt_nome ilike '%" + medico.getNome() + "%'";
//			} else if (medico.getCpf() != null) {
//				where = "sah_medico.txt_cpf = '" + medico.getCpf() + "'";
//			} else if (medico.getRg() != null) {
//				where = "sah_medico.txt_rg = '" + medico.getRg() + "'";
			} else if (medico.getCns() != null) {
				where = where + " and sah_medico.txt_cns = '" + medico.getCns() + "'";
			} else if (medico.getOcupacao() != null
					&& medico.getOcupacao().getDescricao() != null
					&& medico.getOcupacao().getDescricao().trim().equals("") == false) {
				where =  where + " and sah_ocupacao.txt_descricao like '%" + medico.getOcupacao().getDescricao() + "%'";
			}
			
			if(medico.getUsuarioResponsavel() != null)
				where = where + " and (sah_medico.seq_usuario is null or sah_medico.seq_usuario  = " + medico.getUsuarioResponsavel().getSequencial() + " )";
		}
		
		if (agendamento.getPaciente() != null) {
			Paciente paciente = agendamento.getPaciente();
			if (paciente.getCns() != null) {
				where = where + " and sah_paciente.txt_cns = '" + paciente.getCns() + "'";
			}
			
			if (paciente.getCpf() != null) {
				where = where + " and sah_paciente.txt_cpf = '" + paciente.getCpf() + "'";
			}
			
			if (paciente.getNome() != null) {
				where = where + " and sah_paciente.txt_nome ilike '%" + paciente.getNome() + "%'";
			}
			
			if (paciente.getSequencial() != null) {
				where = where + " and sah_paciente.seq_paciente = " + paciente.getSequencial() + "";
			}
			
			if (paciente.getRg() != null) {
				where = where + " and sah_paciente.txt_rg = '" + paciente.getRg() + "'";
			}
		}
		
		if(agendamento.getTipo() != null) {
			if(agendamento.getTipo().equals(Agendamento.MEDICO)) {
				where = where + " and sah_agendamento.seq_medico is not null ";
			}
			
			if(agendamento.getTipo().equals(Agendamento.EQUIPE_MEDICA)) {
				where = where + " and sah_agendamento.seq_equipe_medica is not null ";
			}
		}
		
		return where;
	}
	
	public List<Horario> listarHorariosPorMedico(Medico medico) {
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
		return jdbcTemplate.query(sqlHorario, horarioRowMapper);
	}
	
	private String criarSQLPaginacao(PaginacaoVO medico) {
		String sql = " limit " + medico.getQuantidadePaginacao() +
				" offset " + medico.getInicioPaginacao();
		
		return sql;
	}
	
	@Override
	public Integer totalRegitrosParaPaginacao(Agendamento agendamento) {
//		String sql = "select count(*)" +
//				" from admsah001.sah_agendamento, admsah001.sah_medico, admsah001.sah_ocupacao, admsah001.sah_paciente" +
//				" where sah_agendamento.seq_medico = sah_medico.seq_medico" +
//				" and sah_medico.seq_ocupacao = sah_ocupacao.seq_ocupacao" +
//				" and sah_agendamento.seq_paciente = sah_paciente.seq_paciente" +
		String innerSql = "select count(*)" +
//				" admsah001.sah_agendamento.*, admsah001.sah_medico.*, admsah001.sah_ocupacao.*" +
//				", admsah001.sah_paciente.seq_paciente" +
//				", admsah001.sah_paciente.txt_nome as sah_paciente_txt_nome" +
//				", admsah001.sah_paciente.dat_nascimento as sah_paciente_dat_nascimento" +
//				", admsah001.sah_paciente.txt_cns as sah_paciente_txt_cns" +
//				", admsah001.sah_paciente.seq_ocupacao as sah_paciente_seq_ocupacao" +
//				", count(*)" +
"  from admsah001.sah_agendamento " +
" left join admsah001.sah_medico on admsah001.sah_medico.seq_medico = admsah001.sah_agendamento.seq_medico " +
" left join admsah001.sah_equipe_medica on admsah001.sah_equipe_medica.seq_equipe_medica = admsah001.sah_agendamento.seq_equipe_medica " +
" left join admsah001.sah_ocupacao on admsah001.sah_medico.seq_ocupacao = admsah001.sah_ocupacao.seq_ocupacao " +
" join admsah001.sah_paciente on admsah001.sah_paciente.seq_paciente = admsah001.sah_agendamento.seq_paciente " +
				criarSQLCondicao(agendamento);
//				" group by sah_agendamento.seq_agendamento" +
//				", sah_medico.seq_medico, sah_ocupacao.seq_ocupacao, sah_paciente.seq_paciente";
//		String sql = "select count(*) from (" + innerSql + ") as consulta";
		
		return jdbcTemplate.queryForInt(innerSql);
	}
	
	//-----------------------------------------------------------------------
	private class AgendamentoMedicoPacienteRowMapper implements ParameterizedRowMapper<Agendamento> {

		@Override
		public Agendamento mapRow(ResultSet rs, int rowNum) throws SQLException {
			Agendamento agendamento = new Agendamento();
			agendamento.setData(rs.getDate("dth_agendamento"));
			agendamento.setSequencial(rs.getInt("seq_agendamento"));
			agendamento.setSituacao(rs.getString("txt_situacao"));
			agendamento.setTurno(rs.getString("txt_turno"));
			agendamento.setPeriodoInicial(rs.getDate("dth_periodo_ini"));
			agendamento.setPeriodoFinal(rs.getDate("dth_periodo_fim"));
			
			Agendamento reagendamento = new Agendamento();
			reagendamento.setSequencial(rs.getInt("seq_reagendamento"));
			agendamento.setReagendamento(reagendamento);
			
			Medico medico = new Medico();
			medico.setSequencial(rs.getInt("seq_medico"));
			medico.setNome(rs.getString("txt_nome"));
//			medico.setTelefone1(rs.getString("txt_telefone1"));
//			medico.setTelefone2(rs.getString("txt_telefone2"));
//			medico.setTelefone3(rs.getString("txt_telefone3"));
			medico.setIndicadorAtivo(rs.getString("txt_ind_ativo"));
			medico.setCns(rs.getString("txt_cns"));

			Ocupacao ocupacao = new Ocupacao();
			ocupacao.setSequencial(rs.getInt("seq_ocupacao"));
			ocupacao.setDescricao(rs.getString("txt_descricao"));
			ocupacao.setCodigoOcupacao(rs.getString("num_codigo_ocupacao"));
			medico.setOcupacao(ocupacao);
			
			agendamento.setMedico(medico);
			
			Paciente paciente = new Paciente();
			paciente.setSequencial(rs.getInt("seq_paciente"));
			paciente.setNome(rs.getString("sah_paciente_txt_nome"));
			paciente.setDataNascimento(rs.getDate("sah_paciente_dat_nascimento"));
			paciente.setCns(rs.getString("sah_paciente_txt_cns"));
			
			EquipeMedica equipeMedica = new EquipeMedica();
			equipeMedica.setSequencial(rs.getInt("sah_equipe_medica_seq_equipe_medica"));
			equipeMedica.setDescricao(rs.getString("sah_equipe_medica_descricao"));
			agendamento.setEquipeMedica(equipeMedica);
			
//			Ocupacao ocupacaoPaciente = new Ocupacao();
//			ocupacao.setSequencial(rs.getInt("sah_paciente_seq_ocupacao"));
//			paciente.setOcupacao(ocupacao);
			
			agendamento.setPaciente(paciente);
			
			return agendamento;
		}
		
	}
	//-----------------------------------------------------------------------

	@Override
	public List<Agendamento> listarAgendamentos(Medico medico) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_seq_medico", medico.getSequencial());	
		
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_agendamento",params,
				new SqlParameter("p_in_seq_medico",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,					
				new ParameterizedRowMapper<Agendamento>() {
					public Agendamento mapRow(ResultSet rs, int rowNum)throws SQLException {
								Medico med = new Medico();
								Paciente paciente = new Paciente();
								Agendamento agendamento = new Agendamento();		
								agendamento.setData(rs.getDate("dth_agendamento"));
								agendamento.setTurno(rs.getString("txt_turno"));
								agendamento.setSituacao(rs.getString("txt_situacao"));
								med.setNome(rs.getString("txt_nome_medico"));
								med.setNome(rs.getString("txt_ocupcao_medico"));
								paciente.setNome(rs.getString("txt_nome_paciente"));
								agendamento.setPaciente(paciente);
								agendamento.setMedico(med);					
						return agendamento;
					}
				}));
		return (List<Agendamento>) out.get("p_out_cursor");
		
	}

	@Override
	public List<Agendamento> listarAgendamentoRelatorio(String sql) {
		return jdbcTemplate.query(sqlRelatorio, new AgendamentoMedicoPacienteRowMapper());
	}
	
}
