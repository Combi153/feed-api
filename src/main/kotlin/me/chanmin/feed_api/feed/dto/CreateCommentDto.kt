package me.chanmin.feed_api.feed.dto

data class CreateCommentDto(
    val feedId: Long,
    val authorName: String,
    val authorPassword: String,
    val content: String
)
