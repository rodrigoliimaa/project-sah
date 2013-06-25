package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.integracao.dao.ISigtapDAO;
import br.com.infoliver.sah.negocio.entity.SigtapCID;
import br.com.infoliver.sah.negocio.entity.SigtapProcedimento;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

@Repository("sigtapDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes"})
public class SigtapDAO extends DAOBase implements ISigtapDAO {
	
	@Override
	public SigtapCID consultarCid(String codigo) {
		String sql = "select * from sigtap.tb_cid where co_cid = '" + codigo  + "'";
		List<SigtapCID> lista = jdbcTemplate.query(sql, new SigtapCIDRowMapper());
		if (lista != null && lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}
	
	@Override
	public String consultarDtCompetencia() {
		String sql = "select distinct dt_competencia from sigtap.tb_procedimento";
		
		return jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("dt_competencia");
			}
		}).get(0);
	}
	
	@Override
	public SigtapProcedimento consultarProcedimento(String codigo) {
		String sql = "select distinct tb_procedimento.* from sigtap.tb_procedimento, sigtap.rl_procedimento_ocupacao, sigtap.rl_procedimento_cid " +
				" where tb_procedimento.co_procedimento = rl_procedimento_cid.co_procedimento and tb_procedimento.co_procedimento = rl_procedimento_ocupacao.co_procedimento" +
				" and tb_procedimento.co_procedimento = '" + codigo + "'";
		
		List<SigtapProcedimento> lista = jdbcTemplate.query(sql, new SigtapProcedimentoRowMapper());
		if (lista != null && lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}
	
	@Override
	public List<SigtapCID> listarCIDPaginado(PaginacaoVO cid) {
		String sql = "select * from sigtap.tb_cid " +
				criarSQLCondicaoCID((SigtapCID) cid.getEntidade()) +
				" order by no_cid asc " +
				criarSQLPaginacao(cid);
		List<SigtapCID> sigtapCIDs = jdbcTemplate.query(sql, new SigtapCIDRowMapper());
		
		for (SigtapCID sigtapCid : sigtapCIDs) {
			sigtapCid.setSigtapProcedimentos(listarProcedimentosPorCid(sigtapCid));
		}
		
		return sigtapCIDs;
	}
	
	private String criarSQLCondicaoCID(SigtapCID cid) {
		String where = "";
		
		if (cid.getCo_cid() != null) {
			where = where + " where co_cid = '" + cid.getCo_cid() + "'";
		} else if (cid.getNo_cid() != null) {
			where = where + " where no_cid ilike '%" + cid.getNo_cid() + "%'";
		}
		
		return where;
	}
	
	private List<SigtapProcedimento> listarProcedimentosPorCid(SigtapCID cid) {
		String sql = "select * from sigtap.rl_procedimento_cid" +
				" where co_cid = '" + cid.getCo_cid() + "'";
		
		return jdbcTemplate.query(sql, new RowMapper<SigtapProcedimento>() {
			@Override
			public SigtapProcedimento mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				SigtapProcedimento procedimento = new SigtapProcedimento();
				
				procedimento.setCo_procedimento(rs.getString("co_procedimento"));
				
				return procedimento;
			}
			
		});
	}

	@Override
	public List<SigtapProcedimento> listarProcedimentoPaginado(PaginacaoVO procedimento) {
		String sql = "select distinct tb_procedimento.* from sigtap.tb_procedimento, sigtap.rl_procedimento_ocupacao, sigtap.rl_procedimento_cid " +
				" where tb_procedimento.co_procedimento = rl_procedimento_cid.co_procedimento and tb_procedimento.co_procedimento = rl_procedimento_ocupacao.co_procedimento" +
				criarSQLCondicaoProcedimento((SigtapProcedimento) procedimento.getEntidade()) +
				" order by no_procedimento asc " +
				criarSQLPaginacao(procedimento);
		
		List<SigtapProcedimento> sigtapProcedimentos = jdbcTemplate.query(sql, new SigtapProcedimentoRowMapper());
		return sigtapProcedimentos;
	}
	
	private String criarSQLCondicaoProcedimento(SigtapProcedimento procedimento) {
		String where = "";
		
		if (procedimento.getCo_procedimento() != null) {
			where = where + " and tb_procedimento.co_procedimento = '" + procedimento.getCo_procedimento() + "'";
		} else if (procedimento.getNo_procedimento() != null) {
			where = where + " and no_procedimento ilike '%" + procedimento.getNo_procedimento() + "%'";
		}
		
		if (procedimento.getTp_sexo() != null) {
			where = where + " and tp_sexo in ('I', 'N', '" + procedimento.getTp_sexo().toUpperCase() + "') ";
		}
		
		if (procedimento.getVl_idade_minima() > -1) { // a idade do paciente está no campo vl_idade_minima
			where = where + " and " + procedimento.getVl_idade_minima() + " >= vl_idade_minima ";
			where = where + " and " + procedimento.getVl_idade_minima() + " <= vl_idade_maxima ";
		}
		
		if (procedimento.getSigtapOcupacoes() != null
				&& procedimento.getSigtapOcupacoes().get(0) != null
				&& procedimento.getSigtapOcupacoes().get(0).getCo_ocupacao() != null
				&& procedimento.getSigtapOcupacoes().get(0).getCo_ocupacao()
						.equals("") == false) {
			where = where + " and rl_procedimento_ocupacao.co_ocupacao = '"
					+ procedimento.getSigtapOcupacoes().get(0).getCo_ocupacao().replaceAll("-", "")
					+ "'";
		}
		
		String cidWhere = "";
		final List<SigtapCID> cids = procedimento.getSigtapCIDs();
		if (cids != null) {
			for (SigtapCID cid : cids) {
				final String co_cid = cid.getCo_cid();
				if (co_cid != null && co_cid.equals("") == false) {
					if (cidWhere.equals("")) {
						cidWhere = cidWhere + " and (rl_procedimento_cid.co_cid = '" + co_cid + "'";
					} else {
						cidWhere = cidWhere + " or rl_procedimento_cid.co_cid = '" + co_cid + "'";
					}
				}
			}
			if (cidWhere.equals("") == false) {
				where = where + cidWhere + ")";
			}
		}
		
		return where;
	}
	
	private String criarSQLPaginacao(PaginacaoVO paginacaoVO) {
		String sql = " limit " + paginacaoVO.getQuantidadePaginacao() +
				" offset " + paginacaoVO.getInicioPaginacao();
		
		return sql;
	}
	
	@Override
	public Integer totalRegitrosCIDParaPaginacao(SigtapCID cid) {
		String sql = "select count(*) from sigtap.tb_cid " +
				criarSQLCondicaoCID(cid);
		
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public Integer totalRegitrosProcedimentoParaPaginacao(
			SigtapProcedimento procedimento) {
		String sql = "select count(distinct tb_procedimento.co_procedimento) from sigtap.tb_procedimento, sigtap.rl_procedimento_ocupacao, sigtap.rl_procedimento_cid " +
				" where tb_procedimento.co_procedimento = rl_procedimento_cid.co_procedimento and tb_procedimento.co_procedimento = rl_procedimento_ocupacao.co_procedimento" +
				criarSQLCondicaoProcedimento(procedimento);
		
		return jdbcTemplate.queryForInt(sql);
	}
	
	private class SigtapProcedimentoRowMapper implements ParameterizedRowMapper<SigtapProcedimento> {
		@Override
		public SigtapProcedimento mapRow(ResultSet rs, int rowNum) throws SQLException {
			SigtapProcedimento procedimento = new SigtapProcedimento();

			//procedimento.setCo_financiamento(rs.getString("co_financiamento"));
			procedimento.setCo_procedimento(rs.getString("co_procedimento"));
			procedimento.setCo_rubrica(rs.getString("co_rubrica"));
			procedimento.setDt_competencia(rs.getString("dt_competencia"));
			procedimento.setNo_procedimento(rs.getString("no_procedimento"));
			procedimento.setQt_dias_permanencia(rs.getInt("qt_dias_permanencia"));
			procedimento.setQt_maxima_execucao(rs.getInt("qt_maxima_execucao"));
			procedimento.setQt_pontos(rs.getInt("qt_pontos"));
			procedimento.setTp_complexidade(rs.getString("tp_complexidade"));
			procedimento.setTp_sexo(rs.getString("tp_sexo"));
			procedimento.setVl_idade_maxima(rs.getInt("vl_idade_maxima"));
			procedimento.setVl_idade_minima(rs.getInt("vl_idade_minima"));
			procedimento.setVl_sa(rs.getDouble("vl_sa"));
			procedimento.setVl_sh(rs.getDouble("vl_sh"));
			procedimento.setVl_sp(rs.getDouble("vl_sp"));
			
			return procedimento;
		}
	}
	
	private class SigtapCIDRowMapper implements ParameterizedRowMapper<SigtapCID> {
		@Override
		public SigtapCID mapRow(ResultSet rs, int rowNum) throws SQLException {
			SigtapCID cid = new SigtapCID();

			cid.setCo_cid(rs.getString("co_cid"));
			cid.setNo_cid(rs.getString("no_cid"));
			
			return cid;
		}
	}

	@Override
	public List<SigtapProcedimento> listarProcedimentos() {
		String sql = "SELECT sigtap.tb_procedimento.co_procedimento,  sigtap.tb_procedimento.no_procedimento FROM sigtap.tb_procedimento;";				
		
		
		return jdbcTemplate.query(sql, new RowMapper<SigtapProcedimento>() {
			@Override
			public SigtapProcedimento mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				SigtapProcedimento procedimento = new SigtapProcedimento();
				
				procedimento.setCo_procedimento(rs.getString("co_procedimento"));
				procedimento.setNo_procedimento(rs.getString("no_procedimento"));
				
				return procedimento;
			}
			
		});
	}
	
	@Override
	public SigtapCID obterCid(SigtapCID sigtapCID) {
		String sql = "select * from sigtap.tb_cid where co_cid = '" + sigtapCID.getCo_cid() + "'";
		
		final List<SigtapCID> lista = jdbcTemplate.query(sql, new SigtapCIDRowMapper());
		if (lista != null && lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}
	
	@Override
	public SigtapProcedimento obterProcedimento(
			SigtapProcedimento sigtapProcedimento) {
		String sql = "select distinct tb_procedimento.* from sigtap.tb_procedimento, sigtap.rl_procedimento_ocupacao, sigtap.rl_procedimento_cid " +
				" where tb_procedimento.co_procedimento = rl_procedimento_cid.co_procedimento and tb_procedimento.co_procedimento = rl_procedimento_ocupacao.co_procedimento " +
				criarSQLCondicaoProcedimento(sigtapProcedimento);
		
		List<SigtapProcedimento> lista = jdbcTemplate.query(sql, new SigtapProcedimentoRowMapper());
		if (lista != null && lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public SigtapProcedimento obterSigtapProcedimentoParaConsulta(String codigo) {
		String sql = "select * " +
				"from sigtap.tb_procedimento " +
				" where tb_procedimento.co_procedimento = '" + codigo + "'";
//				criarSQLCondicaoProcedimento(sigtapProcedimento);
		
		List<SigtapProcedimento> lista = jdbcTemplate.query(sql, new SigtapProcedimentoRowMapper());
		if (lista != null && lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

}
