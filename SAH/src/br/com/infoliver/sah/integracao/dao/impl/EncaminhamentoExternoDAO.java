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
import br.com.infoliver.sah.integracao.dao.IEncaminhamentoExternoDAO;
import br.com.infoliver.sah.negocio.entity.EncaminhamentoExterno;
import br.com.infoliver.sah.negocio.entity.Grupo;

@Repository("encaminhamentoExternoDAO")
@Transactional(readOnly=true)
public class EncaminhamentoExternoDAO extends DAOBase implements IEncaminhamentoExternoDAO{

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<EncaminhamentoExterno> listarEncaminhamento() {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",2);
		params.addValue("p_in_seq_encaminhamento_externo",null);
		params.addValue("p_in_txt_descricao",null);
		params.addValue("p_in_dth_cadastro",null);
		params.addValue("p_in_txt_logradouro",Types.VARCHAR);
		params.addValue("p_in_txt_numero",Types.VARCHAR);
		params.addValue("p_in_txt_bairro",Types.VARCHAR);
		params.addValue("p_in_txt_cidade",Types.VARCHAR);
		params.addValue("p_in_txt_referencia",Types.VARCHAR);
		params.addValue("p_in_txt_responsavel",Types.VARCHAR);
		params.addValue("p_out_cursor",Types.OTHER);
		Map out = callProcedureUsingOutResultSet("admsah001", null, "sp_encaminhamento_externo", params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhamento_externo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),
				new SqlParameter("p_in_dth_cadastro",Types.DATE),
				new SqlParameter("p_in_txt_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_numero",Types.VARCHAR),
				new SqlParameter("p_in_txt_bairro",Types.VARCHAR),
				new SqlParameter("p_in_txt_cidade",Types.VARCHAR),
				new SqlParameter("p_in_txt_referencia",Types.VARCHAR),
				new SqlParameter("p_in_txt_responsavel",Types.VARCHAR),							
				new SqlOutParameter("p_out_cursor", Types.OTHER,					
						new ParameterizedRowMapper<EncaminhamentoExterno>(){
							public EncaminhamentoExterno mapRow(ResultSet rs, int rowNum) throws SQLException {
								EncaminhamentoExterno encaminhamento = new EncaminhamentoExterno();
								encaminhamento.setSequencial(rs.getInt("seq_encaminhamento_externo"));
								encaminhamento.setDescricao(rs.getString("txt_descricao"));
								encaminhamento.setDataHoraCadastro(rs.getDate("dth_cadastro"));								
								encaminhamento.setBairro(rs.getString("txt_bairro"));
								encaminhamento.setCidade(rs.getString("txt_cidade"));
								encaminhamento.setLogradouro(rs.getString("txt_logradouro"));
								encaminhamento.setNumero(rs.getString("txt_numero"));
								encaminhamento.setReferencia(rs.getString("txt_referencia"));
								encaminhamento.setResponsavel(rs.getString("txt_responsavel"));
								return encaminhamento ;
							}
						}));
		
