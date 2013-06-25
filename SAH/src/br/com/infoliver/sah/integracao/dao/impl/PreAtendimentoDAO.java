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
import br.com.infoliver.sah.integracao.dao.IPreAtendimentoDAO;
import br.com.infoliver.sah.negocio.entity.EncaminhamentoExterno;
import br.com.infoliver.sah.negocio.entity.PreAtendimento;
import br.com.infoliver.sah.negocio.entity.Setor;
import br.com.infoliver.sah.negocio.entity.TipoStatusAtendimento;
import br.com.infoliver.sah.negocio.entity.Usuario;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

@Repository("preAtendimentoDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"unchecked","rawtypes","unused"})
public class PreAtendimentoDAO extends DAOBase implements IPreAtendimentoDAO {

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void alterar(PreAtendimento preAtendimento) throws DAOException {
		EncaminhamentoExterno encaminhamento = new EncaminhamentoExterno();
		encaminhamento = preAtendimento.getEncaminhamentoExterno();
		Integer sequencialEncaminhamento = encaminhamento.getSequencial();
		
		MapSqlParameterSource params = new MapSqlParameterSource();			
		params.addValue("p_in_tipo_acao", 4);
		params.addValue("p_in_seq_pre_atendimento", preAtendimento.getSequencial());
		params.addValue("p_in_seq_setor", preAtendimento.getSetor().getSequencial());
		params.addValue("p_in_seq_usuario", preAtendimento.getUsuario().getSequencial());			
		params.addValue("p_in_tipo_status_atendimento", preAtendimento.getTipoStatusAtendimento().getSequencial());		
		params.addValue("p_in_data_encaminhamento_recebido_externo", preAtendimento.getDataEncaminhamentoRecebidoExterno());		
		params.addValue("p_in_relato", preAtendimento.getRelato());				
		params.addValue("p_in_data_hora_cadastro", preAtendimento.getDataHoraCadastro());
		params.addValue("p_in_nome",preAtendimento.getNome());	
		params.addValue("p_in_documento",preAtendimento.getDocumento());	
		params.addValue("p_in_tipo_documento",preAtendimento.getTipoDocumento());
		params.addValue("p_in_tipo_paciente",preAtendimento.getTipoPaciente());
		params.addValue("p_in_logradouro",preAtendimento.getLogradouro());
		params.addValue("p_in_cidade",preAtendimento.getCidade());
		params.addValue("p_in_bairro",preAtendimento.getBairro());
		params.addValue("p_in_numero",preAtendimento.getNumero());
		params.addValue("p_in_referencia",preAtendimento.getReferencia());
		params.addValue("p_in_idade",preAtendimento.getIdade());
		params.addValue("p_in_seq_encaminhamento", sequencialEncaminhamento);
		params.addValue("p_in_seq_encaminhamento_interno", preAtendimento.getSetorEncaminhamentoInterno());
		params.addValue("p_in_num_qtd_paginacao",null);
		params.addValue("p_in_num_inicio_paginacao",null);		
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_pre_atendimento",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_pre_atendimento",Types.INTEGER),
				new SqlParameter("p_in_seq_setor",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),				
				new SqlParameter("p_in_tipo_status_atendimento",Types.INTEGER),			
				new SqlParameter("p_in_data_encaminhamento_recebido_externo",Types.DATE),
				new SqlParameter("p_in_relato",Types.VARCHAR),				
				new SqlParameter("p_in_data_hora_cadastro",Types.DATE),
				new SqlParameter("p_in_nome",Types.VARCHAR),	
				new SqlParameter("p_in_documento",Types.VARCHAR),	
				new SqlParameter("p_in_tipo_documento",Types.VARCHAR),	
				new SqlParameter("p_in_tipo_paciente",Types.VARCHAR),
				new SqlParameter("p_in_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_cidade",Types.VARCHAR),
				new SqlParameter("p_in_bairro",Types.VARCHAR),
				new SqlParameter("p_in_numero",Types.VARCHAR),
				new SqlParameter("p_in_referencia",Types.VARCHAR),
				new SqlParameter("p_in_idade",Types.VARCHAR),
				new SqlParameter("p_in_seq_encaminhamento",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhamento_interno",Types.INTEGER),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER));	
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void excluir(PreAtendimento preAtendimento) throws DAOException {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("p_in_tipo_acao", 3);
		params.addValue("p_in_seq_pre_atendimento",preAtendimento.getSequencial());
		params.addValue("p_in_seq_setor",null);
		params.addValue("p_in_seq_usuario",null);			
		params.addValue("p_in_tipo_status_atendimento", null);		
		params.addValue("p_in_data_encaminhamento_recebido_externo", null);		
		params.addValue("p_in_relato", null);				
		params.addValue("p_in_data_hora_cadastro", null);
		params.addValue("p_in_nome",null);	
		params.addValue("p_in_documento",null);	
		params.addValue("p_in_tipo_documento",null);
		params.addValue("p_in_tipo_paciente",null);
		params.addValue("p_in_logradouro",preAtendimento.getLogradouro());
		params.addValue("p_in_cidade",preAtendimento.getCidade());
		params.addValue("p_in_bairro",preAtendimento.getBairro());
		params.addValue("p_in_numero",preAtendimento.getNumero());
		params.addValue("p_in_referencia",preAtendimento.getReferencia());
		params.addValue("p_in_idade",preAtendimento.getIdade());
		params.addValue("p_in_seq_encaminhamento",null);
		params.addValue("p_in_seq_encaminhamento_interno",null);
		params.addValue("p_in_num_qtd_paginacao",null);
		params.addValue("p_in_num_inicio_paginacao",null);
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_pre_atendimento",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_pre_atendimento",Types.INTEGER),
				new SqlParameter("p_in_seq_setor",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),				
				new SqlParameter("p_in_tipo_status_atendimento",Types.INTEGER),			
				new SqlParameter("p_in_data_encaminhamento_recebido_externo",Types.DATE),
				new SqlParameter("p_in_relato",Types.VARCHAR),				
				new SqlParameter("p_in_data_hora_cadastro",Types.DATE),
				new SqlParameter("p_in_nome",Types.VARCHAR),	
				new SqlParameter("p_in_documento",Types.VARCHAR),	
				new SqlParameter("p_in_tipo_documento",Types.VARCHAR),	
				new SqlParameter("p_in_tipo_paciente",Types.VARCHAR),
				new SqlParameter("p_in_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_cidade",Types.VARCHAR),
				new SqlParameter("p_in_bairro",Types.VARCHAR),
				new SqlParameter("p_in_numero",Types.VARCHAR),
				new SqlParameter("p_in_referencia",Types.VARCHAR),
				new SqlParameter("p_in_idade",Types.VARCHAR),
				new SqlParameter("p_in_seq_encaminhamento",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhamento_interno",Types.INTEGER),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER));

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void inserir(PreAtendimento preAtendimento) throws DAOException {
		
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao", 2);
		params.addValue("p_in_seq_pre_atendimento", null);
		params.addValue("p_in_seq_setor", preAtendimento.getSetor().getSequencial());
		params.addValue("p_in_seq_usuario", preAtendimento.getUsuario().getSequencial());			
		params.addValue("p_in_tipo_status_atendimento", preAtendimento.getTipoStatusAtendimento().getSequencial());		
		params.addValue("p_in_data_encaminhamento_recebido_externo", preAtendimento.getDataEncaminhamentoRecebidoExterno());		
		params.addValue("p_in_relato", preAtendimento.getRelato());				
		params.addValue("p_in_data_hora_cadastro", preAtendimento.getDataHoraCadastro());
		params.addValue("p_in_nome",preAtendimento.getNome());	
		params.addValue("p_in_documento",preAtendimento.getDocumento());	
		params.addValue("p_in_tipo_documento",preAtendimento.getTipoDocumento());
		params.addValue("p_in_tipo_paciente",preAtendimento.getTipoPaciente());
		params.addValue("p_in_logradouro",preAtendimento.getLogradouro());
		params.addValue("p_in_cidade",preAtendimento.getCidade());
		params.addValue("p_in_bairro",preAtendimento.getBairro());
		params.addValue("p_in_numero",preAtendimento.getNumero());
		params.addValue("p_in_referencia",preAtendimento.getReferencia());
		params.addValue("p_in_idade",preAtendimento.getIdade());
		params.addValue("p_in_seq_encaminhamento",null);
		params.addValue("p_in_seq_encaminhamento_interno", null);
		params.addValue("p_in_num_qtd_paginacao",null);
		params.addValue("p_in_num_inicio_paginacao",null);
		
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_pre_atendimento",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_pre_atendimento",Types.INTEGER),
				new SqlParameter("p_in_seq_setor",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),				
				new SqlParameter("p_in_tipo_status_atendimento",Types.INTEGER),			
				new SqlParameter("p_in_data_encaminhamento_recebido_externo",Types.DATE),
				new SqlParameter("p_in_relato",Types.VARCHAR),				
				new SqlParameter("p_in_data_hora_cadastro",Types.DATE),
				new SqlParameter("p_in_nome",Types.VARCHAR),	
				new SqlParameter("p_in_documento",Types.VARCHAR),	
				new SqlParameter("p_in_tipo_documento",Types.VARCHAR),	
				new SqlParameter("p_in_tipo_paciente",Types.VARCHAR),
				new SqlParameter("p_in_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_cidade",Types.VARCHAR),
				new SqlParameter("p_in_bairro",Types.VARCHAR),
				new SqlParameter("p_in_numero",Types.VARCHAR),
				new SqlParameter("p_in_referencia",Types.VARCHAR),
				new SqlParameter("p_in_idade",Types.VARCHAR),
				new SqlParameter("p_in_seq_encaminhamento",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhamento_interno",Types.INTEGER),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER));
		
		
		}

	@Override
	public List<PreAtendimento> listar(PaginacaoVO preAtendimento) {		
		PreAtendimento pre = new PreAtendimento();
		pre = (PreAtendimento) preAtendimento.getEntidade();		
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao", 8);
		params.addValue("p_in_seq_pre_atendimento", null);
		params.addValue("p_in_seq_setor",pre.getSetor().getSequencial());
		params.addValue("p_in_seq_usuario", null);			
		params.addValue("p_in_tipo_status_atendimento", pre.getTipoStatusAtendimento().getSequencial());		
		params.addValue("p_in_data_encaminhamento_recebido_externo", null);		
		params.addValue("p_in_relato", null);				
		params.addValue("p_in_data_hora_cadastro",null);
		params.addValue("p_in_nome",pre.getNome());	
		params.addValue("p_in_documento",pre.getDocumento());	
		params.addValue("p_in_tipo_documento",null);
		params.addValue("p_in_tipo_paciente",pre.getTipoPaciente());
		params.addValue("p_in_logradouro",pre.getLogradouro());
		params.addValue("p_in_cidade",pre.getCidade());
		params.addValue("p_in_bairro",pre.getBairro());
		params.addValue("p_in_numero",pre.getNumero());
		params.addValue("p_in_referencia",pre.getReferencia());
		params.addValue("p_in_idade",pre.getIdade());
		params.addValue("p_in_seq_encaminhamento", null);
		params.addValue("p_in_seq_encaminhamento_interno", null);
		params.addValue("p_in_num_qtd_paginacao",preAtendimento.getQuantidadePaginacao());
		params.addValue("p_in_num_inicio_paginacao",preAtendimento.getInicioPaginacao());

		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_pre_atendimento",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_pre_atendimento",Types.INTEGER),
				new SqlParameter("p_in_seq_setor",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_tipo_status_atendimento",Types.INTEGER),
				new SqlParameter("p_in_data_encaminhamento_recebido_externo",Types.DATE),
				new SqlParameter("p_in_relato",Types.VARCHAR),
				new SqlParameter("p_in_data_hora_cadastro",Types.DATE),
				new SqlParameter("p_in_nome",Types.VARCHAR),
				new SqlParameter("p_in_documento",Types.VARCHAR),
				new SqlParameter("p_in_tipo_documento",Types.VARCHAR),
				new SqlParameter("p_in_tipo_paciente",Types.VARCHAR),
				new SqlParameter("p_in_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_cidade",Types.VARCHAR),
				new SqlParameter("p_in_bairro",Types.VARCHAR),
				new SqlParameter("p_in_numero",Types.VARCHAR),
				new SqlParameter("p_in_referencia",Types.VARCHAR),
				new SqlParameter("p_in_idade",Types.VARCHAR),
				new SqlParameter("p_in_seq_encaminhamento",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhamento_interno",Types.INTEGER),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,		

				
				new ParameterizedRowMapper<PreAtendimento>() {
					public PreAtendimento mapRow(ResultSet rs, int rowNum)throws SQLException {
								PreAtendimento preAtendimento = new PreAtendimento();			
								preAtendimento.setSequencial(rs.getInt("seq_pre_atendimento"));						
								preAtendimento.setNome(rs.getString("nome"));
								preAtendimento.setDataEncaminhamentoRecebidoExterno(rs.getDate("dat_encaminhamento_recebido_externo"));
								preAtendimento.setDataHoraCadastro(rs.getDate("dth_cadastro"));
								preAtendimento.setDocumento(rs.getString("txt_documento"));						
								preAtendimento.setRelato(rs.getString("txt_relato"));
								preAtendimento.setTipoPaciente(rs.getString("txt_tipo_paciente"));
								Integer seq=rs.getInt("seq_setor_pre_atendimento");
								Setor setor = new Setor();
								setor.setSequencial(seq);													
								preAtendimento.setSetor(setor);								
								preAtendimento.setTipoDocumento(rs.getString("txt_tipo_documento"));
								preAtendimento.getSetor().setDescricao(rs.getString("txt_descricao"));
								TipoStatusAtendimento tpoStatus = new TipoStatusAtendimento();
								tpoStatus.setSequencial(rs.getInt("seq_tipo_status_atendimento"));
								preAtendimento.setTipoStatusAtendimento(tpoStatus);
								Usuario usuario = new Usuario();
								usuario.setSequencial(rs.getInt("seq_usuario"));								
								preAtendimento.setUsuario(usuario);
								EncaminhamentoExterno encaminhamento = new EncaminhamentoExterno();
								//encaminhamento.setSequencial(rs.getInt("seq_encaminhamento"));
								preAtendimento.setEncaminhamentoExterno(encaminhamento);								
						return preAtendimento;
					}
				}));
		return (List<PreAtendimento>) out.get("p_out_cursor");
		
	}

	@Override
	public List<PreAtendimento> listarPreAtendimentoPaginado(PaginacaoVO preAtendimento) {
		PreAtendimento pre = new PreAtendimento();
		pre = (PreAtendimento) preAtendimento.getEntidade();
		
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao", 1);
		params.addValue("p_in_seq_pre_atendimento", null);
		params.addValue("p_in_seq_setor",pre.getSetor().getSequencial());
		params.addValue("p_in_seq_usuario", null);			
		params.addValue("p_in_tipo_status_atendimento", pre.getTipoStatusAtendimento().getSequencial());		
		params.addValue("p_in_data_encaminhamento_recebido_externo", null);		
		params.addValue("p_in_relato", null);				
		params.addValue("p_in_data_hora_cadastro",null);
		params.addValue("p_in_nome",pre.getNome());	
		params.addValue("p_in_documento",pre.getDocumento());	
		params.addValue("p_in_tipo_documento",null);
		params.addValue("p_in_tipo_paciente",pre.getTipoPaciente());
		params.addValue("p_in_logradouro",pre.getLogradouro());
		params.addValue("p_in_cidade",pre.getCidade());
		params.addValue("p_in_bairro",pre.getBairro());
		params.addValue("p_in_numero",pre.getNumero());
		params.addValue("p_in_referencia",pre.getReferencia());
		params.addValue("p_in_idade",pre.getIdade());
		params.addValue("p_in_seq_encaminhamento",null);
		params.addValue("p_in_seq_encaminhamento_interno", null);
		params.addValue("p_in_num_qtd_paginacao",preAtendimento.getQuantidadePaginacao());
		params.addValue("p_in_num_inicio_paginacao",preAtendimento.getInicioPaginacao());

		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_pre_atendimento",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_pre_atendimento",Types.INTEGER),
				new SqlParameter("p_in_seq_setor",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_tipo_status_atendimento",Types.INTEGER),
				new SqlParameter("p_in_data_encaminhamento_recebido_externo",Types.DATE),
				new SqlParameter("p_in_relato",Types.VARCHAR),
				new SqlParameter("p_in_data_hora_cadastro",Types.DATE),
				new SqlParameter("p_in_nome",Types.VARCHAR),
				new SqlParameter("p_in_documento",Types.VARCHAR),
				new SqlParameter("p_in_tipo_documento",Types.VARCHAR),
				new SqlParameter("p_in_tipo_paciente",Types.VARCHAR),
				new SqlParameter("p_in_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_cidade",Types.VARCHAR),
				new SqlParameter("p_in_bairro",Types.VARCHAR),
				new SqlParameter("p_in_numero",Types.VARCHAR),
				new SqlParameter("p_in_referencia",Types.VARCHAR),
				new SqlParameter("p_in_idade",Types.VARCHAR),
				new SqlParameter("p_in_seq_encaminhamento",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhamento_interno",Types.INTEGER),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,		

				
				new ParameterizedRowMapper<PreAtendimento>() {
					public PreAtendimento mapRow(ResultSet rs, int rowNum)throws SQLException {
								PreAtendimento preAtendimento = new PreAtendimento();			
								preAtendimento.setSequencial(rs.getInt("seq_pre_atendimento"));						
								preAtendimento.setNome(rs.getString("nome"));
								preAtendimento.setDataEncaminhamentoRecebidoExterno(rs.getDate("dat_encaminhamento_recebido_externo"));
								preAtendimento.setDataHoraCadastro(rs.getDate("dth_cadastro"));
								preAtendimento.setDocumento(rs.getString("txt_documento"));						
								preAtendimento.setRelato(rs.getString("txt_relato"));
								preAtendimento.setTipoPaciente(rs.getString("txt_tipo_paciente"));
								Integer seq=rs.getInt("seq_setor_pre_atendimento");
								Setor setor = new Setor();
								setor.setSequencial(seq);													
								preAtendimento.setSetor(setor);								
								preAtendimento.setTipoDocumento(rs.getString("txt_tipo_documento"));
								preAtendimento.getSetor().setDescricao(rs.getString("txt_descricao"));
								TipoStatusAtendimento tpoStatus = new TipoStatusAtendimento();
								tpoStatus.setSequencial(rs.getInt("seq_tipo_status_atendimento"));
								preAtendimento.setTipoStatusAtendimento(tpoStatus);
								Usuario usuario = new Usuario();
								usuario.setSequencial(rs.getInt("seq_usuario"));
								preAtendimento.setUsuario(usuario);
								EncaminhamentoExterno encaminhamento = new EncaminhamentoExterno();
								//encaminhamento.setSequencial(rs.getInt("seq_encaminhamento"));
								preAtendimento.setEncaminhamentoExterno(encaminhamento);
						return preAtendimento;
					}
				}));
		return (List<PreAtendimento>) out.get("p_out_cursor");
	}	

	
	public Integer totalRegitrosParaPaginar() {
		String sql = "select count(*)FROM   admsah001.sah_pre_atendimento a	WHERE  a.dth_cadastro >= COALESCE(CURRENT_DATE);";				
		
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public Integer totalRegitrosParaPaginacao(PaginacaoVO preAtendimento) {
		PreAtendimento pre = new PreAtendimento();
		pre = (PreAtendimento) preAtendimento.getEntidade();
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("p_in_tipo_acao",5);
			params.addValue("p_in_seq_pre_atendimento",null);
			params.addValue("p_in_seq_setor", pre.getSetor().getSequencial());
			params.addValue("p_in_seq_usuario", null);			
			params.addValue("p_in_tipo_status_atendimento", null);		
			params.addValue("p_in_data_encaminhamento_recebido_externo", null);		
			params.addValue("p_in_relato", null);				
			params.addValue("p_in_data_hora_cadastro",null);
			params.addValue("p_in_nome",null);	
			params.addValue("p_in_documento",null);	
			params.addValue("p_in_tipo_documento",null);
			params.addValue("p_in_logradouro",pre.getLogradouro());
			params.addValue("p_in_cidade",pre.getCidade());
			params.addValue("p_in_bairro",pre.getBairro());
			params.addValue("p_in_numero",pre.getNumero());
			params.addValue("p_in_referencia",pre.getReferencia());
			params.addValue("p_in_idade",pre.getIdade());
			params.addValue("p_in_seq_encaminhamento", null);
			params.addValue("p_in_seq_encaminhamento_interno", null);
			params.addValue("p_in_num_qtd_paginacao",preAtendimento.getQuantidadePaginacao());
			params.addValue("p_in_num_inicio_paginacao",preAtendimento.getInicioPaginacao());
			
			//----------------------------------------------------------
			Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_pre_atendimento",params,
					new SqlParameter("p_in_tipo_acao",Types.INTEGER),
					new SqlParameter("p_in_seq_pre_atendimento",Types.INTEGER),
					new SqlParameter("p_in_seq_setor",Types.INTEGER),
					new SqlParameter("p_in_seq_usuario",Types.INTEGER),				
					new SqlParameter("p_in_tipo_status_atendimento",Types.INTEGER),			
					new SqlParameter("p_in_data_encaminhamento_recebido_externo",Types.DATE),
					new SqlParameter("p_in_relato",Types.VARCHAR),				
					new SqlParameter("p_in_data_hora_cadastro",Types.DATE),
					new SqlParameter("p_in_nome",Types.VARCHAR),	
					new SqlParameter("p_in_documento",Types.VARCHAR),	
					new SqlParameter("p_in_tipo_documento",Types.VARCHAR),
					new SqlParameter("p_in_logradouro",Types.VARCHAR),
					new SqlParameter("p_in_cidade",Types.VARCHAR),
					new SqlParameter("p_in_bairro",Types.VARCHAR),
					new SqlParameter("p_in_numero",Types.VARCHAR),
					new SqlParameter("p_in_referencia",Types.VARCHAR),
					new SqlParameter("p_in_idade",Types.VARCHAR),
					new SqlParameter("p_in_seq_encaminhamento",Types.INTEGER),
					new SqlParameter("p_in_seq_encaminhamento_interno",Types.INTEGER),
					new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
					new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER));
					new SqlOutParameter("p_out_cursor",Types.OTHER,
							new ParameterizedRowMapper<Object>() {
						public Object mapRow(ResultSet rs, int rowNum)throws SQLException {							
							return rs.getInt("count");
						}
					});					
			return (Integer)((List)out.get("p_out_cursor")).get(0);
		}

	@Override
	public List<PreAtendimento> listarPreAtendimentoPaginadoFiltro(PaginacaoVO preAtendimento) {
		
		PreAtendimento pre = new PreAtendimento();
		pre = (PreAtendimento) preAtendimento.getEntidade();
		
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao", 6);
		params.addValue("p_in_seq_pre_atendimento", null);
		params.addValue("p_in_seq_setor",pre.getSetor().getSequencial());
		params.addValue("p_in_seq_usuario", null);			
		params.addValue("p_in_tipo_status_atendimento", null);		
		params.addValue("p_in_data_encaminhamento_recebido_externo", null);		
		params.addValue("p_in_relato", null);				
		params.addValue("p_in_data_hora_cadastro",null);
		params.addValue("p_in_nome",pre.getNome());	
		params.addValue("p_in_documento",pre.getDocumento());	
		params.addValue("p_in_tipo_documento",null);
		params.addValue("p_in_tipo_paciente",pre.getTipoPaciente());
		params.addValue("p_in_logradouro",pre.getLogradouro());
		params.addValue("p_in_cidade",pre.getCidade());
		params.addValue("p_in_bairro",pre.getBairro());
		params.addValue("p_in_numero",pre.getNumero());
		params.addValue("p_in_referencia",pre.getReferencia());
		params.addValue("p_in_idade",pre.getIdade());
		params.addValue("p_in_seq_encaminhamento", null);
		params.addValue("p_in_seq_encaminhamento_interno", null);
		params.addValue("p_in_num_qtd_paginacao",preAtendimento.getQuantidadePaginacao());
		params.addValue("p_in_num_inicio_paginacao",preAtendimento.getInicioPaginacao());

		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_pre_atendimento",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_pre_atendimento",Types.INTEGER),
				new SqlParameter("p_in_seq_setor",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_tipo_status_atendimento",Types.INTEGER),
				new SqlParameter("p_in_data_encaminhamento_recebido_externo",Types.DATE),
				new SqlParameter("p_in_relato",Types.VARCHAR),
				new SqlParameter("p_in_data_hora_cadastro",Types.DATE),
				new SqlParameter("p_in_nome",Types.VARCHAR),
				new SqlParameter("p_in_documento",Types.VARCHAR),
				new SqlParameter("p_in_tipo_documento",Types.VARCHAR),
				new SqlParameter("p_in_tipo_paciente",Types.VARCHAR),
				new SqlParameter("p_in_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_cidade",Types.VARCHAR),
				new SqlParameter("p_in_bairro",Types.VARCHAR),
				new SqlParameter("p_in_numero",Types.VARCHAR),
				new SqlParameter("p_in_referencia",Types.VARCHAR),
				new SqlParameter("p_in_idade",Types.VARCHAR),
				new SqlParameter("p_in_seq_encaminhamento",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhamento_interno",Types.INTEGER),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,		

				
				new ParameterizedRowMapper<PreAtendimento>() {
					public PreAtendimento mapRow(ResultSet rs, int rowNum)throws SQLException {
								PreAtendimento preAtendimento = new PreAtendimento();			
								preAtendimento.setSequencial(rs.getInt("seq_pre_atendimento"));						
								preAtendimento.setNome(rs.getString("nome"));
								preAtendimento.setDataEncaminhamentoRecebidoExterno(rs.getDate("dat_encaminhamento_recebido_externo"));
								preAtendimento.setDataHoraCadastro(rs.getDate("dth_cadastro"));
								preAtendimento.setDocumento(rs.getString("txt_documento"));						
								preAtendimento.setRelato(rs.getString("txt_relato"));
								preAtendimento.setTipoPaciente(rs.getString("txt_tipo_paciente"));
								Integer seq=rs.getInt("seq_setor_pre_atendimento");
								Setor setor = new Setor();
								setor.setSequencial(seq);													
								preAtendimento.setSetor(setor);								
								preAtendimento.setTipoDocumento(rs.getString("txt_tipo_documento"));
								preAtendimento.getSetor().setDescricao(rs.getString("txt_descricao"));
								TipoStatusAtendimento tpoStatus = new TipoStatusAtendimento();
								tpoStatus.setSequencial(rs.getInt("seq_tipo_status_atendimento"));
								preAtendimento.setTipoStatusAtendimento(tpoStatus);
								Usuario usuario = new Usuario();
								usuario.setSequencial(rs.getInt("seq_usuario"));
								preAtendimento.setUsuario(usuario);
								EncaminhamentoExterno encaminhamento = new EncaminhamentoExterno();
								//encaminhamento.setSequencial(rs.getInt("seq_encaminhamento"));
								preAtendimento.setEncaminhamentoExterno(encaminhamento);
						return preAtendimento;
					}
				}));
		return (List<PreAtendimento>) out.get("p_out_cursor");
	}

	@Override
	public List<PreAtendimento> listarPreAtendimentoAtendidos(
			PaginacaoVO preAtendimento) {
		
		PreAtendimento pre = new PreAtendimento();
		pre = (PreAtendimento) preAtendimento.getEntidade();		
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao", 7);
		params.addValue("p_in_seq_pre_atendimento", null);
		params.addValue("p_in_seq_setor",pre.getSetor().getSequencial());
		params.addValue("p_in_seq_usuario", null);			
		params.addValue("p_in_tipo_status_atendimento", pre.getTipoStatusAtendimento().getSequencial());
		params.addValue("p_in_data_encaminhamento_recebido_externo", null);		
		params.addValue("p_in_relato", null);				
		params.addValue("p_in_data_hora_cadastro",null);
		params.addValue("p_in_nome",pre.getNome());	
		params.addValue("p_in_documento",pre.getDocumento());	
		params.addValue("p_in_tipo_documento",null);
		params.addValue("p_in_tipo_paciente",pre.getTipoPaciente());
		params.addValue("p_in_logradouro",pre.getLogradouro());
		params.addValue("p_in_cidade",pre.getCidade());
		params.addValue("p_in_bairro",pre.getBairro());
		params.addValue("p_in_numero",pre.getNumero());
		params.addValue("p_in_referencia",pre.getReferencia());
		params.addValue("p_in_idade",pre.getIdade());
		params.addValue("p_in_seq_encaminhamento", null);
		params.addValue("p_in_seq_encaminhamento_interno", null);
		params.addValue("p_in_num_qtd_paginacao",preAtendimento.getQuantidadePaginacao());
		params.addValue("p_in_num_inicio_paginacao",preAtendimento.getInicioPaginacao());

		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_pre_atendimento",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_pre_atendimento",Types.INTEGER),
				new SqlParameter("p_in_seq_setor",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_tipo_status_atendimento",Types.INTEGER),
				new SqlParameter("p_in_data_encaminhamento_recebido_externo",Types.DATE),
				new SqlParameter("p_in_relato",Types.VARCHAR),
				new SqlParameter("p_in_data_hora_cadastro",Types.DATE),
				new SqlParameter("p_in_nome",Types.VARCHAR),
				new SqlParameter("p_in_documento",Types.VARCHAR),
				new SqlParameter("p_in_tipo_documento",Types.VARCHAR),
				new SqlParameter("p_in_tipo_paciente",Types.VARCHAR),
				new SqlParameter("p_in_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_cidade",Types.VARCHAR),
				new SqlParameter("p_in_bairro",Types.VARCHAR),
				new SqlParameter("p_in_numero",Types.VARCHAR),
				new SqlParameter("p_in_referencia",Types.VARCHAR),
				new SqlParameter("p_in_idade",Types.VARCHAR),
				new SqlParameter("p_in_seq_encaminhamento",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhamento_interno",Types.INTEGER),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,		

				
				new ParameterizedRowMapper<PreAtendimento>() {
					public PreAtendimento mapRow(ResultSet rs, int rowNum)throws SQLException {
								PreAtendimento preAtendimento = new PreAtendimento();			
								preAtendimento.setSequencial(rs.getInt("seq_pre_atendimento"));						
								preAtendimento.setNome(rs.getString("nome"));
								preAtendimento.setDataEncaminhamentoRecebidoExterno(rs.getDate("dat_encaminhamento_recebido_externo"));
								preAtendimento.setDataHoraCadastro(rs.getDate("dth_cadastro"));
								preAtendimento.setDocumento(rs.getString("txt_documento"));						
								preAtendimento.setRelato(rs.getString("txt_relato"));
								preAtendimento.setTipoPaciente(rs.getString("txt_tipo_paciente"));
								//Integer seq=rs.getInt("seq_setor_pre_atendimento");
								Setor setor = new Setor();
								//setor.setSequencial(seq);													
								preAtendimento.setSetor(setor);								
								preAtendimento.setTipoDocumento(rs.getString("txt_tipo_documento"));
								preAtendimento.getSetor().setDescricao(rs.getString("txt_descricao"));
								TipoStatusAtendimento tpoStatus = new TipoStatusAtendimento();
								tpoStatus.setSequencial(rs.getInt("seq_tipo_status_atendimento"));
								preAtendimento.setTipoStatusAtendimento(tpoStatus);
								Usuario usuario = new Usuario();
								usuario.setSequencial(rs.getInt("seq_usuario"));
								preAtendimento.setUsuario(usuario);
								EncaminhamentoExterno encaminhamento = new EncaminhamentoExterno();
								//encaminhamento.setSequencial(rs.getInt("seq_encaminhamento"));
								preAtendimento.setEncaminhamentoExterno(encaminhamento);
							return preAtendimento;
					}
				}));
		return (List<PreAtendimento>) out.get("p_out_cursor");
	}

	@Override
	public List<PreAtendimento> listarEncaminhamentoExterno(PreAtendimento preAtendimento) {
		//PreAtendimento pre = new PreAtendimento();
		EncaminhamentoExterno encaminhamento = new EncaminhamentoExterno();
		encaminhamento = preAtendimento.getEncaminhamentoExterno();
		MapSqlParameterSource params = new MapSqlParameterSource();		
		params.addValue("p_in_tipo_acao", 9);
		params.addValue("p_in_seq_pre_atendimento", preAtendimento.getSequencial());
		params.addValue("p_in_seq_setor",null);
		params.addValue("p_in_seq_usuario", null);			
		params.addValue("p_in_tipo_status_atendimento",null);
		params.addValue("p_in_data_encaminhamento_recebido_externo", null);		
		params.addValue("p_in_relato", null);				
		params.addValue("p_in_data_hora_cadastro",null);
		params.addValue("p_in_nome",null);	
		params.addValue("p_in_documento",null);	
		params.addValue("p_in_tipo_documento",null);
		params.addValue("p_in_tipo_paciente",null);
		params.addValue("p_in_logradouro",null);
		params.addValue("p_in_cidade",null);
		params.addValue("p_in_bairro",null);
		params.addValue("p_in_numero",null);
		params.addValue("p_in_referencia",null);
		params.addValue("p_in_idade",null);
		params.addValue("p_in_seq_encaminhamento", null);
		params.addValue("p_in_seq_encaminhamento_interno", null);
		params.addValue("p_in_num_qtd_paginacao",null);
		params.addValue("p_in_num_inicio_paginacao",null);

		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_pre_atendimento",params,
				new SqlParameter("p_in_tipo_acao",Types.INTEGER),
				new SqlParameter("p_in_seq_pre_atendimento",Types.INTEGER),
				new SqlParameter("p_in_seq_setor",Types.INTEGER),
				new SqlParameter("p_in_seq_usuario",Types.INTEGER),
				new SqlParameter("p_in_tipo_status_atendimento",Types.INTEGER),
				new SqlParameter("p_in_data_encaminhamento_recebido_externo",Types.DATE),
				new SqlParameter("p_in_relato",Types.VARCHAR),
				new SqlParameter("p_in_data_hora_cadastro",Types.DATE),
				new SqlParameter("p_in_nome",Types.VARCHAR),
				new SqlParameter("p_in_documento",Types.VARCHAR),
				new SqlParameter("p_in_tipo_documento",Types.VARCHAR),
				new SqlParameter("p_in_tipo_paciente",Types.VARCHAR),
				new SqlParameter("p_in_logradouro",Types.VARCHAR),
				new SqlParameter("p_in_cidade",Types.VARCHAR),
				new SqlParameter("p_in_bairro",Types.VARCHAR),
				new SqlParameter("p_in_numero",Types.VARCHAR),
				new SqlParameter("p_in_referencia",Types.VARCHAR),
				new SqlParameter("p_in_idade",Types.VARCHAR),
				new SqlParameter("p_in_seq_encaminhamento",Types.INTEGER),
				new SqlParameter("p_in_seq_encaminhamento_interno",Types.INTEGER),
				new SqlParameter("p_in_num_qtd_paginacao",Types.INTEGER),
				new SqlParameter("p_in_num_inicio_paginacao",Types.INTEGER),
				new SqlOutParameter("p_out_cursor", Types.OTHER,						
				new ParameterizedRowMapper<PreAtendimento>() {
					public PreAtendimento mapRow(ResultSet rs, int rowNum)throws SQLException {
								PreAtendimento preAtendimento = new PreAtendimento();			
								//preAtendimento.setSequencial(rs.getInt("p.seq_pre_atendimento"));						
								preAtendimento.setNome(rs.getString("nome"));
								//preAtendimento.setDataEncaminhamentoRecebidoExterno(rs.getDate("dat_encaminhamento_recebido_externo"));
								//preAtendimento.setDataHoraCadastro(rs.getDate("dth_cadastro"));
								//preAtendimento.setDocumento(rs.getString("txt_documento"));						
								preAtendimento.setRelato(rs.getString("relato"));
								//preAtendimento.setTipoPaciente(rs.getString("txt_tipo_paciente"));
								preAtendimento.setIdade(rs.getString("idade"));
								//preAtendimento.setBairro(rs.getString("txt_bairro"));
								//preAtendimento.setCidade(rs.getString("txt_cidade"));
								//preAtendimento.setLogradouro(rs.getString("txt_logradouro"));
								//preAtendimento.setNumero(rs.getString("txt_numero"));
								//preAtendimento.setReferencia(rs.getString("txt_referencia"));
								//preAtendimento.setTipoDocumento(rs.getString("txt_tipo_documento"));								
							//	Integer seq=rs.getInt("seq_setor_pre_atendimento");
							//	Setor setor = new Setor();
							//	setor.setSequencial(seq);													
							//	preAtendimento.setSetor(setor);								
								//preAtendimento.setTipoDocumento(rs.getString("txt_tipo_documento"));
								//preAtendimento.getSetor().setDescricao(rs.getString("txt_descricao"));
								TipoStatusAtendimento tpoStatus = new TipoStatusAtendimento();
								//tpoStatus.setSequencial(rs.getInt("seq_tipo_status_atendimento"));
								preAtendimento.setTipoStatusAtendimento(tpoStatus);
								Usuario usuario = new Usuario();
								//usuario.setSequencial(rs.getInt("seq_usuario"));
								preAtendimento.setUsuario(usuario);			
								EncaminhamentoExterno encaminhamento = new EncaminhamentoExterno();
								encaminhamento.setSequencial(rs.getInt("sequencial_externo"));
								encaminhamento.setReferencia(rs.getString("referencia"));
								encaminhamento.setDescricao(rs.getString("descricao"));
								encaminhamento.setLogradouro(rs.getString("logradouro"));
								encaminhamento.setNumero(rs.getString("numero"));
								encaminhamento.setBairro(rs.getString("bairro"));
								encaminhamento.setCidade(rs.getString("cidade"));
								encaminhamento.setResponsavel(rs.getString("responsavel"));								
								preAtendimento.setEncaminhamentoExterno(encaminhamento);
						return preAtendimento;
					}
				}));
		return (List<PreAtendimento>) out.get("p_out_cursor");
	}	
	
	}



