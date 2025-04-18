package org.example.dailydriver.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition(

        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Spring swagger",
                version = "10",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Kadirov Miratdin",
                        email = "meraddin.fx@mail.ru",
                        url = "https://github.com/MiraddinKadirov"
                ),
                license = @io.swagger.v3.oas.annotations.info.License(
                        name = "Apache 2.0",
                        url = "https://springdoc.org"
                )
        ),
        servers = @Server(
                url = "http://localhost:8080",
                description = "Production-Server"
        ),
        security = @SecurityRequirement(
                name = "JwtAuth"
        )
)
@SecurityScheme(
        name = "JwtAuth",
        description = "JWT token base",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

//    @Bean
//    public OpenAPI openApi() {
//
//        Contact contact = new Contact()
//                .name("Kadirov Miratdin ")
//                .url("https://mr.com")
//                .email("meraddin.fx@mail.ru");
//
//
//        License license = new License()
//                .name("Apache 2.0")
//                .url("https://www.apache.org/licenses/LICENSE-2.0.html");
//
//
//        Info info = new Info()
//                .title("My Project API Documentation")
//                .version("1.0.0")
//                .description("Bu loyiha Swagger (OpenAPI 3) bilan hujjatlashtirilgan")
//                .contact(contact)
//                .license(license);
//
//        return new OpenAPI()
//                .info(info)
//                .servers(List.of());
//    }


}
