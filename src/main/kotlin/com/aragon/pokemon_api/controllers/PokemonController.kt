package com.aragon.pokemon_api.controllers

import org.springframework.web.bind.annotation.*
import com.aragon.pokemon_api.models.Pokemon
import com.aragon.pokemon_api.mongoRepositories.PokemonRepository
import com.aragon.pokemon_api.PokeapiService
import org.json.JSONObject
import java.net.URI
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation


@RestController
@RequestMapping(path = ["/pokemon"])
@Api("Controlador dos endpoints de pokemon. Possui um endpoint para post, onde são registrados os pokemons e um para get, onde os pokemons são recuperados.")
class PokemonController(
    val pokemonRepository: PokemonRepository,
    val pokeapiService: PokeapiService
) {
    //É necessário permitir as origens por que o Front End recebe mensagem de CORS caso contrário
    @CrossOrigin(origins = ["*"])
    @GetMapping
    @ApiOperation("Endpoint para servir os pokemons capturados")
    fun getPokemons(): ResponseEntity<Any> {
        // Tenta buscar a lista de pokemons capturados, se não for possível encontrá-la, é por que o banco de dados
        // está indisponível, nesse caso é enviada uma mensagem de erro para o front junto com código 500
        return try{
            ResponseEntity(pokemonRepository.findAll(),HttpStatus.OK)
        } catch (e:Exception){
            ResponseEntity("Banco de dados indisponível",HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }
    //É necessário permitir as origens por que o Front End recebe mensagem de CORS caso contrário
    @CrossOrigin(origins = ["*"])
    @PostMapping
    @ApiOperation("Endpoint para a captura de pokemons")
    fun savePokemon(@RequestBody pokemon: Pokemon): Any? {
        //Tenta buscar as informações do pokemon na "pokeapi". Se encontrar o pokemon, devolve o id do mesmo, juntamente
        //com o url de sua imagem e o nome dado anteriormente.
        try{
            val pokemonWithImage = this.pokeapiService.loadPokemonInfo(pokemon.nome.lowercase())
            pokemonRepository.save(pokemonWithImage)
            return ResponseEntity(pokemonWithImage,HttpStatus.OK)
        } catch (e:Exception){
            val errorMessage= "Pokemon não encontrado. Certifíque-se de que escreveu o nome do pokemon corretamente e que sua conexão está funcionando corretamente"
            return ResponseEntity(errorMessage,HttpStatus.NOT_FOUND)
        }
    }
}