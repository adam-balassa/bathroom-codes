package hu.badam.bathroomcodes.repository

import hu.badam.bathroomcodes.model.User
import org.springframework.data.repository.CrudRepository

interface UsersRepository : CrudRepository<User, Int> {
    override fun findAll(): List<User>
    override fun <S : User?> save(entity: S): S
//    fun findByIdOrNull(id: Int): User?
}