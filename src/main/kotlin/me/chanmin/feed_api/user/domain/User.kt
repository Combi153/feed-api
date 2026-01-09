package me.chanmin.feed_api.user.domain

@JvmInline
value class UserId(val value: Long)

data class User(val id: UserId? = null, val username: String, val email: String, val password: String) {
    init {
        require(password.isNotBlank()) { "Password cannot be empty!" }
        require(password.length <= PASSWORD_MAX_LENGTH) { "Password length must be less than or equal $PASSWORD_MAX_LENGTH" }

        require(username.isNotBlank()) { "Username cannot be empty!" }
        require(username.length <= USER_NAME_MAX_LENGTH) { "Username length must be less than or equal $USER_NAME_MAX_LENGTH" }

        require(email.isNotBlank()) { "Email cannot be empty!" }
        require(email.length <= EMAIL_MAX_LENGTH) { "Email length must be less than or equal $EMAIL_MAX_LENGTH" }
    }

    companion object {
        const val USER_NAME_MAX_LENGTH = 50
        const val EMAIL_MAX_LENGTH = 100
        const val PASSWORD_MAX_LENGTH = 30
    }
}
