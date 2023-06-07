package org.idnp.jetpackpagingsample.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.idnp.jetpackpagingsample.R
import org.idnp.jetpackpagingsample.entities.User

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val cuiText = view.findViewById<TextView>(R.id.textViewCui) as TextView
    private val firstNameText = view.findViewById<TextView>(R.id.textViewFirstName) as TextView
    private val secondNameText = view.findViewById<TextView>(R.id.textViewSecondName) as TextView

    fun bind(user: User) {
        with(user) {
            cuiText.text = cui.toString()
            firstNameText.text = firstName.toString()
            secondNameText.text = lastName.toString()
        }
    }
}