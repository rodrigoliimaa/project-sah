package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.integracao.dao.IPacienteDAO;
import br.com.infoliver.sah.negocio.entity.Encaminhador;
import br.com.infoliver.sah.negocio.entity.Escolaridade;
import br.com.infoliver.sah.negocio.entity.Fornecedor;
import br.com.infoliver.sah.negocio.entity.GrupoLaudo;
import br.com.infoliver.sah.negocio.entity.Movimentacao;
import br.com.infoliver.sah.negocio.entity.Ocupacao;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.entity.Raca;
import br.com.infoliver.sah.negocio.entity.TipoResponsavel;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

@Repository("pacienteDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes","unused"})
public class PacienteDAO extends DAOBase implements IPacienteDAO {
	
	@Override
	public Paciente consultar(Integer sequencial) {
		String sql = "select * from admsah001.sah_paciente" +
				" where seq_paciente = " + sequencial;
		
		return jdbcTemplate.query(sql, new PacienteRowMapper()).get(0);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserir(Paciente paciente) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("p_in_tipo_acao",4);
		params.addValue("p_in_seq_paciente",null);
		params.addValue("p_in_txt_nome",paciente.getNome());
		params.addValue("p_in_txt_cpf",paciente.getCpf());
		params.addValue("p_in_txt_cns",paciente.getCns());
		params.addValue("p_in_txt_rg",paciente.getRg());		
		params.addValue("p_in_seq_raca",paciente.getRaca().getSequencial());
		params.addValue("p_in_seq_escolaridade",paciente.getEscolaridade().getSequencial());
		params.addValue("p_in_seq_encaminhador",paciente.getEncaminhador().getSequencial());
		params.addValue("p_in_seq_tipo_responsavel",paciente.getTipoResponsavel().getSequencial());
		params.addValue("p_in_txt_estado_civil",paciente.getEstadoCivil());
		params.addValue("p_in_txt_sexo",paciente.getSexo());
		params.addValue("p_in_dat_nascimento",paciente.getDataNascimento());
		params.addValue("p_in_txt_tipo_sanguineo",paciente.getTipoSanguineo());
		params.addValue("p_in_txt_uf_naturalidade",paciente.getUfNaturalidade());
		params.addValue("p_in_txt_municipio_naturalidade",paciente.getMunicipioNaturalidade());
		params.addValue("p_in_txt_nome_conjuge",paciente.getNomeConjuge());
		params.addValue("p_in_txt_nome_pai",paciente.getNomePai());
		params.addValue("p_in_txt_nome_mae",paciente.getNomeMae());
		params.addValue("p_in_txt_telefone_mae",paciente.getTelefoneMae());
		params.addValue("p_in_txt_cep",paciente.getCep());
		params.addValue("p_in_txt_logradouro",paciente.getLogradouro());
		params.addValue("p_in_txt_numero_logradouro",paciente.getNumeroLogradouro());
		params.addValue("p_in_txt_complemento_logradouro",paciente.getComplementoLogradouro());
		params.addValue("p_in_txt_referencia_logradouro",paciente.getReferenciaLogradouro());
		params.addValue("p_in_txt_bairro_logradouro",paciente.getBairroLogradouro());
		params.addValue("p_in_txt_municipio_logradouro",paciente.getMunicipioLogradouro());
		params.addValue("p_in_txt_codigo_ibge_municipio_logradouro",paciente.getCodigoIbgeMunicipioLogradouro());
		params.addValue("p_in_txt_uf_logradouro",paciente.getUfLogradouro());
		params.addValue("p_in_txt_telefone_01",paciente.getTelefone01());
		params.addValue("p_in_txt_telefone_02",paciente.getTelefone02());
		params.addValue("p_in_txt_telefone_03",paciente.getTelefone03());
		params.addValue("p_in_txt_endereco_eletronico",paciente.getEnderecoEletronico());
		params.addValue("p_in_img_paciente",paciente.getImagemPaciente());
		params.addValue("p_in_txt_orgao_emissor",paciente.getOrgaoEmissor());
		params.addValue("p_in_dat_expedicao",paciente.getDataExpedicao());
		params.addValue("p_in_txt_nome_cartorio",paciente.getNomeCartorio());
		params.addValue("p_in_num_registro",paciente.getNumeroRegistro());
		params.addValue("p_in_txt_codigo_livro",paciente.getCodigoLivro());
		params.addValue("p_in_num_folha_livro",paciente.getNumeroFolhaLivro());
		params.addValue("p_in_dat_registro_livro",paciente.getDataRegistroLivro());
		params.addValue("p_in_txt_ind_associado",paciente.getIndicadorAssociado());
		params.addValue("p_in_txt_ind_estudo",paciente.getIndicadorEstudo());
		params.addValue("p_in_txt_local_estudo",paciente.getLocalEstudo());
		params.addValue("p_in_txt_ind_trabalho",paciente.getIndicadorTrabalho());
		params.addValue("p_in_txt_local_trabalho",paciente.getLocalTrabalho());
		params.addValue("p_in_txt_nome_responsavel",paciente.getNomeResponsavel());
		params.addValue("p_in_txt_rg_responsavel",paciente.getRgResponsavel());
		params.addValue("p_in_txt_cpf_responsavel",paciente.getCpfResponsavel());
		params.addValue("p_in_txt_telefone_responsavel",paciente.getTelefoneResponsavel());
		params.addValue("p_in_img_digital",paciente.getImagemDigital());
		params.addValue("p_in_txt_uf_orgao_emissor",paciente.getUfOrgaoEmissor());
		params.addValue("p_in_seq_ocupacao",paciente.getOcupacao().getSequencial());
		params.addValue("p_in_seq_usuario_cadastro",paciente.getUsuarioCadastro().getSequencial());
		params.addValue("p_in_txt_ind_img_paciente",paciente.getIndicadorImagemPaciente());
		params.addValue("p_in_txt_ind_img_digital",paciente.getIndicadorImagemDigital());
		params.addValue("p_in_num_qtd_paginacao",null);
		params.addValue("p_in_num_inicio_paginacao",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_paciente",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf",Types.VARCHAR),
				new SqlParameter("p_in_txt_cns",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg",Types.VARCHAR),
				new SqlParameter("p_in_seq_raca",Types.INTEGER),
				new SqlParameter("p_in_seq_escolaridade",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhador",Types.INTEGER),
				new SqlParameter("p_in_seq_tipo_responsavel",Types.INTEGER),
				new SqlParameter("p_in_txt_estado_civil",Types.VARCHAR),
				new SqlParameter("p_in_txt_sexo",Types.VARCHAR),
				new SqlParameter("p_in_dat_nascimento",Types.DATE),
				new SqlParameter("p_in_txt_tipo_sanguineo",Types.VARCHAR),
				new SqlParameter("p_in_txt_uf_naturalidade",Types.VARCHAR),
				new SqlParameter("p_in_txt_municipio_naturalidade",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_conjuge",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_pai",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_mae",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_mae",Types.VARCHAR),
				new SqlParameter("p_in_txt_cep",Types.VARCHAR),
				new SqlParameter("p_in_txt_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_numero_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_complemento_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_referencia_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_bairro_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_municipio_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_codigo_ibge_municipio_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_uf_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_01",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_02",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_03",Types.VARCHAR),
				new SqlParameter("p_in_txt_endereco_eletronico",Types.VARCHAR),
				new SqlParameter("p_in_img_paciente",Types.BINARY),
				new SqlParameter("p_in_txt_orgao_emissor",Types.VARCHAR),
				new SqlParameter("p_in_dat_expedicao",Types.DATE),
				new SqlParameter("p_in_txt_nome_cartorio",Types.VARCHAR),
				new SqlParameter("p_in_num_registro",Types.INTEGER),
				new SqlParameter("p_in_txt_codigo_livro",Types.VARCHAR),
				new SqlParameter("p_in_num_folha_livro",Types.INTEGER),
				new SqlParameter("p_in_dat_registro_livro",Types.DATE),
				new SqlParameter("p_in_txt_ind_associado",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_estudo",Types.VARCHAR),
				new SqlParameter("p_in_txt_local_estudo",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_trabalho",Types.VARCHAR),
				new SqlParameter("p_in_txt_local_trabalho",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_img_digital",Types.BINARY),
				new SqlParameter("p_in_txt_uf_orgao_emissor",Types.VARCHAR),
				new SqlParameter("p_in_seq_ocupacao",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario_cadastro",Types.INTEGER),
				new SqlParameter("p_in_txt_ind_img_paciente",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_img_digital",Types.VARCHAR),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
				new ParameterizedRowMapper<Object>() {
					public Object mapRow(ResultSet rs, int rowNum)throws SQLException {
						return rs.getInt("seq_paciente");
					}
				}));
		return (Integer)((List)out.get("p_out_cursor")).get(0);					
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(Paciente paciente) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("p_in_tipo_acao",6);
		params.addValue("p_in_seq_paciente",paciente.getSequencial());
		params.addValue("p_in_txt_nome",paciente.getNome());
		params.addValue("p_in_txt_cpf",paciente.getCpf());
		params.addValue("p_in_txt_cns",paciente.getCns());
		params.addValue("p_in_txt_rg",paciente.getRg());		
		params.addValue("p_in_seq_raca",paciente.getRaca().getSequencial());
		params.addValue("p_in_seq_escolaridade",paciente.getEscolaridade().getSequencial());
		params.addValue("p_in_seq_encaminhador",paciente.getEncaminhador().getSequencial());
		params.addValue("p_in_seq_tipo_responsavel",paciente.getTipoResponsavel().getSequencial());
		params.addValue("p_in_txt_estado_civil",paciente.getEstadoCivil());
		params.addValue("p_in_txt_sexo",paciente.getSexo());
		params.addValue("p_in_dat_nascimento",paciente.getDataNascimento());
		params.addValue("p_in_txt_tipo_sanguineo",paciente.getTipoSanguineo());
		params.addValue("p_in_txt_uf_naturalidade",paciente.getUfNaturalidade());
		params.addValue("p_in_txt_municipio_naturalidade",paciente.getMunicipioNaturalidade());
		params.addValue("p_in_txt_nome_conjuge",paciente.getNomeConjuge());
		params.addValue("p_in_txt_nome_pai",paciente.getNomePai());
		params.addValue("p_in_txt_nome_mae",paciente.getNomeMae());
		params.addValue("p_in_txt_telefone_mae",paciente.getTelefoneMae());
		params.addValue("p_in_txt_cep",paciente.getCep());
		params.addValue("p_in_txt_logradouro",paciente.getLogradouro());
		params.addValue("p_in_txt_numero_logradouro",paciente.getNumeroLogradouro());
		params.addValue("p_in_txt_complemento_logradouro",paciente.getComplementoLogradouro());
		params.addValue("p_in_txt_referencia_logradouro",paciente.getReferenciaLogradouro());
		params.addValue("p_in_txt_bairro_logradouro",paciente.getBairroLogradouro());
		params.addValue("p_in_txt_municipio_logradouro",paciente.getMunicipioLogradouro());
		params.addValue("p_in_txt_codigo_ibge_municipio_logradouro",paciente.getCodigoIbgeMunicipioLogradouro());
		params.addValue("p_in_txt_uf_logradouro",paciente.getUfLogradouro());
		params.addValue("p_in_txt_telefone_01",paciente.getTelefone01());
		params.addValue("p_in_txt_telefone_02",paciente.getTelefone02());
		params.addValue("p_in_txt_telefone_03",paciente.getTelefone03());
		params.addValue("p_in_txt_endereco_eletronico",paciente.getEnderecoEletronico());
		params.addValue("p_in_img_paciente",paciente.getImagemPaciente());
		params.addValue("p_in_txt_orgao_emissor",paciente.getOrgaoEmissor());
		params.addValue("p_in_dat_expedicao",paciente.getDataExpedicao());
		params.addValue("p_in_txt_nome_cartorio",paciente.getNomeCartorio());
		params.addValue("p_in_num_registro",paciente.getNumeroRegistro());
		params.addValue("p_in_txt_codigo_livro",paciente.getCodigoLivro());
		params.addValue("p_in_num_folha_livro",paciente.getNumeroFolhaLivro());
		params.addValue("p_in_dat_registro_livro",paciente.getDataRegistroLivro());
		params.addValue("p_in_txt_ind_associado",paciente.getIndicadorAssociado());
		params.addValue("p_in_txt_ind_estudo",paciente.getIndicadorEstudo());
		params.addValue("p_in_txt_local_estudo",paciente.getLocalEstudo());
		params.addValue("p_in_txt_ind_trabalho",paciente.getIndicadorTrabalho());
		params.addValue("p_in_txt_local_trabalho",paciente.getLocalTrabalho());
		params.addValue("p_in_txt_nome_responsavel",paciente.getNomeResponsavel());
		params.addValue("p_in_txt_rg_responsavel",paciente.getRgResponsavel());
		params.addValue("p_in_txt_cpf_responsavel",paciente.getCpfResponsavel());
		params.addValue("p_in_txt_telefone_responsavel",paciente.getTelefoneResponsavel());
		params.addValue("p_in_img_digital",paciente.getImagemDigital());
		params.addValue("p_in_txt_uf_orgao_emissor",paciente.getUfOrgaoEmissor());
		params.addValue("p_in_seq_ocupacao",paciente.getOcupacao().getSequencial());
		params.addValue("p_in_seq_usuario_cadastro",paciente.getUsuarioCadastro().getSequencial());
		params.addValue("p_in_txt_ind_img_paciente",paciente.getIndicadorImagemPaciente());
		params.addValue("p_in_txt_ind_img_digital",paciente.getIndicadorImagemDigital());
		params.addValue("p_in_num_qtd_paginacao",null);
		params.addValue("p_in_num_inicio_paginacao",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_paciente",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf",Types.VARCHAR),
				new SqlParameter("p_in_txt_cns",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg",Types.VARCHAR),
				new SqlParameter("p_in_seq_raca",Types.INTEGER),
				new SqlParameter("p_in_seq_escolaridade",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhador",Types.INTEGER),
				new SqlParameter("p_in_seq_tipo_responsavel",Types.INTEGER),
				new SqlParameter("p_in_txt_estado_civil",Types.VARCHAR),
				new SqlParameter("p_in_txt_sexo",Types.VARCHAR),
				new SqlParameter("p_in_dat_nascimento",Types.DATE),
				new SqlParameter("p_in_txt_tipo_sanguineo",Types.VARCHAR),
				new SqlParameter("p_in_txt_uf_naturalidade",Types.VARCHAR),
				new SqlParameter("p_in_txt_municipio_naturalidade",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_conjuge",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_pai",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_mae",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_mae",Types.VARCHAR),
				new SqlParameter("p_in_txt_cep",Types.VARCHAR),
				new SqlParameter("p_in_txt_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_numero_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_complemento_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_referencia_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_bairro_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_municipio_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_codigo_ibge_municipio_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_uf_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_01",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_02",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_03",Types.VARCHAR),
				new SqlParameter("p_in_txt_endereco_eletronico",Types.VARCHAR),
				new SqlParameter("p_in_img_paciente",Types.BINARY),
				new SqlParameter("p_in_txt_orgao_emissor",Types.VARCHAR),
				new SqlParameter("p_in_dat_expedicao",Types.DATE),
				new SqlParameter("p_in_txt_nome_cartorio",Types.VARCHAR),
				new SqlParameter("p_in_num_registro",Types.INTEGER),
				new SqlParameter("p_in_txt_codigo_livro",Types.VARCHAR),
				new SqlParameter("p_in_num_folha_livro",Types.INTEGER),
				new SqlParameter("p_in_dat_registro_livro",Types.DATE),
				new SqlParameter("p_in_txt_ind_associado",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_estudo",Types.VARCHAR),
				new SqlParameter("p_in_txt_local_estudo",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_trabalho",Types.VARCHAR),
				new SqlParameter("p_in_txt_local_trabalho",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_img_digital",Types.BINARY),
				new SqlParameter("p_in_txt_uf_orgao_emissor",Types.VARCHAR),
				new SqlParameter("p_in_seq_ocupacao",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario_cadastro",Types.INTEGER),
				new SqlParameter("p_in_txt_ind_img_paciente",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_img_digital",Types.VARCHAR),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER));
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(Paciente paciente) throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("p_in_tipo_acao",5);
		params.addValue("p_in_seq_paciente",paciente.getSequencial());
		params.addValue("p_in_txt_nome",null);
		params.addValue("p_in_txt_cpf",null);
		params.addValue("p_in_txt_cns",null);
		params.addValue("p_in_txt_rg",null);
		params.addValue("p_in_seq_raca",null);
		params.addValue("p_in_seq_escolaridade",null);
		params.addValue("p_in_seq_encaminhador",null);
		params.addValue("p_in_seq_tipo_responsavel",null);
		params.addValue("p_in_txt_estado_civil",null);
		params.addValue("p_in_txt_sexo",null);
		params.addValue("p_in_dat_nascimento",null);
		params.addValue("p_in_txt_tipo_sanguineo",null);
		params.addValue("p_in_txt_uf_naturalidade",null);
		params.addValue("p_in_txt_municipio_naturalidade",null);
		params.addValue("p_in_txt_nome_conjuge",null);
		params.addValue("p_in_txt_nome_pai",null);
		params.addValue("p_in_txt_nome_mae",null);
		params.addValue("p_in_txt_telefone_mae",null);
		params.addValue("p_in_txt_cep",null);
		params.addValue("p_in_txt_logradouro",null);
		params.addValue("p_in_txt_numero_logradouro",null);
		params.addValue("p_in_txt_complemento_logradouro",null);
		params.addValue("p_in_txt_referencia_logradouro",null);
		params.addValue("p_in_txt_bairro_logradouro",null);
		params.addValue("p_in_txt_municipio_logradouro",null);
		params.addValue("p_in_txt_codigo_ibge_municipio_logradouro",null);
		params.addValue("p_in_txt_uf_logradouro",null);
		params.addValue("p_in_txt_telefone_01",null);
		params.addValue("p_in_txt_telefone_02",null);
		params.addValue("p_in_txt_telefone_03",null);
		params.addValue("p_in_txt_endereco_eletronico",null);
		params.addValue("p_in_img_paciente",null);
		params.addValue("p_in_txt_orgao_emissor",null);
		params.addValue("p_in_dat_expedicao",null);
		params.addValue("p_in_txt_nome_cartorio",null);
		params.addValue("p_in_num_registro",null);
		params.addValue("p_in_txt_codigo_livro",null);
		params.addValue("p_in_num_folha_livro",null);
		params.addValue("p_in_dat_registro_livro",null);
		params.addValue("p_in_txt_ind_associado",null);
		params.addValue("p_in_txt_ind_estudo",null);
		params.addValue("p_in_txt_local_estudo",null);
		params.addValue("p_in_txt_ind_trabalho",null);
		params.addValue("p_in_txt_local_trabalho",null);
		params.addValue("p_in_txt_nome_responsavel",null);
		params.addValue("p_in_txt_rg_responsavel",null);
		params.addValue("p_in_txt_cpf_responsavel",null);
		params.addValue("p_in_txt_telefone_responsavel",null);
		params.addValue("p_in_img_digital",null);
		params.addValue("p_in_txt_uf_orgao_emissor",null);
		params.addValue("p_in_seq_ocupacao",null);
		params.addValue("p_in_seq_usuario_cadastro",null);
		params.addValue("p_in_txt_ind_img_paciente",null);
		params.addValue("p_in_txt_ind_img_digital",null);
		params.addValue("p_in_num_qtd_paginacao",null);
		params.addValue("p_in_num_inicio_paginacao",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_paciente",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf",Types.VARCHAR),
				new SqlParameter("p_in_txt_cns",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg",Types.VARCHAR),
				new SqlParameter("p_in_seq_raca",Types.INTEGER),
				new SqlParameter("p_in_seq_escolaridade",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhador",Types.INTEGER),
				new SqlParameter("p_in_seq_tipo_responsavel",Types.INTEGER),
				new SqlParameter("p_in_txt_estado_civil",Types.VARCHAR),
				new SqlParameter("p_in_txt_sexo",Types.VARCHAR),
				new SqlParameter("p_in_dat_nascimento",Types.DATE),
				new SqlParameter("p_in_txt_tipo_sanguineo",Types.VARCHAR),
				new SqlParameter("p_in_txt_uf_naturalidade",Types.VARCHAR),
				new SqlParameter("p_in_txt_municipio_naturalidade",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_conjuge",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_pai",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_mae",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_mae",Types.VARCHAR),
				new SqlParameter("p_in_txt_cep",Types.VARCHAR),
				new SqlParameter("p_in_txt_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_numero_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_complemento_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_referencia_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_bairro_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_municipio_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_codigo_ibge_municipio_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_uf_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_01",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_02",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_03",Types.VARCHAR),
				new SqlParameter("p_in_txt_endereco_eletronico",Types.VARCHAR),
				new SqlParameter("p_in_img_paciente",Types.BINARY),
				new SqlParameter("p_in_txt_orgao_emissor",Types.VARCHAR),
				new SqlParameter("p_in_dat_expedicao",Types.DATE),
				new SqlParameter("p_in_txt_nome_cartorio",Types.VARCHAR),
				new SqlParameter("p_in_num_registro",Types.INTEGER),
				new SqlParameter("p_in_txt_codigo_livro",Types.VARCHAR),
				new SqlParameter("p_in_num_folha_livro",Types.INTEGER),
				new SqlParameter("p_in_dat_registro_livro",Types.DATE),
				new SqlParameter("p_in_txt_ind_associado",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_estudo",Types.VARCHAR),
				new SqlParameter("p_in_txt_local_estudo",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_trabalho",Types.VARCHAR),
				new SqlParameter("p_in_txt_local_trabalho",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_img_digital",Types.BINARY),
				new SqlParameter("p_in_txt_uf_orgao_emissor",Types.VARCHAR),
				new SqlParameter("p_in_seq_ocupacao",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario_cadastro",Types.INTEGER),
				new SqlParameter("p_in_txt_ind_img_paciente",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_img_digital",Types.VARCHAR),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER));
	}

	@Override
	public Integer totalRegitrosParaPaginacao(PaginacaoVO paciente) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("p_in_tipo_acao",2);
		params.addValue("p_in_seq_paciente",((Paciente)paciente.getEntidade()).getSequencial());
		params.addValue("p_in_txt_nome",((Paciente)paciente.getEntidade()).getNome());
		params.addValue("p_in_txt_cpf",((Paciente)paciente.getEntidade()).getCpf());
		params.addValue("p_in_txt_cns",((Paciente)paciente.getEntidade()).getCns());
		params.addValue("p_in_txt_rg",((Paciente)paciente.getEntidade()).getRg());
		params.addValue("p_in_seq_raca",null);
		params.addValue("p_in_seq_escolaridade",null);
		params.addValue("p_in_seq_encaminhador",null);
		params.addValue("p_in_seq_tipo_responsavel",null);
		params.addValue("p_in_txt_estado_civil",null);
		params.addValue("p_in_txt_sexo",null);
		params.addValue("p_in_dat_nascimento",null);
		params.addValue("p_in_txt_tipo_sanguineo",null);
		params.addValue("p_in_txt_uf_naturalidade",null);
		params.addValue("p_in_txt_municipio_naturalidade",null);
		params.addValue("p_in_txt_nome_conjuge",null);
		params.addValue("p_in_txt_nome_pai",null);
		params.addValue("p_in_txt_nome_mae",null);
		params.addValue("p_in_txt_telefone_mae",null);
		params.addValue("p_in_txt_cep",null);
		params.addValue("p_in_txt_logradouro",null);
		params.addValue("p_in_txt_numero_logradouro",null);
		params.addValue("p_in_txt_complemento_logradouro",null);
		params.addValue("p_in_txt_referencia_logradouro",null);
		params.addValue("p_in_txt_bairro_logradouro",null);
		params.addValue("p_in_txt_municipio_logradouro",null);
		params.addValue("p_in_txt_codigo_ibge_municipio_logradouro",null);
		params.addValue("p_in_txt_uf_logradouro",null);
		params.addValue("p_in_txt_telefone_01",null);
		params.addValue("p_in_txt_telefone_02",null);
		params.addValue("p_in_txt_telefone_03",null);
		params.addValue("p_in_txt_endereco_eletronico",null);
		params.addValue("p_in_img_paciente",null);
		params.addValue("p_in_txt_orgao_emissor",null);
		params.addValue("p_in_dat_expedicao",null);
		params.addValue("p_in_txt_nome_cartorio",null);
		params.addValue("p_in_num_registro",null);
		params.addValue("p_in_txt_codigo_livro",null);
		params.addValue("p_in_num_folha_livro",null);
		params.addValue("p_in_dat_registro_livro",null);
		params.addValue("p_in_txt_ind_associado",null);
		params.addValue("p_in_txt_ind_estudo",null);
		params.addValue("p_in_txt_local_estudo",null);
		params.addValue("p_in_txt_ind_trabalho",null);
		params.addValue("p_in_txt_local_trabalho",null);
		params.addValue("p_in_txt_nome_responsavel",null);
		params.addValue("p_in_txt_rg_responsavel",null);
		params.addValue("p_in_txt_cpf_responsavel",null);
		params.addValue("p_in_txt_telefone_responsavel",null);
		params.addValue("p_in_img_digital",null);
		params.addValue("p_in_txt_uf_orgao_emissor",null);
		params.addValue("p_in_seq_ocupacao",null);
		params.addValue("p_in_seq_usuario_cadastro",null);
		params.addValue("p_in_txt_ind_img_paciente",null);
		params.addValue("p_in_txt_ind_img_digital",null);
		params.addValue("p_in_num_qtd_paginacao",paciente.getQuantidadePaginacao());
		params.addValue("p_in_num_inicio_paginacao",paciente.getInicioPaginacao());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_paciente",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf",Types.VARCHAR),
				new SqlParameter("p_in_txt_cns",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg",Types.VARCHAR),
				new SqlParameter("p_in_seq_raca",Types.INTEGER),
				new SqlParameter("p_in_seq_escolaridade",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhador",Types.INTEGER),
				new SqlParameter("p_in_seq_tipo_responsavel",Types.INTEGER),
				new SqlParameter("p_in_txt_estado_civil",Types.VARCHAR),
				new SqlParameter("p_in_txt_sexo",Types.VARCHAR),
				new SqlParameter("p_in_dat_nascimento",Types.DATE),
				new SqlParameter("p_in_txt_tipo_sanguineo",Types.VARCHAR),
				new SqlParameter("p_in_txt_uf_naturalidade",Types.VARCHAR),
				new SqlParameter("p_in_txt_municipio_naturalidade",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_conjuge",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_pai",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_mae",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_mae",Types.VARCHAR),
				new SqlParameter("p_in_txt_cep",Types.VARCHAR),
				new SqlParameter("p_in_txt_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_numero_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_complemento_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_referencia_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_bairro_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_municipio_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_codigo_ibge_municipio_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_uf_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_01",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_02",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_03",Types.VARCHAR),
				new SqlParameter("p_in_txt_endereco_eletronico",Types.VARCHAR),
				new SqlParameter("p_in_img_paciente",Types.BINARY),
				new SqlParameter("p_in_txt_orgao_emissor",Types.VARCHAR),
				new SqlParameter("p_in_dat_expedicao",Types.DATE),
				new SqlParameter("p_in_txt_nome_cartorio",Types.VARCHAR),
				new SqlParameter("p_in_num_registro",Types.INTEGER),
				new SqlParameter("p_in_txt_codigo_livro",Types.VARCHAR),
				new SqlParameter("p_in_num_folha_livro",Types.INTEGER),
				new SqlParameter("p_in_dat_registro_livro",Types.DATE),
				new SqlParameter("p_in_txt_ind_associado",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_estudo",Types.VARCHAR),
				new SqlParameter("p_in_txt_local_estudo",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_trabalho",Types.VARCHAR),
				new SqlParameter("p_in_txt_local_trabalho",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_img_digital",Types.BINARY),
				new SqlParameter("p_in_txt_uf_orgao_emissor",Types.VARCHAR),
				new SqlParameter("p_in_seq_ocupacao",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario_cadastro",Types.INTEGER),
				new SqlParameter("p_in_txt_ind_img_paciente",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_img_digital",Types.VARCHAR),
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
	public List<Paciente> listarPaginado(PaginacaoVO paciente) {
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao",1);
		params.addValue("p_in_seq_paciente",((Paciente)paciente.getEntidade()).getSequencial());
		params.addValue("p_in_txt_nome",((Paciente)paciente.getEntidade()).getNome());
		params.addValue("p_in_txt_cpf",((Paciente)paciente.getEntidade()).getCpf());
		params.addValue("p_in_txt_cns",((Paciente)paciente.getEntidade()).getCns());
		params.addValue("p_in_txt_rg",((Paciente)paciente.getEntidade()).getRg());
		params.addValue("p_in_seq_raca",null);
		params.addValue("p_in_seq_escolaridade",null);
		params.addValue("p_in_seq_encaminhador",null);
		params.addValue("p_in_seq_tipo_responsavel",null);
		params.addValue("p_in_txt_estado_civil",null);
		params.addValue("p_in_txt_sexo",null);
		params.addValue("p_in_dat_nascimento",null);
		params.addValue("p_in_txt_tipo_sanguineo",null);
		params.addValue("p_in_txt_uf_naturalidade",null);
		params.addValue("p_in_txt_municipio_naturalidade",null);
		params.addValue("p_in_txt_nome_conjuge",null);
		params.addValue("p_in_txt_nome_pai",null);
		params.addValue("p_in_txt_nome_mae",null);
		params.addValue("p_in_txt_telefone_mae",null);
		params.addValue("p_in_txt_cep",null);
		params.addValue("p_in_txt_logradouro",null);
		params.addValue("p_in_txt_numero_logradouro",null);
		params.addValue("p_in_txt_complemento_logradouro",null);
		params.addValue("p_in_txt_referencia_logradouro",null);
		params.addValue("p_in_txt_bairro_logradouro",null);
		params.addValue("p_in_txt_municipio_logradouro",null);
		params.addValue("p_in_txt_codigo_ibge_municipio_logradouro",null);
		params.addValue("p_in_txt_uf_logradouro",null);
		params.addValue("p_in_txt_telefone_01",null);
		params.addValue("p_in_txt_telefone_02",null);
		params.addValue("p_in_txt_telefone_03",null);
		params.addValue("p_in_txt_endereco_eletronico",null);
		params.addValue("p_in_img_paciente",null);
		params.addValue("p_in_txt_orgao_emissor",null);
		params.addValue("p_in_dat_expedicao",null);
		params.addValue("p_in_txt_nome_cartorio",null);
		params.addValue("p_in_num_registro",null);
		params.addValue("p_in_txt_codigo_livro",null);
		params.addValue("p_in_num_folha_livro",null);
		params.addValue("p_in_dat_registro_livro",null);
		params.addValue("p_in_txt_ind_associado",null);
		params.addValue("p_in_txt_ind_estudo",null);
		params.addValue("p_in_txt_local_estudo",null);
		params.addValue("p_in_txt_ind_trabalho",null);
		params.addValue("p_in_txt_local_trabalho",null);
		params.addValue("p_in_txt_nome_responsavel",null);
		params.addValue("p_in_txt_rg_responsavel",null);
		params.addValue("p_in_txt_cpf_responsavel",null);
		params.addValue("p_in_txt_telefone_responsavel",null);
		params.addValue("p_in_img_digital",null);
		params.addValue("p_in_txt_uf_orgao_emissor",null);
		params.addValue("p_in_seq_ocupacao",null);
		params.addValue("p_in_seq_usuario_cadastro",null);
		params.addValue("p_in_txt_ind_img_paciente",null);
		params.addValue("p_in_txt_ind_img_digital",null);
		params.addValue("p_in_num_qtd_paginacao",paciente.getQuantidadePaginacao());
		params.addValue("p_in_num_inicio_paginacao",paciente.getInicioPaginacao());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_paciente",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf",Types.VARCHAR),
				new SqlParameter("p_in_txt_cns",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg",Types.VARCHAR),
				new SqlParameter("p_in_seq_raca",Types.INTEGER),
				new SqlParameter("p_in_seq_escolaridade",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhador",Types.INTEGER),
				new SqlParameter("p_in_seq_tipo_responsavel",Types.INTEGER),
				new SqlParameter("p_in_txt_estado_civil",Types.VARCHAR),
				new SqlParameter("p_in_txt_sexo",Types.VARCHAR),
				new SqlParameter("p_in_dat_nascimento",Types.DATE),
				new SqlParameter("p_in_txt_tipo_sanguineo",Types.VARCHAR),
				new SqlParameter("p_in_txt_uf_naturalidade",Types.VARCHAR),
				new SqlParameter("p_in_txt_municipio_naturalidade",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_conjuge",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_pai",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_mae",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_mae",Types.VARCHAR),
				new SqlParameter("p_in_txt_cep",Types.VARCHAR),
				new SqlParameter("p_in_txt_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_numero_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_complemento_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_referencia_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_bairro_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_municipio_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_codigo_ibge_municipio_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_uf_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_01",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_02",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_03",Types.VARCHAR),
				new SqlParameter("p_in_txt_endereco_eletronico",Types.VARCHAR),
				new SqlParameter("p_in_img_paciente",Types.BINARY),
				new SqlParameter("p_in_txt_orgao_emissor",Types.VARCHAR),
				new SqlParameter("p_in_dat_expedicao",Types.DATE),
				new SqlParameter("p_in_txt_nome_cartorio",Types.VARCHAR),
				new SqlParameter("p_in_num_registro",Types.INTEGER),
				new SqlParameter("p_in_txt_codigo_livro",Types.VARCHAR),
				new SqlParameter("p_in_num_folha_livro",Types.INTEGER),
				new SqlParameter("p_in_dat_registro_livro",Types.DATE),
				new SqlParameter("p_in_txt_ind_associado",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_estudo",Types.VARCHAR),
				new SqlParameter("p_in_txt_local_estudo",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_trabalho",Types.VARCHAR),
				new SqlParameter("p_in_txt_local_trabalho",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_img_digital",Types.BINARY),
				new SqlParameter("p_in_txt_uf_orgao_emissor",Types.VARCHAR),
				new SqlParameter("p_in_seq_ocupacao",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario_cadastro",Types.INTEGER),
				new SqlParameter("p_in_txt_ind_img_paciente",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_img_digital",Types.VARCHAR),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
				new ParameterizedRowMapper<Paciente>() {
					public Paciente mapRow(ResultSet rs, int rowNum)throws SQLException {
						Paciente paciente = new Paciente();
						paciente.setSequencial(rs.getInt("seq_paciente"));
						paciente.setSequencialPacienteAnterior(rs.getInt("seq_paciente_anterior"));
						paciente.setNome(rs.getString("txt_nome"));
						paciente.setEstadoCivil(rs.getString("txt_estado_civil"));
						paciente.setSexo(rs.getString("txt_sexo"));
						paciente.setDataNascimento(rs.getDate("dat_nascimento"));
						paciente.setTipoSanguineo(rs.getString("txt_tipo_sanguineo"));
						//------------------------------------------------------------
						paciente.setRaca(new Raca(rs.getString("txt_descricao_raca")));
						paciente.setEscolaridade(new Escolaridade(rs.getString("txt_descricao_escolaridade")));
						paciente.setEncaminhador(new Encaminhador(rs.getString("txt_descricao_encaminhador")));
						paciente.setTipoResponsavel(new TipoResponsavel(rs.getString("txt_descricao_tipo_responsavel")));
						paciente.setOcupacao(new Ocupacao(rs.getString("txt_descricao_ocupacao")));
						paciente.setUsuarioCadastro(new Usuario(rs.getString("txt_usuario_cadastro")));
						//------------------------------------------------------------
						paciente.setUfNaturalidade(rs.getString("txt_uf_naturalidade"));
						paciente.setMunicipioNaturalidade(rs.getString("txt_municipio_naturalidade"));
						paciente.setNomeConjuge(rs.getString("txt_nome_conjuge"));
						paciente.setNomePai(rs.getString("txt_nome_pai"));
						paciente.setNomeMae(rs.getString("txt_nome_mae"));
						paciente.setTelefoneMae(rs.getString("txt_telefone_mae"));
						paciente.setCep(rs.getString("txt_cep"));
						paciente.setLogradouro(rs.getString("txt_logradouro"));
						paciente.setNumeroLogradouro(rs.getString("txt_numero_logradouro"));
						paciente.setComplementoLogradouro(rs.getString("txt_complemento_logradouro"));
						paciente.setReferenciaLogradouro(rs.getString("txt_referencia_logradouro"));
						paciente.setBairroLogradouro(rs.getString("txt_bairro_logradouro"));
						paciente.setMunicipioLogradouro(rs.getString("txt_municipio_logradouro"));
						paciente.setCodigoIbgeMunicipioLogradouro(rs.getString("txt_codigo_ibge_municipio_logradouro"));
						paciente.setUfLogradouro(rs.getString("txt_uf_logradouro"));
						paciente.setTelefone01(rs.getString("txt_telefone_01"));
						paciente.setTelefone02(rs.getString("txt_telefone_02"));
						paciente.setTelefone03(rs.getString("txt_telefone_03"));
						paciente.setEnderecoEletronico(rs.getString("txt_endereco_eletronico"));
						paciente.setRg(rs.getString("txt_rg"));
						paciente.setOrgaoEmissor(rs.getString("txt_orgao_emissor"));
						paciente.setUfOrgaoEmissor(rs.getString("txt_uf_orgao_emissor"));
						paciente.setDataExpedicao(rs.getDate("dat_expedicao"));
						paciente.setCpf(rs.getString("txt_cpf"));
						paciente.setCns(rs.getString("txt_cns"));
						paciente.setNomeCartorio(rs.getString("txt_nome_cartorio"));
						paciente.setNumeroRegistro(rs.getInt("num_registro"));
						paciente.setCodigoLivro(rs.getString("txt_codigo_livro"));
						paciente.setNumeroFolhaLivro(rs.getInt("num_folha_livro"));
						paciente.setDataRegistroLivro(rs.getDate("dat_registro_livro"));
						paciente.setIndicadorAssociado(rs.getString("txt_ind_associado"));
						paciente.setIndicadorEstudo(rs.getString("txt_ind_estudo"));
						paciente.setLocalEstudo(rs.getString("txt_local_estudo"));
						paciente.setIndicadorTrabalho(rs.getString("txt_ind_trabalho"));
						paciente.setLocalTrabalho(rs.getString("txt_local_trabalho"));
						paciente.setNomeResponsavel(rs.getString("txt_nome_responsavel"));
						paciente.setRgResponsavel(rs.getString("txt_rg_responsavel"));
						paciente.setCpfResponsavel(rs.getString("txt_cpf_responsavel"));
						paciente.setTelefoneResponsavel(rs.getString("txt_telefone_responsavel"));
						paciente.setIndicadorImagemPaciente(rs.getString("txt_ind_img_paciente"));
						paciente.setImagemPaciente(rs.getBytes("img_paciente"));
						paciente.setIndicadorImagemDigital(rs.getString("txt_ind_img_digital"));
						paciente.setImagemDigital(rs.getBytes("img_digital"));
						paciente.setDataHoraCadastro(rs.getTimestamp("dth_cadastro"));
						paciente.setQuantidadeArquivo(rs.getInt("qtd_arquivo"));
						return paciente;
					}
				}));
		return (List<Paciente>) out.get("p_out_cursor");
	}	

	
	@Override
	public List<Paciente> listar() {
		String sql = "select * from admsah001.sah_paciente a order by txt_nome";
		
		List<Paciente> listaPacientes = jdbcTemplate.query(sql, new PacienteRowMapper());
		
		return listaPacientes;
	}
	
	@Override
	public List<Paciente> listarPacienteRelatorio(PaginacaoVO paciente) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("p_in_tipo_acao",7);
		params.addValue("p_in_seq_paciente",((Paciente)paciente.getEntidade()).getSequencial());
		params.addValue("p_in_txt_nome",((Paciente)paciente.getEntidade()).getNome());
		params.addValue("p_in_txt_cpf",((Paciente)paciente.getEntidade()).getCpf());
		params.addValue("p_in_txt_cns",((Paciente)paciente.getEntidade()).getCns());
		params.addValue("p_in_txt_rg",((Paciente)paciente.getEntidade()).getRg());
		params.addValue("p_in_seq_raca",null);
		params.addValue("p_in_seq_escolaridade",null);
		params.addValue("p_in_seq_encaminhador",null);
		params.addValue("p_in_seq_tipo_responsavel",null);
		params.addValue("p_in_txt_estado_civil",null);
		params.addValue("p_in_txt_sexo",null);
		params.addValue("p_in_dat_nascimento",null);
		params.addValue("p_in_txt_tipo_sanguineo",null);
		params.addValue("p_in_txt_uf_naturalidade",null);
		params.addValue("p_in_txt_municipio_naturalidade",null);
		params.addValue("p_in_txt_nome_conjuge",null);
		params.addValue("p_in_txt_nome_pai",null);
		params.addValue("p_in_txt_nome_mae",null);
		params.addValue("p_in_txt_telefone_mae",null);
		params.addValue("p_in_txt_cep",null);
		params.addValue("p_in_txt_logradouro",null);
		params.addValue("p_in_txt_numero_logradouro",null);
		params.addValue("p_in_txt_complemento_logradouro",null);
		params.addValue("p_in_txt_referencia_logradouro",null);
		params.addValue("p_in_txt_bairro_logradouro",null);
		params.addValue("p_in_txt_municipio_logradouro",null);
		params.addValue("p_in_txt_codigo_ibge_municipio_logradouro",null);
		params.addValue("p_in_txt_uf_logradouro",null);
		params.addValue("p_in_txt_telefone_01",null);
		params.addValue("p_in_txt_telefone_02",null);
		params.addValue("p_in_txt_telefone_03",null);
		params.addValue("p_in_txt_endereco_eletronico",null);
		params.addValue("p_in_img_paciente",null);
		params.addValue("p_in_txt_orgao_emissor",null);
		params.addValue("p_in_dat_expedicao",null);
		params.addValue("p_in_txt_nome_cartorio",null);
		params.addValue("p_in_num_registro",null);
		params.addValue("p_in_txt_codigo_livro",null);
		params.addValue("p_in_num_folha_livro",null);
		params.addValue("p_in_dat_registro_livro",null);
		params.addValue("p_in_txt_ind_associado",null);
		params.addValue("p_in_txt_ind_estudo",null);
		params.addValue("p_in_txt_local_estudo",null);
		params.addValue("p_in_txt_ind_trabalho",null);
		params.addValue("p_in_txt_local_trabalho",null);
		params.addValue("p_in_txt_nome_responsavel",null);
		params.addValue("p_in_txt_rg_responsavel",null);
		params.addValue("p_in_txt_cpf_responsavel",null);
		params.addValue("p_in_txt_telefone_responsavel",null);
		params.addValue("p_in_img_digital",null);
		params.addValue("p_in_txt_uf_orgao_emissor",null);
		params.addValue("p_in_seq_ocupacao",null);
		params.addValue("p_in_seq_usuario_cadastro",null);
		params.addValue("p_in_txt_ind_img_paciente",null);
		params.addValue("p_in_txt_ind_img_digital",null);
		params.addValue("p_in_num_qtd_paginacao",paciente.getQuantidadePaginacao());
		params.addValue("p_in_num_inicio_paginacao",paciente.getInicioPaginacao());
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_paciente",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_paciente",Types.INTEGER),
				new SqlParameter("p_in_txt_nome",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf",Types.VARCHAR),
				new SqlParameter("p_in_txt_cns",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg",Types.VARCHAR),
				new SqlParameter("p_in_seq_raca",Types.INTEGER),
				new SqlParameter("p_in_seq_escolaridade",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhador",Types.INTEGER),
				new SqlParameter("p_in_seq_tipo_responsavel",Types.INTEGER),
				new SqlParameter("p_in_txt_estado_civil",Types.VARCHAR),
				new SqlParameter("p_in_txt_sexo",Types.VARCHAR),
				new SqlParameter("p_in_dat_nascimento",Types.DATE),
				new SqlParameter("p_in_txt_tipo_sanguineo",Types.VARCHAR),
				new SqlParameter("p_in_txt_uf_naturalidade",Types.VARCHAR),
				new SqlParameter("p_in_txt_municipio_naturalidade",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_conjuge",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_pai",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_mae",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_mae",Types.VARCHAR),
				new SqlParameter("p_in_txt_cep",Types.VARCHAR),
				new SqlParameter("p_in_txt_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_numero_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_complemento_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_referencia_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_bairro_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_municipio_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_codigo_ibge_municipio_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_uf_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_01",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_02",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_03",Types.VARCHAR),
				new SqlParameter("p_in_txt_endereco_eletronico",Types.VARCHAR),
				new SqlParameter("p_in_img_paciente",Types.BINARY),
				new SqlParameter("p_in_txt_orgao_emissor",Types.VARCHAR),
				new SqlParameter("p_in_dat_expedicao",Types.DATE),
				new SqlParameter("p_in_txt_nome_cartorio",Types.VARCHAR),
				new SqlParameter("p_in_num_registro",Types.INTEGER),
				new SqlParameter("p_in_txt_codigo_livro",Types.VARCHAR),
				new SqlParameter("p_in_num_folha_livro",Types.INTEGER),
				new SqlParameter("p_in_dat_registro_livro",Types.DATE),
				new SqlParameter("p_in_txt_ind_associado",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_estudo",Types.VARCHAR),
				new SqlParameter("p_in_txt_local_estudo",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_trabalho",Types.VARCHAR),
				new SqlParameter("p_in_txt_local_trabalho",Types.VARCHAR),
				new SqlParameter("p_in_txt_nome_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_rg_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_cpf_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_txt_telefone_responsavel",Types.VARCHAR),
				new SqlParameter("p_in_img_digital",Types.BINARY),
				new SqlParameter("p_in_txt_uf_orgao_emissor",Types.VARCHAR),
				new SqlParameter("p_in_seq_ocupacao",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario_cadastro",Types.INTEGER),
				new SqlParameter("p_in_txt_ind_img_paciente",Types.VARCHAR),
				new SqlParameter("p_in_txt_ind_img_digital",Types.VARCHAR),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
				new ParameterizedRowMapper<Paciente>() {
					public Paciente mapRow(ResultSet rs, int rowNum)throws SQLException {
						Paciente paciente = new Paciente();
						paciente.setSequencial(rs.getInt("seq_paciente"));
					//	paciente.setSequencialPacienteAnterior(rs.getInt("seq_paciente_anterior"));
						paciente.setNome(rs.getString("txt_nome"));
						paciente.setDataNascimento(rs.getDate("dat_nascimento"));
						paciente.setCns(rs.getString("txt_cns"));
					/**	paciente.setEstadoCivil(rs.getString("txt_estado_civil"));
						paciente.setSexo(rs.getString("txt_sexo"));						
						paciente.setTipoSanguineo(rs.getString("txt_tipo_sanguineo"));
						//------------------------------------------------------------
						paciente.setRaca(new Raca(rs.getString("txt_descricao_raca")));
						paciente.setEscolaridade(new Escolaridade(rs.getString("txt_descricao_escolaridade")));
						paciente.setEncaminhador(new Encaminhador(rs.getString("txt_descricao_encaminhador")));
						paciente.setTipoResponsavel(new TipoResponsavel(rs.getString("txt_descricao_tipo_responsavel")));
						paciente.setOcupacao(new Ocupacao(rs.getString("txt_descricao_ocupacao")));
						paciente.setUsuarioCadastro(new Usuario(rs.getString("txt_usuario_cadastro")));
						//------------------------------------------------------------
						paciente.setUfNaturalidade(rs.getString("txt_uf_naturalidade"));
						paciente.setMunicipioNaturalidade(rs.getString("txt_municipio_naturalidade"));
						paciente.setNomeConjuge(rs.getString("txt_nome_conjuge"));
						paciente.setNomePai(rs.getString("txt_nome_pai"));*/
						paciente.setNomeMae(rs.getString("txt_nome_mae"));
						/**	paciente.setTelefoneMae(rs.getString("txt_telefone_mae"));
						paciente.setCep(rs.getString("txt_cep"));*/
						paciente.setLogradouro(rs.getString("txt_logradouro"));
						paciente.setNumeroLogradouro(rs.getString("txt_numero_logradouro"));
						paciente.setComplementoLogradouro(rs.getString("txt_complemento_logradouro"));
						//paciente.setReferenciaLogradouro(rs.getString("txt_referencia_logradouro"));
						paciente.setBairroLogradouro(rs.getString("txt_bairro_logradouro"));
						paciente.setMunicipioLogradouro(rs.getString("txt_municipio_logradouro"));
					/**	paciente.setCodigoIbgeMunicipioLogradouro(rs.getString("txt_codigo_ibge_municipio_logradouro"));
						paciente.setUfLogradouro(rs.getString("txt_uf_logradouro"));*/
						paciente.setTelefone01(rs.getString("txt_telefone_01"));
					/**	paciente.setTelefone02(rs.getString("txt_telefone_02"));
						paciente.setTelefone03(rs.getString("txt_telefone_03"));
						paciente.setEnderecoEletronico(rs.getString("txt_endereco_eletronico"));
						paciente.setRg(rs.getString("txt_rg"));
						paciente.setOrgaoEmissor(rs.getString("txt_orgao_emissor"));
						paciente.setUfOrgaoEmissor(rs.getString("txt_uf_orgao_emissor"));
						paciente.setDataExpedicao(rs.getDate("dat_expedicao"));*/
						paciente.setCpf(rs.getString("txt_cpf"));
					/**	paciente.setNomeCartorio(rs.getString("txt_nome_cartorio"));
						paciente.setNumeroRegistro(rs.getInt("num_registro"));
						paciente.setCodigoLivro(rs.getString("txt_codigo_livro"));
						paciente.setNumeroFolhaLivro(rs.getInt("num_folha_livro"));
						paciente.setDataRegistroLivro(rs.getDate("dat_registro_livro"));
						paciente.setIndicadorAssociado(rs.getString("txt_ind_associado"));
						paciente.setIndicadorEstudo(rs.getString("txt_ind_estudo"));
						paciente.setLocalEstudo(rs.getString("txt_local_estudo"));
						paciente.setIndicadorTrabalho(rs.getString("txt_ind_trabalho"));
						paciente.setLocalTrabalho(rs.getString("txt_local_trabalho"));
						paciente.setNomeResponsavel(rs.getString("txt_nome_responsavel"));
						paciente.setRgResponsavel(rs.getString("txt_rg_responsavel"));
						paciente.setCpfResponsavel(rs.getString("txt_cpf_responsavel"));
						paciente.setTelefoneResponsavel(rs.getString("txt_telefone_responsavel"));
						paciente.setIndicadorImagemPaciente(rs.getString("txt_ind_img_paciente"));
						paciente.setImagemPaciente(rs.getBytes("img_paciente"));
						paciente.setIndicadorImagemDigital(rs.getString("txt_ind_img_digital"));
						paciente.setImagemDigital(rs.getBytes("img_digital"));
						paciente.setDataHoraCadastro(rs.getTimestamp("dth_cadastro"));
						paciente.setQuantidadeArquivo(rs.getInt("qtd_arquivo"));*/
						return paciente;
					}
				}));
		return (List<Paciente>) out.get("p_out_cursor");
				
	}
	
	@Override
	public Paciente obter(Paciente paciente) {
		String sql = "select * from admsah001.sah_paciente where seq_paciente = " + paciente.getSequencial();
		
		final List<Paciente> lista = jdbcTemplate.query(sql, new PacienteRowMapper());
		if (lista != null && lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}
	
	private class PacienteRowMapper implements ParameterizedRowMapper<Paciente> {

		@Override
		public Paciente mapRow(ResultSet rs, int rowNum) throws SQLException {
			Paciente paciente = new Paciente();
			paciente.setSequencial(rs.getInt("seq_paciente"));
			paciente.setCpf(rs.getString("txt_cpf"));
			paciente.setCns(rs.getString("txt_cns"));
			paciente.setNome(rs.getString("txt_nome"));
			paciente.setDataNascimento(rs.getDate("dat_nascimento"));
			paciente.setSexo(rs.getString("txt_sexo"));
			paciente.setTelefone01(rs.getString("txt_telefone_01"));
			return paciente;
		}
	}

}