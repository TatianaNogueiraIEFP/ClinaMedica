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

// Aguarda que todo o HTML esteja carregado antes de executar o script
document.addEventListener("DOMContentLoaded", function () {

    // Vai buscar o elemento <select> do perfil (Paciente, Médico, Secretária)
    const perfil = document.querySelector('select[name="perfil"]');

    // Vai buscar o campo (div) das especialidades
    const campoEspecialidades = document.getElementById("campoEspecialidades");

    // Adiciona um evento que reage sempre que o utilizador muda o perfil
    perfil.addEventListener("change", function () {

        // Se o perfil selecionado for MÉDICO
        if (perfil.value === "MEDICO") {

            // Mostra o campo das especialidades
            campoEspecialidades.style.display = "block";

        } else {

            // Caso contrário, esconde o campo das especialidades
            campoEspecialidades.style.display = "none";
        }
    });
});