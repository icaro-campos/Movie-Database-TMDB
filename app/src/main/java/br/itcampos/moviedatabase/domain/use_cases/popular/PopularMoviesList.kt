package br.itcampos.moviedatabase.domain.use_cases.popular

import br.itcampos.moviedatabase.data.model.movies.PopularMovieResponse
import br.itcampos.moviedatabase.domain.MovieDbRepository
import br.itcampos.moviedatabase.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMoviesList @Inject constructor(private val movieDbRepository: MovieDbRepository) {
    suspend operator fun invoke(lang: String, page: Int) : Flow<NetworkResult<PopularMovieResponse>> {
        return movieDbRepository.popularList(lang, page)
    }
}