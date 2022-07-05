package com.example.quizapp_kotlin.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp_kotlin.R
import com.example.quizapp_kotlin.data.Quiz

class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private var stateName = itemView.findViewById<TextView>(R.id.stateTV)
    private var capitalName = itemView.findViewById<TextView>(R.id.capitalTV)

    fun bind(quiz: Quiz){
        stateName.text = quiz.stateName
        capitalName.text = quiz.capitalName
    }

    companion object{
        fun from(parent: ViewGroup): ListViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item, parent, false)
            return ListViewHolder(view)
        }
    }
}