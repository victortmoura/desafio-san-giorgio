# Desafio San Giorgio

## Descrição
Este projeto é uma aplicação em Spring Boot que processa pagamentos, validando vendedores e cobranças, e enviando mensagens para filas SQS da AWS.

## Tecnologias Usadas
- Java
- Spring Boot
- AWS SQS
- H2 Database
- Lombok
- MapStruct

## Instalação
1. Clone o repositório:
   ```bash
   git clone https://github.com/victortmoura/desafio-san-giorgio.git
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd <project-directory>
   ```
3. Instale as dependências:
   ```bash
   mvn install
   ```

## Configuração
**Preencha suas credenciais**:
- Abra o arquivo `application-local.properties.example` e insira suas credenciais AWS:
   ```properties
   aws.accessKeyId=YOUR_ACCESS_KEY_ID
   aws.secretKey=YOUR_SECRET_ACCESS_KEY
   aws.region=YOUR_AWS_REGION
  
## Configurações do H2:
O console do H2 pode ser acessado em http://localhost:8080/h2-console.
Use as configurações padrão para se conectar ao banco de dados em memória.

## Execução
1. Inicie a aplicação:
   ```bash
   mvn spring-boot:run
   ```
   
## Uso da API
1. Exemplo de requisição para o endpoint de pagamentos:
```bash
curl --location 'localhost:8080/api/pagamentos' \
--header 'Content-Type: application/json' \
--data '{
            "codigoVendedor": "VEND123",
            "pagamentos": [
                {
                  "codigoCobranca": "Cobranca1",
                  "valorPago": 100.0
                },
                {
                  "codigoCobranca": "Cobranca2",
                  "valorPago": 50.0
                }
            ]
        }'
   ```

## Validações
* Existência do Vendedor: Verifica se o vendedor informado existe na base de dados.
* Existência da Cobrança: Para cada pagamento, verifica se a cobrança correspondente existe.
* Status de Pagamento: Determina se o pagamento é TOTAL, PARCIAL ou EXCEDENTE com base no valor pago em comparação ao valor original.

 
