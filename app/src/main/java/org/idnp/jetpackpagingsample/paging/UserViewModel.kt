package org.idnp.jetpackpagingsample.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import org.idnp.jetpackpagingsample.entities.User
import org.idnp.jetpackpagingsample.model.UserRepository

class UserViewModel : ViewModel() {
    private val userRepository: UserRepository = UserRepository()

    fun items(): Flow<PagingData<User>> {

        val pager = Pager(
            PagingConfig(
                pageSize = 5,
                enablePlaceholders = false,
                prefetchDistance = 3)
        ) {
            UserPagingSource(userRepository)
        }.flow.cachedIn(viewModelScope)

        return pager

    }

}