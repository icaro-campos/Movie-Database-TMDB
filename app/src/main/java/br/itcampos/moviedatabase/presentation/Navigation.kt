package br.itcampos.moviedatabase.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.itcampos.moviedatabase.presentation.dashboard.DashboardScreen
import br.itcampos.moviedatabase.presentation.movie_details.MovieDetailsScreen
import br.itcampos.moviedatabase.presentation.movie_details.YoutubePlayerScreen
import br.itcampos.moviedatabase.presentation.search_movie.SearchPageScreen
import br.itcampos.moviedatabase.presentation.view_all.ViewAllScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Dashboard.route) {
        composable(route = Screen.Dashboard.route) {
            DashboardScreen(navController = navController)
        }
        composable(
            route = Screen.ViewAll.route + "?moviesType={moviesType}", arguments = listOf(
                navArgument(name = "moviesType") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val moviesType = it.arguments?.getString("moviesType") ?: ""
            ViewAllScreen(navController = navController, moviesType)
        }
        composable(
            route = Screen.MovieDetailsScreen.route + "?movieId={movieId}&moviesTitle={moviesTitle}",
            arguments = listOf(
                navArgument(name = "movieId") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(name = "moviesTitle") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val moviesTitle = it.arguments?.getString("moviesTitle") ?: ""
            val movieId = it.arguments?.getString("movieId") ?: ""
            MovieDetailsScreen(navController = navController, moviesTitle)
        }
        composable(
            route = Screen.YoutubePlayerScreen.route + "youtubeCode={youtubeCode}",
            arguments = listOf(
                navArgument(name = "youtubeCode") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val youtubeCode = it.arguments?.getString("youtubeCode") ?: ""
            YoutubePlayerScreen(navController = navController, youtubeCode)
        }
        composable(
            route = Screen.SearchPageScreen.route
        ) {
            SearchPageScreen(navController = navController)
        }


    }
}