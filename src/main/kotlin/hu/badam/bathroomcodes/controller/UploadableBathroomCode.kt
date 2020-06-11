package hu.badam.bathroomcodes.controller

class UploadableBathroomCode {

    lateinit var code: String
    lateinit var bathroomType: String

    var comment: String? = null
    var userId: Int = 0
    var lat: Double = 0.0
    var lon: Double = 0.0
    var address: String? = null
}