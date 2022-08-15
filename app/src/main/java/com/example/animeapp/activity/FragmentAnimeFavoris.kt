package com.example.animeapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.animeapp.Call.CallSearchAnimes
import com.example.animeapp.databinding.FragmentAnimeFavorisBinding
import com.example.animeapp.recycler.RecyclerAnime
import com.example.animeapp.recycler.RecyclerAnimeFavoris
import com.example.animeapp.service.AnimeService
import com.example.animeapp.service.DataSearch

class FragmentAnimeFavoris: Fragment() {
    companion object{
        private const val TAG = "FragmentAnimeFavoris"
    }

    private var _binding: FragmentAnimeFavorisBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAnimeFavorisBinding.inflate(inflater, container, false)

        val thisFragment = this
        if(arguments?.get("anime") != null){
            val anime: String = arguments?.get("anime") as String
            AnimeService.searchAnime(anime, object : CallSearchAnimes(){
                override fun fireOnResponseOk(data: DataSearch){
                    if(arguments?.get("addTrue") == true){
                        _binding!!.animeFavorisPage.layoutManager = LinearLayoutManager(thisFragment.requireContext())
                        _binding!!.animeFavorisPage.adapter = RecyclerAnimeFavoris(data.data, thisFragment)
                    }
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