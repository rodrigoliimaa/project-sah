CREATE TABLE admsah001.sah_equipamento(
	seq_equipamento integer NOT NULL,
	txt_descricao character varying(50) NOT NULL,
	CONSTRAINT pk_equipamento PRIMARY KEY (seq_equipamento),
	CONSTRAINT ak_equipamento_01 UNIQUE (txt_descricao)
);

CREATE TABLE admsah001.sah_programa(
	seq_programa integer NOT NULL,
	txt_descricao character varying(50) NOT NULL,
	CONSTRAINT pk_programa PRIMARY KEY (seq_programa),
	CONSTRAINT ak_programa_01 UNIQUE (txt_descricao)
);

CREATE TABLE admsah001.sah_movimentacao(
	seq_movimentacao serial NOT NULL,
	txt_apac_bpi character varying(50),
	txt_observacao character varying(4000),
	txt_numero_nota character varying (50),
	dth_solicitacao date NOT NULL,
	dth_entrada date,
	dth_encaminhamento date,
	dth_entrega date,
	dth_autorizacao date,
	dth_vencimento date,
	txt_cid_principal character varying(4) NOT NULL,
	txt_cid_secundario character varying(4),
	txt_cid_causas_associadas character varying(4),
	txt_data_competencia character varying(6) NOT NULL,
	seq_fornecedor integer,
	seq_medico integer,
	seq_paciente integer NOT NULL,
	seq_programa integer NOT NULL,
	CONSTRAINT pk_movimentacao PRIMARY KEY (seq_movimentacao),
	CONSTRAINT fk_movimentacao_fornecedor
		FOREIGN KEY (seq_fornecedor)
		REFERENCES admsah001.sah_fornecedor (seq_fornecedor),
	CONSTRAINT fk_movimentacao_medico
		FOREIGN KEY (seq_medico)
		REFERENCES admsah001.sah_medico (seq_medico),
	CONSTRAINT fk_movimentacao_paciente
		FOREIGN KEY (seq_paciente)
		REFERENCES admsah001.sah_paciente (seq_paciente),
	CONSTRAINT fk_movimentacao_programa
		FOREIGN KEY (seq_programa)
		REFERENCES admsah001.sah_programa (seq_programa),
	CONSTRAINT ak_movimentacao_01 UNIQUE (txt_numero_nota)
);

CREATE TABLE admsah001.sah_procedimento(
	seq_procedimento serial NOT NULL,
	txt_codigo_procedimento character varying(10) NOT NULL,
	txt_cid character varying(4) NOT NULL,
	txt_categoria_tipo character varying(10),
	num_quantidade integer NOT NULL,
	num_valor_serv_amb numeric(10,0) NOT NULL,
	num_valor_serv_hosp numeric(10,0) NOT NULL,
	num_valor_serv_prof numeric(10,0) NOT NULL,
	seq_equipamento integer NOT NULL,
	seq_movimentacao integer NOT NULL,
	CONSTRAINT pk_procedimento PRIMARY KEY (seq_procedimento),
	CONSTRAINT fk_procedimento_equipamento
		FOREIGN KEY (seq_equipamento)
		REFERENCES admsah001.sah_equipamento (seq_equipamento),
	CONSTRAINT fk_procedimento_movimentacao
		FOREIGN KEY (seq_movimentacao)
		REFERENCES admsah001.sah_movimentacao (seq_movimentacao)
);

insert into admsah001.sah_equipamento(seq_equipamento, txt_descricao) values (0, 'ORTESE');
insert into admsah001.sah_equipamento(seq_equipamento, txt_descricao) values (1, 'PROTESE');
insert into admsah001.sah_equipamento(seq_equipamento, txt_descricao) values (2, 'CADEIRA DE RODAS');
insert into admsah001.sah_equipamento(seq_equipamento, txt_descricao) values (3, 'ANDADOR');
insert into admsah001.sah_equipamento(seq_equipamento, txt_descricao) values (4, 'MULETA');
insert into admsah001.sah_equipamento(seq_equipamento, txt_descricao) values (5, 'ADAPTACAO');

insert into admsah001.sah_programa(seq_programa, txt_descricao) values (1, 'ORTESE E PROTESE');
insert into admsah001.sah_programa(seq_programa, txt_descricao) values (2, 'AUDITIVO');