package com.aragon.pokemon_api.mongoRepositories

import org.springframework.data.mongodb.repository.MongoRepository
import com.aragon.pokemon_api.models.Pokemon

interface PokemonRepository : MongoRepository<Pokemon, Int>
