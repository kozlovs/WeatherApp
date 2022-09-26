package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.fragments.MainFragment
import org.json.JSONObject

const val API_KEY = "f6851a729736467aa33123131222409"
const val TAG = "MyLog"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeHolder, MainFragment.newInstance()).commit()
    }

    private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/current.json?" +
                "key=$API_KEY" +
                "&q=$name" +
                "&aqi=no"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val obj = JSONObject(it)
                val temp = obj.getJSONObject("current")
                Log.d(TAG, "Response: ${temp.getString("temp_c")}")
            },
            {
                Log.d(TAG, "Volley error: $it")
            })
        queue.add(stringRequest)
    }
}