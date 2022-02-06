package com.aragon.pokemon_api.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Pokemon(
    var id: Int?,
    @Id val nome: String,
    var imageUrl: String?,
)