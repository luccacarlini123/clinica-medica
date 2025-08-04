CREATE TABLE consulta (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    paciente_id BIGINT NOT NULL,
    agenda_id BIGINT NULL UNIQUE,
    status VARCHAR(50) NOT NULL, 
    observacoes TEXT,
    data_hora_registro DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY (agenda_id) REFERENCES agenda(id),
    FOREIGN KEY (paciente_id) REFERENCES paciente(id)
) engine=InnoDB default charset=utf8;