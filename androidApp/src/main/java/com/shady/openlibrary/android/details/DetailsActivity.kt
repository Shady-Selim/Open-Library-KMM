package com.shady.openlibrary.android.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.shady.openlibrary.entities.LibraryDocument


@Composable
fun DetailsActivity(doc: LibraryDocument?) {
    Text(text = "Hello ${doc?.author_name}!")
}