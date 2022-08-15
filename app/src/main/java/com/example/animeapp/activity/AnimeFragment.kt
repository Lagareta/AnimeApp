package com.example.animeapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeapp.Call.CallSearchAnimes
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentAnimeBinding
import com.example.animeapp.recycler.RecyclerAnime
import com.example.animeapp.service.*

class AnimeFragment: Fragment() {
    companion object{
        private const val TAG = "AnimeFragment"
    }


    private var _binding: FragmentAnimeBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentAnimeBinding.inflate(inflater,container,false)


        val thisFragment = this




        if(arguments?.get("anime") != null){
            val anime: String = arguments?.get("anime") as String
            AnimeService.searchAnime(anime, object : CallSearchAnimes(){
                override fun fireOnResponseOk(data: DataSearch){
                    if(data.data.isNotEmpty()){
                        _binding!!.animePage.layoutManager = LinearLayoutManager(thisFragment.requireContext())
                        _binding!!.animePage.adapter = RecyclerAnime(data.data, thisFragment)
                        _binding!!.animePage.layoutManager = GridLayoutManager(thisFragment.requireContext(),3)
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