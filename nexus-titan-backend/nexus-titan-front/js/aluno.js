let dados = {};

async function iniciar() {
    try {
        dados.alunos = await request("/alunos");

        const alunoSel = document.getElementById("alunoSel");

        alunoSel.innerHTML = option(
            dados.alunos,
            "Selecione seu nome"
        );
    } catch (e) {
        alert(e.message);
    }
}

async function carregarAluno() {
    try {
        const alunoSel = document.getElementById("alunoSel");
        const st = document.getElementById("st");
        const conteudo = document.getElementById("conteudo");

        const id = Number(alunoSel.value);

        if (!id) {
            alert("Selecione um aluno.");
            return;
        }

        dados.treinos = await request("/treinos/aluno/" + id);

        st.innerHTML = option(
            dados.treinos,
            "Selecione o treino"
        );

        conteudo.innerHTML =
            dados.treinos.map(t => `
                <div class="card">
                    <h2>${t.nome}</h2>

                    <span class="badge">
                        ${t.tipoTreino || "Personalizado"}
                    </span>

                    <p>${t.objetivo || ""}</p>

                    <p>
                        Personal:
                        ${t.personal ? t.personal.nome : "-"}
                    </p>

                    <button onclick="verTreino(${t.id})">
                        Ver dias e exercícios
                    </button>
                </div>
            `).join("")
            ||
            '<p class="muted">Nenhum treino para este aluno.</p>';

    } catch (e) {
        alert(e.message);
    }
}

async function carregarDias() {
    try {
        const st = document.getElementById("st");
        const sd = document.getElementById("sd");

        const treinoId = Number(st.value);

        if (!treinoId) {
            sd.innerHTML = option(
                [],
                "Selecione o treino primeiro"
            );
            return;
        }

        const dias = await request(
            "/treino-dias/treino/" + treinoId
        );

        sd.innerHTML = option(
            dias,
            "Selecione o dia"
        );

    } catch (e) {
        alert(e.message);
    }
}

async function verTreino(id) {
    try {
        const conteudo =
            document.getElementById("conteudo");

        const dias =
            await request(
                "/treino-dias/treino/" + id
            );

        let html = "";

        for (const d of dias) {

            const exercicios =
                await request(
                    "/exercicios/dia/" + d.id
                );

            html += `
                <div class="panel">

                    <h2>
                        ${d.diaSemana}
                    </h2>
            `;

            if (exercicios.length === 0) {

                html += `
                    <p class="muted">
                        Nenhum exercício cadastrado.
                    </p>
                `;

            } else {

                html += exercicios.map(x => `
                    <div class="item">

                        <b>
                            ${x.nome}
                        </b>

                        <p>
                            ${x.descricao || ""}
                        </p>

                        <p>
                            ${x.series} séries
                            •
                            ${x.repeticoes} repetições
                            •
                            ${x.tempoDescanso}s descanso

                            ${
                    x.carga != null
                        ? " • " + x.carga + " kg"
                        : ""
                }
                        </p>

                    </div>
                `).join("");
            }

            html += `
                </div>
            `;
        }

        conteudo.innerHTML = html;

    } catch (e) {
        alert(e.message);
    }
}

async function solicitar() {
    try {

        const alunoSel =
            document.getElementById("alunoSel");

        const st =
            document.getElementById("st");

        const sd =
            document.getElementById("sd");

        const sm =
            document.getElementById("sm");

        const outro =
            document.getElementById("outro");

        const alunoId =
            Number(alunoSel.value);

        const treinoId =
            Number(st.value);

        const treinoDiaId =
            Number(sd.value);

        const motivo =
            sm.value;

        const descricao =
            motivo === "OUTRO"
                ? outro.value.trim()
                : "";

        if (!alunoId) {
            alert("Selecione um aluno.");
            return;
        }

        if (!treinoId) {
            alert("Selecione um treino.");
            return;
        }

        if (!treinoDiaId) {
            alert("Selecione um dia.");
            return;
        }

        if (
            motivo === "OUTRO" &&
            !descricao
        ) {
            alert("Descreva o motivo da solicitação.");
            return;
        }

        const treino =
            dados.treinos.find(
                t => t.id === treinoId
            );

        if (!treino) {
            alert("Treino não encontrado.");
            return;
        }

        if (!treino.personal) {
            alert(
                "Este treino não possui um personal responsável."
            );
            return;
        }

        await request(
            "/solicitacoes-alteracao",
            {
                method: "POST",

                body: JSON.stringify({
                    motivo: motivo,

                    descricao: descricao,

                    aluno: {
                        id: alunoId
                    },

                    treino: {
                        id: treinoId
                    },

                    treinoDia: {
                        id: treinoDiaId
                    },

                    personal: {
                        id: treino.personal.id
                    }
                })
            }
        );

        alert(
            "Solicitação enviada com sucesso!"
        );

        outro.value = "";

    } catch (e) {
        alert(e.message);
    }
}

document.addEventListener(
    "DOMContentLoaded",
    function () {

        const st =
            document.getElementById("st");

        if (st) {
            st.addEventListener(
                "change",
                carregarDias
            );
        }

        iniciar();
    }
);