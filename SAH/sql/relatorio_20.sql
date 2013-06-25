-- dúvidas:
-- regional? e responsavel?
-- tecnico responsavel pela confecção ou montagem?
select
movimentacao.seq_movimentacao,
movimentacao.seq_paciente,
movimentacao.seq_fornecedor,
paciente.seq_usuario_cadastro,
paciente.txt_nome,
paciente.txt_cpf,
paciente.txt_rg,
paciente.txt_orgao_emissor,
paciente.txt_uf_orgao_emissor,
paciente.txt_nome_pai,
paciente.txt_nome_mae,
fornecedor.txt_nome_fantasia,
fornecedor.txt_endereco,
fornecedor.txt_cnpj,
fornecedor.txt_ie,
fornecedor.txt_telefone1,
fornecedor.txt_telefone2,
fornecedor.txt_telefone3,
fornecedor.txt_tec_resp,
procedimento.txt_categoria_tipo,
procedimento.sigtap_co_procedimento,
procedimento.num_quantidade,
equipamento.txt_descricao,
sigtap_procedimento.no_procedimento
from
admsah001.sah_movimentacao movimentacao,
admsah001.sah_paciente paciente,
admsah001.sah_movimentacao_procedimento procedimento,
admsah001.sah_equipamento equipamento,
admsah001.sah_fornecedor fornecedor,
sigtap.tb_procedimento sigtap_procedimento
where
movimentacao.seq_paciente = paciente.seq_paciente and
movimentacao.seq_movimentacao = procedimento.seq_movimentacao and
procedimento.seq_equipamento = equipamento.seq_equipamento and
movimentacao.seq_fornecedor = fornecedor.seq_fornecedor and
procedimento.sigtap_co_procedimento = sigtap_procedimento.co_procedimento and
movimentacao.seq_movimentacao = $P{ movimentacao.sequencial }