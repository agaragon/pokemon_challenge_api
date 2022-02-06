package com.aragon.pokemon_api.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Pokemon(
    @Id
    val id: Int,
    val nome: String,
)