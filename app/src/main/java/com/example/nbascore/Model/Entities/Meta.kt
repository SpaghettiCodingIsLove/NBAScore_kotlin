package com.example.nbascore.Model.Entities

data class Meta(
        val total_pages: Int,
        val current_pages: Int,
        val next_page: Int?,
        val per_page: Int,
        val total_count: Int
) {
}