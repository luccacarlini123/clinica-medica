CREATE TABLE profissional (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL UNIQUE,
    especialidade_id BIGINT NOT NULL,
    crm VARCHAR(20) NULL UNIQUE,
    genero_pessoa varchar(50),
    convenio_participa BOOLEAN DEFAULT FALSE,
    endereco_id BIGINT NOT NULL,

    CONSTRAINT fk_profissional_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_profissional_especialidade FOREIGN KEY (especialidade_id) REFERENCES especialidade(id),
    CONSTRAINT fk_profissional_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
) engine=InnoDB default charset=utf8;