CREATE TABLE paciente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuario_id BIGINT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    genero_pessoa varchar(50) NOT NULL,
    nome varchar(100) NOT NULL,
    endereco_id BIGINT NULL UNIQUE,

    CONSTRAINT fk_paciente_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_paciente_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
) engine=InnoDB default charset=utf8;