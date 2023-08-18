package com.android.pet_snap.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.pet_snap.R
import com.android.pet_snap.ui.theme.PetSnapTheme

@Composable
fun HomeScreen(
    nav_to_ImgGenerator: () -> Unit,
    nav_to_Gallery: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.random_dog_generator),
            style = MaterialTheme.typography.headlineLarge
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            Button(
                onClick = nav_to_ImgGenerator,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Text(stringResource(R.string.generate_dogs))
            }
            Button(
                onClick = nav_to_Gallery,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) {
                Text(stringResource(R.string.my_recently_generated_dogs))
            }
        }

    }

}

@Preview(name = "Home")
@Composable
fun PreviewHomeScreen() {
    PetSnapTheme {
        HomeScreen({}, {})
    }
}