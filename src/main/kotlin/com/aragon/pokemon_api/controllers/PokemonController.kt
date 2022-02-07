package com.aragon.pokemon_api.controllers

import org.springframework.web.bind.annotation.*
import com.aragon.pokemon_api.models.Pokemon
import com.aragon.pokemon_api.mongoRepositories.PokemonRepository
import org.json.JSONObject
import java.net.URI
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@RestController
@RequestMapping(path = ["/pokemon"])
class PokemonController(
    val pokemonRepository: PokemonRepository
) {
    //É necessário permitir as origens por que o Front End recebe mensagem de CORS caso contrário
    @CrossOrigin(origins = ["*"])
    @GetMapping
    fun getPokemons(): ResponseEntity<Any> {
        // Tenta buscar a lista de pokemons capturados, se não for possível encontrá-la, é por que o banco de dados
        // está indisponível, nesse caso é enviada uma mensagem de erro para o front junto com código 500
        return try{
            ResponseEntity(pokemonRepository.findAll(),HttpStatus.OK)
        } catch (e:Exception){
            ResponseEntity("Banco de dados indisponível erro = " + e.message,HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }
    //É necessário permitir as origens por que o Front End recebe mensagem de CORS caso contrário
    @CrossOrigin(origins = ["*"])
    @PostMapping
    fun savePokemon(@RequestBody pokemon: Pokemon): Any? {
        //Tenta buscar as informações do pokemon na "pokeapi". Se encontrar o pokemon, devolve o id do mesmo, juntamente
        //com o url de sua imagem e o nome dado anteriormente.
        try{
            val client = HttpClient.newBuilder().build()
            val request = HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + pokemon.nome.lowercase()))
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString());
            val gameIndexObj = JSONObject(response.body()).getJSONArray("game_indices")[0]
            val pokemonIndex =  JSONObject(gameIndexObj.toString())["game_index"] as Int
            val imageUrl = JSONObject(response.body()).getJSONObject("sprites")["back_default"] as String
            pokemon.id = pokemonIndex
            pokemon.imageUrl = imageUrl
            pokemonRepository.save(pokemon)
            return ResponseEntity(pokemon,HttpStatus.OK)
        } catch (e:Exception){
            val errorMessage= "Pokemon não encontrado. Certifíque-se de que escreveu o nome do pokemon corretamente e que sua conexão está funcionando corretamente "
            return ResponseEntity(errorMessage,HttpStatus.NOT_FOUND)
        }
    }
}