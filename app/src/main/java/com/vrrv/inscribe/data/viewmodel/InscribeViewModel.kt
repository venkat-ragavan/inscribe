package com.vrrv.inscribe.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vrrv.inscribe.data.InscribeDatabase
import com.vrrv.inscribe.data.models.InscribeData
import com.vrrv.inscribe.data.repository.InscribeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InscribeViewModel(application: Application) : AndroidViewModel(application) {

    private val inscribeDao = InscribeDatabase.getDatabase(application).inscribeDao()
    private val repository: InscribeRepository = InscribeRepository(inscribeDao)

    val getAllData = repository.getAllData
    val sortByHighPriority = repository.sortByHighPriority
    val sortByLowPriority = repository.sortByLowPriority

    fun insertData(inscribeData: InscribeData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(inscribeData)
        }
    }

    fun updateData(inscribeData: InscribeData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(inscribeData)
        }
    }

    fun deleteItem(inscribeData: InscribeData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(inscribeData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<InscribeData>>{
        return repository.searchDatabase(searchQuery)
    }

}