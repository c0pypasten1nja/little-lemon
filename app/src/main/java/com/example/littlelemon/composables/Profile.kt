package com.example.littlelemon.composables

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.caption
import com.example.littlelemon.ui.theme.customShapes
import com.example.littlelemon.ui.theme.button

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString("firstName", "") ?: "133"
    val lastName = sharedPreferences.getString("lastName", "") ?: "samples"
    val email = sharedPreferences.getString("email", "") ?: "133samples@slip.knot"

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Header()

            Text(
                text = stringResource(R.string.personal_info),
                modifier = Modifier.padding(vertical = 40.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                PersonalInfoField(label = stringResource(R.string.caption_first_name), value = firstName)
                Spacer(modifier = Modifier.height(30.dp))
                PersonalInfoField(label = stringResource(R.string.caption_last_name), value = lastName)
                Spacer(modifier = Modifier.height(30.dp))
                PersonalInfoField(label = stringResource(R.string.caption_email), value = email)
            }

            Spacer(modifier = Modifier.height(120.dp))

            // Logout button
            Button(
                onClick = {
                    // Clear shared preferences
                    val editor = sharedPreferences.edit()
                    editor.clear()
                    editor.apply()

                    // Navigate to Onboarding screen
                    navController.navigate("Onboarding")
                },
                border = BorderStroke(1.dp, LittleLemonColor.stroke),
                shape = customShapes.medium,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = LittleLemonColor.charcoal,
                    containerColor = LittleLemonColor.yellow
                ),
                modifier = Modifier.fillMaxWidth().height(60.dp)
            ) {
                Text(
                    stringResource(R.string.button_logout),
                    style = button
                )
            }
        }
    }
}

@Composable
fun PersonalInfoField(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = caption
        )
        OutlinedTextField(
            value = value,
            onValueChange = { /* static text */ },
            readOnly = true,
            enabled = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(80.dp)
                .padding(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(navController = rememberNavController())
}
