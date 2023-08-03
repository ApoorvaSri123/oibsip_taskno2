package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var etTodoTitle: EditText
    private lateinit var DeleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        val recyclerViewTodo: RecyclerView = findViewById(R.id.recyclerViewTodo)

        recyclerViewTodo.adapter = todoAdapter
        recyclerViewTodo.layoutManager = LinearLayoutManager(this)

        etTodoTitle = findViewById(R.id.etTodoTitle)
        DeleteButton = findViewById(R.id.DeleteButton)

        val btnTask: Button = findViewById(R.id.btnTask)
        btnTask.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

        DeleteButton.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}