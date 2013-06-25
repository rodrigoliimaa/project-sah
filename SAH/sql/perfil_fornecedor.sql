insert into admsah001.sah_grupo (txt_descricao)
values ('FORNECEDOR');

insert into admsah001.sah_permissao(txt_descricao, txt_chave)
values ('INSERIR FORNECEDOR', 'ROLE_INSERIR_FORNECEDOR');

insert into admsah001.sah_permissao(txt_descricao, txt_chave)
values ('ALTERAR FORNECEDOR', 'ROLE_ALTERAR_FORNECEDOR');

insert into admsah001.sah_permissao(txt_descricao, txt_chave)
values ('EXCLUIR FORNECEDOR', 'ROLE_EXCLUIR_FORNECEDOR');

insert into admsah001.sah_permissao(txt_descricao, txt_chave)
values ('LISTAR FORNECEDORES', 'ROLE_LISTAR_FORNECEDOR');

insert into admsah001.sah_grupo_permissao(seq_grupo, seq_permissao)
values (
	(select seq_grupo from admsah001.sah_grupo where txt_descricao = 'FORNECEDOR'),
	(select seq_permissao from admsah001.sah_permissao where txt_chave = 'ROLE_INSERIR_FORNECEDOR')
);

insert into admsah001.sah_grupo_permissao(seq_grupo, seq_permissao)
values (
	(select seq_grupo from admsah001.sah_grupo where txt_descricao = 'FORNECEDOR'),
	(select seq_permissao from admsah001.sah_permissao where txt_chave = 'ROLE_ALTERAR_FORNECEDOR')
);

insert into admsah001.sah_grupo_permissao(seq_grupo, seq_permissao)
values (
	(select seq_grupo from admsah001.sah_grupo where txt_descricao = 'FORNECEDOR'),
	(select seq_permissao from admsah001.sah_permissao where txt_chave = 'ROLE_EXCLUIR_FORNECEDOR')
);

insert into admsah001.sah_grupo_permissao(seq_grupo, seq_permissao)
values (
	(select seq_grupo from admsah001.sah_grupo where txt_descricao = 'FORNECEDOR'),
	(select seq_permissao from admsah001.sah_permissao where txt_chave = 'ROLE_LISTAR_FORNECEDOR')
);