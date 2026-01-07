package me.chanmin.feed_api.controller

import me.chanmin.feed_api.domain.Feed
import me.chanmin.feed_api.dto.FeedDto
import me.chanmin.feed_api.service.FeedService
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
        val feed = feedService.getById(id)
        return ResponseEntity.ok(feed)
    }

    @PostMapping("/feeds")
    fun createFeed(@RequestBody feedDto: FeedDto): ResponseEntity<Feed> {
        val feed = feedService.createFeed(feedDto.content)
        return ResponseEntity.status(HttpStatus.CREATED).body(feed)
    }

    @PatchMapping("/feeds/{id}")
    fun updateFeedById(@RequestBody feedDto: FeedDto, @PathVariable id: Long): ResponseEntity<Feed> {
        val updateFeed = feedService.updateFeed(id, feedDto.content)
        return ResponseEntity.ok(updateFeed)
    }

    @DeleteMapping("/feeds/{id}")
    fun deleteFeedById(@PathVariable id: Long): ResponseEntity<Unit> {
        feedService.deleteFeed(id)
        return ResponseEntity.noContent().build()
    }
}
