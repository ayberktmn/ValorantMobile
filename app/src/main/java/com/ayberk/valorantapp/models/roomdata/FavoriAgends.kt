package com.ayberk.valorantapp.models.roomdata

import android.provider.ContactsContract.CommonDataKinds.Identity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ayberk.valorantapp.models.Agends.Ability
import com.ayberk.valorantapp.models.Agends.Role
import com.ayberk.valorantapp.models.Agends.VoiceLine


@Entity
data class FavoriAgends(

    @PrimaryKey val uuid: String,
    @ColumnInfo(name = "displayIcon")
    val displayIcon: String,
    @ColumnInfo(name = "displayName")
    val displayName: String,

    )