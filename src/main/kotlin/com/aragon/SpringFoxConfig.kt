package com.aragon

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.builders.PathSelectors.regex


@Configuration
@EnableSwagger2
class MySwaggerConfig {
    @Bean
    fun customDocket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.aragon"))
            .paths(PathSelectors.ant("/v3/**"))
            .build()
            .apiInfo(apiInfo());
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Api de captura de pokemons")
            .description("Essa api serve para registrar a captura de pokemons e servir os pokemons capturados de volta ao usuário.")
            .version("0.0.1-SNAPSHOT")
            .build()
    }
}
