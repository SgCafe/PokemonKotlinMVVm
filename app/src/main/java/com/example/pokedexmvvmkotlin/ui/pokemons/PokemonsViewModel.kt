package com.example.pokedexmvvmkotlin.ui.pokemons

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexmvvmkotlin.model.domain.PokemonResponse
import com.example.pokedexmvvmkotlin.model.domain.Pokemons
import com.example.pokedexmvvmkotlin.model.repository.PokemonDataSourceRemote
import com.example.pokedexmvvmkotlin.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val pokemonsDataSourceRemote: PokemonDataSourceRemote,
) : ViewModel() {

    val pokemons: MutableLiveData<Resource<PokemonResponse>> = MutableLiveData()
    val pokeInfo: MutableLiveData<Resource<Pokemons>> = MutableLiveData()


    init {
        getAllPoke()
    }

    fun getAllPoke() = viewModelScope.launch {
        pokemons.postValue(Resource.Loading())
        val response = pokemonsDataSourceRemote.getAllPoke()
        pokemons.postValue(handlePokeResponse(response))

    }

    private fun handlePokeResponse(response: Response<PokemonResponse>) : Resource<PokemonResponse>{
        if (response.isSuccessful){
            response.body()?.let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun getInfoPoke(name: String) = viewModelScope.launch {
        pokemons.postValue(Resource.Loading())
        val responseInfo = pokemonsDataSourceRemote.getInfoPoke(name)
        pokeInfo.postValue(handleInfoPoke(responseInfo))
    }

    private fun handleInfoPoke(response : Response<Pokemons>) : Resource<Pokemons>{
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}