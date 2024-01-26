package br.itcampos.moviedatabase.domain.use_cases.upcoming

import br.itcampos.moviedatabase.data.model.movies.UpcomingMovieResponse
import br.itcampos.moviedatabase.domain.MovieDbRepository
import br.itcampos.moviedatabase.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpcomingMoviesList @Inject constructor(private val movieDbRepository: MovieDbRepository) {
    suspend operator fun invoke(lang: String, page: Int) : Flow<NetworkResult<UpcomingMovieResponse>> {
        return movieDbRepository.upcomingList(lang, page)
    }
}