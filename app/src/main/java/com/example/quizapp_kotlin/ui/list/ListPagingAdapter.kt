package com.example.quizapp_kotlin.ui.list

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.quizapp_kotlin.data.Quiz

class ListPagingAdapter: PagedListAdapter<Quiz, ListViewHolder>(DIFF_CALLBACK) {

    private lateinit var clickListener: ClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentState: Quiz? = getItem(position)
        if (currentState!=null){
            holder.bind(currentState)
            holder.itemView.setOnClickListener {
                clickListener.onItemClick(position, it)
            }
        }
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Quiz>(){
            override fun areItemsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
                return oldItem.stateName == newItem.capitalName
            }

            override fun areContentsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
                return oldItem==newItem
            }

        }
    }

    fun setItemClickListener(clickListener: ClickListener){
        this.clickListener = clickListener
    }

    public interface ClickListener{
        fun onItemClick(position: Int, view: View)
    }

    fun getStateAtPosition(position: Int): Quiz? {
        return getItem(position)
    }
}