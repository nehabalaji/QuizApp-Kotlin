package com.example.quizapp_kotlin.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp_kotlin.R
import com.example.quizapp_kotlin.data.Quiz
import kotlinx.android.synthetic.main.list_item.view.*

class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private var stateName = itemView.stateTV
    private var capitalName = itemView.capitalTV

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