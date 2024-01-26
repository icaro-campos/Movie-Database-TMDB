package br.itcampos.moviedatabase.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.itcampos.moviedatabase.data.ApiService
import br.itcampos.moviedatabase.data.model.movies.MovieItem
import br.itcampos.moviedatabase.utils.Constants.NETWORK_PAGE_SIZE
import br.itcampos.moviedatabase.utils.Constants.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class TopRatedPagingSource(
    private val apiService: ApiService,
    private val lang: String
) : PagingSource<Int, MovieItem>(){
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val data = apiService.getTopRated(
                page = position,
                language = lang
            )
            val nextKey = if (data.results?.isEmpty() == true) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            val prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1
            LoadResult.Page(
                data = data.results!!,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

}