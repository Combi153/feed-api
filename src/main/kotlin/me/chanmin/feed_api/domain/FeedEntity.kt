package me.chanmin.feed_api.domain

import org.jetbrains.exposed.dao.id.LongIdTable

object FeedEntity : LongIdTable(name = "feed") {
    val content = varchar("content", 255)
}
