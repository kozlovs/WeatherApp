package com.example.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.adapters.WeatherAdapter
import com.example.weatherapp.databinding.FragmentDaysBinding
import com.example.weatherapp.viewmodel.MainViewModel

class DaysFragment : Fragment() {
    private lateinit var binding: FragmentDaysBinding
    private lateinit var adapter: WeatherAdapter
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        updateCurrentCard()
    }

    private fun updateCurrentCard() {
        viewModel.liveDataList.observe(viewLifecycleOwner) {
            adapter.submitList(it.subList(1, it.size))
        }
    }

    private fun init() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        rcView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}