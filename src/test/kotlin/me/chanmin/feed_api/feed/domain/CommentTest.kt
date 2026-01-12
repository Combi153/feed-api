package me.chanmin.feed_api.feed.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class CommentTest : BehaviorSpec({

    given("유효한 댓글 데이터가 주어졌을 때") {
        val feedId = FeedId(1L)
        val authorName = "testauthor"
        val authorPassword = "password123"
        val content = "This is a test comment"

        `when`("댓글을 생성하면") {
            val comment = Comment(
                feedId = feedId,
                authorName = authorName,
                authorPassword = authorPassword,
                content = content
            )

            then("댓글이 정상적으로 생성된다") {
                comment.feedId shouldBe feedId
                comment.authorName shouldBe authorName
                comment.authorPassword shouldBe authorPassword
                comment.content shouldBe content
            }
        }

        `when`("ID와 함께 댓글을 생성하면") {
            val commentId = CommentId(1L)
            val comment = Comment(
                id = commentId,
                feedId = feedId,
                authorName = authorName,
                authorPassword = authorPassword,
                content = content
            )

            then("댓글이 지정된 ID를 가진다") {
                comment.id shouldBe commentId
            }
        }
    }

    given("빈 작성자명이 주어졌을 때") {
        val feedId = FeedId(1L)
        val authorPassword = "password123"
        val content = "This is a test comment"

        `when`("댓글 생성을 시도하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                Comment(
                    feedId = feedId,
                    authorName = "",
                    authorPassword = authorPassword,
                    content = content
                )
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Author name cannot be blank!"
            }
        }
    }

    given("공백만 있는 작성자명이 주어졌을 때") {
        val feedId = FeedId(1L)
        val authorPassword = "password123"
        val content = "This is a test comment"

        `when`("댓글 생성을 시도하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                Comment(
                    feedId = feedId,
                    authorName = "   ",
                    authorPassword = authorPassword,
                    content = content
                )
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Author name cannot be blank!"
            }
        }
    }

    given("빈 비밀번호가 주어졌을 때") {
        val feedId = FeedId(1L)
        val authorName = "testauthor"
        val content = "This is a test comment"

        `when`("댓글 생성을 시도하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                Comment(
                    feedId = feedId,
                    authorName = authorName,
                    authorPassword = "",
                    content = content
                )
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Author password cannot be blank!"
            }
        }
    }

    given("공백만 있는 비밀번호가 주어졌을 때") {
        val feedId = FeedId(1L)
        val authorName = "testauthor"
        val content = "This is a test comment"

        `when`("댓글 생성을 시도하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                Comment(
                    feedId = feedId,
                    authorName = authorName,
                    authorPassword = "   ",
                    content = content
                )
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Author password cannot be blank!"
            }
        }
    }

    given("빈 내용이 주어졌을 때") {
        val feedId = FeedId(1L)
        val authorName = "testauthor"
        val authorPassword = "password123"

        `when`("댓글 생성을 시도하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                Comment(
                    feedId = feedId,
                    authorName = authorName,
                    authorPassword = authorPassword,
                    content = ""
                )
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Content cannot be blank!"
            }
        }
    }

    given("공백만 있는 내용이 주어졌을 때") {
        val feedId = FeedId(1L)
        val authorName = "testauthor"
        val authorPassword = "password123"

        `when`("댓글 생성을 시도하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                Comment(
                    feedId = feedId,
                    authorName = authorName,
                    authorPassword = authorPassword,
                    content = "   "
                )
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Content cannot be blank!"
            }
        }
    }

    given("유효한 댓글이 존재할 때") {
        val feedId = FeedId(1L)
        val authorName = "testauthor"
        val authorPassword = "password123"
        val content = "This is a test comment"
        val comment = Comment(
            feedId = feedId,
            authorName = authorName,
            authorPassword = authorPassword,
            content = content
        )

        `when`("올바른 비밀번호로 내용을 수정하면") {
            val newContent = "This is updated content"
            val updatedComment = comment.updateContent(newContent, authorPassword)

            then("댓글 내용이 수정된다") {
                updatedComment.content shouldBe newContent
                updatedComment.feedId shouldBe feedId
                updatedComment.authorName shouldBe authorName
                updatedComment.authorPassword shouldBe authorPassword
            }
        }

        `when`("잘못된 비밀번호로 내용을 수정하려고 하면") {
            val newContent = "This is updated content"
            val wrongPassword = "wrongpassword"
            val exception = shouldThrow<IllegalArgumentException> {
                comment.updateContent(newContent, wrongPassword)
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Author password does not match!"
            }
        }

        `when`("올바른 비밀번호로 비밀번호를 검증하면") {
            val validPassword = authorPassword

            then("검증이 성공한다") {
                comment.validateAuthorPassword(validPassword)
            }
        }

        `when`("잘못된 비밀번호로 비밀번호 검증을 시도하면") {
            val wrongPassword = "wrongpassword"
            val exception = shouldThrow<IllegalArgumentException> {
                comment.validateAuthorPassword(wrongPassword)
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Author password does not match!"
            }
        }
    }
})
