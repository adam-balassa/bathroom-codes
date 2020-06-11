package hu.badam.bathroomcodes.controller

import hu.badam.bathroomcodes.repository.BathroomCodesRepository
import hu.badam.bathroomcodes.service.BathroomCodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody


@Controller class BathroomCodesController {

    @Autowired private lateinit var repository: BathroomCodesRepository
    @Autowired private lateinit var bathroomCodeService: BathroomCodeService

    @RequestMapping(value = ["/hello"], method = [RequestMethod.GET])
    @ResponseBody fun testFunction() = asResponse {
        "hello"
    }

    @RequestMapping(value = ["/codes"], method = [RequestMethod.GET])
    @ResponseBody fun getBathroomCodes() = asResponse {
        repository.findAll()
    }

    @RequestMapping(value = ["/codes"],method = [RequestMethod.POST])
    @ResponseBody fun uploadBathroomCode(@RequestBody uploadableBathroomCode: UploadableBathroomCode) = asResponse {
        bathroomCodeService.uploadNewBathroomCode(uploadableBathroomCode)
    }

    @RequestMapping(value = ["/error"], method = [RequestMethod.GET])
    @ResponseBody fun error() = asResponse {
        Response.Error("An error occurred")
    }

    private inline fun <T> asResponse  (body: () -> T): Response {
        return try {
            val result = body()
            Response.Success(result)
        } catch (error: Response.Error) {
            println(error)
            throw error
        } catch (e: Throwable) {
            println(e)
            throw Response.Error(e.message ?: "An error occurred while processing the request")
        }
    }

    interface Response {
        val error: Boolean

        class Success <T> (val data: T, override val error: Boolean = false): Response
        class Error (message: String, override val error: Boolean = true): Exception(message), Response
    }
}