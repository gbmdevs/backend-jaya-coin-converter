package br.com.jaya.coinconverter.controller


import br.com.jaya.coinconverter.model.CurrencySearchRequestDTO
import br.com.jaya.coinconverter.model.LoginUserRequestDTO
import br.com.jaya.coinconverter.model.SignUpRequestDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.web.context.WebApplicationContext
import java.math.BigDecimal

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class CurrencyControllerTest {
    @Autowired
    lateinit var context: WebApplicationContext

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object {
        var jwtToken: String? = null
    }

    @BeforeAll
    fun setup() {
        val loginDto = createLoginDto(email = "test@test.com.br", password = "teste")
        val result = mockMvc.post("/auth/login") {
            contentType = MediaType.APPLICATION_JSON
            content = toJson(loginDto)
        }.andExpect {
            status { isOk() }
        }.andReturn()

        val responseJson = result.response.contentAsString
        val responseMap = objectMapper.readValue(responseJson, Map::class.java)
        jwtToken = responseMap["token"] as String?
    }

    @Test
    @Order(1)
    fun `should return a list of currency in Database`() {
        mockMvc.get("/currency/types") {
            header("Authorization", "Bearer $jwtToken")
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }
    }

    @Test
    @Order(2)
    fun `Search for convert a currency, and save in database`() {
        var searchCurrency = createCurrencySearch("BRL","USD",BigDecimal(10.0))
        mockMvc.post("/currency/search") {
            header("Authorization", "Bearer $jwtToken")
            contentType = MediaType.APPLICATION_JSON
            content = toJson(searchCurrency)
        }.andExpect {
            status { isOk() }
        }

    }

    @Test
    @Order(3)
    fun `should return a list of Historic Convert in Database`() {
        mockMvc.get("/currency/historic") {
            header("Authorization", "Bearer $jwtToken")
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }
    }

    private fun createLoginDto(email: String, password: String) = LoginUserRequestDTO(
        email = email,
        password = password
    )

    private fun createCurrencySearch(currencyOrigin: String, currencyDestiny: String, amount: BigDecimal) =
        CurrencySearchRequestDTO(
            currencyOrigin = currencyOrigin,
            currencyDestiny = currencyDestiny,
            amount = amount
        )

    private fun <T> toJson(obj: T): String = objectMapper.writeValueAsString(obj)
}