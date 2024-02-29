package com.example.a.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [Factura::class],
    version = 1,
    exportSchema = false
)
abstract class FacturaDB : RoomDatabase() {

    abstract fun facturaDao(): FacturaDao


    companion object {
        @Volatile
        private var INSTANCE: FacturaDB? = null
        fun getDatabase(context: Context): FacturaDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FacturaDB::class.java,
                    "item_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}