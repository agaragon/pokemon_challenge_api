package com.aragon.pokemon_api.controllers

import org.springframework.web.bind.annotation.*
import com.aragon.pokemon_api.models.Pokemon
import com.aragon.pokemon_api.mongoRepositories.PokemonRepository
import org.json.JSONObject
import java.net.URI

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@RestController
@RequestMapping(path = ["/pokemon"])
class PokemonController(
    val pokemonRepository: PokemonRepository
) {
    @CrossOrigin(origins = ["*"])
    @GetMapping
    fun getPokemons(): MutableList<Pokemon> {
        return pokemonRepository.findAll()
    }
    @CrossOrigin(origins = ["*"])
    @PostMapping
    fun savePokemon(@RequestBody pokemon: Pokemon): Any? {

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
        return pokemon
    }
}