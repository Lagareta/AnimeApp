package com.example.animeapp.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeapp.R
import com.example.animeapp.databinding.RecyclerAnimeBinding
import com.example.animeapp.databinding.RecyclerAnimeFavorisBinding
import com.example.animeapp.service.Data

class RecyclerAnimeFavoris(private val animes: List<Data>, private val fragment: Fragment) :
    RecyclerView.Adapter<RecyclerAnimeFavoris.AnimeFavorisViewHolder>()
    {

        companion object {
        private const val TAG = "RecyclerAnimeFavoris"
    }

        class AnimeFavorisViewHolder(val binding: RecyclerAnimeFavorisBinding) :
            RecyclerView.ViewHolder(binding.root)


        override fun getItemCount(): Int = animes.size


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeFavorisViewHolder {

            val binding = RecyclerAnimeFavorisBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return AnimeFavorisViewHolder(binding)
        }


        override fun onBindViewHolder(holder: AnimeFavorisViewHolder, position: Int) {

            val anime: Data = animes[position]

            Glide.with(holder.binding.imageAnime)
                .load(anime.images.jpg.image_url)
                .into(holder.binding.imageAnime)

            holder.binding.textViewName.text = anime.title
            /*holder.binding.textViewGenre.text = anime.genres.toString()
            holder.binding.textViewEpisode.text = "${anime.episodes} episodes"
            holder.binding.textViewRank.text = "${anime.rank} ième"
            holder.binding.textViewSynopsis.text = anime.synopsis*/


            holder.binding.card.setOnClickListener {
                val nav = bundleOf(
                    Pair("idAnime", anime.mal_id),
                    Pair("textViewName", anime.title),
                    Pair("textViewSynopsis", anime.synopsis)
                )



            }

        }
}