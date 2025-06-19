CREATE TABLE agenda (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    medico_id BIGINT NOT NULL,
    data DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fim TIME NOT NULL,

    CONSTRAINT fk_agenda_medico FOREIGN KEY (medico_id) REFERENCES profissional(id),
    CONSTRAINT uq_medico_data_horario UNIQUE (medico_id, data, hora_inicio, hora_fim)
) engine=InnoDB default charset=utf8;