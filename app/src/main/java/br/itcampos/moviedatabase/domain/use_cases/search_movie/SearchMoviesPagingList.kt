package br.itcampos.moviedatabase.domain.use_cases.search_movie

import br.itcampos.moviedatabase.data.model.search_movies.SearchMovieResponse
import br.itcampos.moviedatabase.domain.MovieDbRepository
import br.itcampos.moviedatabase.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class SearchMoviesPagingList @Inject constructor(private val movieDbRepository: MovieDbRepository) {
    suspend operator fun invoke(
        query: String,
        lang: String
    ): Flow<NetworkResult<Response<SearchMovieResponse>>> {
        return movieDbRepository.searchPagingList(query = query, lang = lang)
    }
}