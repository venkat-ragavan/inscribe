package com.vrrv.inscribe.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vrrv.inscribe.data.models.InscribeData

@Database(entities = [InscribeData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class InscribeDatabase : RoomDatabase() {

    abstract fun inscribeDao(): InscribeDao

    companion object {
        @Volatile
        private var INSTANCE: InscribeDatabase? = null

        fun getDatabase(context: Context): InscribeDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                InscribeDatabase::class.java, "inscribe_database"
            ).build()
    }

}