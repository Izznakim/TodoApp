package com.dicoding.todoapp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.ui.list.TaskActivity
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action [SOLVED]

        val id = intent.getIntExtra(TASK_ID, 0)

        val edTitle = findViewById<EditText>(R.id.detail_ed_title)
        val edDesc = findViewById<EditText>(R.id.detail_ed_description)
        val edDate = findViewById<EditText>(R.id.detail_ed_due_date)
        val btnDelete = findViewById<Button>(R.id.btn_delete_task)

        val factory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, factory)[DetailTaskViewModel::class.java]

        detailTaskViewModel.setTaskId(id)
        detailTaskViewModel.task.observe(this) {
            if (it!=null) {
                edTitle.setText(it.title)
                edDesc.setText(it.description)
                edDate.setText(DateConverter.convertMillisToString(it.dueDateMillis))
            }else{
                finish()
            }
        }
        btnDelete.setOnClickListener {
            detailTaskViewModel.deleteTask()
        }
    }
}