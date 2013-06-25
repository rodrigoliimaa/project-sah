
package br.com.infoliver.sah.integracao.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.integracao.dao.IFornecedorDAO;
import br.com.infoliver.sah.negocio.entity.Fornecedor;
import br.com.infoliver.sah.negocio.entity.MovimentacaoProcedimento;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

@Repository("fornecedorDAO")
@Transactional(readOnly=true)
public class FornecedorDAO extends DAOBase implements IFornecedorDAO {
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(final Fornecedor fornecedor) {
		final String sql = "update admsah001.sah_fornecedor" +
				" set txt_cnpj = ?," +
				" txt_email = ?," +
				" txt_cep = ?," +
				" txt_uf = ?," +
				" txt_municipio = ?," +
				" txt_codigo_ibge_municipio = ?," +
				" txt_logradouro = ?," +
				" txt_numero = ?," +
				" txt_complemento = ?," +
				" txt_bairro = ?," +
				" txt_referencia = ?," +
				" txt_ie = ?," +
				" txt_im = ?," +
				" txt_nome_fantasia = ?," +
				" txt_razao_social = ?," +
				" txt_tec_resp = ?," +
				" txt_telefone1 = ?," +
				" txt_telefone2 = ?," +
				" txt_telefone3 = ?," +
				" num_val_max_pedidos = ?," +
				" txt_ind_ativo = ?" +
				" where seq_fornecedor = ?";
	
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, fornecedor.getCnpj());
	            ps.setString(2, fornecedor.getEmail());
	            ps.setString(3, fornecedor.getCep());
	            ps.setString(4, fornecedor.getUf());
	            ps.setString(5, fornecedor.getMunicipio());
	            ps.setString(6, fornecedor.getCodigoIbgeMunicipio());
	            ps.setString(7, fornecedor.getLogradouro());
	            ps.setString(8, fornecedor.getNumero());
	            ps.setString(9, fornecedor.getComplemento());
	            ps.setString(10, fornecedor.getBairro());
	            ps.setString(11, fornecedor.getReferencia());
	            ps.setString(12, fornecedor.getInscricaoEstadual());
	            ps.setString(13, fornecedor.getInscricaoMunicipal());
	            ps.setString(14, fornecedor.getNomeFantasia());
	            ps.setString(15, fornecedor.getRazaoSocial());
	            ps.setString(16, fornecedor.getTecnicoResponsavel());
	            ps.setString(17, fornecedor.getTelefone1());
	            ps.setString(18, fornecedor.getTelefone2());
	            ps.setString(19, fornecedor.getTelefone3());
	            ps.setBigDecimal(20, fornecedor.getValorMaximoPedidos());
	            
	            ps.setString(21, fornecedor.getIndicadorAtivo());
	            
