package com.aragon.pokemon_api.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

// Como o usuário entra apenas com o nome do pokemon, id e imageUrl são facultativos
@Document
data class Pokemon(
    var id: Int?,
    @Id val nome: String,
    var imageUrl: String?,
)