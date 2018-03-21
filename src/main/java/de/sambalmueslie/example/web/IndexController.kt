package de.sambalmueslie.example.web

import org.springframework.stereotype.Controller
import sun.security.x509.OIDMap.addAttribute
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping



@Controller
class IndexController {
	@RequestMapping("/")
	fun index() = "index"
}