	            ps.setInt(22, fornecedor.getSequencial());
			}
		});
	}
	
	@Override
	public Fornecedor consultar(Integer sequencial) {
		String sql = "select * from admsah001.sah_fornecedor" +
				" where seq_fornecedor = " + sequencial;
		
		final Fornecedor fornecedor = jdbcTemplate.query(sql, new FornecedorRowMapper()).get(0);
		fornecedor.setValorVendasRealizadas(consultarValorVendasRealizadas(fornecedor));
		
		return fornecedor;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserir(final Fornecedor fornecedor) {
		final String sql = "insert into admsah001.sah_fornecedor" +
				" (txt_cnpj, txt_email, txt_cep, txt_uf, txt_municipio, txt_codigo_ibge_municipio, txt_logradouro, txt_numero," +
				" txt_complemento, txt_bairro, txt_referencia, txt_ie, txt_im, txt_nome_fantasia, txt_razao_social," +
				" txt_tec_resp, txt_telefone1, txt_telefone2, txt_telefone3, num_val_max_pedidos)" +
				" values" +
				" (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(sql, new String[] {"seq_fornecedor"});
		            ps.setString(1, fornecedor.getCnpj());
		            ps.setString(2, fornecedor.getEmail());
		            ps.setString(3, fornecedor.getCep());
		            ps.setString(4, fornecedor.getUf());
		            ps.setString(5, fornecedor.getMunicipio());
		            ps.setString(6, fornecedor.getCodigoIbgeMunicipio());
		            ps.setString(7, fornecedor.getLogradouro());
		            ps.setString(8, fornecedor.getNumero());
		            ps.setString(9, fornecedor.getComplemento());
		            ps.setString(10, fornecedor.getBairro());
		            ps.setString(11, fornecedor.getReferencia());
		            ps.setString(12, fornecedor.getInscricaoEstadual());
		            ps.setString(13, fornecedor.getInscricaoMunicipal());
		            ps.setString(14, fornecedor.getNomeFantasia());
		            ps.setString(15, fornecedor.getRazaoSocial());
		            ps.setString(16, fornecedor.getTecnicoResponsavel());
		            ps.setString(17, fornecedor.getTelefone1());
		            ps.setString(18, fornecedor.getTelefone2());
		            ps.setString(19, fornecedor.getTelefone3());
		            ps.setBigDecimal(20, fornecedor.getValorMaximoPedidos());
		            return ps;
		        }
		    },
		    keyHolder);
		
		return keyHolder.getKey().intValue();
	}

	@Override
	public List<Fornecedor> listarPaginado(PaginacaoVO fornecedor) {
		String sql = "select * from admsah001.sah_fornecedor " +
				criarSQLCondicao((Fornecedor) fornecedor.getEntidade()) +
				" order by txt_nome_fantasia asc " +
				criarSQLPaginacao(fornecedor);
		List<Fornecedor> fornecedores = jdbcTemplate.query(sql, new FornecedorRowMapper());
		
		for (Fornecedor fornecedorRecuperado : fornecedores) {
			fornecedorRecuperado.setValorVendasRealizadas(consultarValorVendasRealizadas(fornecedorRecuperado));
		}
		
		return fornecedores;
	}
	
	private BigDecimal consultarValorVendasRealizadas(
			Fornecedor fornecedor) {
		final Calendar calendar = Calendar.getInstance();

		calendar.setTime(new java.util.Date());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		final java.util.Date firstDayDate = calendar.getTime();;
		
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getMaximum(Calendar.DAY_OF_MONTH));
		final java.util.Date lastDayDate = calendar.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String sql = "select sah_procedimento.* from admsah001.sah_movimentacao," +
				" admsah001.sah_procedimento" +
				" where sah_movimentacao.seq_movimentacao = sah_procedimento.seq_movimentacao" +
				" and sah_movimentacao.seq_fornecedor = " + fornecedor.getSequencial() +
				" and sah_movimentacao.dth_solicitacao >= '" + sdf.format(firstDayDate) + "'" +
				" and sah_movimentacao.dth_solicitacao <= '" + sdf.format(lastDayDate) + "'";
		
		final List<MovimentacaoProcedimento> procedimentos = jdbcTemplate.query(sql, new RowMapper<MovimentacaoProcedimento>() {
			@Override
			public MovimentacaoProcedimento mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				final MovimentacaoProcedimento procedimento = new MovimentacaoProcedimento();
				procedimento.setSigtap_vl_sa(rs.getDouble("num_valor_serv_amb"));
				procedimento.setSigtap_vl_sh(rs.getDouble("num_valor_serv_hosp"));
				procedimento.setSigtap_vl_sp(rs.getDouble("num_valor_serv_prof"));
				procedimento.setQuantidade(rs.getInt("num_quantidade"));
				return procedimento;
			}
		});
		
		BigDecimal valor = BigDecimal.ZERO;
		for (MovimentacaoProcedimento procedimento : procedimentos) {
			BigDecimal soma = BigDecimal.ZERO;
			soma = soma.add(BigDecimal.valueOf(procedimento.getSigtap_vl_sa()));
			soma = soma.add(BigDecimal.valueOf(procedimento.getSigtap_vl_sh()));
			soma = soma.add(BigDecimal.valueOf(procedimento.getSigtap_vl_sp()));
			BigDecimal multiplicacao = soma.multiply(BigDecimal.valueOf(procedimento.getQuantidade()));
			valor = valor.add( multiplicacao );
		}
		
		return valor;
	}

	private String criarSQLCondicao(Fornecedor fornecedor) {
		String where = "";
		if (fornecedor.getNomeFantasia() != null) {
			where = where + " where txt_nome_fantasia ilike '%" + fornecedor.getNomeFantasia() + "%'";
		} else if (fornecedor.getRazaoSocial() != null) {
			where = where + " where txt_razao_social ilike '%" + fornecedor.getRazaoSocial() + "%'";
		} else if (fornecedor.getCnpj() != null) {
			where = where + " where txt_cnpj = '" + fornecedor.getCnpj() + "'";
		} else if (fornecedor.getInscricaoEstadual() != null) {
			where = where + " where txt_ie = '" + fornecedor.getInscricaoEstadual() + "'";
		} else if (fornecedor.getInscricaoMunicipal() != null) {
			where = where + " where txt_im = '" + fornecedor.getInscricaoMunicipal() + "'";
		}
		
		if (fornecedor.getIndicadorAtivo() != null) {
			if (where.trim().equals("")) {
				where = where + " where txt_ind_ativo = '" +  fornecedor.getIndicadorAtivo() + "'";
			} else {
				where = where + " and txt_ind_ativo = '" +  fornecedor.getIndicadorAtivo() + "'";
			}
		}
		
		return where;
	}
	
	private String criarSQLPaginacao(PaginacaoVO fornecedor) {
		String sql = "limit " + fornecedor.getQuantidadePaginacao() +
				"offset " + fornecedor.getInicioPaginacao();
		
		return sql;
	}

	@Override
	public Integer totalRegitrosParaPaginacao(Fornecedor fornecedor) {
		String sql = "select count(*) from admsah001.sah_fornecedor " +
				criarSQLCondicao(fornecedor);
		
		return jdbcTemplate.queryForInt(sql);
	}
	
	private class FornecedorRowMapper implements ParameterizedRowMapper<Fornecedor> {
		@Override
		public Fornecedor mapRow(ResultSet rs, int rowNum) throws SQLException {
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setSequencial(rs.getInt("seq_fornecedor"));
			
			String logradouro = rs.getString("txt_logradouro");
			String numero = rs.getString("txt_numero");
			String bairro = rs.getString("txt_bairro");
			
			
			fornecedor.setCnpj(rs.getString("txt_cnpj"));
			fornecedor.setDataCadastro(rs.getDate("dth_cadastro"));
			fornecedor.setEmail(rs.getString("txt_email"));
			fornecedor.setEndereco(logradouro + ", " + numero + " " + bairro);
			fornecedor.setCep(rs.getString("txt_cep"));
			fornecedor.setUf(rs.getString("txt_uf"));
			fornecedor.setMunicipio(rs.getString("txt_municipio"));
			fornecedor.setCodigoIbgeMunicipio(rs.getString("txt_codigo_ibge_municipio"));
			fornecedor.setLogradouro(logradouro);
			fornecedor.setNumero(numero);
			fornecedor.setComplemento(rs.getString("txt_complemento"));
			fornecedor.setBairro(bairro);
			fornecedor.setReferencia(rs.getString("txt_referencia"));
			fornecedor.setIndicadorAtivo(rs.getString("txt_ind_ativo"));
			fornecedor.setInscricaoEstadual(rs.getString("txt_ie"));
			fornecedor.setInscricaoMunicipal(rs.getString("txt_im"));
			fornecedor.setNomeFantasia(rs.getString("txt_nome_fantasia"));
			fornecedor.setRazaoSocial(rs.getString("txt_razao_social"));
			fornecedor.setTecnicoResponsavel(rs.getString("txt_tec_resp"));
			fornecedor.setTelefone1(rs.getString("txt_telefone1"));
			fornecedor.setTelefone2(rs.getString("txt_telefone2"));
			fornecedor.setTelefone3(rs.getString("txt_telefone3"));
			fornecedor.setValorMaximoPedidos(rs.getBigDecimal("num_val_max_pedidos"));
			
			return fornecedor;
		}
	}

//	@Override
	public List<Fornecedor> listarFornecedores() {
		String sql = "select * from admsah001.sah_fornecedor order by txt_nome_fantasia asc ";				
		List<Fornecedor> fornecedores = jdbcTemplate.query(sql, new FornecedorRowMapper());
		return fornecedores;
	}
	
	@Override
	public Fornecedor obter(Fornecedor fornecedor) {
		String sql = "select * from admsah001.sah_fornecedor where seq_fornecedor = " + fornecedor.getSequencial();
		
		final List<Fornecedor> lista = jdbcTemplate.query(sql, new FornecedorRowMapper());
		if (lista != null && lista.size() > 0) {
			Fornecedor fornecedorRecuperado = lista.get(0);
			fornecedorRecuperado.setValorVendasRealizadas(consultarValorVendasRealizadas(fornecedorRecuperado));
			return fornecedorRecuperado;
		}
		return null;
	}
	
}
