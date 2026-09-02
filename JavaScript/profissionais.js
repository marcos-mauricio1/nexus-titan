const botoes = document.querySelectorAll(".filtro");
const cards = document.querySelectorAll(".card");

botoes.forEach((botao) => {
    botao.addEventListener("click", () => {

        const filtro = botao.dataset.filtro;

        botoes.forEach((b) => {
            b.classList.remove("ativo");
        });

        botao.classList.add("ativo");

        cards.forEach((card) => {

            const periodo = card.dataset.periodo;

            if (filtro === "todos" || periodo === filtro) {
                card.style.display = "flex";
            } else {
                card.style.display = "none";
            }

        });
    });
});