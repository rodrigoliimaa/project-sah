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

import br.com.infoliver.sah.integracao.dao.IPermissaoDAO;
import br.com.infoliver.sah.negocio.entity.Permissao;
import br.com.infoliver.sah.negocio.entity.Usuario;

@Repository("permissaoDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes"})
public class PermissaoDAO extends DAOBase implements IPermissaoDAO {

	@Override
	public List<Object> listarPermissaoUsuario(Usuario usuario) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_listagem",3);
		params.addValue("p_in_seq_grupo",null);
		params.addValue("p_in_seq_usuario",usuario.getSequencial());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_permissao_listar",params,
				new SqlParameter("p_in_tipo_listagem",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),				
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Object>() {
							public Object mapRow(ResultSet rs, int rowNum)throws SQLException {
								return rs.getString("txt_chave");
							}
						}));
	
		return (List<Object>) out.get("p_out_cursor");
	}

	@Override
	public List<Permissao> listarPermissaoDoGrupo(Integer sequencialGrupo) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_listagem",1);
		params.addValue("p_in_seq_grupo",sequencialGrupo);
		params.addValue("p_in_seq_usuario",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_permissao_listar",params,
				new SqlParameter("p_in_tipo_listagem",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Permissao>() {
							public Permissao mapRow(ResultSet rs, int rowNum)throws SQLException {
								Permissao permissao = new Permissao();
								permissao.setSequencial(rs.getInt("seq_permissao"));
								permissao.setDescricao(rs.getString("txt_descricao"));
								return permissao;
							}
						}));
	
		return (List<Permissao>) out.get("p_out_cursor");
	}

	@Override
	public List<Permissao> listarPermissaoDiferenteDoGrupo(Integer sequencialGrupo) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_listagem",2);
		params.addValue("p_in_seq_grupo",sequencialGrupo);
		params.addValue("p_in_seq_usuario",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_permissao_listar",params,
				new SqlParameter("p_in_tipo_listagem",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Permissao>() {
							public Permissao mapRow(ResultSet rs, int rowNum)throws SQLException {
								Permissao permissao = new Permissao();
								permissao.setSequencial(rs.getInt("seq_permissao"));
								permissao.setDescricao(rs.getString("txt_descricao"));
								return permissao;
							}
						}));
	
		return (List<Permissao>) out.get("p_out_cursor");
	}
}