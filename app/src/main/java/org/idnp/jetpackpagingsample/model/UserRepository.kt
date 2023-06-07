package org.idnp.jetpackpagingsample.model

import android.util.Log
import org.idnp.jetpackpagingsample.entities.User

class UserRepository {
    fun getUsers(nextPageNumber: Int): List<User> {

        Log.d("nextPageNumber:",nextPageNumber.toString())

        val users = arrayListOf<User>()
        var user: User
        val start: Int = 100 * nextPageNumber
        val end = start + 20

        for (i in start..end) {
            user = User(cui = i, firstName = "FirstName " + i, lastName = "SecondName " + i);
            users.add(user)
        }

        return users
    }
}

//data class ResponseUser(val users: List<User>, val nextPageNumber: Int)