package br.com.infoliver.sah.integracao.dao.impl;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcCallOperations;
import org.springframework.stereotype.Component;

/**
 * DAO base pai de todos os dao�s da aplica��o. Esta classe fornece m�todos <i>utilit�rios</i> <br>
 * e propriedades <i>protected</i> para ajudar e simplificar as opera��es de acesso a base de dados
 * 
 * 
 * @author victorsilva
 *
 */
@Component("daoBase")
@SuppressWarnings({"unchecked","rawtypes"})
public class DAOBase {
	
	//@Autowired
	//protected Criteria criteria;
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	/**
	 * Efetua uma consulta por um objeto na base de acordo <br>
	 * com um sql passado e um rowMapper
	 * 
	 * @param <E> Tipo de objeto sobre a qual a consulta ser� efetuada 
	 * @param sql sql da consulta
	 * @param params parametros para a consulta
	 * @param rowMapper objeto que mapear� o resulta para o objeto de retorno do resultado
	 * 
	 * @return um objeto completo como resultado da consulta ou null caso a consulta n�o retorne nenhum valor
	 * 
	 * @author victorsilva
	 */
	public <E> E consultaForObject(String sql, ParameterizedRowMapper<E> rowMapper, Object... params){
		try{
		   return (E) jdbcTemplate.queryForObject(sql, params,rowMapper);
		}catch(EmptyResultDataAccessException ex){
			return null;
		}
	}
	
	/**
	 * Executa um procedimento no banco e retorna um map com os parametros out
	 * 
	 * @param schema schema onde esta o procedimento
	 * @param pacote o pacote onde esta o procedimento
	 * @param nomeProcedure o nome do procedimento
	 * @param paramValues os valores dos parametros de entrada (ou seja os parametros do tipo in) no procedimento e seus respectivos valores. 
	 * @param paramsDeclarados os parametros que foram declarados na assinatura do procedimento inclu�ndo os <i>in</i> e os <i>out</i>
	 * 
	 * @return um Map com os valores retornados pelo procedimento ou seja os do tipo <i>out</i>
	 * 
	 * @author victorsilva
	 */
	public Map callProcedure(String schema,String pacote,
			                 String nomeProcedure,SqlParameterSource paramValues,
			                 SqlParameter... paramsDeclarados){
		SimpleJdbcCallOperations call = createJdbcCallOperations().withSchemaName(schema)
		 							  .withCatalogName(pacote)
		                              .withProcedureName(nomeProcedure)
		                              .declareParameters(paramsDeclarados);
		                              
		return call.execute(paramValues);
	}
	
	/**
	 * Executa um procedimento no banco e retorna um map com os parametros out sendo um ou mais 
	 * parametros out um Cursor (ResultSet).
	 * <br><br>
	 * Para criar parametros out que s�o cursores dentre outras formas pode-se mapear cada registro <br>
	 * do cursos usando um RowMapper. Se nenhum dos parametros for um cursor use  <br>
	 * o m�todo {@link #callProcedure(String, String, String, SqlParameterSource, SqlParameter...)}
	 * <br><br>
	 * Por exemplo: Use nos parametros declarados new SqlOutParameter("nomeParametro", OracleTypes.CURSOR,seuRowMapper)
	 * <br>
	 * @param schema schema onde esta o procedimento
	 * @param pacote o pacote onde esta o procedimento
	 * @param nomeProcedure o nome do procedimento
	 * @param paramValues os valores dos parametros de entrada (ou seja os parametros do tipo in) no procedimento e seus respectivos valores. 
	 * @param paramsDeclarados os parametros que foram declarados na assinatura do procedimento inclu�ndo os <i>in</i> e os <i>out</i>
	 * 
	 * @return um Map com os valores retornados pelo procedimento ou seja os do tipo <i>out</i>
	 * 
	 * @author victorsilva
	 */
	public Map callProcedureUsingOutResultSet(String schema,String pacote,
			String nomeProcedure,SqlParameterSource paramValues,
			SqlParameter... paramsDeclarados){
		SimpleJdbcCallOperations call = createJdbcCallOperations().withSchemaName(schema)
		                               .withoutProcedureColumnMetaDataAccess() 
		                               .withCatalogName(pacote)
		                               .withProcedureName(nomeProcedure)
		                               .declareParameters(paramsDeclarados);

		return call.execute(paramValues);
	}
	
	
	/**
	 * Executa uma stored function e retorna o seu resultado
	 * 
	 * @param tipoRetorno tipo de retono da fun��o
	 * @param schema schema onde esta a fun��o
	 * @param pacote o pacote onde esta a fun��o
	 * @param nomeFunction o nome da fun��o
	 * @param paramsValues os valores dos parametros de entrada (ou seja os parametros do tipo in) na fun��o e seus respectivos valores.
	 * @param paramsDeclarados os parametros que foram declarados na assinatura da fun��o inclu�ndo os <i>in</i> e os <i>out</i>
	 * @param <E>  tipo esperado para o retorno da fun��o
	 * @return resultado da fun��o
	 * 
	 * @author victorsilva
	 */
	public <E> E callFunction(Class<E> tipoRetorno, String schema,
			String pacote, String nomeFunction, Map paramsValues,
			SqlParameter... paramsDeclarados) {
		SimpleJdbcCallOperations call = createJdbcCallOperations().withSchemaName(schema)
										.withCatalogName(pacote)
										.withFunctionName(nomeFunction)
										.declareParameters(paramsDeclarados);

		
		return (E) call.executeFunction(tipoRetorno, paramsValues);
	}
	
	
	
