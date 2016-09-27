USE ficha_e;
-- DROP TABLE TB_EMPRESA;

CREATE TABLE TB_EMPRESA (
  ID_EMPRESA int(11) NOT NULL AUTO_INCREMENT,
  CNPJ varchar(45) NOT NULL,
  TELEFONE varchar(45) NOT NULL,
  NOME varchar(45) NOT NULL,
  ENDERECO varchar(20) NOT NULL,
  CIDADE varchar(20) NOT NULL,
  RAZAO_SOCIAL VARCHAR(45) NOT NULL,
  RESPONSAVEL varchar(11) NOT NULL,
  LOGO varchar(255),
  DATA_INCLUSAO datetime DEFAULT NULL,
  PRIMARY KEY (ID_EMPRESA,CNPJ),
  UNIQUE KEY CPF_UNIQUE  (CNPJ),
  CONSTRAINT U_USERNAME UNIQUE (NOME)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;



INSERT INTO TB_EMPRESA
(ID_EMPRESA, CNPJ, TELEFONE, NOME, ENDERECO, CIDADE, RAZAO_SOCIAL, RESPONSAVEL, LOGO, DATA_INCLUSAO)
VALUES
(null, '17338792000101', '05199677239', 'Go Horse Solu��es', 'r. das empresas, 69', 'Ponta Grossa', 'Socialista Nacional', 'Hortencia', null, '2015-10-01 00:00:00');

