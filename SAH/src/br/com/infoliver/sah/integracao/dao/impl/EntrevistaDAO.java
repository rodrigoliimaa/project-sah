package br.com.infoliver.sah.integracao.dao.impl;



import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.configuracao.exception.BOException;
import br.com.infoliver.sah.configuracao.exception.DAOException;
import br.com.infoliver.sah.integracao.dao.IEntrevistaDAO;
import br.com.infoliver.sah.negocio.entity.Entrevista;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

@Repository("entrevistaDAO")
@Transactional(readOnly=true)
@SuppressWarnings({"rawtypes","unused"})
public class EntrevistaDAO extends DAOBase implements IEntrevistaDAO {

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void inserirEntrevista(Entrevista entrevista)throws DAOException {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(" p_in_tipo_acao ",1);
		params.addValue(" p_in_seq_entrevista ",null);
		params.addValue("p_in_nome", entrevista.getNomePaciente());
		params.addValue("p_in_documento", entrevista.getDocumento());
		params.addValue("p_in_tipo_documento", entrevista.getTipoDocumento());
		params.addValue("p_in_idade", entrevista.getIdade());
		params.addValue("p_in_entidade_encaminhado", entrevista.getEntidadeEncaminhado());		
		params.addValue("p_in_logradouro",entrevista.getLogradouro());
		params.addValue("p_in_cidade_logradouro",entrevista.getCidadeLogradouro());
		params.addValue("p_in_numero_logradouro",entrevista.getNumeroLogradouro());
		params.addValue("p_in_referencia_logradouro",entrevista.getReferenciaLogradouro());
		params.addValue("p_in_objetivo",entrevista.getObjetivo());
		params.addValue("p_in_dth_cadastro",null);
		params.addValue("p_in_nome_assitente",entrevista.getNomeAssistente());	
		
		//----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001",null,"sp_entrevista",params,
		new SqlParameter(" p_in_tipo_acao ",Types.INTEGER),
		new SqlParameter(" p_in_seq_entrevista ",Types.INTEGER),
		new SqlParameter("p_in_nome", Types.VARCHAR),
		new SqlParameter("p_in_documento", Types.VARCHAR),
		new SqlParameter("p_in_tipo_documento", Types.VARCHAR),
		new SqlParameter("p_in_idade", Types.VARCHAR),
		new SqlParameter("p_in_entidade_encaminhado",Types.VARCHAR),		
		new SqlParameter("p_in_logradouro",Types.VARCHAR),
		new SqlParameter("p_in_cidade_logradouro",Types.VARCHAR),
		new SqlParameter("p_in_numero_logradouro",Types.VARCHAR),
		new SqlParameter("p_in_referencia_logradouro",Types.VARCHAR),
		new SqlParameter("p_in_objetivo",Types.VARCHAR),
		new SqlParameter("p_in_dth_cadastro",Types.DATE),
		new SqlParameter("p_in_nome_assitente",Types.VARCHAR));
		}
	

	@Override
	public void alterar(Entrevista entrevista) throws BOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Entrevista entrevista) throws BOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer totalRegitrosParaPaginacao(PaginacaoVO entrevista) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entrevista> listarPaginado(PaginacaoVO entrevista) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginacaoVO listar(PaginacaoVO entrevista) {
		// TODO Auto-generated method stub
		return null;
	}

}
