CREATE TABLE estado (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome varchar(100) NOT NULL,
    sigla varchar(5) NOT NULL
) engine=InnoDB default charset=utf8;

CREATE TABLE cidade (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome varchar(100) NOT NULL,
    estado_id BIGINT NOT NULL,

    CONSTRAINT fk_cidade_estado FOREIGN KEY (estado_id) REFERENCES estado(id)
) engine=InnoDB default charset=utf8;

CREATE TABLE endereco (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    logradouro varchar(100) NOT NULL,
    numero varchar(10) NOT NULL,
    bairro varchar(100) NOT NULL,
    cidade_id BIGINT NOT NULL,
    cep varchar(10) NOT NULL,
    complemento varchar(100) NULL,

    CONSTRAINT fk_endereco_cidade FOREIGN KEY (cidade_id) REFERENCES cidade(id)
) engine=InnoDB default charset=utf8;