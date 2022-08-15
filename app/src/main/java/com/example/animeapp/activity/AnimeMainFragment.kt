package com.example.animeapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animeapp.Call.CallSearchAnimes
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentAnimeMainBinding
import com.example.animeapp.recycler.RecyclerAnime
import com.example.animeapp.recycler.RecyclerAnimeMain
import com.example.animeapp.service.AnimeService
import com.example.animeapp.service.DataSearch

class AnimeMainFragment: Fragment() {
    companion object{
        private const val TAG = "AnimeMainFragment"
    }


    private var _binding: FragmentAnimeMainBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentAnimeMainBinding.inflate(inflater,container,false)


        val thisFragment = this
        /* binding.animePage.apply {
             layoutManager = GridLayoutManager(applicationContext, 3)
             adapter = AnimeItemFragment(animeList, AnimeFragment)
         }*/


        AnimeService.topAnime(object : CallSearchAnimes(){
            override fun fireOnResponseOk(data: DataSearch){
                if(data.data.isNotEmpty()){
                    _binding!!.animeMainPage.layoutManager = LinearLayoutManager(thisFragment.requireContext())
                    _binding!!.animeMainPage.adapter = RecyclerAnimeMain(data.data,thisFragment)
                    _binding!!.animeMainPage.layoutManager = GridLayoutManager(thisFragment.requireContext(),3)
                }
            }
        })

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favorisPage.setOnClickListener{
            findNavController().navigate(R.id.action_AnimeMainFragment_to_FragmentAnimeFavoris)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}