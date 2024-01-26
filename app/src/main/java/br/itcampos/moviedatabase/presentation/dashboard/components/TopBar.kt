package br.itcampos.moviedatabase.presentation.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.itcampos.moviedatabase.R
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun TopBar(
    navController: NavController,
    visibility: Boolean?
) {
    if (visibility == true) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .background(color = Color.Gray)
        ) {
            Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                Text(
                    text = stringResource(R.string.welcome),
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                SearchBar(navController = navController)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}