package com.sourceone.assessment.android.repository

import com.sourceone.assessment.android.model.Movie
import com.sourceone.assessment.android.model.PopularMovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("latest?language=en-US&api_key=909594533c98883408adef5d56143539")
    fun getLatestMovies(): Call<Movie>

    @GET("popular?language=en-US&api_key=909594533c98883408adef5d56143539")
    fun getPopularMovies(): Call<PopularMovieResponse>
}
