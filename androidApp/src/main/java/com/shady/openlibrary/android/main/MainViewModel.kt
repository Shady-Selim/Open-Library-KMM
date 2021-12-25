package com.shady.openlibrary.android.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shady.openlibrary.entities.LibraryDocument
import com.shady.openlibrary.repository.LibraryRepo
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val repo = LibraryRepo()

    fun getDocuments(queryType: String, queryString: String): MutableLiveData<List<LibraryDocument>> {
        val data = MutableLiveData<List<LibraryDocument>>()
        viewModelScope.launch {
            try {
                data.postValue(repo.getDocuments(queryType, queryString))
            } catch (e: Exception) {
                Log.e("Get Docs:", "Error ${e.localizedMessage}")
            }
        }
        return data
    }
}