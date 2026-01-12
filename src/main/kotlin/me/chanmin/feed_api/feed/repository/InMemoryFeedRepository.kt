package me.chanmin.feed_api.feed.repository

import me.chanmin.feed_api.feed.domain.Feed
import me.chanmin.feed_api.feed.domain.FeedId
import org.springframework.stereotype.Repository

@Repository
class InMemoryFeedRepository : FeedRepository {
    override fun save(entity: Feed): Feed {
        entity.id?.let {
            store[it] = entity
            return entity
        }
        val id = FeedId(++sequence)
        val newFeed = Feed(id, entity.content)
        store[id] = newFeed
        return newFeed
    }

    override fun findAll(): List<Feed> {
        return store.values.sortedBy { it.id?.value }.toList()
    }

    override fun findById(id: FeedId): Feed? {
        return store[id]
    }

    override fun deleteById(id: FeedId): Unit {
        store.remove(id)
    }

    companion object {
        private val store = mutableMapOf<FeedId, Feed>()
        private var sequence: Long = 0
    }
}
