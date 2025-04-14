package org.example.dailydriver.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        Contact contact = new Contact()
                .name("Kadirov Miratdin ")
                .url("https://mr.com")
                .email("meraddin.fx@mail.ru");


        License license = new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.html");


        Info info = new Info()
                .title("My Project API Documentation")
                .version("1.0.0")
                .description("Bu loyiha Swagger (OpenAPI 3) bilan hujjatlashtirilgan")
                .contact(contact)
                .license(license);


        return new OpenAPI()
                .info(info)
                .servers(List.of());
    }


}
