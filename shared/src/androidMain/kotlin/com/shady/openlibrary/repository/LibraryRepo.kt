package com.shady.openlibrary.repository

import com.shady.openlibrary.network.ApiImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LibraryRepo {
    suspend fun getDocuments(queryType: String, queryString: String) = withContext(Dispatchers.Default) {
        val response = ApiImpl().getDocuments(queryType, queryString)
        if (response.docs.isNotEmpty())
            response.docs
        else
            emptyList()
    }
}