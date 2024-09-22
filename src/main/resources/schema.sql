CREATE TABLE vendedores (
    codigo_vendedor VARCHAR(50) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE cobrancas (
    codigo_cobranca VARCHAR(50) PRIMARY KEY,
    valor_original DOUBLE PRECISION NOT NULL,
    codigo_vendedor VARCHAR(50) NOT NULL,
    FOREIGN KEY (codigo_vendedor) REFERENCES vendedores(codigo_vendedor)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
