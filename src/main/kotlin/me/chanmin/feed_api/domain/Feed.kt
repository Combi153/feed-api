package me.chanmin.feed_api.domain

@JvmInline
value class FeedId(val value: Long)

data class Feed(val id: FeedId? = null, var content: String) {
    init {
        require(!content.isEmpty()) { "Content cannot be empty!" }
    }

    fun updateContent(newContent: String): Unit {
        content = newContent
    }
}
