package br.com.jaya.coinconverter.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class SwaggerConfig {

    @Bean
    open fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Back-end Jaya Coin Converter")
                    .description("Documentação do conversor de moedas")
                    .version("1.0.0")
                    .contact(
                        Contact()
                            .name("Guilherme Gois Braga")
                            .email("guih.smi2@outlook.com")
                    )
                    .license(
                        License()
                            .name("Apache 2.0")
                            .url("https://www.apache.org/licenses/LICENSE-2.0")
                    )
            )
            .addServersItem(
                Server()
                    .url("http://localhost:9439")
                    .description("Local server")
            )
    }

}