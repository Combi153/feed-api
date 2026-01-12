package me.chanmin.feed_api.feed.repository

import me.chanmin.feed_api.feed.domain.Comment
import me.chanmin.feed_api.feed.domain.CommentEntity
import me.chanmin.feed_api.feed.domain.CommentId
import me.chanmin.feed_api.feed.domain.FeedId
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository

@Repository
class ExposedCommentRepository : CommentRepository {
    override fun save(entity: Comment): Comment {
        entity.id?.let {
            CommentEntity.update({ CommentEntity.id eq it.value }) { row ->
                row[content] = entity.content
            }
            return findByIdOrThrow(it)
        }
        val id = CommentEntity.insertAndGetId {
            it[feedId] = entity.feedId.value
            it[authorName] = entity.authorName
            it[authorPassword] = entity.authorPassword
            it[content] = entity.content
        }
        return findByIdOrThrow(CommentId(id.value))
    }

    private fun findByIdOrThrow(id: CommentId): Comment {
        return findById(id) ?: throw IllegalStateException("Failed to retrieve updated Comment entity.")
    }


    override fun findAll(): List<Comment> {
        return CommentEntity.selectAll().map {
            Comment(
                id = CommentId(it[CommentEntity.id].value),
                feedId = FeedId(it[CommentEntity.feedId]),
                authorName = it[CommentEntity.authorName],
                authorPassword = it[CommentEntity.authorPassword],
                content = it[CommentEntity.content]
            )
        }
    }

    override fun findById(id: CommentId): Comment? {
        return CommentEntity.selectAll().where { CommentEntity.id eq id.value }.firstOrNull()?.let { row ->
            Comment(
                id = CommentId(row[CommentEntity.id].value),
                feedId = FeedId(row[CommentEntity.feedId]),
                authorName = row[CommentEntity.authorName],
                authorPassword = row[CommentEntity.authorPassword],
                content = row[CommentEntity.content]
            )
        }
    }

    override fun deleteById(id: CommentId) {
        CommentEntity.deleteWhere { CommentEntity.id eq id.value }
    }

    override fun findByFeedId(feedId: FeedId): List<Comment> {
        return CommentEntity.selectAll().where { CommentEntity.feedId eq feedId.value }.map {
            Comment(
                id = CommentId(it[CommentEntity.id].value),
                feedId = FeedId(it[CommentEntity.feedId]),
                authorName = it[CommentEntity.authorName],
                authorPassword = it[CommentEntity.authorPassword],
                content = it[CommentEntity.content]
            )
        }
    }
}
