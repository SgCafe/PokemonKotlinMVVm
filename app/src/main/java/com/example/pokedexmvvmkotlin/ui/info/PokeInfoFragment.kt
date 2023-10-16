package com.example.pokedexmvvmkotlin.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pokedexmvvmkotlin.databinding.PokeInfoFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokeInfoFragment : Fragment() {

    private lateinit var binding : PokeInfoFragmentBinding
//    private val viewModel : PokemonInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  PokeInfoFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs : PokeInfoFragmentArgs by navArgs()
        val pokeId = safeArgs.pokeName

//        viewModel.loadPoke(pokeId)
//        binding.viewmodel = viewModel


    }


}