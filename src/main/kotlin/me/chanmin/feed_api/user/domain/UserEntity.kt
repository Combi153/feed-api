package me.chanmin.feed_api.user.domain

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object UserEntity : LongIdTable(name = "user") {
    val username = varchar("username", User.USER_NAME_MAX_LENGTH)
    val email = varchar("email", User.EMAIL_MAX_LENGTH)
    val password = varchar("password", User.PASSWORD_MAX_LENGTH)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val updatedAt = datetime("updated_at").defaultExpression(CurrentDateTime)
}
