package io.github.sunny4381

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.WebAttributes
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@RequestMapping("/")
@Controller
open class HomeController {
    @GetMapping
    fun home() : String {
        return "home"
    }

    @GetMapping("login")
    fun login(request: HttpServletRequest, model: Model) : String {
        if (request.parameterMap.containsKey("error")) {
            model.addAttribute("loginError", true)

            val session = request.getSession(false)
            if (session != null) {
                val ex = session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) as AuthenticationException
                model.addAttribute("errorMsg", ex.message)
            }
        }

        if (request.parameterMap.containsKey("logout")) {
            model.addAttribute("logoutSuccess", true)
        }

        return "login"
    }
}
