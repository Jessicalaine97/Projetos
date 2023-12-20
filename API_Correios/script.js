//cria uma constante e atribui o valor do input inserido pelo usuario
const cep = document.getElementById('numeroCep');
//cria constantes para obter a referência do primeiro elemento com os IDs e atribui o valor às constantes ?Element
const cepElement = document.querySelector("#cep")
const logradouroElement = document.querySelector("#logradouro span")
const bairroElement = document.querySelector("#bairro span")
const localidadeElement = document.querySelector("#localidade span")
const ufElement = document.querySelector("#uf span")
const ibgeElement = document.querySelector("#ibge span")
const dddElement = document.querySelector("#ddd span")
const resultadoContainer = document.querySelector("#Resultado")

async function pesquisaApi(cep){
//editando a url padrão da API para ter o termo de pesquisa desejado pelo usuario
    const urlPesquisa = `https://viacep.com.br/ws/${cep}/json/`
//faz uma requisição para a URL construída usando fetch, aguarda a resposta e a armazenada na constante res 
    const res = await fetch(urlPesquisa)
//converte a resposta obtida para JSON
    const data = await res.json()
//atualiza o conteúdo dos elementos referenciados por ?Element com o valor obtido dos dados
    cepElement.innerText = data.cep;
    logradouroElement.innerText = data.logradouro
    bairroElement.innerText = data.bairro
    localidadeElement.innerText = data.localidade
    ufElement.innerText = data.uf
    ibgeElement.innerText = data.ibge
    dddElement.innerText = data.ddd
}
//cria uma funcao para que toda vez que houver um click no botao, a função pesquisaAPI seja retornada com um novo valor
document.getElementById('botao').addEventListener("click", function(){
    pesquisaApi(cep.value);
});

