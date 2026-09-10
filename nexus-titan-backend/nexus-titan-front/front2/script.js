/* =====================================================
   CONFIGURAÇÃO
===================================================== */

const API = "http://localhost:8080";


// ID do personal logado.
// Como ainda não existe autenticação no código enviado,
// estamos usando um ID fixo temporariamente.

let PERSONAL_ID = 1;

let alunos = [];
let treinos = [];
let agendamentos = [];
let disponibilidades = [];
let avaliacoes = [];


/* =====================================================
   NAVEGAÇÃO
===================================================== */

function mostrarPagina(id, botao = null) {

    document.querySelectorAll(".page").forEach(page => {
        page.classList.remove("active-page");
    });

    const pagina = document.getElementById(id);

    if (pagina) {
        pagina.classList.add("active-page");
    }


    document.querySelectorAll(".menu-btn").forEach(btn => {
        btn.classList.remove("active");
    });


    if (botao) {
        botao.classList.add("active");
    }


    const titulos = {
        dashboard: "Dashboard",
        agenda: "Agenda",
        disponibilidade: "Disponibilidade",
        alunos: "Alunos",
        treinos: "Treinos",
        avaliacoes: "Avaliações físicas",
        perfil: "Meu perfil"
    };

    document.getElementById("pageTitle").textContent =
        titulos[id] || "Dashboard";


    if (id === "dashboard") {
        carregarDashboard();
    }

    if (id === "agenda") {
        carregarAgenda();
    }

    if (id === "disponibilidade") {
        carregarDisponibilidades();
    }

    if (id === "alunos") {
        carregarAlunos();
    }

    if (id === "treinos") {
        carregarTreinos();
    }

    if (id === "avaliacoes") {
        carregarAvaliacoes();
    }

    if (id === "perfil") {
        carregarPerfil();
    }
}


/* =====================================================
   REQUISIÇÃO HTTP
===================================================== */

async function request(url, options = {}) {

    try {

        const resposta = await fetch(API + url, {
            headers: {
                "Content-Type": "application/json"
            },
            ...options
        });


        if (!resposta.ok) {

            let erro = "Erro na requisição";

            try {

                const dados = await resposta.json();

                erro =
                    dados.erro ||
                    dados.message ||
                    erro;

            } catch (_) {}

            throw new Error(erro);
        }


        if (resposta.status === 204) {
            return null;
        }


        return await resposta.json();

    } catch (erro) {

        console.error(erro);

        mostrarToast(
            erro.message || "Erro de conexão",
            true
        );

        throw erro;
    }
}


/* =====================================================
   TOAST
===================================================== */

function mostrarToast(mensagem, erro = false) {

    const toast = document.getElementById("toast");

    toast.textContent = mensagem;

    toast.style.background =
        erro ? "#ff4d4d" : "var(--accent)";

    toast.style.color = "#000";

    toast.classList.add("show");


    setTimeout(() => {

        toast.classList.remove("show");

    }, 3000);
}


/* =====================================================
   MODAIS
===================================================== */

function abrirModal(id) {

    document.getElementById(id).classList.add("show");
}


function fecharModais() {

    document.querySelectorAll(".modal").forEach(modal => {
        modal.classList.remove("show");
    });
}


function abrirModalAluno() {
    abrirModal("modalAluno");
}


function abrirModalDisponibilidade() {
    abrirModal("modalDisponibilidade");
}


async function abrirModalAgendamento() {

    await carregarAlunos();

    preencherSelectAlunos("agAluno");

    abrirModal("modalAgendamento");
}


async function abrirModalTreino() {

    await carregarAlunos();

    preencherSelectAlunos("treinoAluno");

    abrirModal("modalTreino");
}


async function abrirModalAvaliacao() {

    await carregarAlunos();

    preencherSelectAlunos("avaliacaoAluno");

    abrirModal("modalAvaliacao");
}


/* Fecha clicando fora */

document.querySelectorAll(".modal").forEach(modal => {

    modal.addEventListener("click", function (event) {

        if (event.target === modal) {
            fecharModais();
        }

    });

});


/* =====================================================
   ALUNOS
===================================================== */

async function carregarAlunos() {

    try {

        alunos = await request("/alunos");

        document.getElementById("totalAlunos").textContent =
            alunos.length;

        renderizarAlunos(alunos);

        preencherSelectAlunos("agAluno");
        preencherSelectAlunos("treinoAluno");
        preencherSelectAlunos("avaliacaoAluno");

        renderizarDashboardAlunos();

    } catch (_) {}

}


