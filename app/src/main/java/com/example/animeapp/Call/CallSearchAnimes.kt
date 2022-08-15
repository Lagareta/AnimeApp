package com.example.animeapp.Call

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.animeapp.service.Data
import com.example.animeapp.service.DataSearch
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

abstract class CallSearchAnimes: Callback {

    companion object {
        private const val TAG = "CallSearchAnimes"
    }

    abstract fun fireOnResponseOk(data: DataSearch)

    override fun onFailure(call : Call, e: IOException) {
        Log.d(TAG, "onFailure", e)
    }

    override  fun onResponse(call: Call, response: Response) {
        Log.d(TAG, "onResponse")

        val responseData = response.body!!.string()

        val data: DataSearch = Gson().fromJson(responseData, DataSearch::class.java)

        runOnUiThread(Runnable { fireOnResponseOk(data) })
    }

    private fun runOnUiThread(task: Runnable) {
        Handler(Looper.getMainLooper()).post(task)
    }
}