package me.chanmin.feed_api.feed.repository

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import me.chanmin.feed_api.feed.domain.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class ExposedCommentRepositoryTest(private val commentRepository: ExposedCommentRepository) : BehaviorSpec({

    beforeSpec {
        transaction {
            SchemaUtils.create(FeedEntity, CommentEntity)
        }
    }

    afterEach {
        transaction {
            CommentEntity.deleteAll()
            FeedEntity.deleteAll()
        }
    }

    afterSpec {
        transaction {
            SchemaUtils.drop(CommentEntity, FeedEntity)
        }
    }

    given("새로운 댓글 정보가 주어졌을 때") {
        val feedId = FeedEntity.insertAndGetId {
            it[FeedEntity.content] = "Sample feed content"
        }.value
        val authorName = "commenter"
        val authorPassword = "commentpass"
        val content = "This is a comment"

        `when`("댓글을 저장하면") {
            val comment = commentRepository.save(
                Comment(
                    feedId = FeedId(feedId),
                    authorName = authorName,
                    authorPassword = authorPassword,
                    content = content
                )
            )

            then("ID가 할당된 댓글이 반환된다") {
                comment.id.shouldNotBeNull()
                comment.feedId.value shouldBe feedId
                comment.authorName shouldBe authorName
                comment.authorPassword shouldBe authorPassword
                comment.content shouldBe content
            }
        }
    }

    given("기존 댓글이 저장되어 있을 때") {
        val feedId = FeedEntity.insertAndGetId {
            it[FeedEntity.content] = "Another sample feed content"
        }.value
        val savedComment = commentRepository.save(
            Comment(
                feedId = FeedId(feedId),
                authorName = "originalCommenter",
                authorPassword = "originalPass",
                content = "Original comment content"
            )
        )

        `when`("댓글 내용을 수정하면") {
            val updatedContent = "Updated comment content"
            val updatedComment = commentRepository.save(
                savedComment.updateContent(newContent = updatedContent, authorPassword = savedComment.authorPassword)
            )

            then("수정된 내용이 반영된다") {
                updatedComment.id shouldBe savedComment.id
                updatedComment.content shouldBe updatedContent
                updatedComment.authorName shouldBe savedComment.authorName
                updatedComment.authorPassword shouldBe savedComment.authorPassword
            }
        }

        `when`("ID로 댓글을 조회하면") {
            val foundComment = commentRepository.findById(savedComment.id!!)

            then("해당 댓글이 반환된다") {
                foundComment.shouldNotBeNull()
                foundComment.id shouldBe savedComment.id
                foundComment.feedId shouldBe savedComment.feedId
                foundComment.authorName shouldBe savedComment.authorName
                foundComment.authorPassword shouldBe savedComment.authorPassword
                foundComment.content shouldBe savedComment.content
            }
        }

        `when`("댓글을 삭제하면") {
            val commentId = savedComment.id!!
            commentRepository.deleteById(commentId)

            then("해당 댓글을 찾을 수 없다") {
                val foundComment = commentRepository.findById(commentId)
                foundComment.shouldBeNull()
            }
        }
    }

    given("여러 댓글이 저장되어 있을 때") {
        val feedId = FeedEntity.insertAndGetId {
            it[FeedEntity.content] = "Feed with multiple comments"
        }.value
        val anotherFeedId = FeedEntity.insertAndGetId {
            it[FeedEntity.content] = "Another feed"
        }.value

        commentRepository.save(
            Comment(
                feedId = FeedId(feedId),
                authorName = "commenter1",
                authorPassword = "pass1",
                content = "First comment"
            )
        )
        commentRepository.save(
            Comment(
                feedId = FeedId(feedId),
                authorName = "commenter2",
                authorPassword = "pass2",
                content = "Second comment"
            )
        )
        commentRepository.save(
            Comment(
                feedId = FeedId(feedId),
                authorName = "commenter3",
                authorPassword = "pass3",
                content = "Third comment"
            )
        )
        commentRepository.save(
            Comment(
                feedId = FeedId(anotherFeedId),
                authorName = "commenter4",
                authorPassword = "pass4",
                content = "Comment for another feed"
            )
        )

        `when`("모든 댓글을 조회하면") {
            val allComments = commentRepository.findAll()

            then("모든 댓글이 반환된다") {
                allComments shouldHaveSize 4
            }
        }

        `when`("특정 피드 ID로 댓글을 조회하면") {
            val feedComments = commentRepository.findByFeedId(FeedId(anotherFeedId))

            then("해당 피드에 속한 댓글들이 반환된다") {
                feedComments shouldHaveSize 1
                feedComments.forEach { it.feedId.value shouldBe anotherFeedId }
            }
        }
    }

    given("존재하지 않는 댓글 ID가 주어졌을 때") {
        val nonExistentId = CommentId(999L)

        `when`("해당 ID로 댓글을 조회하면") {
            val foundComment = commentRepository.findById(nonExistentId)

            then("null이 반환된다") {
                foundComment.shouldBeNull()
            }
        }
    }
})
