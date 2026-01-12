package me.chanmin.feed_api.feed.repository

import me.chanmin.feed_api.feed.domain.Feed
import me.chanmin.feed_api.feed.domain.FeedEntity
import me.chanmin.feed_api.feed.domain.FeedId
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository

@Repository
class ExposedFeedRepository : FeedRepository {
    override fun save(entity: Feed): Feed {
        entity.id?.let {
            FeedEntity.update({ FeedEntity.id eq it.value }) { row ->
                row[content] = entity.content
            }
            return findByIdOrThrow(it)
        }

        val id = FeedEntity.insertAndGetId {
            it[content] = entity.content
        }
        return findByIdOrThrow(FeedId(id.value))
    }

    private fun findByIdOrThrow(id: FeedId): Feed {
        return findById(id) ?: throw IllegalStateException("Failed to retrieve updated Feed entity.")
    }

    override fun findAll(): List<Feed> {
        return FeedEntity.selectAll().map {
            Feed(
                id = FeedId(it[FeedEntity.id].value),
                content = it[FeedEntity.content]
            )
        }
    }

    override fun findById(id: FeedId): Feed? {
        return FeedEntity.selectAll().where { FeedEntity.id eq id.value }.firstOrNull()?.let {
            Feed(
                id = FeedId(it[FeedEntity.id].value),
                content = it[FeedEntity.content]
            )
        }
    }

    override fun deleteById(id: FeedId) {
        FeedEntity.deleteWhere { FeedEntity.id eq id.value }
    }
}
