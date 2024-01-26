package br.itcampos.moviedatabase.domain.use_cases.popular

import androidx.paging.PagingData
import br.itcampos.moviedatabase.data.model.movies.MovieItem
import br.itcampos.moviedatabase.domain.MovieDbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PopularMoviesPagingList @Inject constructor(private val movieDbRepository: MovieDbRepository) {
    suspend operator fun invoke(lang: String) : Flow<PagingData<MovieItem>> {
        return movieDbRepository.popularPagingPagingList(lang)
    }
}