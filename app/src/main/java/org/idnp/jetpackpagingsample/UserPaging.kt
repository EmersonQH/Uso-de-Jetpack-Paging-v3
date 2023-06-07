package org.idnp.jetpackpagingsample

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.idnp.jetpackpagingsample.entities.User
import org.idnp.jetpackpagingsample.model.UserRepository


class UserPaging (
    private val repo: UserRepository
    ) : PagingSource<Int, User>() {

        override fun getRefreshKey(state: PagingState<Int, User>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            }
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
            return try {
                val nextPageNumber = params.key ?: 1
                val response = repo.getUsers(nextPageNumber)

                LoadResult.Page(
                    data = response.users,
                    prevKey = null,
                    nextKey = if (response.users.isNotEmpty()) response.page + 1 else null
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
    }

