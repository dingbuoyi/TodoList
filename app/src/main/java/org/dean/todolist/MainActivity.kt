package org.dean.todolist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import org.dean.todolist.adapter.TodoAdapter
import org.dean.todolist.databinding.ActivityMainBinding
import org.dean.todolist.model.TodoModel
import org.dean.todolist.viewmodel.TodoViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: TodoViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel.apply {
            fetchTodoInfo(1).observe(getLifecycleOwner(), {
                todoAdapter.addTodoList(it)
            })

            failure.observe(getLifecycleOwner(), {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
            })
        }
        initViews()
    }

    private fun initViews() {
        todoAdapter = TodoAdapter(mutableListOf())
        binding.rvTodoItems.adapter = todoAdapter
        binding.rvTodoItems.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener {
            val todoTitle = binding.etTodoTitle.text.toString()
            if (todoTitle.isNotBlank()) {
                val todo = TodoModel.userInputModel(title = todoTitle)
                todoAdapter.addTodo(todo)
                binding.etTodoTitle.text.clear()
            }
        }
    }

    private fun getLifecycleOwner(): LifecycleOwner {
        return this@MainActivity
    }
}