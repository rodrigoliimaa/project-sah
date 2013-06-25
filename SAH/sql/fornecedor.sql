create table admsah001.sah_fornecedor(
	seq_fornecedor serial NOT NULL,
	txt_cnpj character varying(14) NOT NULL,
	dth_cadastro timestamp without time zone DEFAULT now(),
	txt_email character varying(30),
	-- txt_endereco character varying(100),
	txt_cep character varying(8) NOT NULL,
    txt_uf character varying(2) NOT NULL,
	txt_municipio character varying(50) NOT NULL,
    txt_codigo_ibge_municipio character varying(8) NOT NULL,
    txt_logradouro character varying(100) NOT NULL,
  	txt_numero character varying(10) NOT NULL,
  	txt_complemento character varying(100),
  	txt_referencia character varying(100),
  	txt_bairro character varying(50) NOT NULL,
	txt_ind_ativo character varying(1) DEFAULT 'S'::character varying,
	txt_ie character varying(20) NOT NULL,
	txt_im character varying(20) NOT NULL,
  	txt_nome_fantasia character varying(50) NOT NULL,
  	txt_razao_social character varying(100) NOT NULL,
	txt_tec_resp character varying(100) NOT NULL,
	txt_telefone1 character varying(30),
    txt_telefone2 character varying(30),
    txt_telefone3 character varying(30),
	num_val_max_pedidos numeric(20,0) NOT NULL,
	CONSTRAINT pk_fornecedor PRIMARY KEY (seq_fornecedor),
	CONSTRAINT ak_fornecedor_01 UNIQUE (txt_cnpj),
	CONSTRAINT ak_fornecedor_02 UNIQUE (txt_ie),
	CONSTRAINT ak_fornecedor_03 UNIQUE (txt_im)
)

/* Data for the `FORNECEDOR` table  (Records 1 - 9) */

INSERT INTO ADMSAH001.SAH_FORNECEDOR
(seq_fornecedor, txt_nome_fantasia, txt_razao_social, txt_cnpj, 
txt_logradouro, txt_tec_resp, num_val_max_pedidos, txt_bairro, txt_cep,
 txt_municipio, txt_uf, txt_telefone1, txt_telefone2, txt_ie, txt_im)
VALUES (1, 'OTICOM TELEX', 'OTICOM TELEX', '33060302000104', NULL, '1', 1000000, NULL, '57300000', NULL, NULL, NULL, NULL, '1', '1');

INSERT INTO ADMSAH001.SAH_FORNECEDOR
(seq_fornecedor, txt_nome_fantasia, txt_razao_social, txt_cnpj, 
txt_logradouro, txt_tec_resp, num_val_max_pedidos, txt_bairro, txt_cep,
 txt_municipio, txt_uf, txt_telefone1, txt_telefone2, txt_ie, txt_im)
VALUES (2, 'AUDIOTEC', 'AUDIOTEC', '2', NULL, '2', 1000000, NULL, '57300000', NULL, NULL, NULL, NULL, '2', '2');

INSERT INTO ADMSAH001.SAH_FORNECEDOR
(seq_fornecedor, txt_nome_fantasia, txt_razao_social, txt_cnpj, 
txt_logradouro, txt_tec_resp, num_val_max_pedidos, txt_bairro, txt_cep,
 txt_municipio, txt_uf, txt_telefone1, txt_telefone2, txt_ie, txt_im)
VALUES (3, 'MEDAUDIO', 'MEDAUDIO', '3', NULL, '3', 1000000, NULL, '57300000', NULL, NULL, NULL, NULL, '3', '3');

INSERT INTO ADMSAH001.SAH_FORNECEDOR
(seq_fornecedor, txt_nome_fantasia, txt_razao_social, txt_cnpj, 
txt_logradouro, txt_tec_resp, num_val_max_pedidos, txt_bairro, txt_cep,
 txt_municipio, txt_uf, txt_telefone1, txt_telefone2, txt_ie, txt_im)
