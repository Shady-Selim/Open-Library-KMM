package com.shady.openlibrary.android.navigation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.shady.openlibrary.android.details.DetailsActivity
import com.shady.openlibrary.android.main.MainActivity
import com.shady.openlibrary.android.theme.OpenLibraryTheme
import com.shady.openlibrary.entities.LibraryDocument

class MainNav : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenLibraryTheme{
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Routes.Main.route) {
        composable(Routes.Main.route) { MainActivity(navController) }
        composable("${Routes.Details.route}") {
            val doc = navController.previousBackStackEntry?.arguments?.getParcelable<LibraryDocument>("doc")
            doc?.let {
                DetailsActivity(doc)
            }
         }
    }
}

@Preview(showSystemUi = true, showBackground = true, heightDp = 100)
@Composable
private fun Preview(){
    OpenLibraryTheme(darkTheme = false) {
        MyApp()
    }
}

sealed class Routes(val route: String) {
    object Main : Routes("main")
    object Details : Routes("details")
}