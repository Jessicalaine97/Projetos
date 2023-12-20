var mariana = {
    quantidade: 10,
    conta(){
        var letra = '';
        var contagem = [];
        var i = 1;
        while ( i <= this.quantidade){
            contagem.push(` é ${i} `);
            letra += 
            `Mariana conta ${i} \n`+
            `Mariana conta ${i}: ${contagem}, é!\n`;
            letra += `Ana, viva a Mariana, viva a Mariana \n\n`;
            i++;
        };
        console.log(letra);
    }
};
mariana.conta();