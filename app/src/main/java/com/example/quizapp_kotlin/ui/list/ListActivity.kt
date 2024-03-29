package com.example.quizapp_kotlin.ui.list

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp_kotlin.R
import com.example.quizapp_kotlin.data.Quiz
import com.example.quizapp_kotlin.database.QuizRepository
import com.example.quizapp_kotlin.ui.add.AddActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class ListActivity : AppCompatActivity() {

    val EXTRA_STATE_NAME = "extra_state_name_to_be_updated"
    val EXTRA_CAPITAL_NAME = "extra_capital_name_to_be_updated"
    val EXTRA_STATE_ID = "extra_state_id_to_be_updated"
    val UPDATE_STATE_REQUEST_CODE = 1
    val NEW_STATE_REQUEST_CODE = 2
    private lateinit var sharedPreferences: SharedPreferences

    lateinit var listViewModel: ListViewModel
    lateinit var quiz: Quiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, NEW_STATE_REQUEST_CODE)
        }

        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val s: String? = sharedPreferences.getString("sort_order", "ID")
        if (s!=null){
            listViewModel.changeSortOrder(s)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView2)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val listPagingAdapter = ListPagingAdapter()
        recyclerView.adapter = listPagingAdapter

        listViewModel.statesList.observe(this, Observer {
            listPagingAdapter.submitList(it)
        })

        val clickListener: ListPagingAdapter.ClickListener =
            object : ListPagingAdapter.ClickListener {
                override fun onItemClick(position: Int, view: View) {
                    val currentState: Quiz? = listPagingAdapter.getStateAtPosition(position)
                    if (currentState != null) {
                        launchUpdateActivity(currentState)
                    }
                }
            }

        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout1)
        val snackbar =
            Snackbar.make(constraintLayout, "State Deleted", BaseTransientBottomBar.LENGTH_LONG)
                .setAction("UNDO") {
                    listViewModel.insertState(quiz)
                }

        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position: Int = viewHolder.adapterPosition
                quiz = listPagingAdapter.getStateAtPosition(position)!!
                listViewModel.deleteState(quiz)
                snackbar.show()
            }
        })

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

        itemTouchHelper.attachToRecyclerView(recyclerView)
        listPagingAdapter.setItemClickListener(clickListener)

    }

    fun launchUpdateActivity(currentState: Quiz){
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra(EXTRA_STATE_ID, currentState.id)
        intent.putExtra(EXTRA_STATE_NAME, currentState.stateName)
        intent.putExtra(EXTRA_CAPITAL_NAME, currentState.capitalName)
        startActivityForResult(intent, UPDATE_STATE_REQUEST_CODE)
    }

    private var listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, s ->
        if (s.equals("sort_order")){
            val string =  sharedPreferences.getString(s, "stateID")
            if (string!=null){
                listViewModel.changeSortOrder(string)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}