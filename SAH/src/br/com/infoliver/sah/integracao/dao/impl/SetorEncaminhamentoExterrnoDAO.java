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

import br.com.infoliver.sah.integracao.dao.ISetorEncaminhamentoExternoDAO;
import br.com.infoliver.sah.negocio.entity.SetorEncaminhamentoExterno;

@Repository("setorEncaminhamentoExternoDAO")
@Transactional(readOnly=true)
public class SetorEncaminhamentoExterrnoDAO extends DAOBase implements ISetorEncaminhamentoExternoDAO {

	@Override
	@SuppressWarnings({"unchecked","rawtypes"})
	public List<SetorEncaminhamentoExterno> listarSetor() {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",2);
		params.addValue("p_in_seq_setor_encaminhamento_externo",null);
		params.addValue("p_in_txt_descricao",null);
		params.addValue("p_in_dth_cadastro",null);
		params.addValue("p_in_dth_cadastro",null);
		params.addValue("p_out_cursor",Types.OTHER);
		Map out = callProcedureUsingOutResultSet("admsah001", null, "sp_setor_encaminhamento_externo", params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_setor_encaminhamento_externo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),
				new SqlParameter("p_in_dth_cadastro",Types.DATE),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<SetorEncaminhamentoExterno>(){
							public SetorEncaminhamentoExterno mapRow(ResultSet rs, int rowNum) throws SQLException {
								SetorEncaminhamentoExterno setor = new SetorEncaminhamentoExterno();
								setor.setSequencial(rs.getInt("seq_setor_encaminhamento_externo"));
								setor.setDescricao(rs.getString("txt_descricao"));	
								setor.setDataCadastro(rs.getDate("dth_cadastro"));
								return setor;
							}
						}));
		
		return (List<SetorEncaminhamentoExterno>) out.get("p_out_cursor");
	}

	
	@Override
	@Transactional(readOnly=false,propagation = Propagation.REQUIRED)
	public void inserir(SetorEncaminhamentoExterno setor) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_setor_encaminhamento_externo",null);
		params.addValue("p_in_txt_descricao",setor.getDescricao());	
		params.addValue("p_in_dth_cadastro",null);
		params.addValue("p_out_cursor",Types.OTHER);
		//----------------------------------------------------------	
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_setor_encaminhamento_externo",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_setor_encaminhamento_externo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),
				new SqlParameter("p_in_dth_cadastro",Types.DATE),
				new SqlOutParameter("p_out_cursor", Types.OTHER));
		
	}

}
