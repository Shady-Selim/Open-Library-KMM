package com.shady.openlibrary.entities

import kotlinx.serialization.Serializable

@Serializable
data class LibraryQuery (
    val start: Int,
    val num_found: Int,
    val docs : List<LibraryDocument>
    )