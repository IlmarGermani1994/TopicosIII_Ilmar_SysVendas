CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    data_cadastro DATE NOT NULL,
    ativo BOOLEAN NOT NULL
);

INSERT INTO usuarios (nome, email, senha, data_cadastro, ativo)
VALUES ('Administrador', 'usuario@gmail.com', '123', CURRENT_DATE, TRUE);