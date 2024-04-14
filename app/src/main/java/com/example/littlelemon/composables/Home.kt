package com.example.littlelemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.littlelemon.MenuItemRoom
import com.example.littlelemon.MenuViewModel
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.customShapes
import com.example.littlelemon.ui.theme.h1

@Composable
fun Home(navController: NavController) {
    val viewModel: MenuViewModel = viewModel()
    val databaseMenuItems by viewModel.getAllDatabaseMenuItems().observeAsState(emptyList())
    val searchPhrase = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchMenuDataIfNeeded()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Header(navController)

            HeroSection()

            SearchTextField(searchPhrase)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Text(
                        text = stringResource(R.string.order_delivery).uppercase(),
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = LittleLemonColor.charcoal,
                        modifier = Modifier.padding(start = 16.dp, top = 30.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Filtered MenuItems
            val filteredMenuItems = if  (searchPhrase.value.isBlank()) {
                databaseMenuItems
            } else {
                databaseMenuItems.filter { menuItem ->
                    menuItem.title.contains(searchPhrase.value, ignoreCase = true)
                }
            }

            MenuItems(menuItems = filteredMenuItems)
        }
    }
}

@Composable
fun Header(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo Image",
            modifier = Modifier
                .size(40.dp)
                .weight(1f)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(80.dp)
                .clickable {
                    navController.navigate("Profile")
                }
        )
    }
}

@Composable
fun HeroSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = LittleLemonColor.green)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(R.string.title),
                style = h1,
                color = LittleLemonColor.yellow,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SearchTextField(searchPhrase: MutableState<String>) {
    TextField(
        value = searchPhrase.value,
        onValueChange = { searchPhrase.value = it },
        placeholder = { Text("Enter search phrase") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clip(shape = customShapes.medium),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = ""
            )
        },
        singleLine = true,
    )
}

@Composable
fun MenuItems(menuItems: List<MenuItemRoom>) {
    Spacer(
        modifier = Modifier
            .width(20.dp)
            .padding(top = 10.dp, bottom = 10.dp)
    )
    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        for (menuItem in menuItems) {
            MenuItem(item = menuItem)
        }
    }
}

@Composable
fun MenuItem(item: MenuItemRoom) {
    Spacer(modifier = Modifier.width(10.dp))
    Divider(color = Color.Gray, thickness = 1.dp)
    Spacer(modifier = Modifier.width(10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = item.description,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(text = "$  ${item.price}")
        }
        Column {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = rememberAsyncImagePainter(item.image),
                contentDescription = "MenuItem Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = customShapes.medium)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}