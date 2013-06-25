-- dúvidas:
-- municipio residencia?
-- observacao:
-- não dá pra fazer apenas com sql por causa do cid (uma movimentação tem vários procedimentos e vários cids),
-- quando temos 2 cids e 3 procedimentos para uma movimentação então teremos 6 linhas como resultado,
-- a solução será montar e passar o objeto
select
movimentacao.seq_movimentacao,
movimentacao.seq_paciente,
paciente.txt_cns,
paciente.txt_nome,
paciente.dat_nascimento,
paciente.txt_sexo,
paciente.seq_raca,
procedimento.sigtap_co_procedimento,
procedimento.num_quantidade,
cid.sigtap_co_cid
from
admsah001.sah_movimentacao movimentacao,
admsah001.sah_paciente paciente,
admsah001.sah_movimentacao_procedimento procedimento,
admsah001.sah_movimentacao_cid cid
where
movimentacao.seq_paciente = paciente.seq_paciente and
movimentacao.seq_movimentacao = procedimento.seq_movimentacao and
movimentacao.seq_movimentacao = cid.seq_movimentacao and
movimentacao.dth_entrada is not null and
movimentacao.dth_encaminhamento is not null and
movimentacao.dth_entrega is not null and
order by txt_nome asc