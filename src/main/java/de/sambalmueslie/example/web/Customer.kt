package de.sambalmueslie.example.web

import javax.persistence.*

@Entity
class Customer(
		var firstName: String = "",
		var lastName: String = "",
		@Enumerated(EnumType.STRING)
		var state: State = State.UNKNOWN,
		@Id @GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long = 0
)
