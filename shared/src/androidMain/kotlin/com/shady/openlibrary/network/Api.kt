package com.shady.openlibrary.network

import com.shady.openlibrary.entities.LibraryQuery

interface Api {
    suspend fun getDocuments(queryType: String, queryString: String): LibraryQuery
}