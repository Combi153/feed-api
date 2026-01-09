package me.chanmin.feed_api.user.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class UserTest : BehaviorSpec({

    given("유효한 사용자 데이터가 주어졌을 때") {
        val username = "testuser"
        val email = "test@example.com"
        val password = "password123"

        `when`("사용자를 생성하면") {
            val user = User(username = username, email = email, password = password)

            then("사용자가 정상적으로 생성된다") {
                user.username shouldBe username
                user.email shouldBe email
                user.password shouldBe password
            }
        }

        `when`("ID와 함께 사용자를 생성하면") {
            val userId = UserId(1L)
            val user = User(id = userId, username = username, email = email, password = password)

            then("사용자가 지정된 ID를 가진다") {
                user.id shouldBe userId
            }
        }
    }

    given("빈 사용자명이 주어졌을 때") {
        val email = "test@example.com"
        val password = "password123"

        `when`("사용자 생성을 시도하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                User(username = "", email = email, password = password)
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Username cannot be empty!"
            }
        }
    }

    given("최대 길이를 초과하는 사용자명이 주어졌을 때") {
        val username = "a".repeat(User.USER_NAME_MAX_LENGTH + 1)
        val email = "test@example.com"
        val password = "password123"

        `when`("사용자 생성을 시도하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                User(username = username, email = email, password = password)
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Username length must be less than or equal ${User.USER_NAME_MAX_LENGTH}"
            }
        }
    }

    given("빈 이메일이 주어졌을 때") {
        val username = "testuser"
        val password = "password123"

        `when`("사용자 생성을 시도하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                User(username = username, email = "", password = password)
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Email cannot be empty!"
            }
        }
    }

    given("최대 길이를 초과하는 이메일이 주어졌을 때") {
        val username = "testuser"
        val email = "a".repeat(User.EMAIL_MAX_LENGTH + 1)
        val password = "password123"

        `when`("사용자 생성을 시도하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                User(username = username, email = email, password = password)
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Email length must be less than or equal ${User.EMAIL_MAX_LENGTH}"
            }
        }
    }

    given("빈 비밀번호가 주어졌을 때") {
        val username = "testuser"
        val email = "test@example.com"

        `when`("사용자 생성을 시도하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                User(username = username, email = email, password = "")
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Password cannot be empty!"
            }
        }
    }

    given("최대 길이를 초과하는 비밀번호가 주어졌을 때") {
        val username = "testuser"
        val email = "test@example.com"
        val password = "a".repeat(User.PASSWORD_MAX_LENGTH + 1)

        `when`("사용자 생성을 시도하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                User(username = username, email = email, password = password)
            }

            then("적절한 메시지와 함께 예외가 발생한다") {
                exception.message shouldContain "Password length must be less than or equal ${User.PASSWORD_MAX_LENGTH}"
            }
        }
    }
})
