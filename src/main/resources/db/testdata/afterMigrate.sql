set foreign_key_checks = 0;

delete from usuario;
delete from estado;
delete from cidade;
delete from especialidade;
delete from endereco;
delete from profissional;
delete from convenio;
delete from paciente;
delete from paciente_convenio;
delete from agenda;
delete from forma_pagamento;
delete from consulta;
delete from pagamento;

set foreign_key_checks = 1;

alter table usuario auto_increment = 1;
alter table estado auto_increment = 1;
alter table cidade auto_increment = 1;
alter table especialidade auto_increment = 1;
alter table endereco auto_increment = 1;
alter table profissional auto_increment = 1;
alter table convenio auto_increment = 1;
alter table paciente auto_increment = 1;
alter table paciente_convenio auto_increment = 1;
alter table agenda auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table consulta auto_increment = 1;
alter table pagamento auto_increment = 1;

INSERT INTO usuario (id, nome, email, senha, tipo, telefone)
VALUES 
  (1, 'João Souza', 'joao.souza@clinica.com', '123', 'PROFISSIONAL', 21992202450),
  (2, 'Mariana Oliveira', 'mariana.oliveira@clinica.com', '123', 'RECEPCIONISTA', null),
  (3, 'Carlos Mendes', 'carlos.mendes@clinica.com', '123', 'PROFISSIONAL', 21996587803);
  
INSERT INTO estado (id, nome, sigla) VALUES
(1, 'Acre', 'AC'),
(2, 'Alagoas', 'AL'),
(3, 'Amapá', 'AP'),
(4, 'Amazonas', 'AM'),
(5, 'Bahia', 'BA'),
(6, 'Ceará', 'CE'),
(7, 'Distrito Federal', 'DF'),
(8, 'Espírito Santo', 'ES'),
(9, 'Goiás', 'GO'),
(10, 'Maranhão', 'MA'),
(11, 'Mato Grosso', 'MT'),
(12, 'Mato Grosso do Sul', 'MS'),
(13, 'Minas Gerais', 'MG'),
(14, 'Pará', 'PA'),
(15, 'Paraíba', 'PB'),
(16, 'Paraná', 'PR'),
(17, 'Pernambuco', 'PE'),
(18, 'Piauí', 'PI'),
(19, 'Rio de Janeiro', 'RJ'),
(20, 'Rio Grande do Norte', 'RN'),
(21, 'Rio Grande do Sul', 'RS'),
(22, 'Rondônia', 'RO'),
(23, 'Roraima', 'RR'),
(24, 'Santa Catarina', 'SC'),
(25, 'São Paulo', 'SP'),
(26, 'Sergipe', 'SE'),
(27, 'Tocantins', 'TO');

