package com.example.coffeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffeapp.model.BottomBarItem
import com.example.coffeapp.model.Menu
import com.example.coffeapp.model.dummyBestSellerMenu
import com.example.coffeapp.model.dummyCategory
import com.example.coffeapp.model.dummyMenu
import com.example.coffeapp.ui.components.CategoryItems
import com.example.coffeapp.ui.components.HomeSection
import com.example.coffeapp.ui.components.MenuItem
import com.example.coffeapp.ui.components.MenuItemPreview
import com.example.coffeapp.ui.components.Search
import com.example.coffeapp.ui.components.SectionText
import com.example.coffeapp.ui.theme.CoffeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeAppTheme {
                Homepage()
            }
        }
    }
}


// Homepage Part
@Composable
fun Homepage(modifier: Modifier = Modifier) {
    Scaffold (
        bottomBar = { BottomBar() }
    ){ paddingValues->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())

        ) {
            Banner()
            HomeSection(
                title = stringResource(R.string.section_category),
                content = { CategoryRow() }
            )
            HomeSection(
                title = stringResource(R.string.section_favorite_menu),
                content = { MenuRow(dummyMenu) }
            )
            HomeSection(
                title = stringResource(R.string.section_best_seller_menu),
                content = { MenuRow(dummyBestSellerMenu) }
            )
        }
    }
}


// Composable Banner
@Composable
fun Banner(
    modifier: Modifier = Modifier
){
    Box(modifier = modifier){
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale =  ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        Search()
    }
}


// Category Row Function
@Composable
fun CategoryRow(
    modifier: Modifier=Modifier
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(dummyCategory,key = {it.textCategory}){
            category->
            CategoryItems(category)
        }
    }
}


// Category Row Preview
@Composable
@Preview(showBackground = true)
fun CategoryRowPreview() {
    CoffeAppTheme {
        CategoryRow()
    }
}

// Menu Row Function
@Composable
fun MenuRow(
    listMenu : List<Menu>,
    modifier: Modifier = Modifier
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ){
        items(listMenu, key = { it.title }) { menu ->
            MenuItem(menu)
        }
    }
}

// Bottom Bar Navigation
@Composable
fun BottomBar(
    modifier: Modifier = Modifier
){
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home
            ),
            BottomBarItem(
                title =  stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle
            )
        )

        navigationItems.map { 
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(it.title)
                },
                selected = it.title == navigationItems[0].title,
                onClick = {}
            )
        }
    }
}


// Main App Preview
@Preview(showSystemUi = true,showBackground = true, device = Devices.PIXEL_4)
@Composable
fun GreetingPreview(modifier: Modifier = Modifier) {
    CoffeAppTheme {
        Column(modifier = modifier){
            Homepage()
        }
    }
}