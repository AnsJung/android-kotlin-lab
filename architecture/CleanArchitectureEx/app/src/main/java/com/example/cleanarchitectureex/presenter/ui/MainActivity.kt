package com.example.cleanarchitectureex.presenter.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.cleanarchitectureex.R
import com.example.cleanarchitectureex.databinding.ActivityMainBinding
import com.example.cleanarchitectureex.presenter.ui.adapter.FooterLoadStateAdapter
import com.example.cleanarchitectureex.presenter.ui.adapter.MainAdapter
import com.example.cleanarchitectureex.presenter.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainAdapter by lazy { MainAdapter() }
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
            .apply {
                view = this@MainActivity
                rv.adapter = mainAdapter.withLoadStateFooter(footer = FooterLoadStateAdapter())
            }

        initObserve()
        initListener()
        viewModel.getRepositories("mvvm")
    }

    private fun initListener() {
        mainAdapter.addLoadStateListener { loadState ->
            val errorState = loadState.refresh as? LoadState.Error
                ?: loadState.append as? LoadState.Error
            errorState?.let {
                showErrorDialog(it.error.message ?: "Unknown Error")
            }
        }
    }

    private fun initObserve() {
        lifecycleScope.launch {
            viewModel.repositories.collect {
                mainAdapter.submitData(it)
            }
        }
    }

    private fun showErrorDialog(msg: String) {
        var dialog = AlertDialog.Builder(this)
            .setTitle("에러 발생")
            .setMessage(msg)
        dialog.show()
    }
}