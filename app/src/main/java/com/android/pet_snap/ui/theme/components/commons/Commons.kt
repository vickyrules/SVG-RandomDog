package com.android.pet_snap.ui.theme.components.commons


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.pet_snap.R
import com.android.pet_snap.network.ErrorResponse

@Composable
fun FirstTimeScreen(isFirstTime: Boolean,modifier:Modifier =  Modifier) {
    Column(modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(R.string.click_the_button_below), textAlign = TextAlign.Center,modifier = Modifier.size(200.dp))
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, ErrorResponse: ErrorResponse) {
    Column(modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(modifier = Modifier.size(50.dp),painter = painterResource(R.drawable.ic_connection_error), contentDescription = null)
        ErrorResponse.message?.let { Text(text = it) }
        Text(text = "Status Code: ${ErrorResponse.statusCode}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title:String = "",
    navigateUp: ()->Unit
){
    CenterAlignedTopAppBar(
        title = { Text(modifier = Modifier.padding(start = 12.dp),text = title) },
        navigationIcon = {
            IconButton(onClick = navigateUp){
                Icon(imageVector = Icons.Filled.ArrowBack,contentDescription = "navigate up")
            }
        },
    )
}
