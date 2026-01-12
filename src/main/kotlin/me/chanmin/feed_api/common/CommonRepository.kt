package me.chanmin.feed_api.common

interface CommonRepository<T, U> {
    fun save(entity: T): T
    fun findAll(): List<T>
    fun findById(id: U): T?
    fun deleteById(id: U): Unit
}
