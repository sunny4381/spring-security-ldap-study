package io.github.sunny4381

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/")
@Controller
open class HomeController {
    @GetMapping
    fun home() : String {
        return "home"
    }
}
