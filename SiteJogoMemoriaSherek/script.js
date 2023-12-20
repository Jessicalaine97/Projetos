function embaralharArray(array) {
    return array.sort(() => Math.random() - 0.5);
}

function preencherTabela(arrayImg) {
    const table = document.querySelector("#game-images");
    let indiceImg = 0;

    for (let i = 1; i <= 3; i++) {

        for (let j = 1; j <= 5; j++) {
            const image = document.createElement("img");

            if (indiceImg < 15) { // 15 imagens em tela
                image.src = arrayImg[indiceImg];
                image.classList.add("game-image");
                image.setAttribute("data-src", arrayImg[indiceImg]);
                indiceImg++;
            }

            table.appendChild(image);
        }
    }
}

function identificarImagensNaoExibidas(arraySorteado, arrayOriginal) {
    const imagensAnteriores = arrayOriginal.slice(15, 18);
    const imagensAtuais = arraySorteado.slice(15, 18);

    const imagensNovas = imagensAnteriores.filter(
        (imagem) => !imagensAtuais.includes(imagem)
    );

    return imagensNovas;
}

function verificarVitoria(imagensNovas) {
    const imagensNaTabela = document.querySelectorAll(".game-image");
    const imagensRestantes = Array.from(imagensNaTabela).map((imagem) => imagem.getAttribute("src"));

    const todasCompativeis = imagensRestantes.every((imagem) => imagensNovas.includes(imagem));
    if (todasCompativeis) {
        inserirMensagemVitoria(); 
    }
}

function inserirMensagemVitoria() {
    const mensagemVitoria = document.createElement("h1");
    mensagemVitoria.textContent = "Você venceu!";
    
    const tabela = document.getElementById("game-images");
    tabela.parentNode.appendChild(mensagemVitoria); // Adiciona o <h1> após a tabela
}

function eventoClickImages(imagensNovas) {
    const table = document.getElementById("game-images");

    table.addEventListener("click", function (event) {
        const clickedImage = event.target;
        const srcImagemClicada = clickedImage.getAttribute("data-src");

        if (imagensNovas.includes(srcImagemClicada)) {
            alert('Você perdeu!');
            location.reload();
        } else {
            clickedImage.remove();
            verificarVitoria(imagensNovas);
        }
    });
}

const arrayImg = [
    "img/1.png", "img/2.png", "img/3.png", "img/4.png", "img/5.png",
    "img/6.png", "img/7.png", "img/8.png", "img/9.png", "img/10.png",
    "img/11.png", "img/12.png", "img/13.png", "img/14.png", "img/15.png",
    "img/16.png", "img/17.png", "img/18.png", "img/19.png",
];

const arrayOriginal = embaralharArray(arrayImg);
preencherTabela(arrayOriginal);

document.addEventListener('DOMContentLoaded', function () {
    let segundos = 60;
    const cronometro = document.getElementById('cronometro');
    let intervalId;

    function atualizarCronometro() {
        const minutos = Math.floor(segundos / 60);
        const segundosRestantes = (segundos % 60).toString().padStart(2, '0');

        cronometro.textContent = `${minutos}:${segundosRestantes}`;
        segundos--;

        if (segundos < 0) {
            clearInterval(intervalId);
            alert('Cronômetro chegou a zero!');
            const arrayCopy = [...arrayOriginal];
            const imagensIniciais = document.querySelectorAll(".game-image");

            imagensIniciais.forEach((imagem) => { imagem.remove(); });

            let arraySorteado = embaralharArray(arrayCopy);
            preencherTabela(arraySorteado);
            const imagensNovas = identificarImagensNaoExibidas(arraySorteado, arrayOriginal);
            eventoClickImages(imagensNovas);
        }
    }

    atualizarCronometro();
    intervalId = setInterval(atualizarCronometro, 1000);
});

