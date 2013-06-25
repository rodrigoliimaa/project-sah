package br.com.infoliver.sah.integracao.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.infoliver.sah.integracao.dao.IMovimentacaoDAO;
import br.com.infoliver.sah.negocio.entity.Equipamento;
import br.com.infoliver.sah.negocio.entity.Fornecedor;
import br.com.infoliver.sah.negocio.entity.Medico;
import br.com.infoliver.sah.negocio.entity.Movimentacao;
import br.com.infoliver.sah.negocio.entity.MovimentacaoCID;
import br.com.infoliver.sah.negocio.entity.MovimentacaoProcedimento;
import br.com.infoliver.sah.negocio.entity.Paciente;
import br.com.infoliver.sah.negocio.vo.PaginacaoVO;

@Repository("movimentacaoDAO")
@Transactional(readOnly = true)
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MovimentacaoDAO extends DAOBase implements IMovimentacaoDAO {

	private static final SimpleDateFormat SDF = new SimpleDateFormat(
			"yyyy-MM-dd");

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(final Movimentacao movimentacao) {
		final String sql = "update admsah001.sah_movimentacao set"
				+ " txt_apac_bpi = ?" + ", txt_numero_nota = ?"
				+ ", txt_observacao = ?" + ", dth_entrada = ?"
				+ ", dth_encaminhamento = ?" + ", dth_entrega = ?"
				+ ", dth_autorizacao = ?" + ", dth_vencimento = ?"
				+ ", seq_fornecedor = ?" + " where seq_movimentacao = ?";

		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, movimentacao.getApacBpi());
				ps.setString(2, movimentacao.getNumeroNota());
				ps.setString(3, movimentacao.getObservacao());

				if (movimentacao.getDataEntrada() != null) {
					ps.setDate(4, new Date(movimentacao.getDataEntrada()
							.getTime()));
				} else {
					ps.setDate(4, null);
				}

				if (movimentacao.getDataEncaminhamento() != null) {
					ps.setDate(5, new Date(movimentacao.getDataEncaminhamento()
							.getTime()));
				} else {
					ps.setDate(5, null);
				}

				if (movimentacao.getDataEntrega() != null) {
					ps.setDate(6, new Date(movimentacao.getDataEntrega()
							.getTime()));
				} else {
					ps.setDate(6, null);
				}

				if (movimentacao.getDataAutorizacao() != null) {
					ps.setDate(7, new Date(movimentacao.getDataAutorizacao()
							.getTime()));
				} else {
					ps.setDate(7, null);
				}

				if (movimentacao.getDataVencimento() != null) {
					ps.setDate(8, new Date(movimentacao.getDataVencimento()
							.getTime()));
				} else {
					ps.setDate(8, null);
				}

				if (movimentacao.getFornecedor() != null
						&& movimentacao.getFornecedor().getSequencial() != null
						&& movimentacao.getFornecedor().getSequencial() > 0) {
					ps.setInt(9, movimentacao.getFornecedor().getSequencial());
				}
				else{
					ps.setNull(9, java.sql.Types.INTEGER);

				}

				ps.setInt(10, movimentacao.getSequencial());
			}
		});
	}

	@Override
	public Movimentacao consultar(Integer sequencial) {
		String sql = "select * from admsah001.sah_movimentacao where seq_movimentacao = "
				+ sequencial;

		final List<Movimentacao> listaMovimentacao = jdbcTemplate.query(sql,
				new MovimentacaoRowMapper());
		if (listaMovimentacao.size() > 0) {
			return listaMovimentacao.get(0);
		}

		return null;
	}

	@Override
	public Movimentacao consultarPorNumeroNota(String numeroNota) {
		String sql = "select * from admsah001.sah_movimentacao where txt_numero_nota = '"
				+ numeroNota + "'";

		final List<Movimentacao> listaMovimentacao = jdbcTemplate.query(sql,
				new MovimentacaoRowMapper());
		if (listaMovimentacao.size() > 0) {
			return listaMovimentacao.get(0);
		}

		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer inserir(final Movimentacao movimentacao) {
		
		
		// final String sql = "insert into admsah001.sah_movimentacao"
		// + " (txt_apac_bpi, txt_observacao, dth_solicitacao,"
		// +
		// " txt_cid_principal, txt_cid_secundario, txt_cid_causas_associadas,"
		// + " txt_data_competencia,"
		// + " seq_fornecedor, seq_medico, seq_paciente, seq_programa)"
		// + " values" + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		final String sql = "insert into admsah001.sah_movimentacao"
				+ " (txt_apac_bpi, txt_observacao, dth_solicitacao,"
				+ " txt_cid_principal, txt_cid_secundario, txt_cid_causas_associadas,"
				+ " txt_data_competencia,"
				+ " seq_fornecedor,seq_medico, seq_paciente, seq_programa, dth_entrada)"
				+ " values" + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						new String[] { "seq_movimentacao" });
				ps.setString(1, movimentacao.getApacBpi());
				ps.setString(2, movimentacao.getObservacao());
				ps.setDate(3, new Date(movimentacao.getDataSolicitacao()
						.getTime()));
				ps.setString(4, movimentacao.getCidPrincipal()
						.getSigtap_co_cid());
				if (movimentacao.getCidSecundario() != null
						&& movimentacao.getCidSecundario().getSigtap_co_cid() != null
						&& movimentacao.getCidSecundario().getSigtap_co_cid()
								.equals("") == false) {
					ps.setString(5, movimentacao.getCidSecundario()
							.getSigtap_co_cid());
				} else {
					ps.setString(5, null);
				}

				if (movimentacao.getCidCausasAssociadas() != null
						&& movimentacao.getCidCausasAssociadas()
								.getSigtap_co_cid() != null
						&& movimentacao.getCidCausasAssociadas()
								.getSigtap_co_cid().equals("") == false) {
					ps.setString(6, movimentacao.getCidCausasAssociadas()
							.getSigtap_co_cid());
				} else {
					ps.setString(6, null);
				}
				ps.setString(7, movimentacao.getSigtap_dt_competencia());		
				if(movimentacao.getFornecedor() !=null){
				ps.setInt(8, movimentacao.getFornecedor().getSequencial());
				}else{
					ps.setNull(8, java.sql.Types.INTEGER);

				}
				ps.setInt(9, movimentacao.getMedico().getSequencial());
				ps.setInt(10, movimentacao.getPaciente().getSequencial());
				ps.setInt(11, movimentacao.getPrograma().getSequencial());
				
				if (movimentacao.getDataEntrada() != null) {
					ps.setDate(12, new java.sql.Date(movimentacao.getDataEntrada().getTime()));
				} else {
					ps.setDate(12, null);
				}
				return ps;
			}
		}, keyHolder);
		final int sequencial = keyHolder.getKey().intValue();
		movimentacao.setSequencial(sequencial);

		// inserirCids(movimentacao);
		inserirProcedimentos(movimentacao);

		return sequencial;
	}

	// private void inserirCids(final Movimentacao movimentacao) {
	// final String sql = "insert into admsah001.sah_movimentacao_cid" +
	// " (sigtap_co_cid, seq_movimentacao)" +
	// " values" +
	// " (?, ?)";
	//
	// for (final MovimentacaoCID cid : movimentacao.getCids()) {
	// jdbcTemplate.update(new PreparedStatementCreator() {
	// public PreparedStatement createPreparedStatement(
	// Connection connection) throws SQLException {
	// PreparedStatement ps = connection.prepareStatement(sql);
	// ps.setString(1, cid.getSigtap_co_cid());
	// ps.setInt(2, movimentacao.getSequencial());
	// return ps;
	// }
	// });
	// }
	// }

	private void inserirProcedimentos(final Movimentacao movimentacao) {
		final String sql = "insert into admsah001.sah_procedimento"
				+ " (num_quantidade, txt_categoria_tipo,"
				+ " txt_codigo_procedimento, num_valor_serv_amb, num_valor_serv_hosp, num_valor_serv_prof,"
				+ " txt_cid, seq_equipamento, seq_movimentacao)" + " values"
				+ " (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		for (final MovimentacaoProcedimento procedimento : movimentacao
				.getCidPrincipal().getProcedimentos()) {
			procedimento.setCid(movimentacao.getCidPrincipal());
			procedimento.setMovimentacao(movimentacao);
			jdbcTemplate
					.update(new MovimentacaoProcedimentoPreparedStatementCreator(
							sql, procedimento));
		}

		if (movimentacao.getCidSecundario() != null
				&& movimentacao.getCidSecundario().getProcedimentos() != null) {
			for (final MovimentacaoProcedimento procedimento : movimentacao
					.getCidSecundario().getProcedimentos()) {
				procedimento.setCid(movimentacao.getCidSecundario());
				procedimento.setMovimentacao(movimentacao);
				jdbcTemplate
						.update(new MovimentacaoProcedimentoPreparedStatementCreator(
								sql, procedimento));
			}
		}

		if (movimentacao.getCidCausasAssociadas() != null
				&& movimentacao.getCidCausasAssociadas().getProcedimentos() != null) {
			for (final MovimentacaoProcedimento procedimento : movimentacao
					.getCidSecundario().getProcedimentos()) {
				procedimento.setCid(movimentacao.getCidCausasAssociadas());
				procedimento.setMovimentacao(movimentacao);
				jdbcTemplate
						.update(new MovimentacaoProcedimentoPreparedStatementCreator(
								sql, procedimento));
			}
		}
	}

	@Override
	public List<Movimentacao> listarPaginado(PaginacaoVO movimentacao) {

		String sql = "select * from admsah001.sah_movimentacao "
				+ criarSQLCondicao((Movimentacao) movimentacao.getEntidade()) +
				// " order by dth_solicitacao asc " +
				criarSQLPaginacao(movimentacao);
		List<Movimentacao> movimentacoes = jdbcTemplate.query(sql,
				new MovimentacaoRowMapper());
			
		return movimentacoes;
	}
	
	@Override
	public List<Movimentacao> listarPaginandoAuditivo(String tipoPesquisa) {
		String sql = "";
		String apac = "APAC";
		String bpi = "BPI";
		String vazio = "";
		if(tipoPesquisa.equals("")){
			sql = "select * from admsah001.sah_movimentacao where txt_apac_bpi in (VALUES('" + apac + "'),('" + bpi + "'),('" + vazio + "')) order by txt_apac_bpi ";
		} else {
			sql = "select * from admsah001.sah_movimentacao where txt_apac_bpi = '" + tipoPesquisa  + "'";
		}
		
		List<Movimentacao> movimentacoes = jdbcTemplate.query(sql,
				new MovimentacaoRowMapper());
			
		return movimentacoes;
	}	
	
	public static void main(String[] args) {
		String sql = "";
		String tipoPesquisa = "";
		String apac = "APAC";
		String bpi = "BPI";
		String vazio = "";
		String apacBpi = "APAC, BPI, ''";
		if(tipoPesquisa.equals("")){
			sql = "select * from admsah001.sah_movimentacao where txt_apac_bpi in (VALUES('" + apac + "'),('" + bpi + "'),('" + vazio + "')) order by txt_apac_bpi ";
		}else{
			sql = "select * from admsah001.sah_movimentacao where txt_apac_bpi in (VALUES ('" + tipoPesquisa + "')";
		}
		System.out.println("Texto: "+ sql);
	}

	private String criarSQLCondicao(Movimentacao movimentacao) {

		String where = "";

		where = where + " where seq_programa = " + new Integer(1);
//				+ movimentacao.getPrograma().getSequencial();

		if (movimentacao.getApacBpi() != null) {
			where = where + " and txt_apac_bpi = '" + movimentacao.getApacBpi()
					+ "'";
		}

		if (movimentacao.getFornecedor().getSequencial() != null) {
			where = where + " and seq_fornecedor = '"
					+ movimentacao.getFornecedor().getSequencial() + "'";
		}

		if (movimentacao.getNumeroNota() != null) {
			where = where + " and txt_numero_nota = '"
					+ movimentacao.getNumeroNota() + "'";
		}

		if (movimentacao.getDataInicioSolicitacao() != null) {

			where = where + " and dth_solicitacao >= '"
					+ SDF.format(movimentacao.getDataInicioSolicitacao()) + "'";
		}

		if (movimentacao.getDataFimSolicitacao() != null) {
			where = where + " and dth_solicitacao <= '"
					+ SDF.format(movimentacao.getDataFimSolicitacao()) + "'";
		}

		if (movimentacao.getSituacao().equalsIgnoreCase("ENTRADA")) {
			if (movimentacao.getDataInicioSituacao() != null) {
				where = where + " and dth_entrada >= '"
						+ SDF.format(movimentacao.getDataInicioSituacao())
						+ "'";
				if (movimentacao.getDataFimSituacao() != null) {
					where = where + " and dth_entrada <= '"
							+ SDF.format(movimentacao.getDataFimSituacao())
							+ "'";
				}
			} else {
				where = where + " and dth_entrada is not null ";
			}
		} else if (movimentacao.getSituacao().equalsIgnoreCase("ENCAMINHADO")) {
			if (movimentacao.getDataInicioSituacao() != null) {
				where = where + " and dth_encaminhamento >= '"
						+ SDF.format(movimentacao.getDataInicioSituacao())
						+ "'";
				if (movimentacao.getDataFimSituacao() != null) {
					where = where + " and dth_encaminhamento <= '"
							+ SDF.format(movimentacao.getDataFimSituacao())
							+ "'";
				}
			} else {
				where = where + " and dth_encaminhamento is not null ";
			}
		} else if (movimentacao.getSituacao().equalsIgnoreCase("ENTREGUE")) {
			if (movimentacao.getDataInicioSituacao() != null) {
				where = where + " and dth_entrega >= '"
						+ SDF.format(movimentacao.getDataInicioSituacao())
						+ "'";
				if (movimentacao.getDataFimSituacao() != null) {
					where = where + " and dth_entrada <= '"
							+ SDF.format(movimentacao.getDataFimSituacao())
							+ "'";
				}
			} else {
				where = where + " and dth_entrega is not null ";
			}
		}

		if (movimentacao.getDataAutorizacao() != null) {
			where = where + " and dth_autorizacao is not null ";
		} else {
			where = where + " and dth_autorizacao is null ";
		}

		return where;
	}

	private String criarSQLPaginacao(PaginacaoVO movimentacao) {
		String sql = " limit " + movimentacao.getQuantidadePaginacao()
				+ " offset " + movimentacao.getInicioPaginacao();

		return sql;
	}

	@Override
	public Integer totalRegitrosParaPaginacao(Movimentacao movimentacao) {
		String sql = "select count(*) from admsah001.sah_movimentacao "
				+ criarSQLCondicao(movimentacao);

		return jdbcTemplate.queryForInt(sql);
	}
	
	@Override
	public Integer totalRegitrosParaPaginacaoAuditivo(String tipoPesquisa) {
		String sql = "";
		String apac = "APAC";
		String bpi = "BPI";
		String vazio = "";
		if(tipoPesquisa.equals("")){
			sql = "select count(*) from admsah001.sah_movimentacao where txt_apac_bpi in (VALUES('" + apac + "'),('" + bpi + "'),('" + vazio + "'))";
		} else {
			sql = "select count(*) from admsah001.sah_movimentacao where txt_apac_bpi = '" + tipoPesquisa + "'";
		}
		
		return jdbcTemplate.queryForInt(sql);
	}	

	public List<MovimentacaoCID> consultarCids(Integer sequencial) {
		String sql = "select sah_movimentacao_cid.*, tb_cid.no_cid as sigtap_no_cid"
				+ " from admsah001.sah_movimentacao_cid, sigtap.tb_cid"
				+ " where sah_movimentacao_cid.sigtap_co_cid = tb_cid.co_cid"
				+ " and sah_movimentacao_cid.seq_movimentacao = " + sequencial;

		return jdbcTemplate.query(sql, new MovimentacaoCIDRowMapper());
	}

	@Override
	public List<MovimentacaoProcedimento> consultarProcedimentos(
			Integer sequencial) {
		String sql = "select * from admsah001.sah_procedimento"
				+ " where seq_movimentacao = " + sequencial;

		final List<MovimentacaoProcedimento> procedimentos = jdbcTemplate
				.query(sql, new MovimentacaoProcedimentoRowMapper());
		for (MovimentacaoProcedimento procedimento : procedimentos) {
			procedimento.setEquipamento(consultarEquipamento(procedimento));
		}

		return procedimentos;
	}

	@Override
	public List<MovimentacaoProcedimento> consultarProcedimentos(
			Integer sequencial, String cid) {
		String sql = "select * from admsah001.sah_procedimento"
				+ " where seq_movimentacao = " + sequencial
				+ " and txt_cid = '" + cid + "'";

		final List<MovimentacaoProcedimento> procedimentos = jdbcTemplate
				.query(sql, new MovimentacaoProcedimentoRowMapper());
		for (MovimentacaoProcedimento procedimento : procedimentos) {
			procedimento.setEquipamento(consultarEquipamento(procedimento));
		}

		return procedimentos;
	}

	private Equipamento consultarEquipamento(
			MovimentacaoProcedimento procedimento) {
		String sql = "select * from admsah001.sah_equipamento"
				+ " where seq_equipamento = "
				+ procedimento.getEquipamento().getSequencial();

		return jdbcTemplate.query(sql, new EquipamentoRowMapper()).get(0);
	}

	private class MovimentacaoRowMapper implements
			ParameterizedRowMapper<Movimentacao> {
		@Override
		public Movimentacao mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			final Movimentacao movimentacao = new Movimentacao();

			movimentacao.setSequencial(rs.getInt("seq_movimentacao"));

			movimentacao.setApacBpi(rs.getString("txt_apac_bpi"));
			movimentacao.setDataAutorizacao(rs.getDate("dth_autorizacao"));
			movimentacao
					.setDataEncaminhamento(rs.getDate("dth_encaminhamento"));
			movimentacao.setDataEntrada(rs.getDate("dth_entrada"));
			movimentacao.setDataEntrega(rs.getDate("dth_entrega"));
			movimentacao.setDataSolicitacao(rs.getDate("dth_solicitacao"));
			movimentacao.setDataVencimento(rs.getDate("dth_vencimento"));
			movimentacao.setNumeroNota(rs.getString("txt_numero_nota"));
			movimentacao.setObservacao(rs.getString("txt_observacao"));
			movimentacao.setSigtap_dt_competencia(rs
					.getString("txt_data_competencia"));
			movimentacao.setSequencial(rs.getInt("seq_movimentacao"));

			// movimentacao.setObservacao(rs.getString("txt_cid_principal"));

			final MovimentacaoCID cidPrincipal = new MovimentacaoCID();
			cidPrincipal.setSigtap_co_cid(rs.getString("txt_cid_principal"));
			movimentacao.setCidPrincipal(cidPrincipal);

			final MovimentacaoCID cidSecundario = new MovimentacaoCID();
			cidSecundario.setSigtap_co_cid(rs.getString("txt_cid_secundario"));
			movimentacao.setCidSecundario(cidSecundario);

			final MovimentacaoCID cidCausasAssociadas = new MovimentacaoCID();
			cidCausasAssociadas.setSigtap_co_cid(rs
					.getString("txt_cid_causas_associadas"));
			movimentacao.setCidCausasAssociadas(cidCausasAssociadas);

			final Medico medico = new Medico();

			medico.setSequencial(rs.getInt("seq_medico"));
			movimentacao.setMedico(medico);

			final Paciente paciente = new Paciente();
			paciente.setSequencial(rs.getInt("seq_paciente"));

			// paciente.setDataNascimento(rs.getDate("dth_nascimento"));
			// paciente.setSexo(rs.getString("txt_sexo"));
			// paciente.setNome(rs.getString("txt_nome"));
			// paciente.setCns(rs.getString("txt_cns"));
			// paciente.setMunicipioLogradouro(rs.getString("txt_municipio_logradouro"));
			movimentacao.setPaciente(paciente);

			final Fornecedor fornecedor = new Fornecedor();
			fornecedor.setSequencial(rs.getInt("seq_fornecedor"));
			movimentacao.setFornecedor(fornecedor);

			return movimentacao;
		}
	}

	private class MovimentacaoMapaRowMapper implements
			ParameterizedRowMapper<Movimentacao> {
		@Override
		public Movimentacao mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			Movimentacao movimentacao = new Movimentacao();

			final Paciente paciente = new Paciente();
			paciente.setDataNascimento(rs.getDate("dat_nascimento"));
			paciente.setSexo(rs.getString("txt_sexo"));
			paciente.setNome(rs.getString("txt_nome"));
			paciente.setCns(rs.getString("txt_cns"));
			paciente.setMunicipioLogradouro(rs.getString("txt_municipio_logradouro"));
			movimentacao.setPaciente(paciente);
			movimentacao.setNumeroNota(rs.getString("txt_numero_nota"));
			movimentacao.setObservacao(rs.getString("txt_cid_principal"));
			
			Medico medico = new Medico();
			medico.setNome(rs.getString(9));
			movimentacao.setMedico(medico);
			
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setNomeFantasia(rs.getString("txt_nome_fantasia"));
			movimentacao.setFornecedor(fornecedor );
			
			movimentacao.setApacBpi(rs.getString("txt_apac_bpi"));
			
			MovimentacaoCID cidPrincipal = new MovimentacaoCID();
			cidPrincipal.setMovimentacao(movimentacao);
			cidPrincipal.setSigtap_co_cid(rs.getString("txt_codigo_procedimento"));
			movimentacao.setCidPrincipal(cidPrincipal);

			return movimentacao;
		}
	}

	private class MovimentacaoMapaMovimentacoesRowMapper implements
			ParameterizedRowMapper<Movimentacao> {
		@Override
		public Movimentacao mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			Movimentacao movimentacao = new Movimentacao();

			final Paciente paciente = new Paciente();

			paciente.setNome(rs.getString("txt_nome"));
			paciente.setSequencial(rs.getInt("seq_paciente"));
			movimentacao.setPaciente(paciente);

			movimentacao.setNumeroNota(rs.getString("txt_numero_nota"));
			movimentacao.setApacBpi(rs.getString("no_procedimento"));
			movimentacao.setObservacao(rs.getString("txt_codigo_procedimento"));

			final Fornecedor fornecedor = new Fornecedor();
			fornecedor
					.setValorMaximoPedidos(rs.getBigDecimal("num_quantidade"));

			fornecedor.setValorVendasRealizadas(rs.getBigDecimal("num_valor"));
			fornecedor.setNomeFantasia(rs.getString("txt_nome_fantasia"));
			fornecedor.setSequencial(rs.getInt("seq_fornecedor"));

			movimentacao.setFornecedor(fornecedor);

			// String valorFormatado =
			// nf.format(fornecedor.getValorVendasRealizadas());

			return movimentacao;

		}
	}

	private class MovimentacaoCIDRowMapper implements
			ParameterizedRowMapper<MovimentacaoCID> {
		@Override
		public MovimentacaoCID mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			final MovimentacaoCID cid = new MovimentacaoCID();
			cid.setSigtap_co_cid(rs.getString("sigtap_co_cid"));
			cid.setSigtap_no_cid(rs.getString("sigtap_no_cid"));

			return cid;
		}
	}

	private class MovimentacaoProcedimentoPreparedStatementCreator implements
			PreparedStatementCreator {

		private String sql;
		private MovimentacaoProcedimento procedimento;

		public MovimentacaoProcedimentoPreparedStatementCreator(String sql,
				MovimentacaoProcedimento procedimento) {
			this.sql = sql;
			this.procedimento = procedimento;
		}

		public PreparedStatement createPreparedStatement(Connection connection)
				throws SQLException {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, procedimento.getQuantidade());
			ps.setString(2, procedimento.getCategoriaTipo());
			ps.setString(3, procedimento.getSigtap_co_procedimento());
			ps.setBigDecimal(4, new BigDecimal(procedimento.getSigtap_vl_sa()));
			ps.setBigDecimal(5, new BigDecimal(procedimento.getSigtap_vl_sh()));
			ps.setBigDecimal(6, new BigDecimal(procedimento.getSigtap_vl_sp()));
			ps.setString(7, procedimento.getCid().getSigtap_co_cid());
			ps.setInt(8, procedimento.getEquipamento().getSequencial());
			ps.setInt(9, procedimento.getMovimentacao().getSequencial());
			return ps;
		}
	}

	private class MovimentacaoProcedimentoRowMapper implements
			ParameterizedRowMapper<MovimentacaoProcedimento> {
		@Override
		public MovimentacaoProcedimento mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			final MovimentacaoProcedimento procedimento = new MovimentacaoProcedimento();

			procedimento.setSequencial(rs.getInt("seq_procedimento"));

			procedimento.setCategoriaTipo(rs.getString("txt_categoria_tipo"));
			procedimento.setQuantidade(rs.getInt("num_quantidade"));
			procedimento.setSigtap_co_procedimento(rs
					.getString("txt_codigo_procedimento"));
			procedimento.setSigtap_vl_sa(rs.getDouble("num_valor_serv_amb"));
			procedimento.setSigtap_vl_sh(rs.getDouble("num_valor_serv_hosp"));
			procedimento.setSigtap_vl_sp(rs.getDouble("num_valor_serv_prof"));

			final MovimentacaoCID cid = new MovimentacaoCID();
			cid.setSigtap_co_cid(rs.getString("txt_cid"));
			procedimento.setCid(cid);

			final Equipamento equipamento = new Equipamento();
			equipamento.setSequencial(rs.getInt("seq_equipamento"));
			procedimento.setEquipamento(equipamento);

			return procedimento;
		}
	}

	private class EquipamentoRowMapper implements
			ParameterizedRowMapper<Equipamento> {
		@Override
		public Equipamento mapRow(ResultSet rs, int rowNum) throws SQLException {
			final Equipamento equipamento = new Equipamento();

			equipamento.setSequencial(rs.getInt("seq_equipamento"));

			equipamento.setDescricao(rs.getString("txt_descricao"));

			return equipamento;
		}

	}

	@Override
	public List<Movimentacao> listarRelatorioMapa(Movimentacao movimentacao) {

		String sqlFornecedor = "and sah_movimentacao.seq_fornecedor is not null ";

		if (movimentacao.getFornecedor().getSequencial() != 0) {
			sqlFornecedor = "and sah_movimentacao.seq_fornecedor ="
					+ movimentacao.getFornecedor().getSequencial();
		}

		String sql = "select distinct sah_movimentacao.seq_movimentacao,sah_movimentacao.seq_paciente,sah_movimentacao.seq_fornecedor,"
				+ "sah_movimentacao.txt_numero_nota,sah_movimentacao.txt_data_competencia, sah_movimentacao.dth_solicitacao, "
				+ "sah_movimentacao.dth_entrada, sah_movimentacao.dth_encaminhamento, sah_movimentacao.dth_entrega, sah_movimentacao.dth_autorizacao, "
				+ "sah_paciente.txt_nome,  sah_fornecedor.txt_nome_fantasia,  sah_procedimento.txt_codigo_procedimento, sah_procedimento.num_quantidade,"
				+ " ((round(sah_procedimento.num_valor_serv_amb/100,2) + round(sah_procedimento.num_valor_serv_hosp/100,2) + round(sah_procedimento.num_valor_serv_prof/100,2)) * sah_procedimento.num_quantidade)"
				+ " as num_valor,sigtap.tb_procedimento.no_procedimento,(select sum((sah_procedimento.num_valor_serv_amb + sah_procedimento.num_valor_serv_hosp + "
				+ "sah_procedimento.num_valor_serv_prof) * sah_procedimento.num_quantidade)	from admsah001.sah_movimentacao, admsah001.sah_procedimento	where "
				+ "sah_movimentacao.seq_movimentacao = sah_procedimento.seq_movimentacao "
				+ sqlFornecedor
				+ " and sah_movimentacao.txt_data_competencia = '201207') as num_valor_total "
				+ " FROM admsah001.sah_movimentacao,admsah001.sah_procedimento,admsah001.sah_fornecedor,sigtap.tb_procedimento, admsah001.sah_paciente"
				+ criarSQLCondicaoRelatorioMovimentacoes(movimentacao);

		List<Movimentacao> movimentacoes = jdbcTemplate.query(sql,
				new MovimentacaoMapaMovimentacoesRowMapper());
		return movimentacoes;
	}

	private String criarSQLCondicaoRelatorioMovimentacoes(
			Movimentacao movimentacao) {
		String sqlFornecedor = "";

		String where = "";
		if (movimentacao.getFornecedor().getSequencial() != 0) {
			sqlFornecedor = "  and sah_movimentacao.seq_fornecedor = sah_fornecedor.seq_fornecedor AND sah_movimentacao.seq_fornecedor = "
					+ movimentacao.getFornecedor().getSequencial();
		} else {
			sqlFornecedor = "";
		}
		where = where + " where seq_programa = " + 1;
		where = where
				+ "and sah_movimentacao.seq_paciente = sah_paciente.seq_paciente AND sah_movimentacao.seq_movimentacao ="
				+ "sah_procedimento.seq_procedimento AND "
				+ "sah_procedimento.txt_codigo_procedimento = co_procedimento  "
				+ sqlFornecedor;

		// if (movimentacao.getFornecedor().getSequencial() != null) {
		// where = where + " and seq_fornecedor = '" +
		// movimentacao.getFornecedor().getSequencial() + "'";
		// }

		if (movimentacao.getDataInicioSolicitacao() != null) {

			where = where + " and dth_solicitacao >= '"
					+ SDF.format(movimentacao.getDataInicioSolicitacao()) + "'";
		}

		if (movimentacao.getDataFimSolicitacao() != null) {
			where = where + " and dth_solicitacao <= '"
					+ SDF.format(movimentacao.getDataFimSolicitacao()) + "'";
		}

		where = where
				+ "and sah_movimentacao.txt_data_competencia ILIKE '201207'";

		return where;
	}

	@Override
	public List<Movimentacao> listarRelatorioEncaminhamento(
			Movimentacao movimentacao) {
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("p_in_tipo_acao", 2);
		params.addValue("p_in_seq_fornecedor", null);
		params.addValue("p_in_seq_movimentacao", movimentacao.getSequencial());
		params.addValue("p_in_dt_competencia", null);
		params.addValue("p_in_apac_bpi", null);
		params.addValue("p_in_seq_programa", null);
		params.addValue("p_in_numero_nota", null);
		params.addValue("p_in_dth_inicio_solicitacao",
				movimentacao.getDataInicioSolicitacao());
		params.addValue("p_in_dth_fim_solicitacao",
				movimentacao.getDataFimSolicitacao());
		params.addValue("p_in_inicio_entrada", movimentacao.getDataEntrada());
		params.addValue("p_in_fim_entrada", movimentacao.getDataEntrega());
		params.addValue("p_out_cursor", Types.OTHER);
		// ----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001", null,
				"sp_movimentacao", params, new SqlParameter("p_in_tipo_acao",
						Types.INTEGER), new SqlParameter("p_in_seq_fornecedor",
						Types.INTEGER), new SqlParameter(
						"p_in_seq_movimentacao", Types.INTEGER),
				new SqlParameter("p_in_dt_competencia", Types.VARCHAR),
				new SqlParameter("p_in_apac_bpi", Types.VARCHAR),
				new SqlParameter("p_in_seq_programa", Types.INTEGER),
				new SqlParameter("p_in_numero_nota", Types.VARCHAR),
				new SqlParameter("p_in_dth_inicio_solicitacao", Types.DATE),
				new SqlParameter("p_in_dth_fim_solicitacao", Types.DATE),
				new SqlParameter("p_in_inicio_entrada", Types.DATE),
				new SqlParameter("p_in_fim_entrada", Types.DATE),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Movimentacao>() {
							public Movimentacao mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Movimentacao movimentacao = new Movimentacao();

								final MovimentacaoProcedimento procedimento = new MovimentacaoProcedimento();
								procedimento.setQuantidade(rs.getInt("num_quantidade"));
								procedimento.setSigtap_no_procedimento(rs.getString("no_procedimento"));
								final List<MovimentacaoProcedimento> listaProcedimentos = new ArrayList<MovimentacaoProcedimento>();
								listaProcedimentos.add(procedimento);
								movimentacao.setProcedimentos(listaProcedimentos);

								final Paciente paciente = new Paciente();
								paciente.setSequencial(rs.getInt("seq_paciente"));
								paciente.setNome(rs.getString("txt_nome"));
								paciente.setLogradouro(rs.getString("txt_logradouro"));
								paciente.setNumeroLogradouro(rs.getString("txt_numero_logradouro"));
								paciente.setBairroLogradouro(rs.getString("txt_bairro_logradouro"));
								paciente.setMunicipioLogradouro(rs.getString("txt_municipio_logradouro"));
								paciente.setUfLogradouro(rs.getString("txt_uf_logradouro"));
								paciente.setCep(rs.getString("txt_cep"));
								paciente.setTelefone01(rs.getString(14));
								paciente.setTelefone02(rs.getString(15));
								paciente.setTelefone03(rs.getString(16));
								paciente.setCpf(rs.getString("txt_cpf"));
								paciente.setRg(rs.getString("txt_rg"));
								paciente.setEstadoCivil(rs.getString("txt_categoria_tipo"));
								movimentacao.setPaciente(paciente);
								
								final Fornecedor fornecedor = new Fornecedor();
								fornecedor.setSequencial(rs.getInt("seq_fornecedor"));
								fornecedor.setNomeFantasia(rs.getString("txt_nome_fantasia"));
								fornecedor.setCnpj(rs.getString("txt_cnpj"));
								fornecedor.setLogradouro(rs.getString(20));
								fornecedor.setBairro(rs.getString(21));
								fornecedor.setComplemento(rs.getString(22));
								fornecedor.setMunicipio(rs.getString(23));
								fornecedor.setTelefone1(rs.getString(26));
								fornecedor.setTelefone2(rs.getString(27));
								fornecedor.setTelefone3(rs.getString(28));
								movimentacao.setFornecedor(fornecedor);

								movimentacao.setObservacao(rs.getString("no_procedimento"));
								movimentacao.setNumeroNota(rs.getString("txt_codigo_procedimento"));
								movimentacao.setSequencial(rs.getInt("seq_movimentacao"));
								movimentacao.setSigtap_dt_competencia(rs.getString("txt_data_competencia"));
								movimentacao.setSituacao(rs.getString("txt_categoria_tipo"));

								return movimentacao;

							}
						}));

		return (List<Movimentacao>) out.get("p_out_cursor");
	}

	@Override
	public List<Movimentacao> listarRelatorioTermoCompromisso(
			Movimentacao movimentacao) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("p_in_tipo_acao", 3);
		params.addValue("p_in_seq_fornecedor", null);
		params.addValue("p_in_seq_movimentacao", movimentacao.getSequencial());
		params.addValue("p_in_dt_competencia", null);
		params.addValue("p_in_apac_bpi", null);
		params.addValue("p_in_seq_programa", null);
		params.addValue("p_in_numero_nota", null);
		params.addValue("p_in_dth_inicio_solicitacao",
				movimentacao.getDataInicioSolicitacao());
		params.addValue("p_in_dth_fim_solicitacao",
				movimentacao.getDataFimSolicitacao());
		params.addValue("p_in_inicio_entrada", movimentacao.getDataEntrada());
		params.addValue("p_in_fim_entrada", movimentacao.getDataEntrega());
		params.addValue("p_out_cursor", Types.OTHER);
		// ----------------------------------------------------------
		Map out = callProcedureUsingOutResultSet("admsah001", null,
				"sp_movimentacao", params, new SqlParameter("p_in_tipo_acao",
						Types.INTEGER), new SqlParameter("p_in_seq_fornecedor",
						Types.INTEGER), new SqlParameter(
						"p_in_seq_movimentacao", Types.INTEGER),
				new SqlParameter("p_in_dt_competencia", Types.VARCHAR),
				new SqlParameter("p_in_apac_bpi", Types.VARCHAR),
				new SqlParameter("p_in_seq_programa", Types.INTEGER),
				new SqlParameter("p_in_numero_nota", Types.VARCHAR),
				new SqlParameter("p_in_dth_inicio_solicitacao", Types.DATE),
				new SqlParameter("p_in_dth_fim_solicitacao", Types.DATE),
				new SqlParameter("p_in_inicio_entrada", Types.DATE),
				new SqlParameter("p_in_fim_entrada", Types.DATE),
				new SqlOutParameter("p_out_cursor", Types.OTHER,
						new ParameterizedRowMapper<Movimentacao>() {
							public Movimentacao mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								Movimentacao movimentacao = new Movimentacao();

								final MovimentacaoProcedimento procedimento = new MovimentacaoProcedimento();

								final List<MovimentacaoProcedimento> listaProcedimentos = new ArrayList<MovimentacaoProcedimento>();
								listaProcedimentos.add(procedimento);
								movimentacao.setProcedimentos(listaProcedimentos);

								final Paciente paciente = new Paciente();
								paciente.setSequencial(rs
										.getInt("seq_paciente"));
								paciente.setNome(rs.getString("txt_nome"));
								paciente.setCpf(rs.getString("txt_cpf"));
								paciente.setRg(rs.getString("txt_rg"));
								paciente.setOrgaoEmissor(rs
										.getString("txt_orgao_emissor"));
								paciente.setUfOrgaoEmissor(rs
										.getString("txt_uf_orgao_emissor"));
								paciente.setNomePai(rs
										.getString("txt_nome_pai"));
								paciente.setNomeMae(rs
										.getString("txt_nome_mae"));
								paciente.setMunicipioLogradouro(rs
										.getString("txt_municipio_logradouro"));
								movimentacao.setPaciente(paciente);

								final Fornecedor fornecedor = new Fornecedor();
								fornecedor.setSequencial(rs
										.getInt("seq_fornecedor"));
								fornecedor.setNomeFantasia(rs
										.getString("txt_nome_fantasia"));
								fornecedor.setCnpj(rs.getString("txt_cnpj"));
								// fornecedor.setEndereco(rs.getString("txt_endereco"));
								fornecedor.setTelefone1(rs
										.getString("txt_telefone1"));
								fornecedor.setTelefone2(rs
										.getString("txt_telefone2"));
								fornecedor.setTelefone3(rs
										.getString("txt_telefone3"));
								fornecedor.setInscricaoEstadual(rs
										.getString("txt_ie"));
								fornecedor.setTecnicoResponsavel(rs
										.getString("txt_tec_resp"));
								fornecedor.setLogradouro(rs
										.getString("txt_logradouro"));
								fornecedor.setBairro(rs.getString("txt_bairro"));
								fornecedor.setComplemento(rs
										.getString("txt_complemento"));
								fornecedor.setMunicipio(rs
										.getString("txt_municipio"));
								// fornecedor.setValorMaximoPedidos(rs.getInt("num_quantidade"));
								// fornecedor.setEmail(rs.getString("txt_descricao"));
								movimentacao.setFornecedor(fornecedor);

								movimentacao.setObservacao(rs
										.getString("no_procedimento"));
								movimentacao.setNumeroNota(rs
										.getString("txt_codigo_procedimento"));
								movimentacao.setSequencial(rs
										.getInt("seq_movimentacao"));

								final Medico medico = new Medico();
								medico.setNome(rs.getString("txt_nome_medico"));
								movimentacao.setMedico(medico);

								return movimentacao;

							}
						}));

		return (List<Movimentacao>) out.get("p_out_cursor");
	}

	@Override
	public List<Movimentacao> listarRelatorioMapaBpi(Movimentacao movimentacao) {

		String sql = "select distinct pa.dat_nascimento, pa.txt_sexo, pa.txt_nome, pa.txt_cns, pa.txt_municipio_logradouro, " +
						"pr.txt_codigo_procedimento, mo.txt_cid_principal, mo.txt_apac_bpi, me.txt_nome, fo.txt_nome_fantasia, mo.txt_numero_nota " +
						"from admsah001.sah_movimentacao mo, admsah001.sah_paciente pa, admsah001.sah_procedimento pr, admsah001.sah_medico me, admsah001.sah_fornecedor fo "
						+ criarSQLCondicaoRelatorio(movimentacao) + " and dth_autorizacao is not null " +
						"and mo.seq_medico = me.seq_medico " +
						"and mo.seq_fornecedor = fo.seq_fornecedor";
		// " order by dth_solicitacao asc " +
		// criarSQLPaginacao(movimentacao);
		List<Movimentacao> movimentacoes = jdbcTemplate.query(sql,
				new MovimentacaoMapaRowMapper());
		return movimentacoes;
	}

	private String criarSQLCondicaoRelatorio(Movimentacao movimentacao) {

		String where = "";

		where = where + " where seq_programa = "
				+ movimentacao.getPrograma().getSequencial();
		where = where
				+ " and mo.seq_paciente = pa.seq_paciente " +
				"   AND  mo.seq_movimentacao = pr.seq_movimentacao"
				+ " and mo.seq_programa = "
				+ movimentacao.getPrograma().getSequencial();

		if (movimentacao.getDataInicioSolicitacao() != null) {

			where = where + " and dth_solicitacao >= '"
					+ SDF.format(movimentacao.getDataInicioSolicitacao()) + "'";
		}

		if (movimentacao.getDataFimSolicitacao() != null) {
			where = where + " and dth_solicitacao <= '"
					+ SDF.format(movimentacao.getDataFimSolicitacao()) + "'";
		}

		if (movimentacao.getApacBpi() != null) {
			where = where + " and txt_apac_bpi = '" + movimentacao.getApacBpi()
					+ "'";
		}

		if (movimentacao.getNumeroNota() != null) {
			where = where + " or txt_numero_nota = '"
					+ movimentacao.getNumeroNota() + "'";
		}

		return where;
	}

	@Override
	public List<Movimentacao> listarRelatorioEquipamentos(
			Movimentacao movimentacao) {

		String sql = "select sah_paciente.seq_paciente,sah_paciente.txt_nome, sah_paciente.txt_cpf, sah_paciente.txt_cns,sah_paciente.txt_nome_mae,"
				+" sah_paciente.txt_logradouro, sah_paciente.txt_numero_logradouro, sah_paciente.txt_bairro_logradouro,sah_paciente.txt_municipio_logradouro," 
				+"sah_paciente.txt_telefone_01,sah_paciente.txt_telefone_02,sah_procedimento.txt_codigo_procedimento,tb_procedimento.no_procedimento,"
				+"sah_procedimento.txt_cid,sah_equipamento.txt_descricao,sah_movimentacao.seq_paciente,count(all num_quantidade)/6 as total " 
				+" FROM "
				+ "admsah001.sah_movimentacao,  admsah001.sah_paciente, admsah001.sah_procedimento, admsah001.sah_equipamento,sigtap.tb_procedimento"
				+ " WHERE sah_movimentacao.seq_programa ="
				+ movimentacao.getPrograma().getSequencial()
				+ " and sah_movimentacao.seq_paciente = sah_paciente.seq_paciente AND sah_procedimento.txt_cid = sah_movimentacao.txt_cid_principal AND "
				+ "sah_procedimento.seq_equipamento = sah_equipamento.seq_equipamento AND "
				+ "sigtap.tb_procedimento.co_procedimento = sah_procedimento.txt_codigo_procedimento and "
				+"sah_equipamento.seq_equipamento = sah_procedimento.seq_equipamento and "
				+"sah_movimentacao.seq_movimentacao = sah_procedimento.seq_movimentacao and "
				+ "sah_movimentacao.dth_solicitacao >='"
				+ SDF.format(movimentacao.getDataInicioSolicitacao())
				+ "'"
				+ " and dth_solicitacao <= '"
				+ SDF.format(movimentacao.getDataFimSolicitacao())
				+ "'"
				+ CriarSqlRelatorioEquipamentos(movimentacao)
				+ " group by sah_paciente.txt_cpf, sah_paciente.txt_cns,sah_paciente.txt_nome_mae,sah_paciente.txt_nome,sah_procedimento.txt_cid,"
				+"sah_procedimento.txt_codigo_procedimento,sah_movimentacao.seq_paciente,sigtap.tb_procedimento.no_procedimento,sah_paciente.txt_logradouro, "
				+"sah_paciente.txt_numero_logradouro, sah_paciente.txt_bairro_logradouro,sah_paciente.txt_municipio_logradouro,sah_paciente.txt_telefone_01,"
				+"sah_paciente.txt_telefone_02, sah_paciente.seq_paciente, sah_equipamento.txt_descricao order by sah_equipamento.txt_descricao;";

		List<Movimentacao> movimentacoes = jdbcTemplate.query(sql,
				new RelatorioEquipamentosRowMapper());
		return movimentacoes;
	}

	private String CriarSqlRelatorioEquipamentos(Movimentacao movimentacao) {
		String sql = "";

		if (movimentacao.getSituacao().equals("ENTREGUE")) {
			sql = sql + "and dth_entrega is not null ";
		} else if (movimentacao.getSituacao().equals("ENCAMINHADO")) {
			sql = sql + "and dth_encaminhamento is not null ";
		} else if (movimentacao.getSituacao().equals("ENTRADA")) {
			sql = sql + "and dth_entrada is not null ";
		} else if (movimentacao.getSituacao().equals("TODAS")) {
			sql = sql + "";
		}

		return sql;
	}

	private class RelatorioEquipamentosRowMapper implements
			ParameterizedRowMapper<Movimentacao> {
		@Override
		public Movimentacao mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			final Movimentacao movimentacao = new Movimentacao();
			movimentacao.setSequencial(rs.getInt("total"));
			movimentacao.setApacBpi(rs.getString("txt_codigo_procedimento"));
			movimentacao.setObservacao(rs.getString("no_procedimento"));
			movimentacao.setSituacao(rs.getString("txt_descricao"));
			movimentacao.setNumeroNota(rs.getString("txt_cid"));
			final Paciente paciente = new Paciente();
			paciente.setNome(rs.getString("txt_nome"));
			paciente.setCpf(rs.getString("txt_cpf"));
			paciente.setCns(rs.getString("txt_cns"));
			paciente.setNomeMae(rs.getString("txt_nome_mae"));
			paciente.setMunicipioLogradouro(rs.getString("txt_municipio_logradouro"));
			paciente.setTelefone01(rs.getString("txt_telefone_01"));
			paciente.setTelefone02(rs.getString("txt_telefone_02"));
			paciente.setLogradouro(rs.getString("txt_logradouro"));
			paciente.setNumeroLogradouro(rs.getString("txt_numero_logradouro"));
			paciente.setBairroLogradouro(rs.getString("txt_bairro_logradouro"));
			paciente.setSequencial(rs.getInt("seq_paciente"));
			movimentacao.setPaciente(paciente);

			return movimentacao;
		}
	}

	private class RelatorioEquipamentosSinteticosRowMapper implements
			ParameterizedRowMapper<Movimentacao> {
		@Override
		public Movimentacao mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			final Movimentacao movimentacao = new Movimentacao();
			movimentacao.setSequencial(rs.getInt("total"));
			movimentacao.setApacBpi(rs.getString("txt_codigo_procedimento"));
			movimentacao.setObservacao(rs.getString("no_procedimento"));
			movimentacao.setSituacao(rs.getString("txt_descricao"));
			movimentacao.setNumeroNota(rs.getString("txt_cid"));

			return movimentacao;
		}

	}

	@Override
	public List<Movimentacao> listarRelatorioEquipamentosSintetico(
			Movimentacao movimentacao) {
	
		String sql = "select sah_procedimento.txt_codigo_procedimento,tb_procedimento.no_procedimento,sah_procedimento.txt_cid," +
				"sah_equipamento.txt_descricao,count(all num_quantidade)/6 as total from admsah001.sah_movimentacao ," +
				"admsah001.sah_procedimento,sigtap.tb_procedimento ,admsah001.sah_equipamento where "+
				"sah_equipamento.seq_equipamento = sah_procedimento.seq_equipamento and "+
				"sah_movimentacao.seq_programa = "+	movimentacao.getPrograma().getSequencial()+
				" AND sah_movimentacao.seq_movimentacao = sah_procedimento.seq_movimentacao and sigtap.tb_procedimento.co_procedimento = " +
				"sah_procedimento.txt_codigo_procedimento and "+				
				"sah_movimentacao.dth_solicitacao >='"
				+ SDF.format(movimentacao.getDataInicioSolicitacao())
				+ "'"
				+ " and dth_solicitacao <= '"
				+ SDF.format(movimentacao.getDataFimSolicitacao())
				+ "'"
				+ CriarSqlRelatorioEquipamentos(movimentacao)
				+ "group by sah_procedimento.txt_codigo_procedimento,sah_movimentacao.seq_paciente,tb_procedimento.no_procedimento," +
				"sah_equipamento.txt_descricao,sah_procedimento.txt_cid;";

		List<Movimentacao> movimentacoes = jdbcTemplate.query(sql,
				new RelatorioEquipamentosSinteticosRowMapper());
		
		return movimentacoes;
	}

	private class RelatorioEquipamentosSinteticosTotalRowMapper implements
			ParameterizedRowMapper<Movimentacao> {
		@Override
		public Movimentacao mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			final Movimentacao movimentacao = new Movimentacao();
			movimentacao.setSequencial(rs.getInt("total"));

			return movimentacao;
		}

	}
	public List<Movimentacao> listarQtdProcedimentos(
			Movimentacao movimentacao) {
		String sql = "select count(sah_procedimento.num_quantidade )from admsah001.sah_procedimento ,admsah001.sah_movimentacao"
					+"where sah_movimentacao.seq_movimentacao = sah_procedimento.seq_movimentacao and"
					+"sah_movimentacao.dth_solicitacao >='"+ SDF.format(movimentacao.getDataInicioSolicitacao()) + "'"
					+" and dth_solicitacao <= '"+ SDF.format(movimentacao.getDataFimSolicitacao())+"'"
					+"group by sah_procedimento.txt_codigo_procedimento ;";
				
				

		List<Movimentacao> movimentacoes = jdbcTemplate.query(sql,
				new RelatorioEquipamentosSinteticosTotalRowMapper());
		
		return movimentacoes;
	}
	
	@Override
	public List<Movimentacao> listarPaginandoParaRelatorio(PaginacaoVO movimentacao) {
		Movimentacao entidade = (Movimentacao) movimentacao.getEntidade();
		
		String sql = "select 	pa.seq_paciente, pa.txt_nome, " +
						"	sigpr.co_procedimento, sigpr.no_procedimento, " +
						"	pr.txt_categoria_tipo, pr.num_quantidade, pr.num_valor_serv_amb, " +
						"	mo.txt_numero_nota,  " +
						"	fo.txt_nome_fantasia," +
						"	me.txt_nome," +
						"	mo.txt_apac_bpi  " +
						"from admsah001.sah_procedimento pr, admsah001.sah_movimentacao mo, admsah001.sah_paciente pa, admsah001.sah_fornecedor fo,  " +
						"	sigtap.tb_procedimento sigpr, admsah001.sah_medico me " +
						"	 " +
						"where pr.seq_movimentacao = mo.seq_movimentacao " +
						"and mo.seq_paciente = pa.seq_paciente " +
						"and pr.txt_codigo_procedimento = sigpr.co_procedimento " +
						"and mo.seq_fornecedor is not null " +
						"and mo.seq_fornecedor = fo.seq_fornecedor " +
						"and mo.seq_medico = me.seq_medico " +
						"and mo.dth_solicitacao between '" + SDF.format(entidade.getDataInicioSolicitacao()) + "' and '" + SDF.format(entidade.getDataFimSolicitacao()) + "' ";
		
		if (entidade.getFornecedor().getSequencial() != null) {
			sql += 		"and fo.seq_fornecedor = " + entidade.getFornecedor().getSequencial() + " ";
		}
		
		sql += 			"and mo.seq_programa = " + entidade.getPrograma().getSequencial() + " " +
						"order by mo.dth_solicitacao";
		
		List<Movimentacao> movimentacoes = jdbcTemplate.query(sql, new RelatorioMovimentacoesRowMapper());
		
		return movimentacoes;
	}
	
	private class RelatorioMovimentacoesRowMapper implements ParameterizedRowMapper<Movimentacao>  {

		@Override
		public Movimentacao mapRow(ResultSet rs, int arg1)
				throws SQLException {
			Movimentacao movimentacao = new Movimentacao();
			movimentacao.setNumeroNota(rs.getString("txt_numero_nota"));
			movimentacao.setApacBpi(rs.getString("txt_apac_bpi"));
			
			Paciente paciente = new Paciente();
			paciente.setSequencial(rs.getInt("seq_paciente"));
			paciente.setNome(rs.getString("txt_nome"));
			movimentacao.setPaciente(paciente);
			
			List<MovimentacaoProcedimento> listaMovimentacaoProcedimento = new ArrayList<MovimentacaoProcedimento>();
			MovimentacaoProcedimento procedimento = new MovimentacaoProcedimento();
			procedimento.setSigtap_co_procedimento(rs.getString("co_procedimento"));
			procedimento.setSigtap_no_procedimento(rs.getString("no_procedimento"));
			procedimento.setCategoriaTipo(rs.getString("txt_categoria_tipo"));
			procedimento.setQuantidade(rs.getInt("num_quantidade"));
			procedimento.setSigtap_vl_sa(rs.getDouble("num_valor_serv_amb"));
			listaMovimentacaoProcedimento.add(procedimento);
			
			movimentacao.setProcedimentos(listaMovimentacaoProcedimento);
			
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setNomeFantasia(rs.getString("txt_nome_fantasia"));
			movimentacao.setFornecedor(fornecedor);
			
			Medico medico = new Medico();
			medico.setNome(rs.getString(10));
			movimentacao.setMedico(medico);
			
			return movimentacao;
		}
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluirMovimentacao(Movimentacao movimentacao) {
		String sqlMovimentacao = "delete from admsah001.sah_movimentacao m where m.seq_movimentacao = " + movimentacao.getSequencial();
		String sqlProcedimento = "delete from admsah001.sah_procedimento p where p.seq_movimentacao = " + movimentacao.getSequencial();
		
		jdbcTemplate.update(sqlProcedimento);
		jdbcTemplate.update(sqlMovimentacao);
		String upd = null;
		
	}
}
