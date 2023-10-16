package com.example.pokedexmvvmkotlin.model.domain

import com.google.gson.annotations.SerializedName

data class Pokemons (
    val name: String,
    val id: Int,
    val sprites: Sprites
)

data class Sprites(
    @SerializedName("front_default")
    val frontImg : String
)