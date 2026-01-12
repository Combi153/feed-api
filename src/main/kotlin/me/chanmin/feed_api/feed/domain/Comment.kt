package me.chanmin.feed_api.feed.domain

@JvmInline
value class CommentId(val id: Long)

data class Comment(
    val id: CommentId? = null,
    val feedId: FeedId,
    val authorName: String,
    val authorPassword: String,
    val content: String
) {
    init {
        require(authorName.isNotBlank()) { "Author name cannot be blank!" }
        require(authorPassword.isNotBlank()) { "Author password cannot be blank!" }
        require(content.isNotBlank()) { "Content cannot be blank!" }
    }

    fun updateContent(newContent: String, authorPassword: String): Comment {
        validateAuthorPassword(authorPassword)
        return this.copy(content = newContent)
    }

    private fun validateAuthorPassword(authorPassword: String) {
        require(this.authorPassword == authorPassword) { "Author password does not match!" }
    }
}
