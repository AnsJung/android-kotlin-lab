package com.example.cleanarchitectureex.data.model

data class GithubResponse(
    val incomplete_results: Boolean,
    val items: List<ItemDTO>,
    val total_count: Int
)