INSERT INTO cidade (id, nome, estado_id) VALUES
(1, 'Angra dos Reis', 19),
(2, 'Aperibé', 19),
(3, 'Araruama', 19),
(4, 'Areal', 19),
(5, 'Armação dos Búzios', 19),
(6, 'Arraial do Cabo', 19),
(7, 'Barra do Piraí', 19),
(8, 'Barra Mansa', 19),
(9, 'Belford Roxo', 19),
(10, 'Bom Jardim', 19),
(11, 'Bom Jesus do Itabapoana', 19),
(12, 'Cabo Frio', 19),
(13, 'Cachoeiras de Macacu', 19),
(14, 'Cambuci', 19),
(15, 'Campos dos Goytacazes', 19),
(16, 'Cantagalo', 19),
(17, 'Carapebus', 19),
(18, 'Cardoso Moreira', 19),
(19, 'Carmo', 19),
(20, 'Casimiro de Abreu', 19),
(21, 'Comendador Levy Gasparian', 19),
(22, 'Conceição de Macabu', 19),
(23, 'Cordeiro', 19),
(24, 'Duas Barras', 19),
(25, 'Duque de Caxias', 19),
(26, 'Engenheiro Paulo de Frontin', 19),
(27, 'Guapimirim', 19),
(28, 'Iguaba Grande', 19),
(29, 'Itaboraí', 19),
(30, 'Itaguaí', 19),
(31, 'Italva', 19),
(32, 'Itaocara', 19),
(33, 'Itaperuna', 19),
(34, 'Itatiaia', 19),
(35, 'Japeri', 19),
(36, 'Laje do Muriaé', 19),
(37, 'Macaé', 19),
(38, 'Macuco', 19),
(39, 'Magé', 19),
(40, 'Mangaratiba', 19),
(41, 'Maricá', 19),
(42, 'Mendes', 19),
(43, 'Mesquita', 19),
(44, 'Miguel Pereira', 19),
(45, 'Miracema', 19),
(46, 'Natividade', 19),
(47, 'Nilópolis', 19),
(48, 'Niterói', 19),
(49, 'Nova Friburgo', 19),
(50, 'Nova Iguaçu', 19),
(51, 'Paracambi', 19),
(52, 'Paraíba do Sul', 19),
(53, 'Paraty', 19),
(54, 'Paty do Alferes', 19),
(55, 'Petrópolis', 19),
(56, 'Pinheiral', 19),
(57, 'Piraí', 19),
(58, 'Porciúncula', 19),
(59, 'Porto Real', 19),
(60, 'Quatis', 19),
(61, 'Queimados', 19),
(62, 'Quissamã', 19),
(63, 'Resende', 19),
(64, 'Rio Bonito', 19),
(65, 'Rio Claro', 19),
(66, 'Rio das Flores', 19),
(67, 'Rio das Ostras', 19),
(68, 'Rio de Janeiro', 19),
(69, 'Santa Maria Madalena', 19),
(70, 'Santo Antônio de Pádua', 19),
(71, 'São Fidélis', 19),
(72, 'São Francisco de Itabapoana', 19),
(73, 'São Gonçalo', 19),
(74, 'São João da Barra', 19),
(75, 'São João de Meriti', 19),
(76, 'São José de Ubá', 19),
(77, 'São José do Vale do Rio Preto', 19),
(78, 'São Pedro da Aldeia', 19),
(79, 'São Sebastião do Alto', 19),
(80, 'Sapucaia', 19),
(81, 'Saquarema', 19),
(82, 'Seropédica', 19),
(83, 'Silva Jardim', 19),
(84, 'Sumidouro', 19),
(85, 'Tanguá', 19),
(86, 'Teresópolis', 19),
(87, 'Trajano de Moraes', 19),
(88, 'Três Rios', 19),
(89, 'Valença', 19),
(90, 'Varre-Sai', 19),
(91, 'Vassouras', 19),
(92, 'Volta Redonda', 19);

INSERT INTO especialidade (id, descricao) VALUES
(1, 'Alergia e Imunologia'),
(2, 'Anestesiologia'),
(3, 'Angiologia'),
(4, 'Cardiologia'),
(5, 'Cirurgia Cardiovascular'),
(6, 'Cirurgia da Mão'),
(7, 'Cirurgia de Cabeça e Pescoço'),
(8, 'Cirurgia do Aparelho Digestivo'),
(9, 'Cirurgia Geral'),
(10, 'Cirurgia Oncológica'),
(11, 'Cirurgia Pediátrica'),
(12, 'Cirurgia Plástica'),
(13, 'Cirurgia Torácica'),
(14, 'Cirurgia Vascular'),
(15, 'Clínica Médica'),
(16, 'Coloproctologia'),
(17, 'Dermatologia'),
(18, 'Endocrinologia e Metabologia'),
(19, 'Endoscopia'),
(20, 'Gastroenterologia'),
(21, 'Genética Médica'),
(22, 'Geriatria'),
(23, 'Ginecologia e Obstetrícia'),
(24, 'Hematologia e Hemoterapia'),
(25, 'Homeopatia'),
(26, 'Infectologia'),
(27, 'Mastologia'),
(28, 'Medicina de Emergência'),
(29, 'Medicina de Família e Comunidade'),
(30, 'Medicina do Trabalho'),
(31, 'Medicina Esportiva'),
(32, 'Medicina Física e Reabilitação'),
(33, 'Medicina Intensiva'),
(34, 'Medicina Legal e Perícia Médica'),
(35, 'Medicina Nuclear'),
(36, 'Medicina Preventiva e Social'),
(37, 'Nefrologia'),
(38, 'Neurocirurgia'),
(39, 'Neurologia'),
(40, 'Nutrologia'),
(41, 'Oftalmologia'),
(42, 'Oncologia Clínica'),
(43, 'Ortopedia e Traumatologia'),
(44, 'Otorrinolaringologia'),
(45, 'Patologia'),
(46, 'Patologia Clínica/Medicina Laboratorial'),
(47, 'Pediatria'),
(48, 'Pneumologia'),
(49, 'Psiquiatria'),
(50, 'Radiologia e Diagnóstico por Imagem'),
(51, 'Radioterapia'),
(52, 'Reumatologia'),
(53, 'Urologia');

