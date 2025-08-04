CREATE TABLE pagamento (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    consulta_id BIGINT NOT NULL UNIQUE,
    valor DECIMAL(10,2) NOT NULL,
    forma_pagamento_id BIGINT NOT NULL,
    data_pagamento DATETIME NULL,
    status VARCHAR(50) NOT NULL,

    FOREIGN KEY (consulta_id) REFERENCES consulta(id),
    FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento(id)
) engine=InnoDB default charset=utf8;