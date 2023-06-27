package com.ayberk.valorantapp.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ayberk.valorantapp.models.roomdata.FavoriAgends

@Database(entities = [FavoriAgends::class], version = 2)
abstract class RoomDataBase : RoomDatabase() {
    abstract fun dataDao(): DataDao

}
