package com.example.littlelemon.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import com.example.littlelemon.navigation.Profile
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Home(navController: NavHostController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )
        Text("Welcome to the Home screen!")
        // Navigate to Profile screen when clicked
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .size(50.dp)
                .clickable {
                    navController.navigate(Profile.route)
                }
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    val dummyContext = LocalContext.current
    Home(navController = NavHostController(LocalContext.current))
}