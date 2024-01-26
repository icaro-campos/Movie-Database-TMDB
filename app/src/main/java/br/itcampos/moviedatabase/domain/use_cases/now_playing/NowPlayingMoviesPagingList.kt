package br.itcampos.moviedatabase.domain.use_cases.now_playing

import androidx.paging.PagingData
import br.itcampos.moviedatabase.data.model.movies.MovieItem
import br.itcampos.moviedatabase.domain.MovieDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NowPlayingMoviesPagingList @Inject constructor(private val movieDbRepository: MovieDbRepository) {
    suspend operator fun invoke(lang: String) : Flow<PagingData<MovieItem>> {
        return movieDbRepository.nowPlayingPagingList(lang)
    }
}