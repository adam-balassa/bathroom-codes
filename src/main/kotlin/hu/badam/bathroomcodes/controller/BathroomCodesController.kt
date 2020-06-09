package hu.badam.bathroomcodes.controller

import hu.badam.bathroomcodes.model.BathroomCode
import hu.badam.bathroomcodes.repository.BathroomCodesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody


@Controller class BathroomCodesController {

    @Autowired
    private lateinit var repository: BathroomCodesRepository

    @RequestMapping(value = ["/hello"], method = [RequestMethod.GET])
    @ResponseBody fun testFunction(): List<BathroomCode> {
        return repository.findAll()
    }
}