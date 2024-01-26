package br.itcampos.moviedatabase.presentation.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.itcampos.moviedatabase.R
import br.itcampos.moviedatabase.presentation.Screen
import br.itcampos.moviedatabase.presentation.dashboard.components.ErrorView
import br.itcampos.moviedatabase.presentation.dashboard.components.IsLoading
import br.itcampos.moviedatabase.presentation.dashboard.components.MovieItemCard
import br.itcampos.moviedatabase.presentation.dashboard.components.TopBar
import br.itcampos.moviedatabase.utils.HomeBottomNavigation

@Composable
fun DashboardScreen(navController: NavController, viewModel: DashboardViewModel = hiltViewModel()) {
    Scaffold(bottomBar = {
        BottomNavigationBar(navController = navController)
    }) { paddingValues ->
        Box(modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())) {
            LazyColumn {
                item {
                    TopBar(
                        navController = navController,
                        visibility = viewModel.popularMovieList.isNotEmpty()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Title(
                        navController,
                        viewModel.popularMovieList.isNotEmpty(),
                        MoviesType.POPULAR
                    )
                    
                    PopularList(viewModel = viewModel, navController = navController)
                    
                    Spacer(modifier = Modifier.height(5.dp))
                    Title(
                        navController = navController,
                        visibility = viewModel.nowPlayingMovieList.isNotEmpty(),
                        moviesType = MoviesType.NOW_PLAYING
                    )
                    
                    NowPlayingList(viewModel = viewModel, navController = navController)
                    
                    Spacer(modifier = Modifier.height(5.dp))
                    Title(
                        navController = navController,
                        visibility = viewModel.upcomingMovieList.isNotEmpty(),
                        moviesType = MoviesType.UPCOMING
                    )
                    
                    UpcomingList(viewModel = viewModel, navController = navController)
                    
                    Spacer(modifier = Modifier.height(5.dp))
                    Title(
                        navController = navController,
                        visibility = viewModel.topRatedMovieList.isNotEmpty(),
                        moviesType = MoviesType.TOP_RATED
                    )

                    TopRatedList(viewModel = viewModel, navController = navController)
                }
            }
            IsLoading(isLoading = viewModel.isLoading.containsValue(true))
            ErrorView(networkError = viewModel.apiError.value)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        HomeBottomNavigation.Home,
        HomeBottomNavigation.Favorite,
        HomeBottomNavigation.Profile
    )

    BottomNavigation(
        backgroundColor = Color.Black,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        navigationItems.forEach {
            BottomNavigationItem(
                selected = it.route == currentRoute,
                onClick = { },
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.title
                    )
                }
            )
        }
    }
}

@Composable
fun Title(
    navController: NavController,
    visibility: Boolean?,
    moviesType: MoviesType
) {
    if (visibility == true) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = LocalContext.current.getString(moviesType.value),
                style = MaterialTheme.typography.h6
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.clickable(
                    onClick = {
                        navController.navigate(
                            Screen.ViewAll.route + "?moviesType=${moviesType.value}"
                        )
                    }
                )
            ) {
                Text(
                    text = stringResource(R.string.view_all),
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.double_arrow_right),
                    contentDescription = stringResource(R.string.description_arrow_forward),
                    modifier = Modifier.size(10.dp),
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun PopularList(viewModel: DashboardViewModel, navController: NavController) {
    LazyRow(Modifier.padding(top = 10.dp)) {
        items(
            items = viewModel.popularMovieList,
            key = { item ->
                item.movieId.toString()
            }
        ) { item ->
            MovieItemCard(item, Modifier.width(140.dp), navController)
        }
    }
}

@Composable
fun NowPlayingList(viewModel: DashboardViewModel, navController: NavController) {
    LazyRow(Modifier.padding(top = 10.dp)) {
        items(
            items = viewModel.nowPlayingMovieList,
            key = { item ->
                item.movieId.toString()
            }
        ) { item ->
            MovieItemCard(
                item = item,
                modifier = Modifier.width(140.dp),
                navController = navController
            )
        }
    }
}

@Composable
fun UpcomingList(viewModel: DashboardViewModel, navController: NavController) {
    LazyRow(Modifier.padding(top = 10.dp)) {
        items(
            items = viewModel.upcomingMovieList,
            key = { item ->
                item.movieId.toString()
            }
        ) { item ->
            MovieItemCard(
                item = item,
                modifier = Modifier.width(140.dp),
                navController = navController
            )
        }
    }
}

@Composable
fun TopRatedList(viewModel: DashboardViewModel, navController: NavController) {
    LazyRow(Modifier.padding(top = 10.dp)) {
        items(
            items = viewModel.topRatedMovieList,
            key = { item ->
                item.movieId.toString()
            }
        ) { item ->
            MovieItemCard(
                item = item,
                modifier = Modifier.width(140.dp),
                navController = navController
            )
        }
    }
}