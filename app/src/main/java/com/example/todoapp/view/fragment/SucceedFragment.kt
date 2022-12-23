package com.example.todoapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todoapp.databinding.FragmentSucceedBinding

class SucceedFragment : Fragment(), View.OnClickListener {

    private val binding: FragmentSucceedBinding by lazy {
        FragmentSucceedBinding.inflate(layoutInflater)
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
    }

    private fun initView() {
        binding.btnComplete.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val id = view?.id
        if (id == binding.btnComplete.id) {
            findNavController().navigate(
                SucceedFragmentDirections.actionSucceedFragmentToTodoListFragment()
            )
        }
    }
}