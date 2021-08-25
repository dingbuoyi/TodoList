package org.dean.todolist.adapter

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.dean.todolist.R
import org.dean.todolist.databinding.ItemTodoBinding
import org.dean.todolist.model.TodoModel

class TodoAdapter(
    private val todos: MutableList<TodoModel>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return TodoViewHolder(binding)
    }

    fun addTodo(todo: TodoModel) {
        todos.add(0, todo)
        notifyDataSetChanged()
    }

    fun addTodoList(todoList: List<TodoModel>) {
        todos.clear()
        todos.addAll(todoList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        with(holder.binding) {
            cbDone.setOnCheckedChangeListener(null)
            tvTodoTitle.text = currentTodo.title
            val isCompleted: Boolean = currentTodo.completed
            cbDone.isChecked = isCompleted
            changeBackground(holder.binding.root, isCompleted)
            toggleStrikeThrough(tvTodoTitle, isCompleted)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                currentTodo.completed = isChecked
                changeBackground(holder.binding.root, isChecked)
                toggleStrikeThrough(tvTodoTitle, isChecked)
            }
        }
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isCompleted: Boolean) {
        if (isCompleted) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
            tvTodoTitle.setTextColor(tvTodoTitle.context.getColor(R.color.gray))
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            tvTodoTitle.setTextColor(tvTodoTitle.context.getColor(R.color.black))
        }
    }

    private fun changeBackground(itemView: View, isCompleted: Boolean) {
        if (isCompleted) {
            itemView.setBackgroundColor(itemView.context.getColor(R.color.task_checked))
        } else {
            itemView.setBackgroundColor(itemView.context.getColor(R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}