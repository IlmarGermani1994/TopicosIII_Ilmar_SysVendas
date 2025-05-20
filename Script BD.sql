-- Criação do banco de dados
CREATE DATABASE sysvendas;

-- Conecte-se ao banco de dados antes de prosseguir
\c sysvendas;

-- Tabela de usuários
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(20) NOT NULL
);

CREATE TABLE unidades (
    id SERIAL PRIMARY KEY,
    sigla VARCHAR(10) UNIQUE NOT NULL,
    descricao VARCHAR(50) NOT NULL
);

INSERT INTO unidades (sigla, descricao) VALUES 
('kg', 'Quilograma'),
('un', 'Unidade'),
('pct', 'Pacote'),
('cx', 'Caixa'),
('l', 'Litro');

CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) UNIQUE NOT NULL
);


CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(20) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    valor_compra NUMERIC(10,2) NOT NULL,
    valor_venda NUMERIC(10,2) NOT NULL,
    estoque_atual NUMERIC(10,2) DEFAULT 0,
    ativo BOOLEAN DEFAULT TRUE,
    unidade_id INTEGER NOT NULL REFERENCES unidades(id),
    categoria_id INTEGER NOT NULL REFERENCES categorias(id)
);

-- Tabela de estoque
CREATE TABLE estoque (
    id SERIAL PRIMARY KEY,
    produto_id INTEGER NOT NULL REFERENCES produtos(id) ON DELETE CASCADE,
    quantidade INTEGER NOT NULL CHECK (quantidade >= 0)
);

-- Tabela de vendas
CREATE TABLE vendas (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL REFERENCES usuarios(id),
    data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    valor_total NUMERIC(10,2) NOT NULL
);

-- Tabela de itens da venda
CREATE TABLE itens_venda (
    id SERIAL PRIMARY KEY,
    venda_id INTEGER NOT NULL REFERENCES vendas(id) ON DELETE CASCADE,
    produto_id INTEGER NOT NULL REFERENCES produtos(id),
    quantidade INTEGER NOT NULL CHECK (quantidade > 0),
    valor_unitario NUMERIC(10,2) NOT NULL,
    subtotal NUMERIC(10,2) NOT NULL
);
