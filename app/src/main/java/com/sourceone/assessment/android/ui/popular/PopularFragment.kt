package com.sourceone.assessment.android.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sourceone.assessment.android.R
import com.sourceone.assessment.android.model.Movie
import com.sourceone.assessment.android.ui.theme.SOMovieAssessmentTheme
import com.sourceone.assessment.android.utility.Constant

class PopularFragment : Fragment() {

    private lateinit var viewModel: PopularViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this).get(PopularViewModel::class.java)
        viewModel.getMovies()

        return ComposeView(requireContext()).apply {
            setContent {

                SOMovieAssessmentTheme {

                    Surface(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            LazyColumn(
                                modifier = Modifier.padding(20.dp)
                            ) {
                                viewModel.popularMovies.value.let {
                                    items(it.results) { eachMovie->
                                        MovieListItem(movie = eachMovie)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun MovieListItem(movie: Movie) {
        Card(
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (movie.posterPath.isBlank() || movie.posterPath == "null") {
                    Image(
                        painter = painterResource(id = R.drawable.no_image_preview),
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .weight(2f),
                        contentDescription = "No Image Available"
                    )
                } else {
                    GlideImage(
                        modifier = Modifier
                            .size(height = 100.dp, width = 85.dp)
                            .clip(CircleShape)
                            .weight(2f),
                        model = StringBuilder(Constant.IMAGE_BASE_URL).append(movie.posterPath).toString(),
                        contentDescription = movie.originalTitle,
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(6f)
                ) {

                    Text(
                        text = movie.originalTitle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = movie.releaseDate,
                        fontSize = 10.sp,
                        color = Color.LightGray,
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Star,
                            modifier = Modifier.size(16.dp).padding(0.dp, 0.dp, 5.dp, 0.dp),
                            contentDescription = "Star"
                        )
                        Text(
                            text = movie.voteAverage.toString(),
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}