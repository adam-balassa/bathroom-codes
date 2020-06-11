package hu.badam.bathroomcodes.repository

import hu.badam.bathroomcodes.model.Location
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface LocationRepository : CrudRepository<Location, Int> {
    companion object {
        const val radius: Double = 1.0E-5
    }

    override fun findAll(): List<Location>
    override fun <S : Location?> save(entity: S): S

    @Query("""SELECT l from Location l WHERE 
         l.lat BETWEEN :lat - $radius AND :lat + $radius AND
         l.lon BETWEEN :lon - $radius AND :lon + $radius""")
    fun findCloseLocation(@Param("lat") lat: Double, @Param("lon") lon: Double): Location?
}