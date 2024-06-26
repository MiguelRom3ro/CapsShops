package com.miguelrr.capsshop.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miguelrr.capsshop.data.database.entity.CapEntity

@Dao
interface CapDao {

    @Query("SELECT * FROM cap_table WHERE category = 'NBA'")
    suspend fun getNBACaps() : List<CapEntity>

    @Query("SELECT * FROM cap_table WHERE category = 'NFL'")
    suspend fun getNFLCaps() : List<CapEntity>

    @Query("SELECT * FROM cap_table WHERE category = 'MBL'")
    suspend fun getMBLCaps() : List<CapEntity>

    @Query("SELECT * FROM cap_table WHERE name LIKE '%' || :query || '%'")
    suspend fun getSearchCaps(query: String) : List<CapEntity>

    @Query("SELECT * FROM cap_table WHERE id LIKE :id")
    suspend fun getCapById(id : Int) : CapEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(caps : List<CapEntity>)

    @Query("DELETE FROM cap_table")
    suspend fun deleteAllCaps()

    @Query("SELECT price FROM cap_table WHERE id LIKE :id")
    suspend fun getPriceCap(id: Int) : Double

}