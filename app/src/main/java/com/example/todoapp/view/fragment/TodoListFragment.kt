package com.example.todoapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoapp.databinding.FragmentTodoListBinding
import com.example.todoapp.model.Task
import com.example.todoapp.view.adapter.TaskAdapter
import com.example.todoapp.viewmodel.AppViewModelFactory
import com.example.todoapp.viewmodel.TaskListViewModel

class TodoListFragment : Fragment(), View.OnClickListener {

    private val binding: FragmentTodoListBinding by lazy { FragmentTodoListBinding.inflate(layoutInflater) }
    private val adapter: TaskAdapter by lazy { TaskAdapter(this::onTaskClick) }
    private val viewModel: TaskListViewModel by lazy {
        AppViewModelFactory(requireContext()).create(TaskListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    private fun initView() {
        viewModel.getTaskList()
        binding.rvTasks.adapter = adapter
        binding.fabAdd.setOnClickListener(this)
    }

    private fun initObserver() {
        viewModel.tasks.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.rvTasks.visibility = View.GONE
                binding.tvEmptyMsg.visibility = View.VISIBLE
            }
            adapter.submitList(it)
        }
    }

    private fun onTaskClick(task: Task) {
        viewModel.updateTask(task.invertedCopy())
    }

    override fun onClick(view: View?) {
        val id = view?.id
        if (id == binding.fabAdd.id) {
            findNavController().navigate(
                TodoListFragmentDirections.actionTodoListFragmentToCreateTodoFragment()
            )
        }
    }
}