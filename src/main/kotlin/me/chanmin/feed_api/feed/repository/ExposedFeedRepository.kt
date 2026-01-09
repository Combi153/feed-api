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
            FeedEntity.update({ FeedEntity.id eq it.value }) {
                it[content] = entity.content
            }
            return FeedEntity.selectAll().where { FeedEntity.id eq it.value }.first().let { row ->
                Feed(id = FeedId(row[FeedEntity.id].value), content = row[FeedEntity.content])
            }
        }

        val id = FeedEntity.insertAndGetId {
            it[content] = entity.content
        }
        return FeedEntity.selectAll().where { FeedEntity.id eq id.value }.first().let {
            Feed(id = FeedId(it[FeedEntity.id].value), content = it[FeedEntity.content])
        }
    }

    override fun findAll(): List<Feed> {
        return FeedEntity.selectAll().map {
            Feed(
                id = FeedId(it[FeedEntity.id].value),
                content = it[FeedEntity.content]
            )
        }
    }

    override fun findById(id: Long): Feed? {
        return FeedEntity.selectAll().where { FeedEntity.id eq id }.firstOrNull()?.let {
            Feed(
                id = FeedId(it[FeedEntity.id].value),
                content = it[FeedEntity.content]
            )
        }
    }

    override fun deleteById(id: Long) {
        FeedEntity.deleteWhere { FeedEntity.id eq id }
    }
}
