package br.itcampos.moviedatabase.presentation.view_all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import br.itcampos.moviedatabase.data.model.movies.MovieItem
import br.itcampos.moviedatabase.domain.use_cases.UseCases
import br.itcampos.moviedatabase.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewAllViewModel @Inject constructor(useCases: UseCases) : ViewModel() {

    var popularMoviesPagingItems: Flow<PagingData<MovieItem>> = emptyFlow()
    var nowPlayingMoviesPagingItems: Flow<PagingData<MovieItem>> = emptyFlow()
    var upcomingMoviesPagingItems: Flow<PagingData<MovieItem>> = emptyFlow()
    var topRatedMoviesPagingItems: Flow<PagingData<MovieItem>> = emptyFlow()

    init {
        viewModelScope.launch {
            popularMoviesPagingItems = useCases.popularMoviesPagingList.invoke(Constants.LANG)
            nowPlayingMoviesPagingItems = useCases.nowPlayingMoviesPagingList.invoke(Constants.LANG)
            upcomingMoviesPagingItems = useCases.upcomingMoviesPagingList.invoke(Constants.LANG)
            topRatedMoviesPagingItems = useCases.topRatedMoviesPagingList.invoke(Constants.LANG)
        }
    }
}