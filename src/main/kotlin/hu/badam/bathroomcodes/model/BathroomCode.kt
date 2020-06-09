package hu.badam.bathroomcodes.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class BathroomCode {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
    lateinit var text: String
}