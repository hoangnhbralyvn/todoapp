package com.example.todoapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.TodoItemBinding
import com.example.todoapp.model.Task

class TaskAdapter(
    private var listener: OnClickListener? = null
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    class TaskViewHolder(
        private val binding: TodoItemBinding,
        private val listener: OnClickListener?
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindData(task: Task) {
            binding.title.text = task.title
            binding.description.text = task.description
            binding.checkBox.setImageResource(
                if (task.checked) {
                    R.drawable.ic_check_box
                } else {
                    R.drawable.ic_check_box_blank
                }
            )
            binding.root.setOnClickListener {
                listener?.onItemClick(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TaskViewHolder(
        TodoItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false),
        ), listener
    )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    fun interface OnClickListener {
        fun onItemClick(task: Task)
    }
}