package com.example.todoapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoapp.databinding.FragmentCreateTodoBinding
import com.example.todoapp.model.Task
import com.example.todoapp.viewmodel.AppViewModelFactory
import com.example.todoapp.viewmodel.CreateTaskViewModel

class CreateTodoFragment : Fragment(), View.OnClickListener {

    private val binding: FragmentCreateTodoBinding by lazy { FragmentCreateTodoBinding.inflate(layoutInflater) }
    private val viewModel: CreateTaskViewModel by lazy {
        AppViewModelFactory(requireContext()).create(CreateTaskViewModel::class.java)
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
        binding.fabConfirm.setOnClickListener(this)
    }

    private fun initObserver() {
        viewModel.isCreateSucceed.observe(viewLifecycleOwner) {
            binding.tvError.visibility = View.GONE
            binding.edtTitle.text.clear()
            binding.edtDescription.text.clear()
            findNavController().navigate(
                CreateTodoFragmentDirections.actionCreateTodoFragmentToSucceedFragment()
            )
        }
    }

    override fun onClick(view: View?) {
        val id = view?.id
        if (id == binding.fabConfirm.id) {
            onCreateButtonClick()
        }
    }

    private fun onCreateButtonClick() {
        val title = binding.edtTitle.text.toString()
        val description = binding.edtDescription.text.toString()
        if (title.isEmpty() || description.isEmpty()) {
            binding.tvError.visibility = View.VISIBLE
            return
        }
        val task = Task(title = title, description = description, checked = false)
        viewModel.createNewTask(task)
    }
}