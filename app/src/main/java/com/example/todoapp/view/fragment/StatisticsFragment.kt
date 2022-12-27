package com.example.todoapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentStatisticsBinding
import com.example.todoapp.viewmodel.AppViewModelFactory
import com.example.todoapp.viewmodel.StatisticsViewModel
import com.example.todoapp.viewmodel.TaskListViewModel

class StatisticsFragment : Fragment() {

    private val binding: FragmentStatisticsBinding by lazy {
        FragmentStatisticsBinding.inflate(layoutInflater)
    }
    private val viewModel: StatisticsViewModel by lazy {
        AppViewModelFactory(requireContext()).create(StatisticsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initView() {
        viewModel.getTasksStatistics()
    }

    private fun initObserver() {
        viewModel.completePercent.observe(viewLifecycleOwner) {
            binding.tvCompleteLabel.apply {
                text = "$text ${String.format("%.2f", it)}%"
            }
        }
        viewModel.incompletePercent.observe(viewLifecycleOwner) {
            binding.tvActiveLabel.apply {
                text = "$text ${String.format("%.2f", it)}%"
            }
        }
    }
}