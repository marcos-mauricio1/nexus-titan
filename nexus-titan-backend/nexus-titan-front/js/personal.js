let cache = {};

async function carregarBase() {
    try {
        let [a, p, t, d, s] = await Promise.all([request('/alunos'), request('/personais'), request('/treinos'), request('/treino-dias'), request('/solicitacoes-alteracao')]);
        cache = {a, p, t, d};
        ta.innerHTML = option(a, 'Selecione o aluno');
        tp.innerHTML = option(p, 'Selecione o personal');
        dt.innerHTML = option(t, 'Selecione o treino');
        ed.innerHTML = option(d, 'Selecione o dia');
        sol.innerHTML = s.map(x => `<div class="card item"><b>${x.status}</b><p>Aluno: ${x.aluno?.nome || '-'}</p><p>Treino: ${x.treino?.nome || '-'}</p><p>Dia: ${x.treinoDia?.diaSemana || '-'}</p><p>${x.motivo}</p><p>${x.descricao || ''}</p><button onclick="status(${x.id},'EM_ANALISE')">Em análise</button><button onclick="status(${x.id},'RESOLVIDA')">Resolvida</button><button class="danger" onclick="status(${x.id},'RECUSADA')">Recusar</button></div>`).join('') || '<p class="muted">Nenhuma solicitação.</p>'
    } catch (e) {
        alert(e.message)
    }
}

async function criarTreino() {
    try {
        await request('/treinos', {
            method: 'POST',
            body: JSON.stringify({
                nome: tn.value,
                tipoTreino: tt.value,
                objetivo: to.value,
                descricao: tdesc.value,
                aluno: {id: +ta.value},
                personal: {id: +tp.value}
            })
        });
        alert('Treino criado');
        carregarBase()
    } catch (e) {
        alert(e.message)
    }
}

async function criarDia() {
    try {
        await request('/treino-dias', {
            method: 'POST',
            body: JSON.stringify({diaSemana: ds.value, treino: {id: +dt.value}})
        });
        alert('Dia criado');
        carregarBase()
    } catch (e) {
        alert(e.message)
    }
}

async function criarExercicio() {
    try {
        await request('/exercicios', {
            method: 'POST',
            body: JSON.stringify({
                nome: en.value,
                descricao: edesc.value,
                series: +es.value,
                repeticoes: +er.value,
                tempoDescanso: +edr.value,
                carga: ec.value ? +ec.value : null,
                treinoDia: {id: +ed.value}
            })
        });
        alert('Exercício criado');
        carregarBase()
    } catch (e) {
        alert(e.message)
    }
}

async function status(id, status) {
    try {
        await request('/solicitacoes-alteracao/' + id + '/status', {method: 'PUT', body: JSON.stringify({status})});
        carregarBase()
    } catch (e) {
        alert(e.message)
    }
}

carregarBase();