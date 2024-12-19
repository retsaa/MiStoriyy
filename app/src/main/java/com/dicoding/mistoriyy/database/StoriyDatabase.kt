package com.dicoding.mistoriyy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.mistoriyy.storiyy.ListStoriyItem

@Database(
    entities = [ListStoriyItem::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)
abstract class StoriyDatabase : RoomDatabase() {
    abstract fun storiyDao(): StoriyDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var instance: StoriyDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): StoriyDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    StoriyDatabase::class.java, "sutori_database"
                )
                    .fallbackToDestructiveMigrationFrom()
                    .build()
                    .also { instance = it }
            }
        }
    }
}