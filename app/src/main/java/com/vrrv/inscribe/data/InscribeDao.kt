package com.vrrv.inscribe.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vrrv.inscribe.data.models.InscribeData

@Dao
interface InscribeDao {

    @Query("SELECT * FROM inscribe_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<InscribeData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(inscribeData: InscribeData)

    @Update
    suspend fun updateData(inscribeData: InscribeData)

    @Delete
    suspend fun deleteItem(inscribeData: InscribeData)

    @Query("DELETE FROM inscribe_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM inscribe_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<InscribeData>>

    @Query("SELECT * FROM inscribe_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(): LiveData<List<InscribeData>>

    @Query("SELECT * FROM inscribe_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): LiveData<List<InscribeData>>

}