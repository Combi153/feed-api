package me.chanmin.feed_api.user.repository

import me.chanmin.feed_api.user.domain.User
import me.chanmin.feed_api.user.domain.UserEntity
import me.chanmin.feed_api.user.domain.UserId
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository

@Repository
class ExposedUserRepository : UserRepository {
    override fun save(entity: User): User {
        entity.id?.let {
            UserEntity.update({ UserEntity.id eq it.value }) {
                it[email] = entity.email
                it[username] = entity.username
                it[password] = entity.password
            }
            return UserEntity.selectAll().where { UserEntity.id eq it.value }.first().let { row ->
                User(
                    id = UserId(row[UserEntity.id].value),
                    email = row[UserEntity.email],
                    username = row[UserEntity.username],
                    password = row[UserEntity.password]
                )
            }
        }

        val id = UserEntity.insertAndGetId {
            it[email] = entity.email
            it[username] = entity.username
            it[password] = entity.password
        }
        return UserEntity.selectAll().where { UserEntity.id eq id.value }.first().let {
            User(
                id = UserId(
                    it[UserEntity.id].value
                ),
                email = it[UserEntity.email],
                username = it[UserEntity.username],
                password = it[UserEntity.password]
            )
        }
    }

    override fun findAll(): List<User> {
        return UserEntity.selectAll().map {
            User.withoutPassword(
                id = UserId(it[UserEntity.id].value),
                email = it[UserEntity.email],
                username = it[UserEntity.username]
            )
        }
    }

    override fun findById(id: Long): User? {
        return UserEntity.selectAll().where { UserEntity.id eq id }.firstOrNull()?.let {
            User.withoutPassword(
                id = UserId(it[UserEntity.id].value),
                email = it[UserEntity.email],
                username = it[UserEntity.username]
            )
        }
    }

    override fun findByIdWithPassword(id: Long): User? {
        return UserEntity.selectAll().where { UserEntity.id eq id }.firstOrNull()?.let {
            User(
                id = UserId(it[UserEntity.id].value),
                email = it[UserEntity.email],
                username = it[UserEntity.username],
                password = it[UserEntity.password]
            )
        }
    }

    override fun deleteById(id: Long) {
        UserEntity.deleteWhere { UserEntity.id eq id }
    }
}
