package hu.badam.bathroomcodes.repository

import hu.badam.bathroomcodes.model.BathroomCode
import org.springframework.data.repository.CrudRepository

interface BathroomCodesRepository: CrudRepository<BathroomCode, Int> {
    override fun findAll(): List<BathroomCode>
    override fun <S : BathroomCode?> save(entity: S): S
}