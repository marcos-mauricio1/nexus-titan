# Nexus Titan

Projeto escolar de gerenciamento de treinos.

## Como rodar
1. Crie o banco MySQL `nexus_titan`.
2. Edite `nexus-titan-api/src/main/resources/application.properties` e coloque sua senha do MySQL.
3. Abra a API e execute `NexusTitanApiApplication`.
4. Confirme `http://localhost:8080/alunos` no navegador.
5. Abra o frontend por um servidor local (IntelliJ/Live Server). A página inicial é `index.html`.

## Ordem recomendada para testar
1. Admin: cadastre aluno e personal.
2. Personal: crie treino, dias e exercícios.
3. Aluno: selecione o aluno e visualize o treino.
4. Aluno: envie uma solicitação.
5. Personal: atualize o status da solicitação.

## Tipos de treino
EMAGRECIMENTO, GANHO_MASSA, CONDICIONAMENTO e PERSONALIZADO.
