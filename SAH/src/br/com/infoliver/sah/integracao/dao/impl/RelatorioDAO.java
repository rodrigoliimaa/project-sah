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

import br.com.infoliver.sah.integracao.dao.IRelatorioDAO;
import br.com.infoliver.sah.negocio.entity.Relatorio;

@Repository("relatorioDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes"})
public class RelatorioDAO extends DAOBase implements IRelatorioDAO {

	@Override
	public List<Relatorio> listarRelatorioProcedimento() {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_relatorio",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_relatorio",params,	
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_relatorio",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Relatorio>() {
							public Relatorio mapRow(ResultSet rs, int rowNum)throws SQLException {
								Relatorio relatorio = new Relatorio();
								relatorio.setSequencial(rs.getInt("seq_relatorio"));
								relatorio.setNome(rs.getString("txt_nome"));
								relatorio.setNomeArquivo(rs.getString("txt_nome_arquivo"));
								return relatorio;
							}
						}));
	
		return (List<Relatorio>) out.get("p_out_cursor");
	}

	@Override
	public Relatorio consultar(Integer sequencial) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_relatorio",sequencial);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_relatorio",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_relatorio",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Relatorio>() {
							public Relatorio mapRow(ResultSet rs, int rowNum)throws SQLException {
								Relatorio relatorio = new Relatorio();
								relatorio.setSequencial(rs.getInt("seq_relatorio"));
								relatorio.setNome(rs.getString("txt_nome"));
								relatorio.setNomeArquivo(rs.getString("txt_nome_arquivo"));
								return relatorio;
							}
						}));
	
		List<Relatorio> lista=(List<Relatorio>) out.get("p_out_cursor");
		return lista.size()>0?lista.get(0):null;
	}
}