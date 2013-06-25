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
import br.com.infoliver.sah.integracao.dao.IUsuarioDAO;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

@Repository("usuarioDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes","unused"})
public class UsuarioDAO extends DAOBase implements IUsuarioDAO {

	@Override
	public Usuario consultarUsuario(Usuario usuario) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",4);
		params.addValue("p_in_seq_usuario",null);
		params.addValue("p_in_txt_nome",null);
		params.addValue("p_in_txt_login",usuario.getLogin());
		params.addValue("p_in_txt_senha",usuario.getSenha());
		params.addValue("p_in_dat_bloqueio",null);
		params.addValue("p_in_txt_motivo_bloqueio",null);
		params.addValue("p_in_txt_ind_primeiro_acesso",null);
		params.addValue("p_in_seq_usuario_cadastro",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_usuario_manter",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_login",Types.VARCHAR),
				new SqlParameter("p_in_txt_senha",Types.VARCHAR),
				new SqlParameter("p_in_dat_bloqueio",Types.DATE),
				new SqlParameter("p_in_txt_motivo_bloqueio",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_primeiro_acesso",Types.VARCHAR),
				new SqlParameter("p_in_seq_usuario_cadastro",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Usuario>() {
							public Usuario mapRow(ResultSet rs, int rowNum)throws SQLException {
								Usuario usuario = new Usuario();
								usuario.setSequencial(rs.getInt("seq_usuario"));
								usuario.setNome(rs.getString("txt_nome"));
								usuario.setSenha(rs.getString("txt_senha"));
								usuario.setDataBloqueio(rs.getDate("dat_bloqueio"));
								usuario.setMotivoBloqueio(rs.getString("txt_motivo_bloqueio"));
								usuario.setIndicadorPrimeiroAcesso(rs.getString("txt_ind_primeiro_acesso"));
								return usuario;
							}
						}));
		List<Usuario> lista=(List<Usuario>) out.get("p_out_cursor");
		return lista.size()>0?lista.get(0):null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterarSenha(Usuario usuario) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",5);
		params.addValue("p_in_seq_usuario",usuario.getSequencial());
		params.addValue("p_in_txt_nome",null);
		params.addValue("p_in_txt_login",null);
		params.addValue("p_in_txt_senha",usuario.getSenha());
		params.addValue("p_in_dat_bloqueio",null);
		params.addValue("p_in_txt_motivo_bloqueio",null);
		params.addValue("p_in_txt_ind_primeiro_acesso",usuario.getIndicadorPrimeiroAcesso());
		params.addValue("p_in_seq_usuario_cadastro",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_usuario_manter",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_login",Types.VARCHAR),
				new SqlParameter("p_in_txt_senha",Types.VARCHAR),
				new SqlParameter("p_in_dat_bloqueio",Types.DATE),
				new SqlParameter("p_in_txt_motivo_bloqueio",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_primeiro_acesso",Types.VARCHAR),
				new SqlParameter("p_in_seq_usuario_cadastro",Types.INTEGER));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Usuario> inserir(Usuario usuario) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_usuario",null);
		params.addValue("p_in_txt_nome",usuario.getNome());
		params.addValue("p_in_txt_login",usuario.getLogin());
		params.addValue("p_in_txt_senha",null);
		params.addValue("p_in_dat_bloqueio",usuario.getDataBloqueio());
		params.addValue("p_in_txt_motivo_bloqueio",usuario.getMotivoBloqueio());
		params.addValue("p_in_txt_ind_primeiro_acesso",null);
		params.addValue("p_in_seq_usuario_cadastro",usuario.getSequencialUsuarioCadastro());
		
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_usuario_manter",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_login",Types.VARCHAR),
				new SqlParameter("p_in_txt_senha",Types.VARCHAR),
				new SqlParameter("p_in_dat_bloqueio",Types.DATE),
				new SqlParameter("p_in_txt_motivo_bloqueio",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_primeiro_acesso",Types.VARCHAR),
				new SqlParameter("p_in_seq_usuario_cadastro",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Usuario>() {
							public Usuario mapRow(ResultSet rs, int rowNum)throws SQLException {
								Usuario usuario = new Usuario();
								usuario.setSequencial(rs.getInt("seq_usuario"));
								usuario.setNome(rs.getString("txt_nome"));
								usuario.setLogin(rs.getString("txt_login"));
								usuario.setDataBloqueio(rs.getDate("dat_bloqueio"));
								usuario.setMotivoBloqueio(rs.getString("txt_motivo_bloqueio"));
								usuario.setIndicadorPrimeiroAcesso(rs.getString("txt_ind_primeiro_acesso"));
								usuario.setDataHoraCadastro(rs.getTimestamp("dth_cadastro"));
								return usuario;
							}
						}));
	
		return (List<Usuario>) out.get("p_out_cursor");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Usuario> alterar(Usuario usuario) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",2);
		params.addValue("p_in_seq_usuario",usuario.getSequencial());
		params.addValue("p_in_txt_nome",usuario.getNome());
		params.addValue("p_in_txt_login",usuario.getLogin());
		params.addValue("p_in_txt_senha",usuario.getSenha());
		params.addValue("p_in_dat_bloqueio",usuario.getDataBloqueio());
		params.addValue("p_in_txt_motivo_bloqueio",usuario.getMotivoBloqueio());
		params.addValue("p_in_txt_ind_primeiro_acesso",usuario.getIndicadorPrimeiroAcesso());
		params.addValue("p_in_seq_usuario_cadastro",usuario.getSequencialUsuarioCadastro());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_usuario_manter",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_login",Types.VARCHAR),
				new SqlParameter("p_in_txt_senha",Types.VARCHAR),
				new SqlParameter("p_in_dat_bloqueio",Types.DATE),
				new SqlParameter("p_in_txt_motivo_bloqueio",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_primeiro_acesso",Types.VARCHAR),
				new SqlParameter("p_in_seq_usuario_cadastro",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Usuario>() {
							public Usuario mapRow(ResultSet rs, int rowNum)throws SQLException {
								Usuario usuario = new Usuario();
								usuario.setSequencial(rs.getInt("seq_usuario"));
								usuario.setNome(rs.getString("txt_nome"));
								usuario.setLogin(rs.getString("txt_login"));
								usuario.setDataBloqueio(rs.getDate("dat_bloqueio"));
								usuario.setMotivoBloqueio(rs.getString("txt_motivo_bloqueio"));
								usuario.setIndicadorPrimeiroAcesso(rs.getString("txt_ind_primeiro_acesso"));
								usuario.setDataHoraCadastro(rs.getTimestamp("dth_cadastro"));
								return usuario;
							}
						}));
	
		return (List<Usuario>) out.get("p_out_cursor");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Usuario> excluir(Usuario usuario) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",3);
		params.addValue("p_in_seq_usuario",usuario.getSequencial());
		params.addValue("p_in_txt_nome",null);
		params.addValue("p_in_txt_login",null);
		params.addValue("p_in_txt_senha",null);
		params.addValue("p_in_dat_bloqueio",null);
		params.addValue("p_in_txt_motivo_bloqueio",null);
		params.addValue("p_in_txt_ind_primeiro_acesso",null);
		params.addValue("p_in_seq_usuario_cadastro",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_usuario_manter",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_login",Types.VARCHAR),
				new SqlParameter("p_in_txt_senha",Types.VARCHAR),
				new SqlParameter("p_in_dat_bloqueio",Types.DATE),
				new SqlParameter("p_in_txt_motivo_bloqueio",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_primeiro_acesso",Types.VARCHAR),
				new SqlParameter("p_in_seq_usuario_cadastro",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Usuario>() {
							public Usuario mapRow(ResultSet rs, int rowNum)throws SQLException {
								Usuario usuario = new Usuario();
								usuario.setSequencial(rs.getInt("seq_usuario"));
								usuario.setNome(rs.getString("txt_nome"));
								usuario.setLogin(rs.getString("txt_login"));
								usuario.setDataBloqueio(rs.getDate("dat_bloqueio"));
								usuario.setMotivoBloqueio(rs.getString("txt_motivo_bloqueio"));
								usuario.setIndicadorPrimeiroAcesso(rs.getString("txt_ind_primeiro_acesso"));
								usuario.setDataHoraCadastro(rs.getTimestamp("dth_cadastro"));
								return usuario;
							}
						}));
	
		return (List<Usuario>) out.get("p_out_cursor");
	}
	
	
	@Override
	public Integer totalRegitrosParaPaginacao(PaginacaoVO usuario) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("p_in_tipo_listagem",1);
		params.addValue("p_in_txt_nome",((Usuario)usuario.getEntidade()).getNome());
		params.addValue("p_in_txt_login",((Usuario)usuario.getEntidade()).getLogin());
		params.addValue("p_in_dat_bloqueio",((Usuario)usuario.getEntidade()).getDataBloqueio());
		params.addValue("p_in_txt_motivo_bloqueio",((Usuario)usuario.getEntidade()).getMotivoBloqueio());
		params.addValue("p_in_txt_ind_primeiro_acesso",((Usuario)usuario.getEntidade()).getIndicadorPrimeiroAcesso());
		params.addValue("p_in_seq_grupo",null);
		params.addValue("p_in_num_qtd_paginacao",usuario.getQuantidadePaginacao());
		params.addValue("p_in_num_inicio_paginacao",usuario.getInicioPaginacao());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_usuario_listar",params,
				new SqlParameter("p_in_tipo_listagem",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_login",Types.VARCHAR),
				new SqlParameter("p_in_dat_bloqueio",Types.DATE),
				new SqlParameter("p_in_txt_motivo_bloqueio",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_primeiro_acesso",Types.VARCHAR),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),
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
	public List<Usuario> listarPaginado(PaginacaoVO usuario) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_listagem",2);
		params.addValue("p_in_txt_nome",((Usuario)usuario.getEntidade()).getNome());
		params.addValue("p_in_txt_login",((Usuario)usuario.getEntidade()).getLogin());
		params.addValue("p_in_dat_bloqueio",((Usuario)usuario.getEntidade()).getDataBloqueio());
		params.addValue("p_in_txt_motivo_bloqueio",((Usuario)usuario.getEntidade()).getMotivoBloqueio());
		params.addValue("p_in_txt_ind_primeiro_acesso",((Usuario)usuario.getEntidade()).getIndicadorPrimeiroAcesso());
		params.addValue("p_in_seq_grupo",null);
		params.addValue("p_in_num_qtd_paginacao",usuario.getQuantidadePaginacao());
		params.addValue("p_in_num_inicio_paginacao",usuario.getInicioPaginacao());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_usuario_listar",params,
				new SqlParameter("p_in_tipo_listagem",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_login",Types.VARCHAR),
				new SqlParameter("p_in_dat_bloqueio",Types.DATE),
				new SqlParameter("p_in_txt_motivo_bloqueio",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_primeiro_acesso",Types.VARCHAR),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),				
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Usuario>() {
							public Usuario mapRow(ResultSet rs, int rowNum)throws SQLException {
								Usuario usuario = new Usuario();
								usuario.setSequencial(rs.getInt("seq_usuario"));
								usuario.setNome(rs.getString("txt_nome"));
								usuario.setLogin(rs.getString("txt_login"));
								usuario.setDataBloqueio(rs.getDate("dat_bloqueio"));
								usuario.setMotivoBloqueio(rs.getString("txt_motivo_bloqueio"));
								usuario.setIndicadorPrimeiroAcesso(rs.getString("txt_ind_primeiro_acesso"));
								usuario.setDataHoraCadastro(rs.getTimestamp("dth_cadastro"));
								return usuario;
							}
						}));
	
		return (List<Usuario>) out.get("p_out_cursor");
	}

	@Override
	public List<Usuario> listar() {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_listagem",3);
		params.addValue("p_in_txt_nome",null);
		params.addValue("p_in_txt_login",null);
		params.addValue("p_in_dat_bloqueio",null);
		params.addValue("p_in_txt_motivo_bloqueio",null);
		params.addValue("p_in_txt_ind_primeiro_acesso",null);
		params.addValue("p_in_seq_grupo",null);
		params.addValue("p_in_num_qtd_paginacao",null);
		params.addValue("p_in_num_inicio_paginacao",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_usuario_listar",params,
				new SqlParameter("p_in_tipo_listagem",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_login",Types.VARCHAR),
				new SqlParameter("p_in_dat_bloqueio",Types.DATE),
				new SqlParameter("p_in_txt_motivo_bloqueio",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_primeiro_acesso",Types.VARCHAR),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),				
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Usuario>() {
					public Usuario mapRow(ResultSet rs, int rowNum)throws SQLException {
						Usuario usuario = new Usuario();
						usuario.setSequencial(rs.getInt("seq_usuario"));
						usuario.setNome(rs.getString("txt_nome"));
						return usuario;
					}
				}));
		
		return (List<Usuario>) out.get("p_out_cursor");
	}

	@Override
	public List<Usuario> listarUsuarioDoGrupo(Integer sequencialGrupo) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_listagem",4);
		params.addValue("p_in_txt_nome",null);
		params.addValue("p_in_txt_login",null);
		params.addValue("p_in_dat_bloqueio",null);
		params.addValue("p_in_txt_motivo_bloqueio",null);
		params.addValue("p_in_txt_ind_primeiro_acesso",null);
		params.addValue("p_in_seq_grupo",sequencialGrupo);
		params.addValue("p_in_num_qtd_paginacao",null);
		params.addValue("p_in_num_inicio_paginacao",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_usuario_listar",params,
				new SqlParameter("p_in_tipo_listagem",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_login",Types.VARCHAR),
				new SqlParameter("p_in_dat_bloqueio",Types.DATE),
				new SqlParameter("p_in_txt_motivo_bloqueio",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_primeiro_acesso",Types.VARCHAR),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),				
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Usuario>() {
							public Usuario mapRow(ResultSet rs, int rowNum)throws SQLException {
								Usuario usuario = new Usuario();
								usuario.setSequencial(rs.getInt("seq_usuario"));
								usuario.setNome(rs.getString("txt_nome"));
								return usuario;
							}
						}));
	
		return (List<Usuario>) out.get("p_out_cursor");
	}

	@Override
	public List<Usuario> listarUsuarioDiferenteDoGrupo(Integer sequencialGrupo) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_listagem",5);
		params.addValue("p_in_txt_nome",null);
		params.addValue("p_in_txt_login",null);
		params.addValue("p_in_dat_bloqueio",null);
		params.addValue("p_in_txt_motivo_bloqueio",null);
		params.addValue("p_in_txt_ind_primeiro_acesso",null);
		params.addValue("p_in_seq_grupo",sequencialGrupo);
		params.addValue("p_in_num_qtd_paginacao",null);
		params.addValue("p_in_num_inicio_paginacao",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_usuario_listar",params,
				new SqlParameter("p_in_tipo_listagem",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_login",Types.VARCHAR),
				new SqlParameter("p_in_dat_bloqueio",Types.DATE),
				new SqlParameter("p_in_txt_motivo_bloqueio",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_primeiro_acesso",Types.VARCHAR),
				new SqlParameter("p_in_seq_grupo",Types.INTEGER),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),				
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Usuario>() {
							public Usuario mapRow(ResultSet rs, int rowNum)throws SQLException {
								Usuario usuario = new Usuario();
								usuario.setSequencial(rs.getInt("seq_usuario"));
								usuario.setNome(rs.getString("txt_nome"));
								return usuario;
							}
						}));
	
		return (List<Usuario>) out.get("p_out_cursor");
	}
	
	@Override
	public Usuario obter(Usuario usuario) {
		String sql = "select * from admsah001.sah_usuario where seq_usuario = " + usuario.getSequencial();
		
		final List<Usuario> lista = jdbcTemplate.query(sql, new UsuarioRowMapper());
		if (lista != null && lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}
	
	private class UsuarioRowMapper implements ParameterizedRowMapper<Usuario> {

		@Override
		public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
			Usuario usuario = new Usuario();
			usuario.setSequencial(rs.getInt("seq_usuario"));
			usuario.setLogin(rs.getString("txt_login"));
			usuario.setNome(rs.getString("txt_nome"));
			return usuario;
		}
	}
	
}