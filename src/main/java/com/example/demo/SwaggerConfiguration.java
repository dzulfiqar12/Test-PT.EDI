package com.example.demo;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {

        var info = new Info()
                .title("Test PT.EDI API Documentation")
                .description("Dokumentasi Untuk Test PT.EDI")
                .version("v 1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));

        String schemeName = "bearerAuth";
        var requirement = new SecurityRequirement().addList(schemeName);

        var scheme = new SecurityScheme()
                .name(schemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer")
                .bearerFormat("JWT");

        var components = new Components().addSecuritySchemes(schemeName, scheme);

        var openApi = new OpenAPI()
                .info(info)
                .addSecurityItem(requirement)
                .components(components);

        return openApi;

    }
}
