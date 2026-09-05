package com.example.achievements14_b

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.achievements14_b.Compose.AchievementScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initObserver()

        setContent {
            AchievementScreen(viewModel)
        }
    }

    private fun initObserver() {
        viewModel.achievementsSuccessLiveData.observe(this){ response ->
            Toast.makeText(this, "Success = {$response.toString()}", Toast.LENGTH_LONG).show()
        }
        viewModel.achievementsErrorLiveData.observe(this){ e ->
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
}
