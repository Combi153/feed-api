package me.chanmin.feed_api.common

interface CommonRepository<T> {
    fun save(entity: T): T
    fun findAll(): List<T>
    fun findById(id: Long): T?
    fun deleteById(id: Long): Unit
}
