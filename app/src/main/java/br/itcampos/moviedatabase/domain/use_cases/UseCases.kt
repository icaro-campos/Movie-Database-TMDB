package br.itcampos.moviedatabase.domain.use_cases

import br.itcampos.moviedatabase.domain.use_cases.details.GetVideos
import br.itcampos.moviedatabase.domain.use_cases.details.MovieCredits
import br.itcampos.moviedatabase.domain.use_cases.details.MovieDetails
import br.itcampos.moviedatabase.domain.use_cases.now_playing.NowPlayingMoviesList
import br.itcampos.moviedatabase.domain.use_cases.now_playing.NowPlayingMoviesPagingList
import br.itcampos.moviedatabase.domain.use_cases.popular.PopularMoviesList
import br.itcampos.moviedatabase.domain.use_cases.popular.PopularMoviesPagingList
import br.itcampos.moviedatabase.domain.use_cases.search_movie.SearchMoviesPagingList
import br.itcampos.moviedatabase.domain.use_cases.top_rated.TopRatedMoviesList
import br.itcampos.moviedatabase.domain.use_cases.top_rated.TopRatedMoviesPagingList
import br.itcampos.moviedatabase.domain.use_cases.upcoming.UpcomingMoviesList
import br.itcampos.moviedatabase.domain.use_cases.upcoming.UpcomingMoviesPagingList

data class UseCases(

    val popularMoviesPagingList: PopularMoviesPagingList,
    val nowPlayingMoviesPagingList: NowPlayingMoviesPagingList,
    val upcomingMoviesPagingList: UpcomingMoviesPagingList,
    val topRatedMoviesPagingList: TopRatedMoviesPagingList,

    val popularMoviesList: PopularMoviesList,
    val nowPlayingMoviesList: NowPlayingMoviesList,
    val upcomingMoviesList: UpcomingMoviesList,
    val topRatedMoviesList: TopRatedMoviesList,
    val movieDetails: MovieDetails,
    val movieCredits: MovieCredits,
    val getVideos: GetVideos,
    val searchMoviesPagingList: SearchMoviesPagingList
)
