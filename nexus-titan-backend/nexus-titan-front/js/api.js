const API = "http://localhost:8080";

async function request(path, opt = {}) {

    const resposta = await fetch(API + path, {
        headers: {
            "Content-Type": "application/json"
        },
        ...opt
    });

    if (!resposta.ok) {

        let mensagem = "Erro " + resposta.status;

        try {

            const dadosErro = await resposta.json();

            mensagem = dadosErro.erro || mensagem;

        } catch (erro) {

        }

        throw new Error(mensagem);
    }

    if (resposta.status === 204) {
        return null;
    }

    return resposta.json();
}


function option(lista, label = "Selecione") {

    return `
        <option value="">
            ${label}
        </option>
    ` + lista.map(item => `

        <option value="${item.id}">
            ${item.nome || item.diaSemana}
        </option>

    `).join("");
}