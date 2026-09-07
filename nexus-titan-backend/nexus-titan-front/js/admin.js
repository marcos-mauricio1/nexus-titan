async function aluno() {
    try {
        await request('/alunos', {
            method: 'POST',
            body: JSON.stringify({
                nome: an.value,
                email: ae.value,
                idade: +ai.value,
                peso: +ap.value,
                altura: +aa.value,
                genero: ag.value
            })
        });
        alert('Aluno cadastrado');
        listar()
    } catch (e) {
        alert(e.message)
    }
}

async function personal() {
    try {
        await request('/personais', {
            method: 'POST',
            body: JSON.stringify({
                nome: pn.value,
                idade: +pi.value,
                cpf: pc.value,
                genero: pg.value,
                tipoPersonal: pt.value
            })
        });
        alert('Personal cadastrado');
        listar()
    } catch (e) {
        alert(e.message)
    }
}

async function listar() {
    try {
        let [a, p] = await Promise.all([request('/alunos'), request('/personais')]);
        alunos.innerHTML = a.map(x => `<div class="card item"><b>${x.nome}</b><br>${x.email}</div>`).join('') || '<p class="muted">Nenhum aluno.</p>';
        personais.innerHTML = p.map(x => `<div class="card item"><b>${x.nome}</b><br>${x.tipoPersonal}</div>`).join('') || '<p class="muted">Nenhum personal.</p>'
    } catch (e) {
        alert(e.message)
    }
}

listar();