#!/bin/bash

echo "Aguardando PostgreSQL estar pronto..."

until nc -z postgres 5432; do
  echo "PostgreSQL não está pronto... aguardando..."
  sleep 2
done

echo "PostgreSQL está pronto! Iniciando aplicação..."

exec java -jar /app/app.jar

