package com.example.animeapp.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeapp.R
import com.example.animeapp.databinding.RecyclerAnimeMainBinding
import com.example.animeapp.service.Data

class RecyclerAnimeMain(private val animes: List<Data>, private val fragment: Fragment) :
    RecyclerView.Adapter<RecyclerAnimeMain.AnimeMainViewHolder>(){

    companion object {
        private const val TAG = "RecyclerAnimeMain"
    }
    var addTrue = false

    class AnimeMainViewHolder(val binding: RecyclerAnimeMainBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun getItemCount(): Int = animes.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeMainViewHolder {

        val binding = RecyclerAnimeMainBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return AnimeMainViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AnimeMainViewHolder, position: Int) {

        val anime: Data = animes[position]

        Glide.with(holder.binding.imageAnime)
            .load(anime.images.jpg.image_url)
            .into(holder.binding.imageAnime)

        holder.binding.textViewName.text = anime.title

        holder.binding.addFavoris.setOnClickListener {
            addTrue = true
            val nav = bundleOf(
                Pair("idAnime", anime.mal_id),
                Pair("name", anime.title),
                Pair("synopsis", anime.synopsis),
                Pair("episode", anime.episodes),
                Pair("rank", anime.rank),
                Pair("genre", anime.genres.toString()),
                Pair("image", anime.images.jpg.image_url),
                Pair("addTrue", addTrue)
            )
        }

        holder.binding.cardMain.setOnClickListener {
            val nav = bundleOf(
                Pair("idAnime", anime.mal_id),
                Pair("name", anime.title),
                Pair("synopsis", anime.synopsis),
                Pair("episode", anime.episodes),
                Pair("rank", anime.rank),
                Pair("genre", anime.genres.toString()),
                Pair("image", anime.images.jpg.image_url),
                Pair("rating",anime.rating),
                Pair("status",anime.status),
                Pair("duration",anime.duration),
                Pair("season",anime.season)
            )
            fragment.findNavController().navigate(R.id.action_AnimeMainFragment_to_AnimeItemFragment, nav)
        }

    }
}