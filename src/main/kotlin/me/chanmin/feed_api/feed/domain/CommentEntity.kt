package me.chanmin.feed_api.feed.domain

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object CommentEntity : LongIdTable(name = "comment") {
    val feedId = long("feed_id").references(FeedEntity.id)
    val authorName = varchar("author_name", 100)
    val authorPassword = varchar("author_password", 100)
    val content = varchar("content", 100)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}
