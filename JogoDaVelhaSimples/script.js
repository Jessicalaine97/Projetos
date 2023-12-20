const casas = document.querySelectorAll('.casa');
let atual = 'X';
let contador = 0;

casas.forEach(casa => {

    casa.addEventListener('click', event => {
        if (event.target.innerText == '') {
            event.target.innerText = atual;
            contador++;
            if (atual == 'X') {
                atual = 'O';
            } else {
                atual = 'X';
            }
            const ganhador = verificaVitoria();
            if(ganhador){
                console.log(`O jogador ${ganhador} venceu!`);
            }
            else if(contador == 9){
                alert(`O jogo empatou!`);
            }
        }
    });
});

function verificaVitoria() {
    //verificar as linhas
    if ((casas[0].innerText != '') &&
        casas[0].innerText == casas[1].innerText &&
        casas[1].innerText == casas[2].innerText) {
        return casas[0].innerText;
    }
    if ((casas[3].innerText != '') &&
        casas[3].innerText == casas[4].innerText &&
        casas[4].innerText == casas[5].innerText) {
        return casas[3].innerText;
    }
    if ((casas[6].innerText != '') &&
        casas[6].innerText == casas[7].innerText &&
        casas[7].innerText == casas[8].innerText) {
        return casas[6].innerText;
    }

    //verificar as colunas
    if ((casas[0].innerText != '') &&
        casas[0].innerText == casas[3].innerText &&
        casas[3].innerText == casas[6].innerText) {
        return casas[0].innerText;
    }
    if ((casas[1].innerText != '') &&
        casas[1].innerText == casas[4].innerText &&
        casas[4].innerText == casas[7].innerText) {
        return casas[1].innerText;
    }
    if ((casas[2].innerText != '') &&
        casas[2].innerText == casas[5].innerText &&
        casas[5].innerText == casas[8].innerText) {
        return casas[2].innerText;
    }

    //verificar as diagonais
    if ((casas[0].innerText != '') &&
        casas[0].innerText == casas[4].innerText &&
        casas[4].innerText == casas[8].innerText) {
        return casas[0].innerText;
    }
    if ((casas[2].innerText != '') &&
        casas[2].innerText == casas[4].innerText &&
        casas[4].innerText == casas[6].innerText) {
        return casas[2].innerText;
    }
}