VALUES (4, 'UTENSILAB', 'UTENSILAB', '11779004000136', 'RUA CLEMENTINO DO MONTE, No. 58', 'LUPERCIO FRAGOSO GUEDES', 30301, 'FAROL', '57055190', 'MACEIO', 'AL', '33381865', '33178000', '242248144', '4');

INSERT INTO ADMSAH001.SAH_FORNECEDOR
(seq_fornecedor, txt_nome_fantasia, txt_razao_social, txt_cnpj, 
txt_logradouro, txt_tec_resp, num_val_max_pedidos, txt_bairro, txt_cep,
 txt_municipio, txt_uf, txt_telefone1, txt_telefone2, txt_ie, txt_im)
VALUES (5, 'ORTOTEC', 'ORTOTEC', '24499337000153', 'RUA DR. ABELARDO PONTES DE LIMA, No. 687', 'FRANCISCO EVANDRO DE MORAES', 16500, 'GRUTA', '57052695', 'MACEIO', 'AL', '32411675', NULL, '160847605', '5');

INSERT INTO ADMSAH001.SAH_FORNECEDOR
(seq_fornecedor, txt_nome_fantasia, txt_razao_social, txt_cnpj, 
txt_logradouro, txt_tec_resp, num_val_max_pedidos, txt_bairro, txt_cep,
 txt_municipio, txt_uf, txt_telefone1, txt_telefone2, txt_ie, txt_im)
VALUES (6, 'ORTECAL', 'ORTECAL', '03648848000175', 'TRAV. BOMFIM, No. 180', 'AMERICO AMORIM DOS SANTOS JUNIOR', 16500, 'POCO', '57025600', 'MACEIO', 'AL', '32213541', '99753069', '248399144', '6');

INSERT INTO ADMSAH001.SAH_FORNECEDOR
(seq_fornecedor, txt_nome_fantasia, txt_razao_social, txt_cnpj, 
txt_logradouro, txt_tec_resp, num_val_max_pedidos, txt_bairro, txt_cep,
 txt_municipio, txt_uf, txt_telefone1, txt_telefone2, txt_ie, txt_im)
VALUES (7, 'UTENSIMED', 'UTENSIMED', '01050882000162', 'AV. PROFESSOR LOUREIRO, 123', 'JOSÉ MARCELO DA SILVA', 1000000, 'PONTA GROSSA', '57014210', 'MACEIÓ', 'AL', '33360689', '33381904', '240887425','7');

INSERT INTO ADMSAH001.SAH_FORNECEDOR
(seq_fornecedor, txt_nome_fantasia, txt_razao_social, txt_cnpj, 
txt_logradouro, txt_tec_resp, num_val_max_pedidos, txt_bairro, txt_cep,
 txt_municipio, txt_uf, txt_telefone1, txt_telefone2, txt_ie, txt_im)
VALUES (8, 'ORTOTEC - OTNN', 'ORTOTEC - OTNN', '10668364000106', 'RUA DR. ABELARDO PONTES LIMA, N° 687 B', 'FRANCISCO EVANDRO DE MORAES', 1000000, 'GRUTA', '57052695', 'MACEIO', 'AL', '32411675', NULL, '242165320','8');

INSERT INTO ADMSAH001.SAH_FORNECEDOR
(seq_fornecedor, txt_nome_fantasia, txt_razao_social, txt_cnpj, 
txt_logradouro, txt_tec_resp, num_val_max_pedidos, txt_bairro, txt_cep,
 txt_municipio, txt_uf, txt_telefone1, txt_telefone2, txt_ie, txt_im)
VALUES (9, 'ORTOPER', 'ORTOPER', '03432545000165',
 'RUA CLEMENTINO DO MONTE, N° 151 - FAROL', 'WELLINGTON MARQUES FERREIRA',
  1000000, 'FAROL', '57055190', 'MACEIO', 'AL', '93155593', NULL, '242471013',
  '9');

select setval('admsah001.sah_fornecedor_seq_fornecedor_seq',8);