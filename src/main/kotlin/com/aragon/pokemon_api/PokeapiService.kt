package com.aragon.pokemon_api

import com.aragon.pokemon_api.models.Pokemon
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class PokeapiService() {
    fun loadPokemonInfo(pokemonName:String):Pokemon{
        val pokemon = Pokemon(id=null,nome=pokemonName,imageUrl = null)
        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://pokeapi.co/api/v2/pokemon/$pokemonName"))
            .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString());
        val gameIndexObj = JSONObject(response.body()).getJSONArray("game_indices")[0]
        val pokemonIndex =  JSONObject(gameIndexObj.toString())["game_index"] as Int
        val imageUrl = JSONObject(response.body()).getJSONObject("sprites")["back_default"] as String
        pokemon.id = pokemonIndex
        pokemon.imageUrl = imageUrl
        return pokemon
    }
}