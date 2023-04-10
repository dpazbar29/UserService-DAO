package dao

import dao.entity.UserEntity
import java.util.*

interface UserDAO {
    fun create(user: UserEntity):UserEntity
    fun getAll(): List<UserEntity>
    fun getById(id: UUID): UserEntity?
    fun update(user: UserEntity):UserEntity
    fun delete(id: UUID)
}