function renderizarAlunos(lista) {

    const container =
        document.getElementById("alunosLista");


    if (!lista || lista.length === 0) {

        container.innerHTML = `
            <div class="empty">
                Nenhum aluno cadastrado.
            </div>
        `;

        return;
    }


    container.innerHTML = lista.map(aluno => `

        <div class="student-card">

            <div class="student-top">

                <div class="student-avatar">
                    ${primeiraLetra(aluno.nome)}
                </div>

                <div>
                    <strong>${aluno.nome}</strong>
                    <span>${aluno.email}</span>
                </div>

            </div>


            <div class="student-data">

                <div>
                    <small>Idade</small>
                    <strong>${aluno.idade} anos</strong>
                </div>

                <div>
                    <small>Gênero</small>
                    <strong>${aluno.genero}</strong>
                </div>

                <div>
                    <small>Peso</small>
                    <strong>${aluno.peso} kg</strong>
                </div>

                <div>
                    <small>Altura</small>
                    <strong>${aluno.altura} m</strong>
                </div>

            </div>

        </div>

    `).join("");
}


function primeiraLetra(nome) {

    if (!nome) return "A";

    return nome.charAt(0).toUpperCase();
}


function filtrarAlunos() {

    const pesquisa =
        document.getElementById("pesquisaAluno")
            .value
            .toLowerCase();


    const resultado = alunos.filter(aluno =>
        aluno.nome.toLowerCase().includes(pesquisa)
    );


    renderizarAlunos(resultado);
}


function preencherSelectAlunos(id) {

    const select =
        document.getElementById(id);

    if (!select) return;


    select.innerHTML =
        `<option value="">Selecione o aluno</option>`;


    alunos.forEach(aluno => {

        select.innerHTML += `
            <option value="${aluno.id}">
                ${aluno.nome}
            </option>
        `;

    });
}


/* =====================================================
   CRIAR ALUNO
===================================================== */

document.getElementById("alunoForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();


        const aluno = {

            nome:
                document.getElementById("alunoNome").value,

            email:
                document.getElementById("alunoEmail").value,

            idade:
                Number(document.getElementById("alunoIdade").value),

            peso:
                Number(document.getElementById("alunoPeso").value),

            altura:
                Number(document.getElementById("alunoAltura").value),

            genero:
                document.getElementById("alunoGenero").value
        };


        try {

            await request("/alunos", {

                method: "POST",

                body: JSON.stringify(aluno)

            });


            mostrarToast("Aluno cadastrado com sucesso!");

            event.target.reset();

            fecharModais();

            carregarAlunos();

        } catch (_) {}

    });


/* =====================================================
   DISPONIBILIDADE
===================================================== */

async function carregarDisponibilidades() {

    try {

        disponibilidades =
            await request(
                `/api/disponibilidade/personal/${PERSONAL_ID}`
            );


        document.getElementById(
            "totalDisponibilidades"
        ).textContent = disponibilidades.length;


        renderizarDisponibilidades();

    } catch (_) {}

}


function renderizarDisponibilidades() {

    const dias = [
        "SEGUNDA",
        "TERCA",
        "QUARTA",
        "QUINTA",
        "SEXTA",
        "SABADO",
        "DOMINGO"
    ];


    dias.forEach(dia => {

        const container =
            document.getElementById(dia);

        if (!container) return;


        const horarios =
            disponibilidades.filter(
                item => item.diaSemana === dia
            );


        if (horarios.length === 0) {

            container.innerHTML = `
                <div class="empty">
                    Sem horários
                </div>
            `;

            return;
        }


        container.innerHTML =
            horarios.map(horario => `

                <div class="time-item">

                    <span>
                        ${formatarHora(horario.horaInicio)}
                        -
                        ${formatarHora(horario.horaFim)}
                    </span>

                    <button
                        class="remove-time"
                        onclick="removerDisponibilidade(${horario.id})">
                        ×
                    </button>

                </div>

            `).join("");

    });
}


function formatarHora(hora) {

    if (!hora) return "";

    return hora.substring(0, 5);
}


document.getElementById("disponibilidadeForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();


        const dados = {

            personalId: PERSONAL_ID,

            diaSemana:
                document.getElementById("dispDia").value,

            horaInicio:
                document.getElementById("dispInicio").value,

            horaFim:
                document.getElementById("dispFim").value

        };


        try {

            await request(
                "/api/disponibilidade",
                {
                    method: "POST",
                    body: JSON.stringify(dados)
                }
            );


            mostrarToast("Horário adicionado!");

            event.target.reset();

            fecharModais();

            carregarDisponibilidades();

        } catch (_) {}

    });


