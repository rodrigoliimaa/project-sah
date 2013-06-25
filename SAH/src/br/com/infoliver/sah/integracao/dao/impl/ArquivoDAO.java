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
import br.com.infoliver.sah.integracao.dao.IArquivoDAO;
import br.com.infoliver.sah.negocio.entity.Arquivo;

@Repository("arquivoDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes","unused"})
public class ArquivoDAO extends DAOBase implements IArquivoDAO {

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void inserir(Arquivo arquivo) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",2);
		params.addValue("p_in_seq_arquivo",null);
		params.addValue("p_in_seq_usuario",arquivo.getUsuario().getSequencial());
		params.addValue("p_in_seq_paciente",arquivo.getPaciente().getSequencial());
		params.addValue("p_in_txt_nome",arquivo.getNome());
		params.addValue("p_in_num_tamanho",arquivo.getTamanho());
		params.addValue("p_in_img_arquivo",arquivo.getImagemArquivo());
		params.addValue("p_in_txt_codigo_hash",arquivo.getCodigoHash());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_arquivo",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_arquivo",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_num_tamanho",Types.INTEGER),
				new SqlParameter("p_in_img_arquivo",Types.BINARY),		
				new SqlParameter("p_in_txt_codigo_hash",Types.VARCHAR));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Arquivo> excluir(Arquivo arquivo) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("p_in_tipo_acao",3);
		params.addValue("p_in_seq_arquivo",arquivo.getSequencial());
		params.addValue("p_in_seq_usuario",null);
		params.addValue("p_in_seq_paciente",arquivo.getPaciente().getSequencial());
		params.addValue("p_in_txt_nome",null);
		params.addValue("p_in_num_tamanho",null);
		params.addValue("p_in_img_arquivo",null);
		params.addValue("p_in_txt_codigo_hash",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_arquivo",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_arquivo",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_num_tamanho",Types.INTEGER),
				new SqlParameter("p_in_img_arquivo",Types.BINARY),		
				new SqlParameter("p_in_txt_codigo_hash",Types.VARCHAR),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
				new ParameterizedRowMapper<Arquivo>() {
					public Arquivo mapRow(ResultSet rs, int rowNum)throws SQLException {
						Arquivo arquivo = new Arquivo();
						arquivo.setSequencial(rs.getInt("seq_arquivo"));
						arquivo.setNome(rs.getString("txt_nome"));
						arquivo.setTamanho(rs.getInt("num_tamanho"));
						return arquivo;
					}
				}));
		return (List<Arquivo>) out.get("p_out_cursor");
	}

	@Override
	public List<Arquivo> listar(Integer sequencialPaciente) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_arquivo",null);
		params.addValue("p_in_seq_usuario",null);
		params.addValue("p_in_seq_paciente",sequencialPaciente);
		params.addValue("p_in_txt_nome",null);
		params.addValue("p_in_num_tamanho",null);
		params.addValue("p_in_img_arquivo",null);
		params.addValue("p_in_txt_codigo_hash",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_arquivo",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_arquivo",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_num_tamanho",Types.INTEGER),
				new SqlParameter("p_in_img_arquivo",Types.BINARY),		
				new SqlParameter("p_in_txt_codigo_hash",Types.VARCHAR),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
				new ParameterizedRowMapper<Arquivo>() {
					public Arquivo mapRow(ResultSet rs, int rowNum)throws SQLException {
						Arquivo arquivo = new Arquivo();
						arquivo.setSequencial(rs.getInt("seq_arquivo"));
						arquivo.setNome(rs.getString("txt_nome"));
						arquivo.setTamanho(rs.getInt("num_tamanho"));
						return arquivo;
					}
				}));
		return (List<Arquivo>) out.get("p_out_cursor");
	}

	@Override
	public Arquivo consultarImagem(Integer sequencial) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("p_in_tipo_acao",4);
		params.addValue("p_in_seq_arquivo",sequencial);
		params.addValue("p_in_seq_usuario",null);
		params.addValue("p_in_seq_paciente",null);
		params.addValue("p_in_txt_nome",null);
		params.addValue("p_in_num_tamanho",null);
		params.addValue("p_in_img_arquivo",null);
		params.addValue("p_in_txt_codigo_hash",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_arquivo",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_arquivo",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_num_tamanho",Types.INTEGER),
				new SqlParameter("p_in_img_arquivo",Types.BINARY),		
				new SqlParameter("p_in_txt_codigo_hash",Types.VARCHAR),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
				new ParameterizedRowMapper<Arquivo>() {
					public Arquivo mapRow(ResultSet rs, int rowNum)throws SQLException {
						Arquivo arquivo = new Arquivo();
						arquivo.setSequencial(rs.getInt("seq_arquivo"));
						arquivo.setNome(rs.getString("txt_nome"));
						arquivo.setImagemArquivo(rs.getBytes("img_arquivo"));
						arquivo.setCodigoHash(rs.getString("txt_codigo_hash"));
						return arquivo;
					}
				}));
		List<Arquivo> lista=(List<Arquivo>) out.get("p_out_cursor");
		return (lista.isEmpty()? null:lista.get(0));
	}
}