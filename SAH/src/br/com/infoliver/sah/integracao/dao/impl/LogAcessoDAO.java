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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.integracao.dao.ILogAcessoDAO;
import br.com.infoliver.sah.negocio.entity.LogAcesso;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

@Repository("logAcessoDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes","unused"})
public class LogAcessoDAO extends DAOBase implements ILogAcessoDAO {

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void inserir(LogAcesso logAcesso) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue("p_in_tipo_listagem",1);
		params.addValue("p_in_seq_usuario",logAcesso.getSequencialUsuario());
		params.addValue("p_in_txt_nome",logAcesso.getNomeUsuario());
		params.addValue("p_in_data_inicial",null);
		params.addValue("p_in_data_final",null);
		params.addValue("p_in_num_qtd_paginacao",null);
		params.addValue("p_in_num_inicio_paginacao",null);		
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_log_acesso_listar",params,
				new SqlParameter("p_in_tipo_listagem",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_data_inicial",Types.DATE),
				new SqlParameter("p_in_data_final",Types.DATE),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER));
	}

	@Override
	public Integer totalRegitrosParaPaginacao(PaginacaoVO logAcesso) {
		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue("p_in_tipo_listagem",2);
		params.addValue("p_in_seq_usuario",((LogAcesso)logAcesso.getEntidade()).getSequencialUsuario());
		params.addValue("p_in_txt_nome",null);
		params.addValue("p_in_data_inicial",((LogAcesso)logAcesso.getEntidade()).getDataInicial());
		params.addValue("p_in_data_final",((LogAcesso)logAcesso.getEntidade()).getDataFinal());
		params.addValue("p_in_num_qtd_paginacao",logAcesso.getQuantidadePaginacao());
		params.addValue("p_in_num_inicio_paginacao",logAcesso.getInicioPaginacao());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_log_acesso_listar",params,
				new SqlParameter("p_in_tipo_listagem",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_data_inicial",Types.DATE),
				new SqlParameter("p_in_data_final",Types.DATE),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),				
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Object>() {
					public Object mapRow(ResultSet rs, int rowNum)throws SQLException {
						return rs.getInt("count");
					}
				}));
		
		return (Integer)((List)out.get("p_out_cursor")).get(0);
	}

	@Override
	public List<LogAcesso> listarPaginado(PaginacaoVO logAcesso) {
		MapSqlParameterSource params = new MapSqlParameterSource();	
		params.addValue("p_in_tipo_listagem",3);
		params.addValue("p_in_seq_usuario",((LogAcesso)logAcesso.getEntidade()).getSequencialUsuario());
		params.addValue("p_in_txt_nome",null);
		params.addValue("p_in_data_inicial",((LogAcesso)logAcesso.getEntidade()).getDataInicial());
		params.addValue("p_in_data_final",((LogAcesso)logAcesso.getEntidade()).getDataFinal());
		params.addValue("p_in_num_qtd_paginacao",logAcesso.getQuantidadePaginacao());
		params.addValue("p_in_num_inicio_paginacao",logAcesso.getInicioPaginacao());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_log_acesso_listar",params,
				new SqlParameter("p_in_tipo_listagem",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_data_inicial",Types.DATE),
				new SqlParameter("p_in_data_final",Types.DATE),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),				
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<LogAcesso>() {
							public LogAcesso mapRow(ResultSet rs, int rowNum)throws SQLException {
								LogAcesso logAcesso = new LogAcesso();
								logAcesso.setSequencialUsuario(rs.getInt("seq_usuario"));
								logAcesso.setNomeUsuario(rs.getString("txt_nome"));
								logAcesso.setDataHoraCadastro(rs.getTimestamp("dth_cadastro"));
								return logAcesso;
							}
						}));
		
		return (List<LogAcesso>) out.get("p_out_cursor");
	}
}