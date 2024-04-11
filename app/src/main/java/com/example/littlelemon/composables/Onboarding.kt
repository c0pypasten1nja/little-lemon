package com.example.littlelemon.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.navigation.Home

fun validateInput(firstName: String, lastName: String, email: String): Boolean {
    return firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()
}

@Composable
fun Onboarding(navController: NavHostController, context: Context) {
    val firstNameState = remember { mutableStateOf("") }
    val lastNameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = firstNameState.value,
            onValueChange = { firstNameState.value = it },
            label = { Text("First Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = lastNameState.value,
            onValueChange = { lastNameState.value = it },
            label = { Text("Last Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text("Email Address") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (validateInput(firstNameState.value, lastNameState.value, emailState.value)) {
                    navController.navigate(Home.route)
                } else {
                    Toast.makeText(context, "Registration unsuccessful. Please enter all data.", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Register")
        }
    }
}

@Preview
@Composable
fun OnboardingPreview() {
    val dummyContext = LocalContext.current
    Onboarding(navController = NavHostController(LocalContext.current), context = dummyContext)
}
