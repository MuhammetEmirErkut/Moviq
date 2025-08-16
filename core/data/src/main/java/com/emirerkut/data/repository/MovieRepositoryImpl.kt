package com.emirerkut.data.repository

import com.emirerkut.model.Movie
import com.emirerkut.common.helper.DispatcherProvider
import com.emirerkut.common.model.ErrorType
import com.emirerkut.common.model.Failure
import com.emirerkut.data.mapper.toMovieList
import com.emirerkut.network.model.MovieResponseDTO
import com.emirerkut.network.source.MovieRemoteDataSource
import com.emirerkut.network.util.asRestApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import java.net.UnknownHostException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
): MovieRepository{
    override fun getPopularMovies(language: String): Flow<List<Movie>> =
        movieRemoteDataSource.getPopularMovies(language)
            .asRestApiCall(MovieResponseDTO::toMovieList)
            .catch {
                if (it is UnknownHostException) {
                    throw Failure(ErrorType.CONNECTION_ERROR)
                } else throw it
            }
            .flowOn(dispatcherProvider.ioDispatcher)

    override fun getTopRatedMovies(language: String): Flow<List<Movie>> =
        movieRemoteDataSource.getTopRatedMovies(language)
            .asRestApiCall(MovieResponseDTO::toMovieList)
            .catch {
                if (it is UnknownHostException) {
                    throw Failure(ErrorType.CONNECTION_ERROR)
                } else throw it
            }
            .flowOn(dispatcherProvider.ioDispatcher)

    override fun getUpcomingMovies(language: String): Flow<List<Movie>> =
        movieRemoteDataSource.getUpcomingMovies(language)
            .asRestApiCall(MovieResponseDTO::toMovieList)
            .catch {
                if (it is UnknownHostException) {
                    throw Failure(ErrorType.CONNECTION_ERROR)
                } else throw it
            }
            .flowOn(dispatcherProvider.ioDispatcher)

    override fun getNowPlayingMovies(language: String): Flow<List<Movie>> =
        movieRemoteDataSource.getNowPlayingMovies(language)
            .asRestApiCall(MovieResponseDTO::toMovieList)
            .catch{
                if (it is UnknownHostException) {
                    throw Failure(ErrorType.CONNECTION_ERROR)
                } else throw it
            }
            .flowOn(dispatcherProvider.ioDispatcher)

    override fun searchMovies(query: String): Flow<List<Movie>> {
        return movieRemoteDataSource.searchMovies(query)
            .asRestApiCall(MovieResponseDTO::toMovieList)
            .catch {
                if (it is UnknownHostException) {
                    throw Failure(ErrorType.CONNECTION_ERROR)
                } else throw it
            }
            .flowOn(dispatcherProvider.ioDispatcher)
    }

}