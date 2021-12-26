package com.shady.openlibrary.android.main

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shady.openlibrary.android.navigation.Routes
import com.shady.openlibrary.entities.LibraryDocument

@Composable
fun MainActivity(navController: NavHostController, vm: MainViewModel = viewModel()){
    var queryType by remember { mutableStateOf("q") }
    var dropMenuText by remember { mutableStateOf("By Any") }
    var expanded by remember { mutableStateOf(false) }
    var queryString by remember { mutableStateOf("") }
    var search by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }

    fun clearPage(){
        search = false
        loading = false
    }

    Column() {
        Text(text = "Search the Open Library", style = typography.h4 )
        Surface(elevation = 16.dp) {
            Row() {
                Button(onClick = { expanded = !expanded },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    modifier = Modifier.height(60.dp)){
                    Text (dropMenuText)
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    DropdownMenuItem(onClick = {
                        expanded = false
                        dropMenuText = "By Any"
                        queryType = "q"
                        clearPage()
                    }) { Text(text = "By Any") }
                    DropdownMenuItem(onClick = {
                        expanded = false
                        dropMenuText = "By Title"
                        queryType = "title"
                        clearPage()
                    }) { Text(text = "By Title") }
                    DropdownMenuItem(onClick = {
                        expanded = false
                        dropMenuText = "By Author"
                        queryType = "author"
                        clearPage()
                    }) { Text(text = "By Author") }
                }
                TextField(
                    value = queryString, onValueChange = { queryString = it
                        clearPage()},
                    label = { Text(text = "Your Search Text") },
                    placeholder = { Text(text = "e.g.: the lord of the rings") },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                )
                Button(onClick = { search = true },
                    modifier = Modifier.height(60.dp), enabled = !loading){
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                    )
                }
            }
        }
        if (search && queryString.isNotEmpty()){
            val docs by vm.getDocuments(queryType, queryString.trim()).observeAsState()
            when (docs) {
                null -> loading = true
                else -> loading = false
            }
            if (loading){
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            } else {
                ShowDocs(docs!!, navController)
            }
        }
    }
}

@Composable
private fun ShowDocs(docs: List<LibraryDocument>, navController: NavHostController){
    LazyColumn(){
        docs?.let {
            items(it){ i ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 4.dp)
                    .clickable(onClick = {
                        navController.currentBackStackEntry?.arguments= Bundle().apply {
                            putParcelable("doc", i)
                        }
                        navController.navigate("${Routes.Details.route}") //?title=${i.title_suggest},author=${i.author_name?.toTypedArray()},ISDNs=${i.isbn?.toTypedArray()}
                    })
                ) {
                    Column() {
                        Text(text = i.title_suggest, style = typography.h5)
                        if (!i.author_name.isNullOrEmpty()){
                            Row() {
                                Text(text = "Written by: ")
                                Text(text = i.author_name.toString().replace("[","").replace("]",""),
                                    fontStyle = FontStyle.Italic )
                            }
                        }
                    }
                }

            }
        }
    }
}