package br.itcampos.moviedatabase.domain

import androidx.paging.PagingData
import br.itcampos.moviedatabase.data.model.cast.MovieCreditsResponse
import br.itcampos.moviedatabase.data.model.details.MovieDetailsResponse
import br.itcampos.moviedatabase.data.model.movies.MovieItem
import br.itcampos.moviedatabase.data.model.movies.NowPlayingMovieResponse
import br.itcampos.moviedatabase.data.model.movies.PopularMovieResponse
import br.itcampos.moviedatabase.data.model.movies.TopRatedMovieResponse
import br.itcampos.moviedatabase.data.model.movies.UpcomingMovieResponse
import br.itcampos.moviedatabase.data.model.search_movies.SearchMovieResponse
import br.itcampos.moviedatabase.data.model.videos.GetVideosResponse
import br.itcampos.moviedatabase.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieDbRepository {

    suspend fun popularPagingPagingList(lang: String): Flow<PagingData<MovieItem>>
    suspend fun nowPlayingPagingList(lang: String): Flow<PagingData<MovieItem>>
    suspend fun upcomingPagingList(lang: String): Flow<PagingData<MovieItem>>
    suspend fun topRatedPagingList(lang: String): Flow<PagingData<MovieItem>>

    suspend fun popularList(lang: String, page: Int): Flow<NetworkResult<PopularMovieResponse>>
    suspend fun nowPlayingList(
        lang: String,
        page: Int
    ): Flow<NetworkResult<NowPlayingMovieResponse>>

    suspend fun upcomingList(lang: String, page: Int): Flow<NetworkResult<UpcomingMovieResponse>>
    suspend fun topRatedList(lang: String, page: Int): Flow<NetworkResult<TopRatedMovieResponse>>

    suspend fun movieDetails(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieDetailsResponse>>>

    suspend fun movieCredits(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieCreditsResponse>>>

    suspend fun getVideos(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<GetVideosResponse>>>

    suspend fun searchPagingList(query: String, lang: String): Flow<NetworkResult<Response<SearchMovieResponse>>>
}