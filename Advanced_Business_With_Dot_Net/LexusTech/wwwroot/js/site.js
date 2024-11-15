// Please see documentation at https://learn.microsoft.com/aspnet/core/client-side/bundling-and-minification
// for details on configuring this project to bundle and minify static web assets.

// Write your JavaScript code.


document.addEventListener("DOMContentLoaded", function () {
    const textElement = document.querySelector('.progress-text');
    textElement.addEventListener('animationstart', () => {
        textElement.classList.add('animate-text');
    });

    textElement.addEventListener('animationend', () => {
        textElement.classList.remove('animate-text');
    });
});


$("form").submit(function (e) {
    e.preventDefault();

    var selectedValues = [];
    $("select").each(function () {
        var value = $(this).val();
        if (value) {
            selectedValues.push(value);
        }
    });

    // Concatena os valores selecionados em uma string separada por vírgula
    var ambientesSelecionados = selectedValues.join(", ");

    // Atribui a string concatenada ao campo 'Descricao' (é o campo do formulário)
    $("input[name='Descricao']").val(ambientesSelecionados);

    // Agora submete o formulário
    this.submit();
});
