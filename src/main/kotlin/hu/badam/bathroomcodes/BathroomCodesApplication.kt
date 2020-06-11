package hu.badam.bathroomcodes

import hu.badam.bathroomcodes.service.BathroomCodeService
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = ["hu.badam.bathroomcodes.controller"])
class BathroomCodesApplication {
	@Bean
	fun bathroomCodeService(): BathroomCodeService {
		return BathroomCodeService()
	}
}

fun main(args: Array<String>) {
	runApplication<BathroomCodesApplication>(*args)
}
