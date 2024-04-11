package com.example.littlelemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import android.content.Context

@Composable
fun Profile(navController: NavHostController, context: Context) {
    val sharedPreferences = remember { context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE) }
    var firstName by remember { mutableStateOf(sharedPreferences.getString("firstName", "") ?: "") }
    var lastName by remember { mutableStateOf(sharedPreferences.getString("lastName", "") ?: "") }
    var email by remember { mutableStateOf(sharedPreferences.getString("email", "") ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(100.dp)
        )
        Text("Profile information:")
        Text("First Name: $firstName")
        Text("Last Name: $lastName")
        Text("Email: $email")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Clear shared preferences
                sharedPreferences.edit().clear().apply()
                // Navigate to onboarding screen
                navController.navigate(Destinations.Onboarding.route)
            }
        ) {
            Text("Log out")
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    Profile(navController = NavHostController(), context = TODO())
}
