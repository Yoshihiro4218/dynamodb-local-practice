package com.kadowork.dynamodbpractice.config;

import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;

//
@Configuration
public class OpenApiConfiguration {
    @Bean
    public Docket allApi() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .paths(PathSelectors.ant("/users/**"))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("OpenAPI")
                .description("公開 API 情報")
                .build();
    }
}
