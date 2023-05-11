package com.mahapp1397.borutoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mahapp1397.borutoapp.data.local.HeroDao
import com.mahapp1397.borutoapp.data.local.HeroRemoteKeysDao
import com.mahapp1397.borutoapp.domain.models.Hero
import com.mahapp1397.borutoapp.domain.models.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDatabase: RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeysDao
}