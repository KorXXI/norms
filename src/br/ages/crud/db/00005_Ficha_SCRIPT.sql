--liquibase formatted sql
--changeset cassio:1
--comment Script para cria��o das primeiras tabelas do projeto.
/***
* Scripts para criacao e insersao de dados
* Base Dados template
* Homero Oliveira
* 09 / 2016
***/

USE ficha_e;

ALTER TABLE TB_UNIDADE_MEDIDA CHANGE COLUMN DESCRICAO_ORIGEM UNIDADE_MEDIDA VARCHAR(60) NULL;
ALTER TABLE TB_UNIDADE_MEDIDA CHANGE COLUMN MEDIDA_CONVERSAO FATOR_CONVERSAO DOUBLE NULL;
ALTER TABLE TB_UNIDADE_MEDIDA CHANGE COLUMN DESCRICAO_CONVERSAO MEDIDA_CONVERSAO VARCHAR(60) NULL;
ALTER TABLE TB_UNIDADE_MEDIDA CHANGE COLUMN SIGLA SIGLA_UNIDADE_MEDIDA VARCHAR(10) NULL;


