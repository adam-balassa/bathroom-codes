package hu.badam.bathroomcodes.service

import hu.badam.bathroomcodes.controller.BathroomCodesController.Response.Error
import hu.badam.bathroomcodes.controller.UploadableBathroomCode
import hu.badam.bathroomcodes.model.BathroomCode
import hu.badam.bathroomcodes.model.Location
import hu.badam.bathroomcodes.model.User
import hu.badam.bathroomcodes.repository.BathroomCodesRepository
import hu.badam.bathroomcodes.repository.LocationRepository
import hu.badam.bathroomcodes.repository.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
class BathroomCodeService {

    @Autowired private lateinit var bathroomCodesRepository: BathroomCodesRepository
    @Autowired private lateinit var locationRepository: LocationRepository
    @Autowired private lateinit var usersRepository: UsersRepository

    @Modifying
    @Transactional
    fun uploadNewBathroomCode(code: UploadableBathroomCode): BathroomCode {
        val finalLocation = updateLocationInfo(code.lon, code.lat, code.address ?: "")

        val user: User = usersRepository.findByIdOrNull(code.userId) ?: throw Error("Invalid user")
        val bathroomCode = BathroomCode(
                code = code.code,
                bathroomType = BathroomCode.BathroomType.valueOf(code.bathroomType),
                comment = code.comment,
                uploadedBy = user,
                location = finalLocation
        )

        bathroomCodesRepository.save(bathroomCode)
        return bathroomCode
    }


    fun updateLocationInfo(lon: Double, lat: Double, address: String): Location {
        val location: Location? = locationRepository.findCloseLocation(lat, lon)
        val inputLocation = Location(lat, lon, address)
        if (location == null) {
            locationRepository.save(inputLocation)
            return inputLocation
        }

        if (location.address == "" && inputLocation.address != "") {
            location.address = inputLocation.address
            locationRepository.save(location)
        }
        return location
    }
}