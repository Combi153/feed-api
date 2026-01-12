package me.chanmin.feed_api.feed.controller

import me.chanmin.feed_api.feed.domain.Feed
import me.chanmin.feed_api.feed.domain.FeedId
import me.chanmin.feed_api.feed.dto.FeedDto
import me.chanmin.feed_api.feed.service.FeedService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class FeedController(private val feedService: FeedService) {

    @GetMapping("/feeds")
    fun getFeeds(): ResponseEntity<List<Feed>> {
        val feeds = feedService.getAll()
        return ResponseEntity.ok(feeds)
    }

    @GetMapping("/feeds/{id}")
    fun getFeedById(@PathVariable id: Long): ResponseEntity<Feed> {
        val feed = feedService.getById(FeedId(id))
        return ResponseEntity.ok(feed)
    }

    @PostMapping("/feeds")
    fun createFeed(@RequestBody feedDto: FeedDto): ResponseEntity<Feed> {
        val feed = feedService.createFeed(feedDto.content)
        return ResponseEntity.status(HttpStatus.CREATED).body(feed)
    }

    @PatchMapping("/feeds/{id}")
    fun updateFeedById(@RequestBody feedDto: FeedDto, @PathVariable id: Long): ResponseEntity<Feed> {
        val updateFeed = feedService.updateFeed(
            FeedId(id), feedDto.content
        )
        return ResponseEntity.ok(updateFeed)
    }

    @DeleteMapping("/feeds/{id}")
    fun deleteFeedById(@PathVariable id: Long): ResponseEntity<Unit> {
        feedService.deleteFeed(FeedId(id))
        return ResponseEntity.noContent().build()
    }
}
