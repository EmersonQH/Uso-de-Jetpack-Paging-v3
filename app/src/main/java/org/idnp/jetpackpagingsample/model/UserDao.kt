package org.idnp.jetpackpagingsample.model

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.idnp.jetpackpagingsample.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Query("SELECT * FROM users")
    fun pagingSource(query: String): PagingSource<Int, User>

    @Query("DELETE FROM users")
    suspend fun clearAll()
}