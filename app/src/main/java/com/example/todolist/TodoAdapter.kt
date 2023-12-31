package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView




class TodoAdapter(

    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo : Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(TodoTitleTextView:TextView, isChecked : Boolean){
        if (isChecked){
          TodoTitleTextView.paintFlags= TodoTitleTextView.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            TodoTitleTextView.paintFlags = TodoTitleTextView.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]

        holder.itemView.apply {
            val todoTitleTextView: TextView = findViewById(R.id.TodoTitle)
            val cbDoneCheckBox: CheckBox = findViewById(R.id.cbDone)
            todoTitleTextView.text = curTodo.title
            cbDoneCheckBox.isChecked = curTodo.isChecked
            toggleStrikeThrough(todoTitleTextView,curTodo.isChecked )
            cbDoneCheckBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(todoTitleTextView, isChecked)
                curTodo.isChecked =!curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}