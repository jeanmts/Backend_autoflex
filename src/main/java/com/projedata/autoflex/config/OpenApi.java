package com.projedata.autoflex.config;

import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApi {

    @Bean
    public OpenAPI myOpenApi() {
        Server devServer = new Server();

        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Servidor local!");

        Contact contact = new Contact();
        contact.setName("Jean matos");
        contact.setEmail("matos.sd52@gmail.com");
        contact.setUrl("https://www.linkedin.com/in/-jeancarlos/");

        License apacheLicense = new License().name("Apache license Version 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");

        Info info = new Info()
                .title("API - Autoflex")
                .version("1.0")
                .contact(contact)
                .description("API do projeto autoflex")
                .termsOfService("https://www.autoflex.com.br")
                .license(apacheLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));

    }
}
