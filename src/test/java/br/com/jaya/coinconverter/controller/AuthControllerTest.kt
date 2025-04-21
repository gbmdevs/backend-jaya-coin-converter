package br.com.jaya.coinconverter.controller

import br.com.jaya.coinconverter.model.LoginUserRequestDTO
import br.com.jaya.coinconverter.model.SignUpRequestDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest {

    @Autowired
    lateinit var context: WebApplicationContext

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Value("\${security.jwt.expiration-time}")
    private var expiration: Long = 0

    @Test
    fun `should authenticate user and return JWT tokena`() {
        val loginDto = createLoginDto(email = "test@test.com.br", password = "teste")
        mockMvc.post("/auth/login") {
            contentType = MediaType.APPLICATION_JSON
            content = toJson(loginDto)
        }.andExpect {
            status { isOk() }
           content {
                jsonPath("$.expiresIn") { value(expiration) }
            }
        }
    }

    @Test
    fun `should create a new user on application`() {
        val signup = createSignupDto(email = "junit@testemail", password = "teste",name = "Teste Unitario")
        mockMvc.post("/auth/signup") {
            contentType = MediaType.APPLICATION_JSON
            content = toJson(signup)
        }.andExpect {
            status { isOk() }
            /* content {
                jsonPath("$.expiresIn") { value(expiration) }
            }*/
        }
    }

    @Test
    fun `should return error - User already exist`() {
        val signup = createSignupDto(email = "junit@testemail", password = "teste",name = "Teste Unitario")
        mockMvc.post("/auth/signup") {
            contentType = MediaType.APPLICATION_JSON
            content = toJson(signup)
        }.andExpect {
            status { isForbidden()}
            /* content {
                jsonPath("$.expiresIn") { value(expiration) }
            }*/
        }
    }

    private fun createLoginDto(email: String, password: String) = LoginUserRequestDTO(
        email = email,
        password = password
    )

    private fun createSignupDto(email: String, password: String, name: String) = SignUpRequestDTO(
        email = email,
        password = password,
        name = name
    )

    private fun <T> toJson(obj: T): String = objectMapper.writeValueAsString(obj)

}