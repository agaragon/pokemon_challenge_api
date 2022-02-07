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
fun falhaAoCriarPokemon(){
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

}
