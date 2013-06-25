	package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.integracao.dao.IProcedimentoMedicoDAO;
import br.com.infoliver.sah.negocio.dto.CodigoDescricaoProcedimentoDTO;
import br.com.infoliver.sah.negocio.entity.ProcedimentoMedico;

@Repository("procedimentoMedicoDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes"})
public class ProcedimentoMedicoDAO extends DAOBase implements IProcedimentoMedicoDAO {

	@Override
	public List<ProcedimentoMedico> listarTipoProcedimentoMedico() {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_procedimento_medico",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_procedimento_medico",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_procedimento_medico",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<ProcedimentoMedico>() {
							public ProcedimentoMedico mapRow(ResultSet rs, int rowNum)throws SQLException {
								ProcedimentoMedico procedimentoMedico = new ProcedimentoMedico();
								procedimentoMedico.setSequencial(rs.getInt("seq_procedimento_medico"));
								procedimentoMedico.setTipoProcedimento(rs.getString("txt_tipo_procedimento"));
								procedimentoMedico.setQuantidadeLinha(rs.getInt("num_qtd_linha"));
								procedimentoMedico.setCodigoPrincipal(rs.getString("txt_codigo_principal"));
								procedimentoMedico.setDescricaoPrincipal(rs.getString("txt_descricao_principal"));
								return procedimentoMedico;
							}
						}));
	
		return (List<ProcedimentoMedico>) out.get("p_out_cursor");
	}

	@Override
	public ProcedimentoMedico consultarTipoProcedimentoMedico(Integer sequencial) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_procedimento_medico",sequencial);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_procedimento_medico",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_procedimento_medico",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<ProcedimentoMedico>() {
							public ProcedimentoMedico mapRow(ResultSet rs, int rowNum)throws SQLException {
								ProcedimentoMedico procedimentoMedico = new ProcedimentoMedico();
								procedimentoMedico.setSequencial(rs.getInt("seq_procedimento_medico"));
								procedimentoMedico.setTipoProcedimento(rs.getString("txt_tipo_procedimento"));
								procedimentoMedico.setQuantidadeLinha(rs.getInt("num_qtd_linha"));
								procedimentoMedico.setCodigoPrincipal(rs.getString("txt_codigo_principal"));
								procedimentoMedico.setDescricaoPrincipal(rs.getString("txt_descricao_principal"));
								return procedimentoMedico;
							}
						}));

		List<ProcedimentoMedico> lista=(List<ProcedimentoMedico>) out.get("p_out_cursor");
		return lista.size()>0?lista.get(0):null;
	}
	
	@Override
	public CodigoDescricaoProcedimentoDTO consultarCodigoProcedimentoPrincipal(Integer seqProcedimentoPrincipalPoliclinica) {
		String sql = "select txt_codigo_principal, txt_descricao_principal from admsah001.sah_procedimento_medico " +
						"where seq_procedimento_medico = " + seqProcedimentoPrincipalPoliclinica;

		List<CodigoDescricaoProcedimentoDTO> codigoProcedimentoPrincipal = jdbcTemplate.query(sql, new ParameterizedRowMapper<CodigoDescricaoProcedimentoDTO>() {

			@Override
			public CodigoDescricaoProcedimentoDTO mapRow(ResultSet rs, int arg1) throws SQLException {
				CodigoDescricaoProcedimentoDTO dto = new CodigoDescricaoProcedimentoDTO();
				dto.setCodigo(rs.getString("txt_codigo_principal"));
				dto.setDescricao(rs.getString("txt_descricao_principal"));
				
				return dto;
			}
			
		});
		
		return codigoProcedimentoPrincipal.get(0);
	}


	@Override
	public List<ProcedimentoMedico> listarProcedimentoMedicoPrincipal() {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",2);
		params.addValue("p_in_seq_procedimento_medico",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_procedimento_medico",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_procedimento_medico",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<ProcedimentoMedico>() {
							public ProcedimentoMedico mapRow(ResultSet rs, int rowNum)throws SQLException {
								ProcedimentoMedico procedimentoMedico = new ProcedimentoMedico();
								procedimentoMedico.setCodigoPrincipal(rs.getString("txt_codigo_principal"));
								procedimentoMedico.setDescricaoPrincipal(rs.getString("txt_descricao_principal"));
								return procedimentoMedico;
							}
						}));
	
		return (List<ProcedimentoMedico>) out.get("p_out_cursor");
	}

	@Override
	public List<ProcedimentoMedico> listarProcedimentoMedicoSecundario() {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",3);
		params.addValue("p_in_seq_procedimento_medico",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_procedimento_medico",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_procedimento_medico",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<ProcedimentoMedico>() {
					public ProcedimentoMedico mapRow(ResultSet rs, int rowNum)throws SQLException {
						ProcedimentoMedico procedimentoMedico = new ProcedimentoMedico();
						procedimentoMedico.setCodigoSecundario(rs.getString("txt_codigo_secundario"));
						procedimentoMedico.setDescricaoSecundario(rs.getString("txt_descricao_secundario"));
						return procedimentoMedico;
					}
				}));
		
		return (List<ProcedimentoMedico>) out.get("p_out_cursor");
	}

	@Override
	public List<ProcedimentoMedico> listarProcedimentoMedicoPrincipalPoliclinica() {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",4);
		params.addValue("p_in_seq_procedimento_medico",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_procedimento_medico",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_procedimento_medico",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<ProcedimentoMedico>() {
							public ProcedimentoMedico mapRow(ResultSet rs, int rowNum)throws SQLException {
								ProcedimentoMedico procedimentoMedico = new ProcedimentoMedico();
								procedimentoMedico.setSequencial(rs.getInt("seq_procedimento_medico"));
								procedimentoMedico.setCodigoPrincipal(rs.getString("txt_codigo_principal"));
								procedimentoMedico.setDescricaoPrincipal(rs.getString("txt_descricao_principal"));
								return procedimentoMedico;
							}
						}));
	
		return (List<ProcedimentoMedico>) out.get("p_out_cursor");
	}

}