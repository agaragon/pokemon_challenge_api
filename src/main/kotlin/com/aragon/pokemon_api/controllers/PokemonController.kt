package com.aragon.pokemon_api.controllers

import org.springframework.web.bind.annotation.*
import com.aragon.pokemon_api.models.Pokemon
import com.aragon.pokemon_api.mongoRepositories.PokemonRepository

@RestController
@RequestMapping(path = ["/pokemon"])
class PokemonController(
    val pokemonRepository: PokemonRepository
) {
    @GetMapping("/{pokemonId}")
    fun getPokemonById(@PathVariable pokemonId: Int): Pokemon {
        return Pokemon(pokemonId,"Ditto")
    }
    @PostMapping("/")
    fun savePokemon(@RequestBody pokemon: Pokemon):Pokemon{
        pokemonRepository.save(pokemon)
        return pokemon
    }

}