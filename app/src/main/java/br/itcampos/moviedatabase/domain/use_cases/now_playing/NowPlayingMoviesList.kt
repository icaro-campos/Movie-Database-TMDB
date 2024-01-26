package br.itcampos.moviedatabase.domain.use_cases.now_playing

import br.itcampos.moviedatabase.data.model.movies.NowPlayingMovieResponse
import br.itcampos.moviedatabase.domain.MovieDbRepository
import br.itcampos.moviedatabase.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NowPlayingMoviesList @Inject constructor(private val movieDbRepository: MovieDbRepository) {
    suspend operator fun invoke(lang: String, page: Int) : Flow<NetworkResult<NowPlayingMovieResponse>> {
        return movieDbRepository.nowPlayingList(lang, page)
    }
}