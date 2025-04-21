package br.com.jaya.coinconverter.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class WebConfig: WebMvcConfigurer {
    @Value("\${server.cors.front-end.url}")
    private lateinit var frontendUrl: String
    override fun addCorsMappings(registry: CorsRegistry){
        registry.addMapping("/**")
            .allowedOrigins(frontendUrl)
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowCredentials(true)
    }
}