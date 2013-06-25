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
import br.com.infoliver.sah.integracao.dao.IGrupoLaudoDAO;
import br.com.infoliver.sah.negocio.entity.GrupoLaudo;
import br.com.infoliver.sah.negocio.entity.GrupoLaudoPaciente;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.entity.Raca;

@Repository("grupoLaudoDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes"})
public class GrupoLaudoDAO extends DAOBase implements IGrupoLaudoDAO {

	@Override
	public List<GrupoLaudo> listar() {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_grupo_laudo",null);
		params.addValue("p_in_txt_descricao",null);
		params.addValue("p_in_seq_paciente",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_laudo",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo_laudo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),	
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),	
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<GrupoLaudo>() {
							public GrupoLaudo mapRow(ResultSet rs, int rowNum)throws SQLException {
								GrupoLaudo grupoLaudo = new GrupoLaudo();
								grupoLaudo.setSequencial(rs.getInt("seq_grupo_laudo"));
								grupoLaudo.setDescricao(rs.getString("txt_descricao"));
								return grupoLaudo;
							}
						}));
	
		return (List<GrupoLaudo>) out.get("p_out_cursor");
	}

	@Override
	public List<Paciente> listarPacienteGrupoLaudo(GrupoLaudo grupoLaudo) {
		String sql = "SELECT a.seq_paciente,a.txt_nome,a.txt_sexo,a.dat_nascimento, " +
				      "to_char(b.seq_raca,'00')txt_descricao,a.txt_nome_mae,a.txt_telefone_mae, " +
				      "a.txt_cep,a.txt_logradouro,a.txt_numero_logradouro,a.txt_complemento_logradouro, " +
				      "a.txt_bairro_logradouro,a.txt_municipio_logradouro, " +
				      "a.txt_codigo_ibge_municipio_logradouro,a.txt_uf_logradouro,a.txt_cns, " +
				      "a.txt_nome_responsavel,a.txt_telefone_responsavel,a.txt_telefone_01 " +
				 	  "FROM admsah001.sah_paciente a, " +
				           "admsah001.sah_raca b, " +
				      	   "admsah001.sah_grupo_laudo_paciente c " +
				 	  "WHERE a.seq_raca=b.seq_raca  " +
				      "AND a.seq_paciente=c.seq_paciente " +
				      "AND c.seq_grupo_laudo=" + grupoLaudo.getSequencial() + " " +
				      "order by 2;";
		
		List<Paciente> listaPacientes = jdbcTemplate.query(sql, new ParameterizedRowMapper<Paciente>() {
								public Paciente mapRow(ResultSet rs, int rowNum)throws SQLException {
									Paciente paciente = new Paciente();
									paciente.setSequencial(rs.getInt("seq_paciente"));
									paciente.setNome(rs.getString("txt_nome"));
									paciente.setSexo(rs.getString("txt_sexo"));
									paciente.setDataNascimento(rs.getDate("dat_nascimento"));
									paciente.setRaca(new Raca(rs.getString("txt_descricao")));
									paciente.setNomeMae(rs.getString("txt_nome_mae"));
									paciente.setTelefoneMae(rs.getString("txt_telefone_mae"));
									paciente.setCep(rs.getString("txt_cep"));
									paciente.setLogradouro(rs.getString("txt_logradouro"));
									paciente.setNumeroLogradouro(rs.getString("txt_numero_logradouro"));
									paciente.setComplementoLogradouro(rs.getString("txt_complemento_logradouro"));
									paciente.setBairroLogradouro(rs.getString("txt_bairro_logradouro"));
									paciente.setMunicipioLogradouro(rs.getString("txt_municipio_logradouro"));
									paciente.setCodigoIbgeMunicipioLogradouro(rs.getString("txt_codigo_ibge_municipio_logradouro"));
									paciente.setUfLogradouro(rs.getString("txt_uf_logradouro"));
									paciente.setCns(rs.getString("txt_cns"));
									paciente.setNomeResponsavel(rs.getString("txt_nome_responsavel"));
									paciente.setTelefoneResponsavel(rs.getString("txt_telefone_responsavel"));
									paciente.setTelefone01(rs.getString("txt_telefone_01"));
									return paciente;
								}
							});
		
		return listaPacientes;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserir(GrupoLaudo grupo) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",4);
		params.addValue("p_in_seq_grupo_laudo",null);
		params.addValue("p_in_txt_descricao",grupo.getDescricao());
		params.addValue("p_in_seq_paciente",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_laudo",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo_laudo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),	
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),	
				new SqlOutParameter("p_out_retorno", Types.OTHER));
		
		return (Integer) out.get("p_out_retorno");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer alterar(GrupoLaudo grupo) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",5);
		params.addValue("p_in_seq_grupo_laudo",grupo.getSequencial());
		params.addValue("p_in_txt_descricao",grupo.getDescricao());
		params.addValue("p_in_seq_paciente",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_laudo",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo_laudo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlOutParameter("p_out_retorno", Types.OTHER));
		
		return (Integer) out.get("p_out_retorno");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer excluir(GrupoLaudo grupo) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",6);
		params.addValue("p_in_seq_grupo_laudo",grupo.getSequencial());
		params.addValue("p_in_txt_descricao",null);
		params.addValue("p_in_seq_paciente",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_laudo",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo_laudo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlOutParameter("p_out_retorno", Types.OTHER));
		
		return (Integer) out.get("p_out_retorno");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserirGrupoLaudoPaciente(GrupoLaudoPaciente grupoLaudoPaciente) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",7);
		params.addValue("p_in_seq_grupo_laudo",grupoLaudoPaciente.getSequencialGrupo());
		params.addValue("p_in_txt_descricao",null);
		params.addValue("p_in_seq_paciente",grupoLaudoPaciente.getSequencialPaciente());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_laudo",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo_laudo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlOutParameter("p_out_retorno", Types.OTHER));
		
		return (Integer) out.get("p_out_retorno");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer excluirGrupoLaudoPaciente(GrupoLaudoPaciente grupoLaudoPaciente) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",8);
		params.addValue("p_in_seq_grupo_laudo",grupoLaudoPaciente.getSequencialGrupo());
		params.addValue("p_in_txt_descricao",null);
		params.addValue("p_in_seq_paciente",grupoLaudoPaciente.getSequencialPaciente());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_grupo_laudo",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_grupo_laudo",Types.INTEGER),	
				new SqlParameter("p_in_txt_descricao",Types.VARCHAR),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlOutParameter("p_out_retorno", Types.OTHER));
		
		return (Integer) out.get("p_out_retorno");
	}
}