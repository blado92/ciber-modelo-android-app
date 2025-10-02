package com.cibermodelo.databasemanager.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cibermodelo.databasemanager.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: List<UserEntity>)

    @Query("DELETE FROM user")
    fun deleteUser(): Int

    @Query("SELECT * FROM user")
    fun selectUser(): List<UserEntity>

}