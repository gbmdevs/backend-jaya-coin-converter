package br.com.jaya.coinconverter.config

import br.com.jaya.coinconverter.filter.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
open class SecurityConfig(private val jwtAuthenticationFilter: JwtAuthenticationFilter,
                          private val authenticationProvider: AuthenticationProvider) {

    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable().cors().and()
            .headers().frameOptions().disable()
            .and()
            .authorizeHttpRequests()
            .requestMatchers(AntPathRequestMatcher("/auth/**")).permitAll()
            .requestMatchers(AntPathRequestMatcher("/h2-console/**")).permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

}