async function removerDisponibilidade(id) {

    if (!confirm("Remover este horário?")) return;


    try {

        await request(
            `/api/disponibilidade/${id}`,
            {
                method: "DELETE"
            }
        );


        mostrarToast("Horário removido.");

        carregarDisponibilidades();

    } catch (_) {}

}


/* =====================================================
   AGENDAMENTOS
===================================================== */

async function carregarAgenda() {

    try {

        agendamentos =
            await request(
                `/api/agendamento/personal/${PERSONAL_ID}`
            );


        document.getElementById(
            "totalAgendamentos"
        ).textContent = agendamentos.length;


        renderizarAgenda();

        renderizarDashboardAgendamentos();

    } catch (_) {}

}


function encontrarAluno(id) {

    return alunos.find(
        aluno => Number(aluno.id) === Number(id)
    );
}


function renderizarAgenda() {

    const container =
        document.getElementById("agendaLista");


    if (!agendamentos.length) {

        container.innerHTML = `
            <div class="empty">
                Nenhum agendamento encontrado.
            </div>
        `;

        return;
    }


    container.innerHTML =
        agendamentos.map(ag => {

            const aluno =
                encontrarAluno(ag.alunoId);


            const data =
                new Date(ag.dataHoraInicio);


            const dia =
                data.getDate()
                    .toString()
                    .padStart(2, "0");


            const mes =
                data.toLocaleDateString(
                    "pt-BR",
                    { month: "short" }
                );


            const horaInicio =
                data.toLocaleTimeString(
                    "pt-BR",
                    {
                        hour: "2-digit",
                        minute: "2-digit"
                    }
                );


            const fim =
                new Date(ag.dataHoraFim);


            const horaFim =
                fim.toLocaleTimeString(
                    "pt-BR",
                    {
                        hour: "2-digit",
                        minute: "2-digit"
                    }
                );


            const cancelado =
                ag.status === "CANCELADO";


            return `

                <div class="appointment">

                    <div class="appointment-left">

                        <div class="appointment-date">

                            <strong>${dia}</strong>

                            <span>${mes}</span>

                        </div>


                        <div class="appointment-info">

                            <strong>
                                ${aluno ? aluno.nome : "Aluno #" + ag.alunoId}
                            </strong>

                            <span>
                                ${horaInicio} - ${horaFim}
                            </span>

                        </div>

                    </div>


                    <div style="display:flex;align-items:center;gap:10px">

                        <span class="badge ${cancelado ? "cancelled" : "active"}">

                            ${ag.status || "AGENDADO"}

                        </span>


                        ${
                            !cancelado
                            ?
                            `
                            <button
                                class="danger-btn"
                                onclick="cancelarAgendamento(${ag.id})">

                                Cancelar

                            </button>
                            `
                            :
                            ""
                        }

                    </div>

                </div>

            `;

        }).join("");
}


async function cancelarAgendamento(id) {

    if (!confirm("Cancelar este agendamento?")) {
        return;
    }


    try {

        await request(
            `/api/agendamento/${id}/cancelar`,
            {
                method: "PATCH"
            }
        );


        mostrarToast("Agendamento cancelado.");

        carregarAgenda();

    } catch (_) {}

}


/* =====================================================
   CRIAR AGENDAMENTO
===================================================== */

document.getElementById("agendamentoForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();


        const dados = {

            alunoId:
                Number(
                    document.getElementById("agAluno").value
                ),

            personalId:
                PERSONAL_ID,

            dataHoraInicio:
                document.getElementById("agInicio").value,

            dataHoraFim:
                document.getElementById("agFim").value
        };


        try {

            await request(
                "/api/agendamento",
                {
                    method: "POST",
                    body: JSON.stringify(dados)
                }
            );


            mostrarToast("Agendamento criado!");

            event.target.reset();

            fecharModais();

            carregarAgenda();

        } catch (_) {}

    });


/* =====================================================
   TREINOS
===================================================== */

async function carregarTreinos() {

    try {

        treinos =
            await request("/treinos");


        document.getElementById(
            "totalTreinos"
        ).textContent = treinos.length;


        renderizarTreinos();

    } catch (_) {}

}


