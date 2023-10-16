package com.example.pokedexmvvmkotlin.model.remote

import com.example.pokedexmvvmkotlin.model.domain.PokemonResponse
import com.example.pokedexmvvmkotlin.model.domain.Pokemons
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonService {

    @GET("pokemon")
    suspend fun findAllPoke() : Response<PokemonResponse>

    @GET("pokemon/{name}/")
    suspend fun findInfoPoke(@Path("name") id : String ) : Response<Pokemons>

//    @GET("pokemon")
//    fun findAllPoke(@Query("limit") limit : Int) : Call<PokemonsApiResult>
//
//    @GET("pokemon/{id}")
//    fun getPokemon(id : Int) : Call<PokemonApiResult>

}