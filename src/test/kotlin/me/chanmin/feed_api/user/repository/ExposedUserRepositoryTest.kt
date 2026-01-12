package me.chanmin.feed_api.user.repository

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import me.chanmin.feed_api.user.domain.User
import me.chanmin.feed_api.user.domain.UserEntity
import me.chanmin.feed_api.user.domain.UserId
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class ExposedUserRepositoryTest(
    private val userRepository: ExposedUserRepository
) : BehaviorSpec({

    beforeSpec {
        transaction {
            SchemaUtils.create(UserEntity)
        }
    }

    afterEach {
        transaction {
            UserEntity.deleteAll()
        }
    }

    afterSpec {
        transaction {
            SchemaUtils.drop(UserEntity)
        }
    }

    given("새로운 사용자 정보가 주어졌을 때") {
        val username = "testuser"
        val email = "test@example.com"
        val password = "password123"

        `when`("사용자를 저장하면") {
            val user = userRepository.save(
                User(
                    username = username,
                    email = email,
                    password = password
                )
            )

            then("ID가 할당된 사용자가 반환된다") {
                user.id.shouldNotBeNull()
                user.username shouldBe username
                user.email shouldBe email
                user.password shouldBe password
            }
        }
    }

    given("기존 사용자가 저장되어 있을 때") {
        val savedUser = userRepository.save(
            User(
                username = "original",
                email = "original@example.com",
                password = "password123"
            )
        )

        `when`("사용자 정보를 수정하면") {
            val updatedUser = userRepository.save(
                savedUser.copy(
                    username = "updated",
                    email = "updated@example.com",
                )
            )

            then("수정된 정보가 반영된다") {
                updatedUser.id shouldBe savedUser.id
                updatedUser.username shouldBe "updated"
                updatedUser.email shouldBe "updated@example.com"
            }
        }

        `when`("ID로 사용자를 조회하면") {
            val foundUser = userRepository.findById(savedUser.id!!)

            then("해당 사용자가 반환된다") {
                foundUser.shouldNotBeNull()
                foundUser.id shouldBe savedUser.id
                foundUser.username shouldBe savedUser.username
                foundUser.email shouldBe savedUser.email
                foundUser.password shouldBe savedUser.password
            }
        }

        `when`("사용자를 삭제하면") {
            val userId = savedUser.id!!
            userRepository.deleteById(userId)

            then("해당 사용자를 찾을 수 없다") {
                val foundUser = userRepository.findById(userId)
                foundUser.shouldBeNull()
            }
        }
    }

    given("여러 사용자가 저장되어 있을 때") {
        userRepository.save(User(username = "user1", email = "user1@example.com", password = "pass1"))
        userRepository.save(User(username = "user2", email = "user2@example.com", password = "pass2"))
        userRepository.save(User(username = "user3", email = "user3@example.com", password = "pass3"))

        `when`("모든 사용자를 조회하면") {
            val allUsers = userRepository.findAll()

            then("모든 사용자가 반환된다") {
                allUsers shouldHaveSize 3
            }
        }
    }

    given("존재하지 않는 사용자 ID가 주어졌을 때") {
        val nonExistentId = UserId(999L)

        `when`("해당 ID로 사용자를 조회하면") {
            val foundUser = userRepository.findById(nonExistentId)

            then("null이 반환된다") {
                foundUser.shouldBeNull()
            }
        }
    }
})
