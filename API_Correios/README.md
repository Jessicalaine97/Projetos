O primeiro passo para o desenvolvimento do meu trabalho foi pesquisar sobre os métodos GET e POST de uma API e como utilizá-los de forma assíncrona utilizando JavaScript. Foi um desafio, pois essa foi a primeira vez que consumi uma API dessa maneira. Normalmente, no estágio, eu uso o Postman.

A ideia inicial do meu trabalho era abordar a API de filmes e simular um site como o IMDB, por exemplo. Pesquisando sobre o tema, encontrei a Open Movie Database (https://www.omdbapi.com/), me cadastrei para receber uma chave de uso. No entanto, no desenvolver do projeto, não consegui fazer a API retornar os dados da forma correta. Pesquisando mais sobre o erro, parecia que eu teria que transformar o teste em localhost para https, e isso me daria muito trabalho. Assim sendo, decidi trocar de API.

Assim, pesquisei uma API que não precisava de uma chave e encontrei uma de consulta de CEP (https://viacep.com.br/), que utiliza os dados do IBGE e retorna os valores em diversos formatos. Para esse trabalho, utilizei o JSON.

A ideia foi criar um código e uma interface limpa que demonstrasse para o usuário, sem grandes complicações, as informações do CEP inserido. Para isso, inseri uma simples caixa de texto de input do CEP e um botão de pesquisar. É interessante mencionar que as cores também foram levadas em consideração para a execução desse projeto. O fundo possui uma cor terrosa que remete a mapas, contextualizando com o tema, e o botão apresenta uma coloração azul (oposta ao marrom no círculo cromático), a fim de chamar mais atenção do usuário para a correta forma de uso.

A explicação do código foi feita no arquivo script.js.