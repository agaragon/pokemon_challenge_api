# pokemon_api

Essa aplicação tem como objetivo registrar e fornecer os registros dos pokemons capturados pelo usuário.

## Iniciand a aplicação

Para iniciar a aplicação, certifique-se de que está no diretório de nível mais alto do projeto, no mesmo nível do arquivo
docker-compose.yml. Em seguide rode o comando:
`sudo docker-compose up`
Esse comando iniciará a aplicação principal, assim como um banco mongodb e uma rede de conexão entre os dois. A aplicação é
servida na porta 8080.

A aplicação conta também com um arquivo `.env`, o qual aqui não está no `.gitignore`. Nesse arquivo `.env` estão
contidas as credenciais do banco de dados. Se houver necessidade de alterá-las, também é necessário alterar as variáveis
utilizadas na inicializão do banco mongo diretamente no arquivo docker-compose.yml

## Utilizando a aplicação

A aplicação conta com apenas um endpoint, com os métodos Get e Post.
Endpoint: `http://localhost:8080/pokemon`
GET: retorna um json com todos os pokemons já capturados pelo usuário
    Exemplo de payload: [
    {"nome":"Charizard",
    "id":180,
    "imageUrl":"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/6.png"},
    {"nome":"Bulbasaur",
    "id":1,
    "imageUrl":"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png"},
]
POST:
    Recebe o nome de um pokemon e busca em `https://pokeapi.co/` o id do pokemon e a url de sua imagem. Em seguida
    registra o nome do pokemon, seu id e Url no banco Mongo e fornece uma resposta ao usuário.
    Exemplo de payload enviado para o endpoint:    {"nome":"charizard"}
    Resposta:
    {"nome":"Charizard",
    "id":180,
    "imageUrl":"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/6.png"
    }
    Caso haja algum erro durante a busca das informações do pokemon no banco, o usuário é informado de que o pokemon
não pode ser encontrado e é levantado um erro de não encontrado.
    

## Documentação

A documentação da aplicação pode ser encontrada no endpoint: `http://localhost:8080/swagger-ui.html#/`
Lá há uma descrição do endpoint `/pokemon` e uma descrição do model `Pokemon`