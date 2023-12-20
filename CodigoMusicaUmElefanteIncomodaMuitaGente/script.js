let elefante = {
    quantidade : 10,
    titulo: 'Um elefante incomoda muita gente.',
    musica(){
        let musica = "1 elefante incomoda muita gente\n";
        let incomodam = [`incomodam `];
        for (let i = 2; i <= this.quantidade; i++){
            incomodam.push(`incomodam `);

            if ( i % 2 == 1 ){
                musica += `${i} elefantes incomodam muita gente\n`;           
            }else{
                musica += `${i} elefantes ${incomodam} muito mais! \n`;
            }
        }

        musica +=`\n`;

        for (let i = this.quantidade; i >= 2; i--){
            if ( i % 2 == 0 ){
                musica += `${i} elefantes incomodam muita gente\n`;           
            }else{
                musica += `${i} elefantes ${incomodam} muito menos! \n`;
            }
            incomodam.pop();
        }

        musica += "1 elefante incomoda muito menos\n";
        return musica;
    }
};

let titulo = document.getElementById('titulo');
titulo.innerText = elefante.titulo;

let paragrafo = document.getElementById('paragrafo');
paragrafo.innerText = elefante.musica();