function renderizarTreinos() {

    const container =
        document.getElementById("treinosLista");


    const meusTreinos =
        treinos.filter(
            treino =>
                treino.personal &&
                Number(treino.personal.id) === Number(PERSONAL_ID)
        );


    if (!meusTreinos.length) {

        container.innerHTML = `
            <div class="empty">
                Nenhum treino criado.
            </div>
        `;

        return;
    }


    container.innerHTML =
        meusTreinos.map(treino => {

            const aluno =
                treino.aluno;


            return `

                <div class="training-card">

                    <span class="student-name">
                        ${aluno ? aluno.nome : "Aluno"}
                    </span>

                    <h3>
                        ${treino.nome}
                    </h3>


                    <div class="training-info">

                        <span>
                            ${treino.tipoTreino}
                        </span>

                        <span>
                            ${treino.objetivo}
                        </span>

                        <span>
                            ${treino.ativo ? "ATIVO" : "INATIVO"}
                        </span>

                    </div>


                    <p class="training-description">

                        ${treino.descricao || "Sem descrição."}

                    </p>

                </div>

            `;

        }).join("");
}


/* =====================================================
   CRIAR TREINO
===================================================== */

document.getElementById("treinoForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();


        const alunoId =
            Number(
                document.getElementById("treinoAluno").value
            );


        const aluno =
            alunos.find(
                a => Number(a.id) === alunoId
            );


        if (!aluno) {

            mostrarToast(
                "Selecione um aluno.",
                true
            );

            return;
        }


        const dados = {

            nome:
                document.getElementById("treinoNome").value,

            tipoTreino:
                document.getElementById("treinoTipo").value,

            objetivo:
                document.getElementById("treinoObjetivo").value,

            descricao:
                document.getElementById("treinoDescricao").value,

            dataCriacao:
                new Date().toISOString().split("T")[0],

            ativo: true,

            aluno: {
                id: alunoId
            },

            personal: {
                id: PERSONAL_ID
            }

        };


        try {

            await request(
                "/treinos",
                {
                    method: "POST",
                    body: JSON.stringify(dados)
                }
            );


            mostrarToast("Treino criado com sucesso!");

            event.target.reset();

            fecharModais();

            carregarTreinos();

        } catch (_) {}

    });


/* =====================================================
   AVALIAÇÕES
===================================================== */

async function carregarAvaliacoes() {

    avaliacoes = [];


    if (!alunos.length) {
        await carregarAlunos();
    }


    for (const aluno of alunos) {

        try {

            const historico =
                await request(
                    `/api/avaliacoes/aluno/${aluno.id}`
                );


            if (Array.isArray(historico)) {

                historico.forEach(avaliacao => {

                    avaliacoes.push({
                        ...avaliacao,
                        alunoNome: aluno.nome
                    });

                });

            }

        } catch (_) {}

    }


    renderizarAvaliacoes();
}


function renderizarAvaliacoes() {

    const container =
        document.getElementById("avaliacoesLista");


    if (!avaliacoes.length) {

        container.innerHTML = `
            <div class="empty">
                Nenhuma avaliação cadastrada.
            </div>
        `;

        return;
    }


    container.innerHTML =
        avaliacoes.map(av => `

            <div class="evaluation-card">

                <h3>
                    ${av.alunoNome}
                </h3>

                <span class="date">
                    ${av.dataAvaliacao || "-"}
                </span>


                <div class="imc">

                    <strong>
                        ${av.imc ? av.imc.toFixed(2) : "-"}
                    </strong>

                    <span>
                        IMC · ${av.classificacaoImc || "-"}
                    </span>

                </div>


                <div class="student-data">

                    <div>
                        <small>Peso</small>
                        <strong>${av.peso} kg</strong>
                    </div>

                    <div>
                        <small>Altura</small>
                        <strong>${av.altura} m</strong>
                    </div>

                </div>


                ${
                    av.observacoes
                    ?
                    `
                    <p class="training-description"
                       style="margin-top:15px">

                        ${av.observacoes}

                    </p>
                    `
                    :
                    ""
                }

            </div>

        `).join("");
}


/* =====================================================
   CRIAR AVALIAÇÃO
===================================================== */

document.getElementById("avaliacaoForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();


        const dados = {

            alunoId:
                Number(
                    document.getElementById("avaliacaoAluno").value
                ),

            peso:
                Number(
                    document.getElementById("avaliacaoPeso").value
                ),

            altura:
                Number(
                    document.getElementById("avaliacaoAltura").value
                ),

            dataAvaliacao:
                document.getElementById("avaliacaoData").value
                || null,

            observacoes:
                document.getElementById("avaliacaoObs").value

        };


        try {

            await request(
                "/api/avaliacoes",
                {
                    method: "POST",
                    body: JSON.stringify(dados)
                }
            );


            mostrarToast("Avaliação registrada!");

            event.target.reset();

            fecharModais();

            carregarAvaliacoes();

        } catch (_) {}

    });


