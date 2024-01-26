package br.itcampos.moviedatabase.data

import br.itcampos.moviedatabase.data.model.cast.MovieCreditsResponse
import br.itcampos.moviedatabase.data.model.details.MovieDetailsResponse
import br.itcampos.moviedatabase.data.model.movies.NowPlayingMovieResponse
import br.itcampos.moviedatabase.data.model.movies.PopularMovieResponse
import br.itcampos.moviedatabase.data.model.movies.TopRatedMovieResponse
import br.itcampos.moviedatabase.data.model.movies.UpcomingMovieResponse
import br.itcampos.moviedatabase.data.model.search_movies.SearchMovieResponse
import br.itcampos.moviedatabase.data.model.videos.GetVideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): NowPlayingMovieResponse

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): PopularMovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): UpcomingMovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): TopRatedMovieResponse

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: String,
        @Query("language") language: String?,
    ): Response<MovieDetailsResponse>

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCredits(
        @Path("movieId") movieId: String,
        @Query("language") language: String?,
    ): Response<MovieCreditsResponse>

    @GET("movie/{movieId}/videos")
    suspend fun getVideos(
        @Path("movieId") movieId: String,
        @Query("language") language: String?,
    ): Response<GetVideosResponse>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String?,
        @Query("page") page: Int?,
        @Query("language") language: String?,
    ): Response<SearchMovieResponse>
}