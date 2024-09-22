INSERT INTO vendedores (codigo_vendedor, nome) VALUES ('VEND123', 'João Silva');
INSERT INTO vendedores (codigo_vendedor, nome) VALUES ('VEND456', 'Maria Oliveira');

INSERT INTO cobrancas (codigo_cobranca, valor_original, codigo_vendedor) VALUES ('Cobranca1', 100.0, 'VEND123');
INSERT INTO cobrancas (codigo_cobranca, valor_original, codigo_vendedor) VALUES ('Cobranca2', 50.0, 'VEND123');
INSERT INTO cobrancas (codigo_cobranca, valor_original, codigo_vendedor) VALUES ('Cobranca3', 75.0, 'VEND456');
