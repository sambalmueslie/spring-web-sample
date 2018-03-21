package de.sambalmueslie.example.web

import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Controller
import java.util.*
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.beans.factory.annotation.Autowired



@Controller
class CustomerUpdateController(val repository:CustomerRepository) {
	@Autowired
	private val webSocket: SimpMessagingTemplate? = null

	@Scheduled(fixedRate = 1000)
	fun update() {
		val customers = repository.findAll().toList();
		val random = Random()
		val customer = customers[random.nextInt(customers.size)]
		customer.state = if(random.nextBoolean()) State.ONLINE else State.OFFLINE
		repository.save(customer)
		webSocket?.convertAndSend("/topic/customer",  customer)
	}

}