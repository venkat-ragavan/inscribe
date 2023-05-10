package com.vrrv.inscribe.data.repository

import androidx.lifecycle.LiveData
import com.vrrv.inscribe.data.InscribeDao
import com.vrrv.inscribe.data.models.InscribeData

class InscribeRepository(private val inscribeDao: InscribeDao) {

    val getAllData: LiveData<List<InscribeData>> = inscribeDao.getAllData()
    val sortByHighPriority: LiveData<List<InscribeData>> = inscribeDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<InscribeData>> = inscribeDao.sortByLowPriority()

    suspend fun insertData(inscribeData: InscribeData){
        inscribeDao.insertData(inscribeData)
    }

    suspend fun updateData(inscribeData: InscribeData){
        inscribeDao.updateData(inscribeData)
    }

    suspend fun deleteItem(inscribeData: InscribeData){
        inscribeDao.deleteItem(inscribeData)
    }

    suspend fun deleteAll(){
        inscribeDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<InscribeData>> {
        return inscribeDao.searchDatabase(searchQuery)
    }

}