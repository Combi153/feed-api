package me.chanmin.feed_api.repository

import me.chanmin.feed_api.domain.Feed
import org.springframework.stereotype.Repository

@Repository
class InMemoryFeedRepository : FeedRepository {
    override fun save(feed: Feed): Feed {
        feed.id?.let {
            store[it] = feed
            return feed
        }
        val id = ++sequence
        val newFeed = Feed(id, feed.content)
        store[id] = newFeed
        return newFeed
    }

    override fun findAll(): List<Feed> {
        return store.values.sortedBy { it.id }.toList()
    }

    override fun findById(id: Long): Feed? {
        return store[id]
    }

    override fun deleteById(id: Long): Unit {
        store.remove(id)
    }

    companion object {
        private val store = mutableMapOf<Long, Feed>()
        private var sequence: Long = 0
    }
}
