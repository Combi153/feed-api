package me.chanmin.feed_api.feed.service

import me.chanmin.feed_api.feed.domain.Feed
import me.chanmin.feed_api.feed.domain.FeedId
import me.chanmin.feed_api.feed.repository.FeedRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FeedService(@param:Qualifier("exposedFeedRepository") private val feedRepository: FeedRepository) {
    fun createFeed(content: String): Feed {
        val feed = Feed(null, content)
        return feedRepository.save(feed)
    }

    @Transactional(readOnly = true)
    fun getById(id: FeedId): Feed {
        val feed = feedRepository.findById(id)
        require(feed != null) { "Feed not found!" }
        return feed
    }

    @Transactional(readOnly = true)
    fun getAll(): List<Feed> {
        return feedRepository.findAll()
    }

    fun updateFeed(id: FeedId, content: String): Feed {
        val feed = getById(id)
        feed.updateContent(content)
        return feedRepository.save(feed)
    }

    fun deleteFeed(id: FeedId) {
        val feed = getById(id)
        feedRepository.deleteById(id)
    }
}
