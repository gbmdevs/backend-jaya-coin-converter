package br.com.jaya.coinconverter.controller

import br.com.jaya.coinconverter.model.LoginUserRequestDTO
import br.com.jaya.coinconverter.model.LoginUserResponseDTO
import br.com.jaya.coinconverter.model.SignUpRequestDTO
import br.com.jaya.coinconverter.model.SignUpResponseDTO
import br.com.jaya.coinconverter.services.AuthenticationService
import br.com.jaya.coinconverter.services.JwtService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController(
    private val authenticationService: AuthenticationService,
    private val jwtService: JwtService
) {

    @PostMapping("/signup")
    fun signUpUser(@RequestBody signup: SignUpRequestDTO): ResponseEntity<SignUpResponseDTO>{
        return ResponseEntity.ok(authenticationService.signup(signup))
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody login: LoginUserRequestDTO): ResponseEntity<LoginUserResponseDTO> {
        val user = authenticationService.authenticate(login)
        val jwtToken = jwtService.generateToken(user)
        val loginResponse = LoginUserResponseDTO(jwtToken, jwtService.getJwtExpiration())
        return ResponseEntity.ok(loginResponse)
    }

}