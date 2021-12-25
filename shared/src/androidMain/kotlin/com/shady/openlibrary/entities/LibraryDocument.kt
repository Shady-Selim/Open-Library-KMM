package com.shady.openlibrary.entities

import android.os.Parcelable
import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize

@Serializable
@Parcelize
data class LibraryDocument (
    val title_suggest: String,
    val cover_i: Int? = 0,
    val isbn: List<String>? = emptyList(),
    val author_name: List<String>? = emptyList()
    ): Parcelable