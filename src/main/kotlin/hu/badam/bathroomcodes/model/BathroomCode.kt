package hu.badam.bathroomcodes.model

import java.util.*
import javax.persistence.*

@Entity
class BathroomCode () {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    lateinit var code: String

    @Temporal(TemporalType.DATE)
    lateinit var dateUploaded: Date

    @Enumerated(EnumType.ORDINAL)
    lateinit var bathroomType: BathroomType

    @ManyToOne
    @JoinColumn (name = "location_id")
    lateinit var location: Location

    var comment: String? = null

    @ManyToOne
    @JoinColumn (name = "user_id")
    private lateinit var uploadedBy: User

    enum class BathroomType {
        MALE, FEMALE, OTHER, BOTH
    }

    constructor(code: String, bathroomType: BathroomType, location: Location, comment: String?, uploadedBy: User): this() {
        this.code = code
        this.bathroomType = bathroomType
        this.location = location
        this.comment = comment
        this.uploadedBy = uploadedBy
        this.dateUploaded = Date()
    }
}