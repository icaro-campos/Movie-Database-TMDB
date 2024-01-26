package br.itcampos.moviedatabase.domain.use_cases.details

import br.itcampos.moviedatabase.data.model.videos.GetVideosResponse
import br.itcampos.moviedatabase.domain.MovieDbRepository
import br.itcampos.moviedatabase.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GetVideos @Inject constructor(private val movieDbRepository: MovieDbRepository) {
    suspend operator fun invoke(lang: String, movieId: String) : Flow<NetworkResult<Response<GetVideosResponse>>> {
        return movieDbRepository.getVideos(lang, movieId)
    }
}