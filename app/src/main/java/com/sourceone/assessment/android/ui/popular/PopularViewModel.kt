package com.sourceone.assessment.android.ui.popular

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sourceone.assessment.android.model.PopularMovieResponse
import com.sourceone.assessment.android.repository.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularViewModel : ViewModel() {

    private val apiClient = ApiClient.create()
    var popularMovies: MutableState<PopularMovieResponse> = mutableStateOf(PopularMovieResponse())

    fun getMovies() {
        apiClient.getPopularMovies().enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(call: Call<PopularMovieResponse>?, response: Response<PopularMovieResponse>?) {
                popularMovies.value = response!!.body()
                Log.d("p_success_res_", response.body().toString())
            }

            override fun onFailure(call: Call<PopularMovieResponse>?, t: Throwable?) {
                Log.d("error_res_", t!!.message!!)
            }
        })
    }

}