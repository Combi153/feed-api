package me.chanmin.feed_api.repository

import me.chanmin.feed_api.domain.Feed
import me.chanmin.feed_api.domain.FeedId
import org.springframework.stereotype.Repository

@Repository
class InMemoryFeedRepository : FeedRepository {
    override fun save(feed: Feed): Feed {
        feed.id?.let {
            store[it] = feed
            return feed
        }
        val id = FeedId(++sequence)
        val newFeed = Feed(id, feed.content)
        store[id] = newFeed
        return newFeed
    }

    override fun findAll(): List<Feed> {
        return store.values.sortedBy { it.id?.value }.toList()
    }

    override fun findById(id: Long): Feed? {
        return store[FeedId(id)]
    }

    override fun deleteById(id: Long): Unit {
        store.remove(FeedId(id))
    }

    companion object {
        private val store = mutableMapOf<FeedId, Feed>()
        private var sequence: Long = 0
    }
}
