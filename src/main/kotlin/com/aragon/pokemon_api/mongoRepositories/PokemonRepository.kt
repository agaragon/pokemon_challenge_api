package com.aragon.pokemon_api.mongoRepositories

import org.springframework.data.mongodb.repository.MongoRepository
import com.aragon.pokemon_api.models.Pokemon


//Essa interface permite o registro e recuperação dos pokemons no mongo
interface PokemonRepository : MongoRepository<Pokemon, Int>
