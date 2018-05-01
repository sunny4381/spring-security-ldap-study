package io.github.sunny4381

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource

@Configuration
open class I18nConfig {
    @Bean
    open fun messageSource() : MessageSource {
        val source = ResourceBundleMessageSource()
        source.setBasenames("i18n/messages")
        source.setCacheSeconds(0)
        source.setDefaultEncoding("UTF-8")
        return source
    }
}