/* =====================================================
   PERFIL
===================================================== */

async function carregarPerfil() {

    try {

        const personal =
            await request(
                `/personais/${PERSONAL_ID}`
            );


        document.getElementById(
            "perfilNome"
        ).textContent = personal.nome;


        document.getElementById(
            "perfilTipo"
        ).textContent = personal.tipoPersonal;


        document.getElementById(
            "sidebarNome"
        ).textContent = personal.nome;


        document.getElementById(
            "welcomeName"
        ).textContent =
            personal.nome.split(" ")[0];


        document.getElementById(
            "perfilNomeInput"
        ).value = personal.nome;


        document.getElementById(
            "perfilIdadeInput"
        ).value = personal.idade;


        document.getElementById(
            "perfilCpfInput"
        ).value = personal.cpf;


        document.getElementById(
            "perfilGeneroInput"
        ).value = personal.genero;


        document.getElementById(
            "perfilTipoInput"
        ).value = personal.tipoPersonal;

    } catch (_) {}

}


/* =====================================================
   ATUALIZAR PERFIL
===================================================== */

document.getElementById("perfilForm")
    .addEventListener("submit", async function (event) {

        event.preventDefault();


        const dados = {

            nome:
                document.getElementById(
                    "perfilNomeInput"
                ).value,

            idade:
                Number(
                    document.getElementById(
                        "perfilIdadeInput"
                    ).value
                ),

            cpf:
                document.getElementById(
                    "perfilCpfInput"
                ).value,

            genero:
                document.getElementById(
                    "perfilGeneroInput"
                ).value,

            tipoPersonal:
                document.getElementById(
                    "perfilTipoInput"
                ).value

        };


        try {

            await request(
                `/personais/${PERSONAL_ID}`,
                {
                    method: "PUT",
                    body: JSON.stringify(dados)
                }
            );


            mostrarToast(
                "Perfil atualizado!"
            );

            carregarPerfil();

        } catch (_) {}

    });


/* =====================================================
   DASHBOARD
===================================================== */

async function carregarDashboard() {

    await Promise.all([
        carregarAlunos(),
        carregarAgenda(),
        carregarDisponibilidades(),
        carregarTreinos()
    ]);

}


function renderizarDashboardAlunos() {

    const container =
        document.getElementById(
            "dashboardAlunos"
        );


    if (!alunos.length) {

        container.innerHTML = `
            <div class="empty">
                Nenhum aluno.
            </div>
        `;

        return;
    }


    container.innerHTML =
        alunos.slice(0, 5).map(aluno => `

            <div class="appointment">

                <div class="appointment-left">

                    <div class="student-avatar">
                        ${primeiraLetra(aluno.nome)}
                    </div>

                    <div class="appointment-info">

                        <strong>
                            ${aluno.nome}
                        </strong>

                        <span>
                            ${aluno.email}
                        </span>

                    </div>

                </div>

            </div>

        `).join("");
}


function renderizarDashboardAgendamentos() {

    const container =
        document.getElementById(
            "dashboardAgendamentos"
        );


    if (!agendamentos.length) {

        container.innerHTML = `
            <div class="empty">
                Nenhum agendamento.
            </div>
        `;

        return;
    }


    container.innerHTML =
        agendamentos.slice(0, 5).map(ag => {

            const aluno =
                encontrarAluno(ag.alunoId);


            const data =
                new Date(ag.dataHoraInicio);


            return `

                <div class="appointment">

                    <div class="appointment-info">

                        <strong>
                            ${aluno
                                ? aluno.nome
                                : "Aluno #" + ag.alunoId}
                        </strong>

                        <span>

                            ${data.toLocaleDateString("pt-BR")}

                            às

                            ${data.toLocaleTimeString(
                                "pt-BR",
                                {
                                    hour: "2-digit",
                                    minute: "2-digit"
                                }
                            )}

                        </span>

                    </div>


                    <span class="badge active">
                        ${ag.status || "AGENDADO"}
                    </span>

                </div>

            `;

        }).join("");
}


/* =====================================================
   INICIALIZAÇÃO
===================================================== */

document.addEventListener("DOMContentLoaded", async () => {

    await carregarPerfil();

    await carregarDashboard();

});