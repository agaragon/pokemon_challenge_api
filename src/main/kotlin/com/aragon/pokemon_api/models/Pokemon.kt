package com.aragon.pokemon_api.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

// Como o usuário entra apenas com o nome do pokemon, id e imageUrl são facultativos para a entidade, porém devem ser registrados no banco de dados.
@Document
@ApiModel("Pokemon que foi capturado pelo usuário")
data class Pokemon(
    @ApiModelProperty(notes = "Id do pokemon buscado da pokemon api", example = "1", required = false, position = 0)
    var id: Int?,
    @ApiModelProperty(notes = "Nome do pokemon entrado pelo usuário", example = "Charizard", required = true, position = 0)
    @Id val nome: String,
    @ApiModelProperty(notes = "Url da imagem do pokemon buscado da pokemon api", example = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/6.png", required = false, position = 0)
    var imageUrl: String?,
)