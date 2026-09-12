const botoesCategoria = document.querySelectorAll('.categoria-profissional');
const cardsProfissionais = document.querySelectorAll('.card-profissional');

function mostrarCategoria(categoria) {
    cardsProfissionais.forEach(card => {
        if (card.dataset.modalidade === categoria) {
            card.style.display = 'block';
        } else {
            card.style.display = 'none';
        }
    });
}

botoesCategoria.forEach(botao => {
    botao.addEventListener('click', () => {
        const categoriaSelecionada = botao.dataset.categoria;

        botoesCategoria.forEach(item => {
            item.classList.remove('ativo');
        });

        botao.classList.add('ativo');

        mostrarCategoria(categoriaSelecionada);
    });
});

mostrarCategoria('musculacao');