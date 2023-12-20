O presente trabalho tem como objetivo tornar prático os conhecimentos aprendidos em sala de aula na disciplina de Programação Orientada a Objetos, do curso de Análise e Desenvolvimento de Sistemas do curso Vianna Jr. 

Para tal, fez-se um projeto de um sistema de petshop denominado "Petshop Cão e Gato" para ser usado tanto por funcionários, quanto por clientes. 

A ideia dessa divisão de tipos de usuarios se deu em virtude de pesquisas de sistemas similares na internet e na literatura. O cliente teria a acesso aos dados de seu animal como consultas, remedios e exames soicitados, podendo acompanhar a evolução de seu pet nas consultas com o passar do tempo. Já o funcionario teria um maior acesso ao sistema, podendo cadastrar animais, ver dados mais sensiveis (como laudos tecnicos por exemplo) e informações dos clientes e de outros funcionarios.

A primeira parte do trabalho foi planejar o escopo do projeto por meio de uma criação de um diagrama de classes. Baseado em algumas pesquisas, desenvolvemos um diagrama que apresentava as seguintes classes:

- Classes:

        - Usuario: id (int), nome (String), cpf (String), email (String), senha (String), tipoUsuario(ETipoUsuario)

        - ETipoUsuario (enum): FUNCIONARIO, CLIENTE

        - Animal: id (int), nome (String), idade (int), sexo (ESexoAnimal), peso (int), raca (Raca).

        - ESexoAnimal (enum): FEMININO, MASCULINO.

        - Raca: id (int), tipoAnimal (ETipoAnimal), nome (String).

        - ETipoAnimal (enum): GATO, CACHORRO.

        - Consulta: id (int), data (Date), exames (String), animal (Animal).

        - Tratamento: id (int), consulta (Consulta), medicamentos (String), procedimentos (String).

A partir disso, iniciamos efetivamente o desenvolvimento do codigo. O primeiro passo foi criar as classes com seus respectivos construtores e gets e sets. Apos isso, criamos os Enums e as classes DAO. Logo depois, fizemos o banco e criamos testes de inserção (para ver se o banco estava funcionando) desenvolvendo o main do projeto, o qual foi apagado posteriormente para ser reescrito pelo Node. Depois, criamos as telas. No total foram criandas 7 telas, sendo elas: login, usuários, raças, animais, condicoes especiais, consultas e tratamento.

A parte mais trabalhosa do trabalho foi conseguir unir as telas com o banco (que apresentou inumeros problemas pois criamos chaves estrangeiras desnecessarias e mudamos o tipo da variavel, o que gerou conflitos com o codigo). Alem disso, entender os erros em java tambem foi um grande desafio, já que essa é a primeira vez que estamos tendo contato com a materia.

Por fim, para finalizar, esperamos que a apresentação não tenha sido muito penosa para a nossa nota. Ficamos muito nervosos mas estamos orgulhos deste trabalho que vamos apresentar :)

Ob.: Daves, vc pode ver que tem uma branche no nosso trabalho pois eu custei a entender essa denominação de dono e desenvolvedor.

