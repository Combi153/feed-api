package me.chanmin.feed_api.domain

class Feed(var id: Long?, var content: String) {
    init {
        require(!content.isEmpty()) { "Content cannot be empty!" }
    }

    fun updateContent(newContent: String): Unit {
        content = newContent
    }
}