	/**
	 * Executa uma stored function que tem como retorno um {@link ResultSet} <br>
	 * e retorna uma lista de objetos mapeada para o resultado da fun��o.
	 * 
	 * Obs: N�o usar esse m�todo por enquanto. Esse m�todo foi depreciado por problemas encontrados
	 * 
	 * @param tipoRetorno tipo de retono da fun��o
	 * @param schema schema onde esta a fun��o
	 * @param pacote o pacote onde esta a fun��o
	 * @param nomeFunction o nome da fun��o
	 * @param paramsValues os valores dos parametros de entrada (ou seja os parametros do tipo in) na fun��o e seus respectivos valores.
	 * @param rm RowMapper que mapeara o {@link ResultSet} (Resultado da fun��o) para objetos 
	 * @param paramsDeclarados os parametros que foram declarados na assinatura da fun��o inclu�ndo os <i>in</i> e os <i>out</i>
	 * @param <E> tipo de objeto esperado para a lista  de retorno da fun��o
	 * @return lista de objetos contendo o resultado da fun��o
	 */
	@Deprecated
	public <E> List<E> callFunction(Class<E> tipoRetorno, String schema,
			String pacote, String nomeFunction, Map paramsValues,ParameterizedRowMapper<E> rm,
			SqlParameter... paramsDeclarados) {
		SimpleJdbcCallOperations call = createJdbcCallOperations()
		.withoutProcedureColumnMetaDataAccess()
		.withSchemaName(schema)
		.withCatalogName(pacote)
		.withFunctionName(nomeFunction)
		.declareParameters(paramsDeclarados)
		.returningResultSet("result", rm);
		
		return (List<E>) call.executeFunction(tipoRetorno, paramsValues);
	}

	
	/**
	 * Efetua uma consulta e retorna uma lista contendo o resultado da consulta
	 * 
	 * @param sql sql da consulta
	 * @param rm RowMapper responsavel por efetuar o mapeamento do resultado (ResultSet)<br>
	 *        da consulta para os objetos do tipo esprado na lista de resultado
	 * @param params parametros para a consulta. Esses parametros s�o declarados no sql utilizando o caracter ?
	 * @param <E> tipo de elementos esperados na lista
	 * @return uma lista de objetos do tipo <E> com o resultado retornado pela consulta 
	 * 
	 * @author victorsilva
	 */
	public <E> List<E> query(String sql, ParameterizedRowMapper<E> rm, Object... params){
	   return jdbcTemplate.query(sql, params, rm);   
	}
	
	
	/**
	 * Efetua uma atualiza��o no banco e retorna o n�mero de registros afetados
	 * 
	 * @param sql sql do comando update
	 * @param paramsValues valores dos parametros que dever�o ser passados para o comando de atualiza��o 
	 * @return o n�mero de registros afetados
	 * 
	 * @author victorsilva
	 */
	public int update(String sql,Object... paramsValues){
		return jdbcTemplate.update(sql, paramsValues);
	}
	
	/**
	 * Cria e retorna um novo objeto {@link SimpleJdbcCallOperations}
	 * que fornece suporte a diversas opera��es envolvendo persistencia
	 * 
	 * @return um novo objeto {@link SimpleJdbcCallOperations}
	 * 
	 * @author victorsilva
	 */
	public SimpleJdbcCallOperations createJdbcCallOperations(){
		return new SimpleJdbcCall(jdbcTemplate);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
