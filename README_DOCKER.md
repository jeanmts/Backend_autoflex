# 🐳 Docker Setup - AutoFlex

## Pré-requisitos

- Docker instalado (versão 20.10+)
- Docker Compose instalado (versão 1.29+)

## 🚀 Iniciar os Serviços

### 1. Construir e iniciar os containers

```bash
docker-compose up --build
```

Este comando irá:
- ✅ Construir a imagem do aplicativo Spring Boot
- ✅ Iniciar o PostgreSQL
- ✅ Executar as migrations do Hibernate
- ✅ Iniciar a aplicação na porta `8080`

### 2. Parar os serviços

```bash
docker-compose down
```

### 3. Remover volumes (limpar dados do banco)

```bash
docker-compose down -v
```

## 📊 Acessar o Banco de Dados

### Via pgAdmin (Opcional)

Se quiser acessar o banco via interface gráfica, adicione ao `docker-compose.yml`:

```yaml
pgadmin:
  image: dpage/pgadmin4:latest
  container_name: autoflex-pgadmin
  environment:
    PGADMIN_DEFAULT_EMAIL: admin@autoflex.com
    PGADMIN_DEFAULT_PASSWORD: admin
  ports:
    - "5050:80"
  depends_on:
    - postgres
  networks:
    - autoflex-network
```

### Via psql (linha de comando)

```bash
docker exec -it autoflex-postgres psql -U postgres -d autoflex
```

## 🌐 URLs da Aplicação

- **API REST**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 📝 Variáveis de Ambiente

Edite o arquivo `.env` para customizar:

```env
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_DB=autoflex
SPRING_JPA_SHOW_SQL=false
```

## 🔍 Logs

### Ver logs da aplicação

```bash
docker-compose logs -f app
```

### Ver logs do PostgreSQL

```bash
docker-compose logs -f postgres
```

## ⚠️ Troubleshooting

### Erro de conexão com banco de dados

Verifique se o PostgreSQL está saudável:

```bash
docker-compose ps
```

Aguarde alguns segundos para o healthcheck passar (até 30s).

### Porta 5432 já em uso

Se a porta 5432 já está sendo usada, mude no `docker-compose.yml`:

```yaml
postgres:
  ports:
    - "5433:5432"  # Host:Container
```

### Limpar tudo e recomeçar

```bash
docker-compose down -v
docker-compose up --build
```

## 📦 Estrutura

```
.
├── docker-compose.yml      # Configuração dos containers
├── Dockerfile              # Build da aplicação
├── .dockerignore           # Arquivos ignorados no build
├── .env                    # Variáveis de ambiente
└── pom.xml                 # Dependências Maven
```

## 🎯 Próximas Steps

1. Acesse http://localhost:8080/swagger-ui.html
2. Teste os endpoints da API
3. Registre matérias-primas
4. Registre produtos com suas relações
5. Consulte as sugestões de produção

