package com.dicoding.mistoriyy.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.mistoriyy.storiyy.ListStoriyItem


@Dao
interface StoriyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(story: List<ListStoriyItem>)

    @Query("SELECT * FROM storiy")
    fun getAllStory(): PagingSource<Int, ListStoriyItem>

    @Query("DELETE FROM storiy")
    suspend fun deleteAll()
}