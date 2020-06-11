package hu.badam.bathroomcodes.model

import javax.persistence.*

@Entity
class Location () {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    @Column(columnDefinition = "decimal(15, 12)")
    var lat: Double = 0.0

    @Column(columnDefinition = "decimal(15, 12)")
    var lon: Double = 0.0

    var address: String? = null

    constructor(lat: Double, lon: Double, address: String): this() {
        this.lat = lat
        this.lon = lon
        this.address = address
    }
}