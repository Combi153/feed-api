package me.chanmin.feed_api.feed.controller

import me.chanmin.feed_api.feed.domain.Comment
import me.chanmin.feed_api.feed.domain.CommentId
import me.chanmin.feed_api.feed.domain.FeedId
import me.chanmin.feed_api.feed.dto.CreateCommentDto
import me.chanmin.feed_api.feed.dto.DeleteCommentDto
import me.chanmin.feed_api.feed.dto.UpdateCommentDto
import me.chanmin.feed_api.feed.service.CommentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentController(private val commentService: CommentService) {

    @GetMapping()
    fun getCommentsByFeedId(@RequestParam("feedId") feedId: Long?): List<Comment> {
        if (feedId == null) {
            return emptyList()
        }
        return commentService.getCommentsByFeedId(FeedId(feedId))
    }

    @PostMapping()
    fun createComment(@RequestBody dto: CreateCommentDto): Comment {
        return commentService.createComment(
            feedId = FeedId(dto.feedId),
            authorName = dto.authorName,
            authorPassword = dto.authorPassword,
            content = dto.content
        )
    }

    @PatchMapping("/{id}")
    fun updateCommentContent(
        @PathVariable id: Long,
        @RequestBody dto: UpdateCommentDto
    ): Comment {
        return commentService.updateCommentContent(
            commentId = CommentId(id),
            newContent = dto.content,
            authorPassword = dto.authorPassword
        )
    }

    @DeleteMapping("/{id}")
    fun deleteComment(
        @PathVariable id: Long,
        @RequestBody dto: DeleteCommentDto
    ): Unit {
        return commentService.deleteComment(
            commentId = CommentId(id),
            authorPassword = dto.authorPassword
        )
    }
}
