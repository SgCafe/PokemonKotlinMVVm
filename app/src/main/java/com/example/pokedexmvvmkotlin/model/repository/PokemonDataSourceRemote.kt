package com.example.pokedexmvvmkotlin.model.repository

import com.example.pokedexmvvmkotlin.model.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonDataSourceRemote @Inject constructor() {

    suspend fun getAllPoke() =
        ApiService.api.findAllPoke()

    suspend fun getInfoPoke(name : String) =
        ApiService.api.findInfoPoke(name)

}