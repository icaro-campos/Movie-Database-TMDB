package br.itcampos.moviedatabase.domain.use_cases.top_rated

import br.itcampos.moviedatabase.data.model.movies.TopRatedMovieResponse
import br.itcampos.moviedatabase.domain.MovieDbRepository
import br.itcampos.moviedatabase.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopRatedMoviesList @Inject constructor(private val movieDbRepository: MovieDbRepository) {
    suspend operator fun invoke(lang: String, page: Int) : Flow<NetworkResult<TopRatedMovieResponse>> {
        return movieDbRepository.topRatedList(lang, page)
    }
}