INSERT INTO endereco (
    id,
    logradouro,
    numero,
    bairro,
    cidade_id,
    cep,
    complemento
)
VALUES
    (1, 'Rua das Palmeiras', '120', 'Jardim das Flores', 1, '12345-678', 'Casa 1'),
    (2, 'Av. Brasil', '450', 'Centro', 2, '23456-789', 'Apto 302 - Bloco B'),
    (3, 'Rua São João', '88', 'Bela Vista', 3, '34567-890', NULL);

INSERT INTO profissional (
    id,
    usuario_id,
    especialidade_id,
    crm,
    genero_pessoa,
    convenio_participa,
    endereco_id
)
VALUES 
    (1, 1, 1, '123456-SP', 'MASCULINO', TRUE, 1),
    (2, 2, 2, '654321-SP', 'FEMININO', FALSE, 2),
    (3, 3, 3, '789012-SP', 'MASCULINO', TRUE, 3);

INSERT INTO convenio (id, nome, cnpj, email, telefone) VALUES
(1, 'Amil', '01.001.001/0001-01', 'contato@amil.com.br', '(11) 3019-00001'),
(2, 'Bradesco Saúde', '02.002.002/0001-02', 'contato@bradescosaúde.com.br', '(11) 3029-00002'),
(3, 'Unimed', '03.003.003/0001-03', 'contato@unimed.com.br', '(11) 3039-00003'),
(4, 'Hapvida', '04.004.004/0001-04', 'contato@hapvida.com.br', '(11) 3049-00004'),
(5, 'SulAmérica Saúde', '05.005.005/0001-05', 'contato@sulaméricasaúde.com.br', '(11) 3059-00005'),
(6, 'NotreDame Intermédica', '06.006.006/0001-06', 'contato@notredameintermédica.com.br', '(11) 3069-00006'),
(7, 'Porto Seguro Saúde', '07.007.007/0001-07', 'contato@portosegurosaúde.com.br', '(11) 3079-00007'),
(8, 'Caixa Seguradora Saúde', '08.008.008/0001-08', 'contato@caixaseguradorasaúde.com.br', '(11) 3089-00008'),
(9, 'Assim Saúde', '09.009.009/0001-09', 'contato@assimsaúde.com.br', '(11) 3099-00009'),
(10, 'São Francisco Saúde', '10.010.010/0001-10', 'contato@sãofranciscosaúde.com.br', '(11) 3109-00010'),
(11, 'Promed Assistência Médica', '11.011.011/0001-11', 'contato@promedassistênciamédica.com.br', '(11) 3119-00011'),
(12, 'Cassi', '12.012.012/0001-12', 'contato@cassi.com.br', '(11) 3129-00012'),
(13, 'Postal Saúde', '13.013.013/0001-13', 'contato@postalsaúde.com.br', '(11) 3139-00013'),
(14, 'GEAP Saúde', '14.014.014/0001-14', 'contato@geapsaúde.com.br', '(11) 3149-00014'),
(15, 'CNU - Central Nacional Unimed', '15.015.015/0001-15', 'contato@cnu-centralnacionalunimed.com.br', '(11) 3159-00015'),
(16, 'MedSênior', '16.016.016/0001-16', 'contato@medsênior.com.br', '(11) 3169-00016'),
(17, 'Viva Saúde', '17.017.017/0001-17', 'contato@vivasaúde.com.br', '(11) 3179-00017'),
(18, 'Biovida Saúde', '18.018.018/0001-18', 'contato@biovidasaúde.com.br', '(11) 3189-00018'),
(19, 'GreenLine', '19.019.019/0001-19', 'contato@greenline.com.br', '(11) 3199-00019'),
(20, 'Trasmontano Saúde', '20.020.020/0001-20', 'contato@trasmontanosaúde.com.br', '(11) 3209-00020');

INSERT INTO paciente (
    id,
    usuario_id,
    data_nascimento,
    cpf,
    genero_pessoa,
    nome,
    endereco_id
) VALUES 
(1, null, '1990-05-14', '12345678900', 'MASCULINO', 'Carlos Eduardo Silva', null),
(2, null, '1985-09-22', '98765432100', 'FEMININO', 'Mariana Lopes Pereira', null);

insert into forma_pagamento(id, descricao)
values (1, 'Dinheiro'),
(2, 'Cartão de crédito'),
(3, 'Cartão de débito'),
(4, 'Pix'),
(5, 'Bitcoin');

insert into agenda(id, medico_id, data, hora_inicio, hora_fim)
values(1, 1, '2025-05-20', '15:00:00', '15:30:00');