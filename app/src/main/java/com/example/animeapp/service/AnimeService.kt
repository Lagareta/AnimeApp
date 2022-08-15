package com.example.animeapp.service

import android.telecom.Call
import android.util.Log
import com.example.animeapp.Call.CallSearchAnimeItem
import com.example.animeapp.Call.CallSearchAnimes
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.URLEncoder

class AnimeService {

    /*@GET("search/anime")
    fun searchAnime(@Query("q")queryString: String): Call<DataSearch>

    companion object{
        val URL = "https://api.jikan.moe/v4/"

        fun create(): AnimeService{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()
            return retrofit.create(AnimeService::class.java)
        }
    }*/

    companion object {
        private const val TAG = "AnimeService"

        fun topAnime(callback: CallSearchAnimes) {

            val surl = "https://api.jikan.moe/v4/anime?q="

            Log.d(TAG, "surl $surl")
            val request: Request = Request.Builder()
                .url(surl)
                .build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(callback)


        }

        fun searchAnime(name: String, callback: CallSearchAnimes) {

            Log.d(TAG, "searchAnime $name")
            val surl = "https://api.jikan.moe/v4/anime?q=" + URLEncoder.encode(name) + "&sfw"

            Log.d(TAG, "surl $surl")
            val request: Request = Request.Builder()
                .url(surl)
                .build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(callback)

            Log.d(TAG, "searchAnime $name")

        }
        fun searchItemAnime(idAnime: Int, callback: CallSearchAnimeItem) {

            Log.d(TAG, "searchItemAnime $idAnime")
            val surl = "https://api.jikan.moe/v4/anime/" + idAnime

            Log.d(TAG, "surl $surl")
            val request: Request = Request.Builder()
                .url(surl)
                .build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(callback)

            Log.d(TAG, "searchAnime $idAnime")

        }
        fun searchGenreAnime(idAnime: Int, callback: CallSearchAnimeItem) {

            Log.d(TAG, "searchItemAnime $idAnime")
            val surl = "https://api.jikan.moe/v4/anime/genre/" + idAnime

            Log.d(TAG, "surl $surl")
            val request: Request = Request.Builder()
                .url(surl)
                .build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(callback)

            Log.d(TAG, "searchAnime $idAnime")

        }

    }

}