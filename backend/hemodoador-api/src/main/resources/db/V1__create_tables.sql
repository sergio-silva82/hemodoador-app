CREATE TABLE candidato (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(20) NOT NULL UNIQUE,
    nome VARCHAR(200),
    rg VARCHAR(20),
    data_nasc DATE,
    sexo VARCHAR(10),
    mae VARCHAR(200),
    pai VARCHAR(200),
    email VARCHAR(200),
    altura DECIMAL(4,2),
    peso DECIMAL(5,2),
    tipo_sanguineo VARCHAR(3) NOT NULL
);

CREATE TABLE telefone (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    candidato_id BIGINT NOT NULL,
    tipo ENUM('FIXO','CELULAR'),
    numero VARCHAR(30),
    FOREIGN KEY (candidato_id) REFERENCES candidato(id)
);

CREATE TABLE endereco (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	cep VARCHAR(20),
    endereco VARCHAR(200),
    numero INT,
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado CHAR(2)	
);

CREATE TABLE candidato_endereco (
	candidato_id BIGINT NOT NULL,
    endereco_id BIGINT NOT NULL,
    PRIMARY KEY (candidato_id, endereco_id),
    FOREIGN KEY (candidato_id) REFERENCES candidato(id),
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE usuario (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE,
  password VARCHAR(255),
  role VARCHAR(30)
);