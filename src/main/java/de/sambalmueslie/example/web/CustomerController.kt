package de.sambalmueslie.example.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.scheduling.annotation.Scheduled
import java.util.*


@RestController
@RequestMapping(value = "/customers")
class CustomerController (val repository:CustomerRepository) {

	init {
		repository.save(Customer("Jack", "Bauer", State.ONLINE))
		repository.save(Customer("Chloe", "O'Brian", State.OFFLINE))
		repository.save(Customer("Kim", "Bauer", State.ABSEND))
		repository.save(Customer("David", "Palmer"))
		repository.save(Customer("Michelle", "Dessler"))
	}

	@GetMapping("/all")
	fun findAll() = repository.findAll()



}