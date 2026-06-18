// Apresenta uma mensagem de confirmação
function mostrarMensagem() {
    alert("Operação realizada com sucesso!");
}

// Mostra ou esconde a tabela de registos
function esconderMostrarTabela() {

    // Obtém a tabela através do seu ID
    const tabela = document.getElementById("tabelaRegisto");

    // Verifica se a tabela está escondida
    if (tabela.style.display === "none") {
        tabela.style.display = "table";
    } else {
        tabela.style.display = "none";
    }
}

// Destaca ou remove o destaque dos nomes
function destacarNome() {

    // Seleciona todos os elementos com a classe nome-registo
    const nomes = document.querySelectorAll(".nome-registo");

    // Percorre todos os nomes encontrados
    nomes.forEach(function(nome) {
        nome.classList.toggle("destacado");
    });
}