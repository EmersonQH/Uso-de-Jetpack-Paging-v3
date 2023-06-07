package org.idnp.jetpackpagingsample.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.idnp.jetpackpagingsample.entities.User
import org.idnp.jetpackpagingsample.model.UserRepository
import java.io.IOException

class UserPagingSource(
    private val userRepository: UserRepository
) : PagingSource<Int, User>() {

    override val keyReuseSupported: Boolean = true

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val nextPageNumber = params.key ?: 1
            val users = userRepository.getUsers(nextPageNumber)

            return LoadResult.Page(
                data = users ?: listOf(),
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else 1,
                nextKey = nextPageNumber + 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}