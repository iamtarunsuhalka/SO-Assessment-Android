package com.sourceone.assessment.android.ui.latest

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sourceone.assessment.android.model.Movie
import com.sourceone.assessment.android.repository.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LatestViewModel : ViewModel() {

    private val apiClient = ApiClient.create()
    var latestMovie: MutableState<Movie> = mutableStateOf(Movie())

    fun getMovies() {
        apiClient.getLatestMovies().enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
                latestMovie.value = response!!.body()
                Log.d("success_l_res_", response.body().toString())
            }

            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
                Log.d("error_res_", t!!.message!!)
            }
        })
    }

}