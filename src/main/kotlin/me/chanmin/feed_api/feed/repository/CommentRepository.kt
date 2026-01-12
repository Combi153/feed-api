package me.chanmin.feed_api.feed.repository

import me.chanmin.feed_api.common.CommonRepository
import me.chanmin.feed_api.feed.domain.Comment
import me.chanmin.feed_api.feed.domain.CommentId

interface CommentRepository : CommonRepository<Comment, CommentId> {
}
