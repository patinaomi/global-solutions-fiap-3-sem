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
    var itensSelecionados = selectedValues.join(", ");

    // Atribui a string concatenada ao campo 'Descricao' (é o campo do formulário)
    $("input[name='DescricaoItem']").val(itensSelecionados);

    // Agora submete o formulário
    this.submit();
});


// Mapa

// Dados fictícios de consumo por cômodo
const roomData = {
    "sala": {
        consumo: 120,
        itens: [
            { nome: "Ar-condicionado", consumo: 80 },
            { nome: "TV", consumo: 40 }
        ]
    },
    "cozinha": {
        consumo: 150,
        itens: [
            { nome: "Geladeira", consumo: 100 },
            { nome: "Micro-ondas", consumo: 50 }
        ]
    },
    "quarto1": {
        consumo: 90,
        itens: [
            { nome: "Luz", consumo: 10 },
            { nome: "Ar-condicionado", consumo: 80 }
        ]
    },
    "quarto2": {
        consumo: 80,
        itens: [
            { nome: "Luz", consumo: 10 },
            { nome: "Computador", consumo: 70 }
        ]
    },
    "banheiro": {
        consumo: 50,
        itens: [
            { nome: "Luz", consumo: 20 },
            { nome: "Aquecedor", consumo: 30 }
        ]
    },
    "lavanderia": {
        consumo: 40,
        itens: [
            { nome: "Máquina de lavar", consumo: 40 }
        ]
    }
};

// Adicionando interatividade aos cômodos e itens
document.querySelectorAll('.room').forEach(room => {
    room.addEventListener('click', function () {
        const roomName = this.getAttribute('data-room');
        showPopup(roomName);
    });
});

document.querySelectorAll('.item').forEach(item => {
    item.addEventListener('click', function (event) {
        event.stopPropagation();
        const itemName = this.getAttribute('data-item');
        const roomName = this.parentElement.getAttribute('data-room');
        showPopup(roomName, itemName);
    });
});

// Mostrar popup com detalhes
function showPopup(roomName, itemName = null) {
    const room = roomData[roomName];
    document.getElementById('room-title').textContent = roomName.charAt(0).toUpperCase() + roomName.slice(1);
    document.getElementById('energy-consumption').textContent = room.consumo;

    const itemList = document.getElementById('items-list');
    itemList.innerHTML = '';
    room.itens.forEach(item => {
        const listItem = document.createElement('li');
        listItem.textContent = `${item.nome}: ${item.consumo} kWh`;
        itemList.appendChild(listItem);
    });

    if (itemName) {
        alert(`Item: ${itemName}`);
    }

    document.getElementById('popup').classList.add('active');
}

// Fechar popup
function closePopup() {
    document.getElementById('popup').classList.remove('active');
}
