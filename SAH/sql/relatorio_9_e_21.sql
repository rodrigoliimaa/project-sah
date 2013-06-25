select
movimentacao.seq_movimentacao,
movimentacao.seq_paciente,
movimentacao.seq_fornecedor,
movimentacao.dth_encaminhamento,
movimentacao.txt_observacao,
paciente.txt_nome,
paciente.txt_logradouro,
paciente.txt_numero_logradouro,
paciente.txt_bairro_logradouro,
paciente.txt_municipio_logradouro,
paciente.txt_uf_logradouro,
paciente.txt_cep,
paciente.txt_telefone_01,
paciente.txt_telefone_02,
paciente.txt_telefone_03,
fornecedor.txt_nome_fantasia,
fornecedor.txt_cnpj,
fornecedor.txt_endereco,
fornecedor.txt_telefone1,
fornecedor.txt_telefone2,
fornecedor.txt_telefone3,
procedimento.txt_categoria_tipo,
procedimento.sigtap_co_procedimento,
procedimento.num_quantidade,
sigtap_procedimento.no_procedimento
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
movimentacao.seq_movimentacao = $P{ movimentacao.sequencial }