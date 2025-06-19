CREATE TABLE paciente_convenio (
    paciente_id BIGINT NOT NULL,
    convenio_id BIGINT NOT NULL,
    numero_carteirinha VARCHAR(100) NOT NULL,
    data_validade DATE NOT NULL,

    PRIMARY KEY (paciente_id, convenio_id),
    FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    FOREIGN KEY (convenio_id) REFERENCES convenio(id)
) engine=InnoDB default charset=utf8;