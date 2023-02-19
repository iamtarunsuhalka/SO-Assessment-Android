package com.sourceone.assessment.android.model

data class PopularMovieResponse(
    var page: Int,
    var results: ArrayList<Movie>,
    var totalPages: Int,
    var totalResults: Long
) {
    constructor() : this(0, arrayListOf(), 0, 0)
}
