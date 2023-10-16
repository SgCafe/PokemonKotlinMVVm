package com.example.pokedexmvvmkotlin.ui.pokemons

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedexmvvmkotlin.R
import com.example.pokedexmvvmkotlin.ui.pokemons.adapter.PokemonsListAdapter
import com.example.pokedexmvvmkotlin.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonsListFragment : Fragment() {

    private val pokemonsViewModel : PokemonsViewModel by viewModels()
    private lateinit var pokeAdapter: PokemonsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.pokemons_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val rvMain = view.findViewById<RecyclerView>(R.id.rvMain)
//        rvMain.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        rvMain.adapter = pokeAdapter

        setupRecyclerView()
        initRecyclerView()




    }

    private fun initRecyclerView(){
        pokemonsViewModel.pokemons.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {pokemonResponse ->
                        pokeAdapter.differ.submitList(pokemonResponse.results)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {message ->
                        Log.e("error", message)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun setupRecyclerView(){

        pokeAdapter = PokemonsListAdapter({pokeName ->
            val action = PokemonsListFragmentDirections.actionPokemonsListFragmentToPokeInfoFragment(pokeName.name)
            findNavController().navigate(action)
        })
        val rvMain : RecyclerView = requireView().findViewById(R.id.rvMain)

        rvMain.apply {
            adapter = pokeAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun goToPokemonInfo(id: Int){
//        val action = PokemonsListFragmentDirections.actionPokemonsListFragmentToPokeInfoFragment(id)
//        findNavController().navigate(action)
    }

    private fun hideProgressBar() {
        val progressBarAction = view?.findViewById<ProgressBar>(R.id.progressBarAction)
        progressBarAction?.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        val progressBarAction = view?.findViewById<ProgressBar>(R.id.progressBarAction)
        progressBarAction?.visibility = View.VISIBLE
    }


}