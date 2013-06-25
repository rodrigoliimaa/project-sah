select
movimentacao.seq_movimentacao,
movimentacao.seq_paciente,
movimentacao.seq_fornecedor,
movimentacao.txt_numero_nota,
movimentacao.sigtap_dt_competencia,
paciente.txt_nome,
fornecedor.txt_nome_fantasia,
procedimento.sigtap_co_procedimento,
procedimento.num_quantidade,
((procedimento.sigtap_vl_sa + procedimento.sigtap_vl_sh + procedimento.sigtap_vl_sp) * procedimento.num_quantidade) as num_valor,
sigtap_procedimento.no_procedimento,
	(
	select
	sum((sigtap_vl_sa + sigtap_vl_sh + sigtap_vl_sp) * num_quantidade)
	from
	admsah001.sah_movimentacao movimentacao, admsah001.sah_movimentacao_procedimento procedimento
	where
	movimentacao.seq_movimentacao = procedimento.seq_movimentacao
	and movimentacao.seq_fornecedor = $P{ fornecedor.sequencial }
	and movimentacao.sigtap_dt_competencia = $P{ movimentacao.sigtap_dt_competencia } -- exemplo: '201207'
	)
as num_valor_total
from
admsah001.sah_movimentacao movimentacao,
admsah001.sah_paciente paciente,
admsah001.sah_movimentacao_procedimento procedimento,
admsah001.sah_fornecedor fornecedor,
sigtap.tb_procedimento sigtap_procedimento
where
movimentacao.seq_paciente = paciente.seq_paciente and
movimentacao.seq_movimentacao = procedimento.seq_movimentacao and
movimentacao.seq_fornecedor = fornecedor.seq_fornecedor and
procedimento.sigtap_co_procedimento = sigtap_procedimento.co_procedimento and
fornecedor.seq_fornecedor = $P{ fornecedor.sequencial }
and movimentacao.sigtap_dt_competencia ilike $P{ movimentacao.sigtap_dt_competencia } -- exemplo: '201207'
order by paciente.txt_nome asc, movimentacao.seq_movimentacao asc