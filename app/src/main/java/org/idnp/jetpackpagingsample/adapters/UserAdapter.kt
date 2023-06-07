package org.idnp.jetpackpagingsample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import org.idnp.jetpackpagingsample.R
import org.idnp.jetpackpagingsample.entities.User

//class UserAdapter(diffCallback: DiffUtil.ItemCallback<User>) :
class UserAdapter : PagingDataAdapter<User, UserViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        getItem(position)?.let { holder.bind(it) }
//        val item = getItem(position)
//        holder.bind(item)

        val item = getItem(position)
        item?.let { user ->
            holder.bind(user)
        }

    }

}

class DiffUtilCallBack : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.cui == newItem.cui
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}