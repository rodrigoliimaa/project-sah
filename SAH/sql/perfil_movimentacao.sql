insert into admsah001.sah_grupo (txt_descricao)
values ('MOVIMENTACAO');

insert into admsah001.sah_permissao(txt_descricao, txt_chave)
values ('INSERIR MOVIMENTAÇÃO', 'ROLE_INSERIR_MOVIMENTACAO');

insert into admsah001.sah_permissao(txt_descricao, txt_chave)
values ('ALTERAR MOVIMENTAÇÃO', 'ROLE_ALTERAR_MOVIMENTACAO');

insert into admsah001.sah_permissao(txt_descricao, txt_chave)
values ('EXCLUIR MOVIMENTAÇÃO', 'ROLE_EXCLUIR_MOVIMENTACAO');

insert into admsah001.sah_permissao(txt_descricao, txt_chave)
values ('LISTAR MOVIMENTAÇÃO', 'ROLE_LISTAR_MOVIMENTACAO');

insert into admsah001.sah_grupo_permissao(seq_grupo, seq_permissao)
values (
	(select seq_grupo from admsah001.sah_grupo where txt_descricao = 'MOVIMENTACAO'),
	(select seq_permissao from admsah001.sah_permissao where txt_chave = 'ROLE_INSERIR_MOVIMENTACAO')
);

insert into admsah001.sah_grupo_permissao(seq_grupo, seq_permissao)
values (
	(select seq_grupo from admsah001.sah_grupo where txt_descricao = 'MOVIMENTACAO'),
	(select seq_permissao from admsah001.sah_permissao where txt_chave = 'ROLE_ALTERAR_MOVIMENTACAO')
);

insert into admsah001.sah_grupo_permissao(seq_grupo, seq_permissao)
values (
	(select seq_grupo from admsah001.sah_grupo where txt_descricao = 'MOVIMENTACAO'),
	(select seq_permissao from admsah001.sah_permissao where txt_chave = 'ROLE_EXCLUIR_MOVIMENTACAO')
);

insert into admsah001.sah_grupo_permissao(seq_grupo, seq_permissao)
values (
	(select seq_grupo from admsah001.sah_grupo where txt_descricao = 'MOVIMENTACAO'),
	(select seq_permissao from admsah001.sah_permissao where txt_chave = 'ROLE_LISTAR_MOVIMENTACAO')
);