package com.sourceone.assessment.android.ui.latest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sourceone.assessment.android.ui.theme.SOMovieAssessmentTheme
import com.sourceone.assessment.android.utility.Constant
import com.sourceone.assessment.android.R

class LatestFragment : Fragment() {

    lateinit var viewModel: LatestViewModel

    @OptIn(ExperimentalGlideComposeApi::class)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        viewModel = ViewModelProvider(this).get(LatestViewModel::class.java)
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

                            Text(
                                modifier = Modifier.padding(20.dp),
                                text = viewModel.latestMovie.value.originalTitle,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                modifier = Modifier.padding(20.dp),
                                text = viewModel.latestMovie.value.overview,
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            if (viewModel.latestMovie.value.posterPath.isNullOrBlank() || viewModel.latestMovie.value.posterPath == "null") {
                                Image(
                                    painter = painterResource(id = R.drawable.no_image_preview),
                                    modifier = Modifier.size(width = 400.dp, height = 300.dp),
                                    contentDescription = "No Image Available"
                                )
                            } else {
                                GlideImage(
                                    model = StringBuilder(Constant.IMAGE_BASE_URL)
                                        .append(viewModel.latestMovie.value.posterPath)
                                        .toString(),
                                    contentDescription = stringResource(id = R.string.latest),
                                    modifier = Modifier.size(width = 400.dp, height = 300.dp)
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}