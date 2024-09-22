CREATE TABLE vendedores (
    id LONG PRIMARY KEY,
    codigo_vendedor VARCHAR(50),
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE cobrancas (
    id LONG PRIMARY KEY,
    codigo_cobranca VARCHAR(50),
    valor_original DOUBLE PRECISION NOT NULL
);
