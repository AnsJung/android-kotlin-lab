package com.example.cleanarchitectureex.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.cleanarchitectureex.domain.model.Item
import com.example.cleanarchitectureex.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val githubUseCase: GithubUseCase) : ViewModel() {
    private val searchResults = MutableStateFlow("")

    val repositories: Flow<PagingData<Item>> =
        searchResults
            .filter { it.isNotEmpty() }
            .flatMapLatest { query ->
                delay(3000)
                githubUseCase.getRepositories(query)
            }

    fun getRepositories(query: String) {
        searchResults.value = query
    }
}