package com.devtalk.carparking.configuration;

import com.devtalk.carparking.dataaccess.entity.CityEntity;
import com.devtalk.carparking.dataaccess.entity.FacilityEntity;
import com.devtalk.carparking.dataaccess.entity.StateEntity;
import com.devtalk.carparking.dataaccess.entity.UserInfoDetailsEntity;
import com.devtalk.carparking.model.Facility;
import com.devtalk.carparking.model.UserInfoDetails;
import com.devtalk.carparking.model.seeddata.City;
import com.devtalk.carparking.model.seeddata.State;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Swagger uses this Docket Bean to provide anything and everything about API documentation as per configuration.
     *
     * @return
     */
    @Bean
    public Docket swaggerConfigurationsProvidedByDocketBean() {
        return new Docket(DocumentationType.SWAGGER_2) // Docket constructor
                .ignoredParameterTypes(City.class, State.class, Facility.class, UserInfoDetails.class, GrantedAuthority.class, Authentication.class)
                .ignoredParameterTypes(CityEntity.class, FacilityEntity.class, StateEntity.class, UserInfoDetailsEntity.class)
                .select() // Calling Select method on Docket to get hold of Docket Builder object of type ApiSelectorBuilder.
                //Below two builders paths and apis are used to put restriction in the sense what should be included in our documentation.
                //.paths(PathSelectors.ant("/api/**")) // This decides which paths/API URIs should get included in documentation.
                // If we use PathSelectors.any(), then it would show framework specific documentation like Error controller endpoints also.
                .apis(RequestHandlerSelectors.basePackage("com.devtalk.carparking")) // This sets base package to be scanned by Docket for API documentation in that package.
                .build()// Calling build to get configured Docket Object from Builder
                .apiInfo(apiDetails())
                .securitySchemes(Arrays.asList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext())); //
    }

    /*@Bean
    public Docket swaggerConfigurationsProvidedByDocketBean() {
        Parameter authHeader = new ParameterBuilder()
                .parameterType("header")
                .name("Authorization")
                .modelRef(new ModelRef("string"))
                .build();
        return new Docket(DocumentationType.SWAGGER_2) // Docket constructor
                .select() // Calling Select method on Docket to get hold of Docket Builder object of type ApiSelectorBuilder.
                //Below two builders paths and apis are used to put restriction in the sense what should be included in our documentation.
                .paths(PathSelectors.ant("/v1/**")) // This decides which paths/API URIs should get included in documentation.
                // If we use PathSelectors.any(), then it would show framework specific documentation like Error controller endpoints also.
                .apis(RequestHandlerSelectors.basePackage("com.devtalk.carparking")) // This sets base package to be scanned by Docket for API documentation in that package.
                .build()// Calling build to get configured Docket Object from Builder
                .apiInfo(apiDetails())
                .globalOperationParameters(Collections.singletonList(authHeader));
    }*/


    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Parking Management API",
                "RESTful backend Apis for parking management system.",
                "1.0",
                "Learning & tutorial purpose",
                new Contact("Suraj Chaudhary", "http://bookyourparking.com", "chaudharysurajsing@bookyourparking.com"),
                "API License",
                "http://bookyourparking.com",
                Collections.emptyList()
        );
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }
}
