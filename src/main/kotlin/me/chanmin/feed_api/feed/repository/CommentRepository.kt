package me.chanmin.feed_api.feed.repository

import me.chanmin.feed_api.common.CommonRepository
import me.chanmin.feed_api.feed.domain.Comment
import me.chanmin.feed_api.feed.domain.CommentId
import me.chanmin.feed_api.feed.domain.FeedId

interface CommentRepository : CommonRepository<Comment, CommentId> {
    fun findByFeedId(feedId: FeedId): List<Comment>
}
