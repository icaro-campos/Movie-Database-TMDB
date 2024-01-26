package br.itcampos.moviedatabase.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.itcampos.moviedatabase.data.ApiService
import br.itcampos.moviedatabase.data.model.cast.MovieCreditsResponse
import br.itcampos.moviedatabase.data.model.details.MovieDetailsResponse
import br.itcampos.moviedatabase.data.model.movies.MovieItem
import br.itcampos.moviedatabase.data.model.movies.NowPlayingMovieResponse
import br.itcampos.moviedatabase.data.model.movies.PopularMovieResponse
import br.itcampos.moviedatabase.data.model.movies.TopRatedMovieResponse
import br.itcampos.moviedatabase.data.model.movies.UpcomingMovieResponse
import br.itcampos.moviedatabase.data.model.search_movies.SearchMovieResponse
import br.itcampos.moviedatabase.data.model.videos.GetVideosResponse
import br.itcampos.moviedatabase.data.paging.NowPlayingPagingSource
import br.itcampos.moviedatabase.data.paging.PopularPagingSource
import br.itcampos.moviedatabase.data.paging.TopRatedPagingSource
import br.itcampos.moviedatabase.data.paging.UpcomingPagingSource
import br.itcampos.moviedatabase.domain.MovieDbRepository
import br.itcampos.moviedatabase.utils.Constants
import br.itcampos.moviedatabase.utils.NetworkResult
import br.itcampos.moviedatabase.utils.ResponseCodeManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class MovieDbRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieDbRepository {

    override suspend fun popularPagingPagingList(lang: String): Flow<PagingData<MovieItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { PopularPagingSource(apiService, lang) }
    ).flow

    override suspend fun nowPlayingPagingList(
        lang: String
    ): Flow<PagingData<MovieItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { NowPlayingPagingSource(apiService, lang) }
    ).flow

    override suspend fun upcomingPagingList(lang: String): Flow<PagingData<MovieItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { UpcomingPagingSource(apiService, lang) }
    ).flow

    override suspend fun topRatedPagingList(lang: String): Flow<PagingData<MovieItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { TopRatedPagingSource(apiService, lang) }
    ).flow


    //non-paging
    override suspend fun popularList(
        lang: String,
        page: Int
    ): Flow<NetworkResult<PopularMovieResponse>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.getPopular(
                    page = page, language = lang
                )
                emit(
                    NetworkResult.Success(response)
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()
                                    ?.let { ResponseCodeManager.checkRetrofitApiResponse(it) })
                        }
                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }
                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun nowPlayingList(
        lang: String,
        page: Int
    ): Flow<NetworkResult<NowPlayingMovieResponse>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.getNowPlaying(
                    page = page, language = lang
                )
                emit(
                    NetworkResult.Success(response)
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()
                                    ?.let { ResponseCodeManager.checkRetrofitApiResponse(it) })
                        }
                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }
                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun upcomingList(
        lang: String,
        page: Int
    ): Flow<NetworkResult<UpcomingMovieResponse>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.getUpcoming(
                    page = page, language = lang
                )
                emit(
                    NetworkResult.Success(response)
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()
                                    ?.let { ResponseCodeManager.checkRetrofitApiResponse(it) })
                        }
                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }
                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun topRatedList(
        lang: String,
        page: Int
    ): Flow<NetworkResult<TopRatedMovieResponse>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.getTopRated(
                    page = page, language = lang
                )
                emit(
                    NetworkResult.Success(response)
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()
                                    ?.let { ResponseCodeManager.checkRetrofitApiResponse(it) })
                        }
                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }
                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }


    override suspend fun movieDetails(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieDetailsResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.getMovieDetails(
                    movieId = movieId, language = lang
                )
                emit(
                    if (response.isSuccessful) NetworkResult.Success(response) else
                        NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()
                                    ?.let { ResponseCodeManager.checkRetrofitApiResponse(it) })
                        }
                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }
                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun movieCredits(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<MovieCreditsResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.getMovieCredits(
                    movieId = movieId, language = lang
                )
                emit(
                    if (response.isSuccessful) NetworkResult.Success(response) else
                        NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()
                                    ?.let { ResponseCodeManager.checkRetrofitApiResponse(it) })
                        }
                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }
                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun getVideos(
        lang: String,
        movieId: String
    ): Flow<NetworkResult<Response<GetVideosResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.getVideos(
                    movieId = movieId, language = lang
                )
                emit(
                    if (response.isSuccessful) NetworkResult.Success(response) else
                        NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()
                                    ?.let { ResponseCodeManager.checkRetrofitApiResponse(it) })
                        }
                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }
                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun searchPagingList(
        query: String,
        lang: String
    ): Flow<NetworkResult<Response<SearchMovieResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = apiService.searchMovie(query = query, language = lang, page = 1)
                emit(
                    if (response.isSuccessful) NetworkResult.Success(response) else
                        NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                )
            } catch (throwable: Throwable) {
                emit(
                    when (throwable) {
                        is HttpException -> {
                            NetworkResult.Failure(
                                false,
                                throwable.code(),
                                throwable.response()?.errorBody(),
                                throwable.response()
                                    ?.let { ResponseCodeManager.checkRetrofitApiResponse(it) })
                        }
                        is IOException -> {
                            NetworkResult.Failure(true, null, null, Constants.STS_DEFAULT)
                        }
                        else -> {
                            NetworkResult.Failure(false, null, null, Constants.CONVERSION_FAILURE)
                        }
                    }
                )
            }
        }.flowOn(Dispatchers.IO)

    }

}