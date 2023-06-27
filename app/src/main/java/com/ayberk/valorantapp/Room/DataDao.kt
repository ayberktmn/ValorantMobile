package com.ayberk.valorantapp.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ayberk.valorantapp.models.roomdata.FavoriAgends

@Dao
interface DataDao {

    @Query("SELECT * FROM FavoriAgends")
    fun getAll(): List<FavoriAgends>
    @Delete
    fun delete(advent: FavoriAgends)
    @Insert
    fun insert(advent: FavoriAgends)

    @Query("SELECT COUNT(*) FROM FavoriAgends WHERE uuid = :uuid")
    fun checkIfDataExists(uuid: String): Int
}