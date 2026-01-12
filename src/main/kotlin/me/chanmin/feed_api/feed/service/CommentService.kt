package me.chanmin.feed_api.feed.service

import me.chanmin.feed_api.feed.domain.Comment
import me.chanmin.feed_api.feed.domain.CommentId
import me.chanmin.feed_api.feed.domain.FeedId
import me.chanmin.feed_api.feed.repository.CommentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommentService(private val commentRepository: CommentRepository) {
    fun createComment(
        feedId: FeedId,
        authorName: String,
        authorPassword: String,
        content: String
    ): Comment {
        val comment = Comment(
            feedId = feedId,
            authorName = authorName,
            authorPassword = authorPassword,
            content = content
        )

        return commentRepository.save(comment)
    }

    @Transactional(readOnly = true)
    fun getCommentsByFeedId(feedId: FeedId): List<Comment> {
        return commentRepository.findByFeedId(feedId)
    }

    fun updateCommentContent(
        commentId: CommentId,
        newContent: String,
        authorPassword: String
    ): Comment {
        val comment = commentRepository.findById(commentId)
            ?: throw IllegalArgumentException("Comment not found!")
        val updatedComment = comment.updateContent(newContent, authorPassword)
        return commentRepository.save(updatedComment)
    }

    fun deleteComment(commentId: CommentId, authorPassword: String): Unit {
        val comment = commentRepository.findById(commentId)
            ?: throw IllegalArgumentException("Comment not found!")
        comment.validateAuthorPassword(authorPassword)
        commentRepository.deleteById(commentId)
    }
}