		return (List<EncaminhamentoExterno>) out.get("p_out_cursor");
	}

		@Override
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
		public void inserir(EncaminhamentoExterno encaminhamentoExterno) throws DAOException {
			
			MapSqlParameterSource params = new MapSqlParameterSource();		
			params.addValue("p_in_tipo_acao",1);
			params.addValue("p_in_seq_encaminhamento_externo",null);
			params.addValue("p_in_txt_descricao",encaminhamentoExterno.getDescricao());
			params.addValue("p_in_dth_cadastro",null);
			params.addValue("p_in_txt_logradouro",encaminhamentoExterno.getLogradouro());
			params.addValue("p_in_txt_numero",encaminhamentoExterno.getNumero());
			params.addValue("p_in_txt_bairro",encaminhamentoExterno.getBairro());
			params.addValue("p_in_txt_cidade",encaminhamentoExterno.getCidade());
			params.addValue("p_in_txt_referencia",encaminhamentoExterno.getReferencia());
			params.addValue("p_in_txt_responsavel",encaminhamentoExterno.getResponsavel());
			params.addValue("p_out_cursor",Types.OTHER);
			//----------------------------------------------------------	
			Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_encaminhamento_externo",params,
					new SqlParameter("p_in_tipo_acao",Types.INTEGER),
					new SqlParameter("p_in_seq_encaminhamento_externo",Types.INTEGER),	
					new SqlParameter("p_in_txt_descricao",Types.VARCHAR),
					new SqlParameter("p_in_dth_cadastro",Types.DATE),
					new SqlParameter("p_in_txt_logradouro",Types.VARCHAR),
					new SqlParameter("p_in_txt_numero",Types.VARCHAR),
					new SqlParameter("p_in_txt_bairro",Types.VARCHAR),
					new SqlParameter("p_in_txt_cidade",Types.VARCHAR),
					new SqlParameter("p_in_txt_referencia",Types.VARCHAR),
					new SqlParameter("p_in_txt_responsavel",Types.VARCHAR),
					new SqlOutParameter("p_out_cursor", Types.OTHER));
			//return (Integer) out.get("p_out_retorno");
		
		
	}

		@Override
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
		public void alterar(EncaminhamentoExterno encaminhamentoExterno) {
			MapSqlParameterSource params = new MapSqlParameterSource();		
			params.addValue("p_in_tipo_acao",3);
			params.addValue("p_in_seq_encaminhamento_externo",encaminhamentoExterno.getSequencial());
			params.addValue("p_in_txt_descricao",encaminhamentoExterno.getDescricao());
			params.addValue("p_in_dth_cadastro",null);
			params.addValue("p_in_txt_logradouro",encaminhamentoExterno.getLogradouro());
			params.addValue("p_in_txt_numero",encaminhamentoExterno.getNumero());
			params.addValue("p_in_txt_bairro",encaminhamentoExterno.getBairro());
			params.addValue("p_in_txt_cidade",encaminhamentoExterno.getCidade());
			params.addValue("p_in_txt_referencia",encaminhamentoExterno.getReferencia());
			params.addValue("p_in_txt_responsavel",encaminhamentoExterno.getResponsavel());
			params.addValue("p_out_cursor",Types.OTHER);
			//----------------------------------------------------------	
			Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_encaminhamento_externo",params,
					new SqlParameter("p_in_tipo_acao",Types.INTEGER),
					new SqlParameter("p_in_seq_encaminhamento_externo",Types.INTEGER),	
					new SqlParameter("p_in_txt_descricao",Types.VARCHAR),
					new SqlParameter("p_in_dth_cadastro",Types.DATE),
					new SqlParameter("p_in_txt_logradouro",Types.VARCHAR),
					new SqlParameter("p_in_txt_numero",Types.VARCHAR),
					new SqlParameter("p_in_txt_bairro",Types.VARCHAR),
					new SqlParameter("p_in_txt_cidade",Types.VARCHAR),
					new SqlParameter("p_in_txt_referencia",Types.VARCHAR),
					new SqlParameter("p_in_txt_responsavel",Types.VARCHAR),
					new SqlOutParameter("p_out_cursor", Types.OTHER));
			
		}

		@Override
		public List<EncaminhamentoExterno> listarEncaminhamentoFiltro(EncaminhamentoExterno encaminhamentoExterno) {
			MapSqlParameterSource params = new MapSqlParameterSource();		
			params.addValue("p_in_tipo_acao",4);
			params.addValue("p_in_seq_encaminhamento_externo",encaminhamentoExterno.getSequencial());
			params.addValue("p_in_txt_descricao",null);
			params.addValue("p_in_dth_cadastro",null);
			params.addValue("p_in_txt_logradouro",Types.VARCHAR);
			params.addValue("p_in_txt_numero",Types.VARCHAR);
			params.addValue("p_in_txt_bairro",Types.VARCHAR);
			params.addValue("p_in_txt_cidade",Types.VARCHAR);
			params.addValue("p_in_txt_referencia",Types.VARCHAR);
			params.addValue("p_in_txt_responsavel",Types.VARCHAR);
			params.addValue("p_out_cursor",Types.OTHER);
			Map out = callProcedureUsingOutResultSet("admsah001", null, "sp_encaminhamento_externo", params,
					new SqlParameter("p_in_tipo_acao",Types.INTEGER),
					new SqlParameter("p_in_seq_encaminhamento_externo",Types.INTEGER),	
					new SqlParameter("p_in_txt_descricao",Types.VARCHAR),
					new SqlParameter("p_in_dth_cadastro",Types.DATE),
					new SqlParameter("p_in_txt_logradouro",Types.VARCHAR),
					new SqlParameter("p_in_txt_numero",Types.VARCHAR),
					new SqlParameter("p_in_txt_bairro",Types.VARCHAR),
					new SqlParameter("p_in_txt_cidade",Types.VARCHAR),
					new SqlParameter("p_in_txt_referencia",Types.VARCHAR),
					new SqlParameter("p_in_txt_responsavel",Types.VARCHAR),							
					new SqlOutParameter("p_out_cursor", Types.OTHER,					
							new ParameterizedRowMapper<EncaminhamentoExterno>(){
								public EncaminhamentoExterno mapRow(ResultSet rs, int rowNum) throws SQLException {
									EncaminhamentoExterno encaminhamento = new EncaminhamentoExterno();
									encaminhamento.setSequencial(rs.getInt("seq_encaminhamento_externo"));
									encaminhamento.setDescricao(rs.getString("txt_descricao"));
									encaminhamento.setDataHoraCadastro(rs.getDate("dth_cadastro"));								
									encaminhamento.setBairro(rs.getString("txt_bairro"));
									encaminhamento.setCidade(rs.getString("txt_cidade"));
									encaminhamento.setLogradouro(rs.getString("txt_logradouro"));
									encaminhamento.setNumero(rs.getString("txt_numero"));
									encaminhamento.setReferencia(rs.getString("txt_referencia"));
									encaminhamento.setResponsavel(rs.getString("txt_responsavel"));
									return encaminhamento ;
								}
							}));
			
			return (List<EncaminhamentoExterno>) out.get("p_out_cursor");
		}

}
