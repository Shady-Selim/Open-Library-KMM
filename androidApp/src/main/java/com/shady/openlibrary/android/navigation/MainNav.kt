package com.shady.openlibrary.android.navigation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
        composable(Routes.Details.route) {
            val doc = navController.previousBackStackEntry?.arguments?.getParcelable<LibraryDocument>("document")
            DetailsActivity(doc) }
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

/*
@Composable

navController.navigate("Red")

fun NavigateButton(
    text: String,
    modifier: Modifier = Modifier,
    listener: () -> Unit = { }
) {
    Button(
        onClick = listener,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun NavigateBackButton(navController: NavController) {
    // Use LocalLifecycleOwner.current as a proxy for the NavBackStackEntry
    // associated with this Composable
    if (navController.currentBackStackEntry == LocalLifecycleOwner.current &&
        navController.previousBackStackEntry != null
    ) {
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Go to Previous screen")
        }
    }
}*/
