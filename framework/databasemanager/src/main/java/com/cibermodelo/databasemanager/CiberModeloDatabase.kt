package com.cibermodelo.databasemanager

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cibermodelo.databasemanager.daos.UserDao
import com.cibermodelo.databasemanager.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CiberModeloDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "cibermodelo_db"
    }

    abstract fun userDao(): UserDao

}