package br.itcampos.moviedatabase.di

import android.content.Context
import androidx.room.Room
import br.itcampos.moviedatabase.BuildConfig
import br.itcampos.moviedatabase.data.ApiService
import br.itcampos.moviedatabase.data.local.database.AppDatabase
import br.itcampos.moviedatabase.data.local.database.UserDao
import br.itcampos.moviedatabase.data.local.database.UserRepositoryImpl
import br.itcampos.moviedatabase.domain.MovieDbRepository
import br.itcampos.moviedatabase.domain.repositories.UserRepository
import br.itcampos.moviedatabase.domain.use_cases.UseCases
import br.itcampos.moviedatabase.domain.use_cases.details.GetVideos
import br.itcampos.moviedatabase.domain.use_cases.details.MovieCredits
import br.itcampos.moviedatabase.domain.use_cases.details.MovieDetails
import br.itcampos.moviedatabase.domain.use_cases.now_playing.NowPlayingMoviesList
import br.itcampos.moviedatabase.domain.use_cases.now_playing.NowPlayingMoviesPagingList
import br.itcampos.moviedatabase.domain.use_cases.popular.PopularMoviesList
import br.itcampos.moviedatabase.domain.use_cases.popular.PopularMoviesPagingList
import br.itcampos.moviedatabase.domain.use_cases.search_movie.SearchMoviesPagingList
import br.itcampos.moviedatabase.domain.use_cases.top_rated.TopRatedMoviesList
import br.itcampos.moviedatabase.domain.use_cases.top_rated.TopRatedMoviesPagingList
import br.itcampos.moviedatabase.domain.use_cases.upcoming.UpcomingMoviesList
import br.itcampos.moviedatabase.domain.use_cases.upcoming.UpcomingMoviesPagingList
import br.itcampos.moviedatabase.utils.Constants.CONNECT_TIMEOUT
import br.itcampos.moviedatabase.utils.Constants.READ_TIMEOUT
import br.itcampos.moviedatabase.utils.Constants.WRITE_TIMEOUT
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    fun clientInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()

            val newRequest = request.newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder().also { client ->
                    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    }
                    client.addInterceptor(httpLoggingInterceptor)
                    client.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    client.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    client.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    client.addNetworkInterceptor(clientInterceptor())
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun useCases(movieDbRepository: MovieDbRepository): UseCases = UseCases(

        PopularMoviesPagingList(movieDbRepository),
        NowPlayingMoviesPagingList(movieDbRepository),
        UpcomingMoviesPagingList(movieDbRepository),
        TopRatedMoviesPagingList(movieDbRepository),

        PopularMoviesList(movieDbRepository),
        NowPlayingMoviesList(movieDbRepository),
        UpcomingMoviesList(movieDbRepository),
        TopRatedMoviesList(movieDbRepository),

        MovieDetails(movieDbRepository),
        MovieCredits(movieDbRepository),
        GetVideos(movieDbRepository),
        SearchMoviesPagingList(movieDbRepository),
    )

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }
}