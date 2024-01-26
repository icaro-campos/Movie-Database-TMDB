package br.itcampos.moviedatabase.presentation.dashboard

import androidx.annotation.StringRes
import br.itcampos.moviedatabase.R

sealed class MoviesType(@StringRes val value: Int) {
    object POPULAR : MoviesType(R.string.type_popular)
    object NOW_PLAYING : MoviesType(R.string.type_now_playing)
    object UPCOMING : MoviesType(R.string.type_upcoming)
    object TOP_RATED : MoviesType(R.string.type_top_rated)
}
