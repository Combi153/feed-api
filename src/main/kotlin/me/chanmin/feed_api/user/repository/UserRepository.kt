package me.chanmin.feed_api.user.repository

import me.chanmin.feed_api.common.CommonRepository
import me.chanmin.feed_api.user.domain.User
import me.chanmin.feed_api.user.domain.UserId

interface UserRepository : CommonRepository<User, UserId>
