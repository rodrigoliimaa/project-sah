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
import br.com.infoliver.sah.integracao.dao.IEmpresaDAO;
import br.com.infoliver.sah.negocio.entity.Empresa;

@Repository("empresaDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes","unused"})
public class EmpresaDAO extends DAOBase implements IEmpresaDAO {

	@Override
	public Empresa consultar() {
		
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_empresa",null);
		params.addValue("p_in_txt_razao_social",null);
		params.addValue("p_in_txt_nome_fantasia",null);
		params.addValue("p_in_txt_cnpj",null);
		params.addValue("p_in_txt_cnss",null);
		params.addValue("p_in_txt_ie",null);
		params.addValue("p_in_txt_endereco",null);
		params.addValue("p_in_txt_telefone",null);
		params.addValue("p_in_txt_nome_presidente",null);
		params.addValue("p_in_txt_rg_presidente",null);
		params.addValue("p_in_txt_cpf_presidente",null);
		params.addValue("p_in_img_logo",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_empresa",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_empresa",Types.INTEGER),
				new SqlParameter("p_in_txt_razao_social",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_fantasia",Types.VARCHAR),
				new SqlParameter("p_in_txt_cnpj",Types.VARCHAR),
				new SqlParameter("p_in_txt_cnss",Types.VARCHAR),
				new SqlParameter("p_in_txt_ie",Types.VARCHAR),
				new SqlParameter("p_in_txt_endereco",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_presidente",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg_presidente",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf_presidente",Types.VARCHAR),
				new SqlParameter("p_in_img_logo",Types.BINARY),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Empresa>() {
							public Empresa mapRow(ResultSet rs, int rowNum)throws SQLException {
								Empresa empresa = new Empresa();
								empresa.setSequencial(rs.getInt("seq_empresa"));
								empresa.setRazaoSocial(rs.getString("txt_razao_social"));
								empresa.setNomeFantasia(rs.getString("txt_nome_fantasia"));
								empresa.setCnpj(rs.getString("txt_cnpj"));
								empresa.setCnss(rs.getString("txt_cnss"));
								empresa.setIe(rs.getString("txt_ie"));
								empresa.setEndereco(rs.getString("txt_endereco"));
								empresa.setTelefone(rs.getString("txt_telefone"));
								empresa.setNomePresidente(rs.getString("txt_nome_presidente"));
								empresa.setRgPresidente(rs.getString("txt_rg_presidente"));
								empresa.setCpfPresidente(rs.getString("txt_cpf_presidente"));
								empresa.setImagemLogo(rs.getBytes("img_logo"));
								return empresa;
							}
						}));
		List<Empresa> lista=(List<Empresa>) out.get("p_out_cursor");
		return lista.size()>0?lista.get(0):null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void inserir(Empresa empresa) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",2);
		params.addValue("p_in_seq_empresa",null);
		params.addValue("p_in_txt_razao_social",empresa.getRazaoSocial());
		params.addValue("p_in_txt_nome_fantasia",empresa.getNomeFantasia());
		params.addValue("p_in_txt_cnpj",empresa.getCnpj());
		params.addValue("p_in_txt_cnss",empresa.getCnss());
		params.addValue("p_in_txt_ie",empresa.getIe());
		params.addValue("p_in_txt_endereco",empresa.getEndereco());
		params.addValue("p_in_txt_telefone",empresa.getTelefone());
		params.addValue("p_in_txt_nome_presidente",empresa.getNomePresidente());
		params.addValue("p_in_txt_rg_presidente",empresa.getRgPresidente());
		params.addValue("p_in_txt_cpf_presidente",empresa.getCpfPresidente());
		params.addValue("p_in_img_logo",empresa.getImagemLogo());		
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_empresa",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_empresa",Types.INTEGER),
				new SqlParameter("p_in_txt_razao_social",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_fantasia",Types.VARCHAR),
				new SqlParameter("p_in_txt_cnpj",Types.VARCHAR),
				new SqlParameter("p_in_txt_cnss",Types.VARCHAR),
				new SqlParameter("p_in_txt_ie",Types.VARCHAR),
				new SqlParameter("p_in_txt_endereco",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_presidente",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg_presidente",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf_presidente",Types.VARCHAR),
				new SqlParameter("p_in_img_logo",Types.BINARY));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(Empresa empresa) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",3);
		params.addValue("p_in_seq_empresa",empresa.getSequencial());
		params.addValue("p_in_txt_razao_social",empresa.getRazaoSocial());
		params.addValue("p_in_txt_nome_fantasia",empresa.getNomeFantasia());
		params.addValue("p_in_txt_cnpj",empresa.getCnpj());
		params.addValue("p_in_txt_cnss",empresa.getCnss());
		params.addValue("p_in_txt_ie",empresa.getIe());
		params.addValue("p_in_txt_endereco",empresa.getEndereco());
		params.addValue("p_in_txt_telefone",empresa.getTelefone());
		params.addValue("p_in_txt_nome_presidente",empresa.getNomePresidente());
		params.addValue("p_in_txt_rg_presidente",empresa.getRgPresidente());
		params.addValue("p_in_txt_cpf_presidente",empresa.getCpfPresidente());
		params.addValue("p_in_img_logo",empresa.getImagemLogo());		
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_empresa",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_empresa",Types.INTEGER),
				new SqlParameter("p_in_txt_razao_social",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_fantasia",Types.VARCHAR),
				new SqlParameter("p_in_txt_cnpj",Types.VARCHAR),
				new SqlParameter("p_in_txt_cnss",Types.VARCHAR),
				new SqlParameter("p_in_txt_ie",Types.VARCHAR),
				new SqlParameter("p_in_txt_endereco",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_presidente",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg_presidente",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf_presidente",Types.VARCHAR),
				new SqlParameter("p_in_img_logo",Types.BINARY));
	}
}