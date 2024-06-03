create database barbeariaDB;
use barbeariaDB;

create table if not exists Funcionario(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(256),
	login VARCHAR(256),
    senha VARCHAR(256)
);

create table if not exists Cliente(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(256),
    telefone VARCHAR(15),
	email VARCHAR(256),
	preferencia TEXT
);

create table if not exists Servico(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(256) UNIQUE
);

create table if not exists Disponibilidade (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	funcionario_id_fk BIGINT NOT NULL,
		FOREIGN KEY (funcionario_id_fk) REFERENCES Funcionario(id),
	horario_inicio DATETIME NOT NULL,
	horario_fim DATETIME NOT NULL,
	UNIQUE (funcionario_id_fk, horario_inicio, horario_fim),
	ativo boolean default true
);

create table if not exists Agendar (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	funcionario_id_fk BIGINT NOT NULL,
		FOREIGN KEY (funcionario_id_fk) REFERENCES Funcionario(id),
	cliente_id_fk BIGINT NOT NULL,
		FOREIGN KEY (cliente_id_fk) REFERENCES Cliente(id),
	servico_id_fk BIGINT NOT NULL,
		FOREIGN KEY (servico_id_fk) REFERENCES Servico(id),
	horario DATETIME,
	ativo boolean
);

create table if not exists Agendar_Cliente(
	agendar_id BIGINT NOT NULL,
		FOREIGN KEY (agendar_id) REFERENCES Agendar(id),
	cliente_id BIGINT NOT NULL,
		FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);