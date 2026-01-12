package me.chanmin.feed_api.common

interface CommonRepository<T, R> {
    fun save(entity: T): T
    fun findAll(): List<T>
    fun findById(id: R): T?
    fun deleteById(id: R): Unit
}
