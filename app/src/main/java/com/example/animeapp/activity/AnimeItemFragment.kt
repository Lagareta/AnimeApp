package com.example.animeapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.animeapp.Call.CallSearchAnimeItem
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentAnimeItemBinding
import com.example.animeapp.service.*

class AnimeItemFragment: Fragment() {

    companion object{
        private const val TAG = "AnimeItemFragment"
    }

    private var _binding: FragmentAnimeItemBinding? = null

    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeItemBinding.inflate(inflater, container, false)



        val thisFragment = this
        if(arguments?.get("idAnime") != null){
            val idanime: Int = arguments?.get("idAnime") as Int
            AnimeService.searchItemAnime(idanime, object : CallSearchAnimeItem(){
                override fun fireOnResponseOk(data: Data){
                    Glide.with(binding.imageAnime)
                        .load(arguments?.get("image"))
                        .into(binding.imageAnime)

                    binding.textViewName.text = "Title : " + arguments?.get("name") + "\n"
                    binding.textViewEpisode.text = "Episodes : " + "${arguments?.get("episode")} episodes" + "\n"
                    binding.textViewRank.text = "Rank : " + "${arguments?.get("rank")} ième" + "\n"
                    binding.textViewRating.text = "Rating : " + arguments?.get("rating") + "\n"
                    binding.textViewStatus.text = "Status : " + arguments?.get("status") + "\n"
                    binding.textViewDuration.text = "Duration : " + arguments?.get("duration") + "\n"
                    binding.textViewSeason.text = "Season : " + arguments?.get("season") + "\n"
                    binding.textViewSynopsis.text = "Synopsis : \n \n" + arguments?.get("synopsis") + "\n"

                }
            })
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}