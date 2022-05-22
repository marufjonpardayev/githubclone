package dev.davlatov.githubproject.data.model.search

import dev.davlatov.githubproject.data.model.search.Item

data class SearchRepository(
    val incomplete_results: Boolean,
    val items: List<Item>? = null,
    val total_count: Int,
)