set foreign_key_checks = 0;

delete from usuario;

set foreign_key_checks = 1;

INSERT INTO usuario (id, nome, email, senha, tipo, telefone)
VALUES 
  (1, 'João Souza', 'joao.souza@clinica.com', '123', 'MEDICO', 21992202450),
  (2, 'Mariana Oliveira', 'mariana.oliveira@clinica.com', '123', 'RECEPCIONISTA', null),
  (3, 'Carlos Mendes', 'carlos.mendes@clinica.com', '123', 'MEDICO', 21996587803);