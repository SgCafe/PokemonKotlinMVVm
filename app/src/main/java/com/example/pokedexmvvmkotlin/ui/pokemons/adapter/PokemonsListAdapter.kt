package com.example.pokedexmvvmkotlin.ui.pokemons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedexmvvmkotlin.databinding.CardPokemonsBinding
import com.example.pokedexmvvmkotlin.model.domain.PokemonResult
import com.example.pokedexmvvmkotlin.model.domain.Pokemons

class PokemonsListAdapter( private val onCLickListener: (Pokemons) -> Unit ) : RecyclerView.Adapter<PokemonsListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CardPokemonsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Pokemons) {
            binding.idPokemon.text = item.id.toString()
            binding.namePokemon.text = item.name
            Glide.with(binding.imgPoke).load(item.sprites.frontImg).into(binding.imgPoke)

            itemView.setOnClickListener {
                onCLickListener(item)
            }

        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<Pokemons>(){
        override fun areItemsTheSame(oldItem: Pokemons, newItem: Pokemons): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Pokemons, newItem: Pokemons): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardPokemonsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }


}