# nexus-titan
Site oficial da Nexus Titan - Academia
🚨 *MANUAL GIT — EQUIPE NEXUS TITAN* 🚨

Galera, vamos usar esse fluxo para ninguém sobrescrever ou quebrar o código dos outros KKKKK.

📌 *REGRA Nº 1*

❌ NÃO trabalhem diretamente na `main`.

Cada pessoa trabalha na sua própria `branch`.

Exemplo:

`feature/home`
`feature/modalidades`
`feature/planos`
`feature/sobre`
`feature/contato`

---

🟢 *1. PRIMEIRA VEZ NO PROJETO*

Depois de receber o convite do GitHub:

1️⃣ Clone o projeto:

`git clone LINK_DO_REPOSITORIO`

Exemplo:

`git clone https://github.com/USUARIO/nexus-titan.git`

2️⃣ Entre na pasta:

`cd nexus-titan`

3️⃣ Abra no VS Code:

`code .`

---

🟢 *2. ANTES DE COMEÇAR UMA TAREFA*

SEMPRE faça:

`git checkout main`

Depois:

`git pull`

⚠️ Isso atualiza seu projeto antes de você começar.

Só depois disso crie sua branch:

`git checkout -b feature/nome-da-tarefa`

Exemplo:

`git checkout -b feature/home`

---

🟢 *3. AGORA PODE CODAR*

Faça sua parte normalmente.

Exemplo:

Pessoa da Home → mexe na Home.

Pessoa de Planos → mexe nos Planos.

Pessoa de Contato → mexe no Contato.

⚠️ *Evitem mexer nos arquivos que outra pessoa está trabalhando.*

Se precisar alterar algo que pertence à tarefa de outra pessoa, AVISE antes.

---

🟢 *4. TERMINOU UMA PARTE?*

Primeiro confira:

`git status`

Veja se aparecem somente os arquivos que você realmente alterou.

Depois:

`git add .`

Depois faça o commit:

`git commit -m "Descrever o que foi feito"`

Exemplos:

`git commit -m "Criar estrutura da Home"`

`git commit -m "Adicionar cards de planos"`

`git commit -m "Ajustar header"`

❌ NÃO façam:

`git commit -m "mudanças"`

`git commit -m "final"`

`git commit -m "aaaa"`

---

🟢 *5. ENVIAR PARA O GITHUB*

Depois do commit:

`git push -u origin feature/nome-da-tarefa`

Exemplo:

`git push -u origin feature/home`

---

🟢 *6. PULL REQUEST*

Entre no GitHub.

Vai aparecer a opção para criar um *Pull Request*.

Clique em:

➡️ Compare & pull request

Coloque um título explicando o que foi feito.

Exemplo:

*Criar estrutura da Home*

Na descrição, coloque resumidamente:

* Criada estrutura da Home
* Adicionado banner
* Adicionados botões
* Criados cards

Depois:

➡️ Create pull request

---

🟡 *7. NÃO FAÇA MERGE SOZINHO*

Outra pessoa da equipe deve revisar.

Ela verifica:

✅ HTML
✅ CSS
✅ JavaScript
✅ Imagens
✅ Links
✅ Responsividade
✅ Se não quebrou outra parte do projeto

Estando tudo certo:

➡️ Approve
➡️ Merge

---

🔵 *8. DEPOIS QUE ALGUÉM FIZER MERGE*

Todo mundo precisa atualizar o próprio projeto.

Faça:

`git checkout main`

Depois:

`git pull`

Pronto.

Sua `main` está atualizada.

---

🔄 *9. COMEÇAR OUTRA TAREFA*

NUNCA continue trabalhando na branch antiga se a tarefa já terminou.

Faça novamente:

`git checkout main`

`git pull`

`git checkout -b feature/nova-tarefa`

Exemplo:

`git checkout -b feature/footer`

---

🚨 *10. DEU CONFLITO?*

Se aparecer algo como:

`<<<<<<< HEAD`

`=======`

`>>>>>>>`

NÃO saia apagando tudo KKKKK.

⚠️ Pare.

Avise a equipe:

*"Deu conflito no arquivo X."*

Aí vocês verificam qual código deve permanecer.

---

☠️ *11. COMANDOS PROIBIDOS PARA INICIANTES*

🚫 NÃO USE:

`git push --force`

`git push -f`

`git reset --hard`

a menos que alguém que saiba exatamente o que está fazendo peça para usar.

🚫 NÃO apague a `main`.

🚫 NÃO faça `push` direto na `main`.

🚫 NÃO faça merge de qualquer coisa sem revisar.

---

⭐ *FLUXO QUE TODO MUNDO DEVE DECORAR*

ANTES DE CODAR:

`git checkout main`

`git pull`

`git checkout -b feature/minha-tarefa`

👇

💻 CODIFICAR

👇

`git status`

👇

`git add .`

👇

`git commit -m "O que eu fiz"`

👇

`git push -u origin feature/minha-tarefa`

👇

🔵 PULL REQUEST

👇

👀 REVISÃO

👇

✅ APPROVE

👇

🔀 MERGE

👇

TODO MUNDO:

`git checkout main`

`git pull`

---

🔥 *REGRA DE OURO*

Se você não sabe o que um comando do Git vai fazer:

*PARA E PERGUNTA ANTES.*

É melhor perguntar "posso fazer isso?" do que destruir a `main` e depois fingir que foi bug do Windows KKKKKKK.

O objetivo é:

👤 Cada um → sua branch
💻 Cada um → sua tarefa
📦 Commit → salva seu trabalho
☁️ Push → manda para o GitHub
🔵 Pull Request → pede para entrar na main
👀 Revisão → alguém confere
🔀 Merge → entra na main
🔄 Pull → todo mundo atualiza

















Gente, como já baixamos o Live Share, podemos fazer assim:

👤 1. Uma pessoa abre o projeto no VS Code

* Essa pessoa vai ter a pasta do projeto no computador.
* No VS Code, é só ir em File → Open Folder e abrir a pasta.

🔗 2. Iniciar o Live Share

* No VS Code, clicar em Live Share na barra inferior.
* Fazer login com GitHub ou Microsoft, se pedir.
* Depois de iniciar, ele vai gerar um link de convite.

📲 3. Mandar o link no grupo

* As outras 4 pessoas clicam no link.
* O VS Code vai abrir e elas entram na sessão compartilhada.

💻 4. Pronto!
Agora os 5 conseguem acessar o mesmo projeto e trabalhar juntos. Podemos abrir o mesmo index.html, style.css, etc., e as alterações aparecem para todos em tempo real.

⚠️ IMPORTANTE: o Live Share é para trabalhar juntos em tempo real, mas ele não substitui o GitHub.

A ideia é usar:

🟣 GitHub → guardar/versionar o projeto
🔵 Live Share → trabalhar juntos ao mesmo tempo

Então primeiro vamos colocar o projeto no GitHub e cada um pode ter uma cópia no próprio computador. Quando quisermos programar juntos, usamos o Live Share.

Assim fica bem mais organizado e, se alguma coisa der errado, o GitHub mantém o histórico das alterações. 😎