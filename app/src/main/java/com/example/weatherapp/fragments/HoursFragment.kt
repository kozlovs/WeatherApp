package com.example.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapters.WeatherAdapter
import com.example.weatherapp.adapters.WeatherModel
import com.example.weatherapp.databinding.FragmentHoursBinding
import com.example.weatherapp.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject

class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        updateCurrentCard()
    }

    private fun updateCurrentCard() = with(binding) {
        viewModel.liveDataCurrent.observe(viewLifecycleOwner) {
            adapter.submitList(getHoursList(it))
        }
    }

    private fun init() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter(null)
        rcView.adapter = adapter
    }

    private fun getHoursList(item: WeatherModel): List<WeatherModel> {
        val list = ArrayList<WeatherModel>()
        val hoursArray = JSONArray(item.hours)

        for (i in 0 until hoursArray.length()) {
            val jsonHour = hoursArray.getJSONObject(i)
            val hour = WeatherModel(
                "",
                jsonHour.getString("time"),
                jsonHour.getJSONObject("condition").getString("text"),
                jsonHour.getJSONObject("condition").getString("icon"),
                jsonHour.getString("temp_c"),
                "",
                "",
                ""
            )
            list += hour
        }
        return list
    }

    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}