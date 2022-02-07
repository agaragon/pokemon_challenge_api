package com.aragon.pokemon_api


import com.aragon.pokemon_api.controllers.PokemonController
import com.aragon.pokemon_api.models.Pokemon
import com.aragon.pokemon_api.mongoRepositories.PokemonRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class PokemonControllerTests {

@Test
fun tentaCriarPokemon() {
    val pokeapiService = Mockito.mock(PokeapiService::class.java)
    Mockito.`when`(pokeapiService.loadPokemonInfo("charizard")).thenReturn(Pokemon(1,"charizard","http://imagem.com"))
    val pokeapiRepository = Mockito.mock(PokemonRepository::class.java)
    Mockito.`when`(pokeapiRepository.save(Pokemon(1,"charizard","http://imagem.com"))).thenReturn(Pokemon(1,"charizard","http://imagem.com"))
    val controller = PokemonController(pokeapiRepository,pokeapiService)
    val output = controller.savePokemon(Pokemon(id=null,nome="charizard",imageUrl = null))
    val expected = ResponseEntity(Pokemon(1,"charizard","http://imagem.com"), HttpStatus.OK)
    if (output!=expected){
        throw Exception("Falha ao criar pokemon")
        }
    }

@Test
fun falhaAoCriarPokemon(){
    val pokeapiService = Mockito.mock(PokeapiService::class.java)
    Mockito.`when`(pokeapiService.loadPokemonInfo("charizard")).thenThrow(NullPointerException::class.java)
    val pokeapiRepository = Mockito.mock(PokemonRepository::class.java)
    Mockito.`when`(pokeapiRepository.save(Pokemon(1,"charizard","http://imagem.com"))).thenReturn(Pokemon(1,"charizard","http://imagem.com"))
    val controller = PokemonController(pokeapiRepository,pokeapiService)
    val output = controller.savePokemon(Pokemon(id=null,nome="charizard",imageUrl = null))
    val errorMessage= "Pokemon não encontrado. Certifíque-se de que escreveu o nome do pokemon corretamente e que sua conexão está funcionando corretamente"
    val expected = ResponseEntity(errorMessage,HttpStatus.NOT_FOUND)
    if (output!=expected){
        throw Exception("Falha ao criar pokemon não está sendo tratada corretamente")
    }
}
@Test
fun falhaAoSalvarPokemon(){
        val pokeapiService = Mockito.mock(PokeapiService::class.java)
        Mockito.`when`(pokeapiService.loadPokemonInfo("charizard")).thenReturn(Pokemon(1,"charizard","http://imagem.com"))
        val pokeapiRepository = Mockito.mock(PokemonRepository::class.java)
        Mockito.`when`(pokeapiRepository.save(Pokemon(1,"charizard","http://imagem.com"))).thenThrow(NullPointerException::class.java)
        val controller = PokemonController(pokeapiRepository,pokeapiService)
        val output = controller.savePokemon(Pokemon(id=null,nome="charizard",imageUrl = null))
        val errorMessage= "Pokemon não encontrado. Certifíque-se de que escreveu o nome do pokemon corretamente e que sua conexão está funcionando corretamente"
        val expected = ResponseEntity(errorMessage,HttpStatus.NOT_FOUND)
        if (output!=expected){
            throw Exception("Falha ao salvar pokemon não está sendo tratada corretamente")
        }
    }
@Test
fun falhaAoBuscarPokemon(){
        val pokeapiService = Mockito.mock(PokeapiService::class.java)
        val pokeapiRepository = Mockito.mock(PokemonRepository::class.java)
        Mockito.`when`(pokeapiRepository.findAll()).thenThrow(NullPointerException::class.java)
        val controller = PokemonController(pokeapiRepository,pokeapiService)
        val output = controller.getPokemons()
        val errorMessage= "Banco de dados indisponível"
        val expected = ResponseEntity(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR)
        if (output!=expected){
            throw Exception("Falha ao salvar pokemon não está sendo tratada corretamente")
        }
}
    @Test
    fun buscarPokemon(){
        val pokeapiService = Mockito.mock(PokeapiService::class.java)
        val pokeapiRepository = Mockito.mock(PokemonRepository::class.java)
        val pokemon1 = Pokemon(1,"charmander",imageUrl = "http:localhost:8000")
        val pokemon2 = Pokemon(2,"ivysaur",imageUrl = "http:localhost:8001")
        Mockito.`when`(pokeapiRepository.findAll()).thenReturn(listOf(pokemon1,pokemon2))
        val controller = PokemonController(pokeapiRepository,pokeapiService)
        val output = controller.getPokemons()
        val expected = ResponseEntity(listOf(pokemon1,pokemon2),HttpStatus.OK)
        if (output!=expected){
            throw Exception("Busca de pokemons no banco não está funcionando")
        }
}
}
