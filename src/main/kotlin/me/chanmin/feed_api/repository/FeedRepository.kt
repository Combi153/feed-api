package me.chanmin.feed_api.repository

import me.chanmin.feed_api.domain.Feed

interface FeedRepository {
    fun save(feed: Feed): Feed
    fun findAll(): List<Feed>
    fun findById(id: Long): Feed?
    fun deleteById(id: Long): Unit
}
