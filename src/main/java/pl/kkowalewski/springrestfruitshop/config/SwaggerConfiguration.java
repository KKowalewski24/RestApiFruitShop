package pl.kkowalewski.springrestfruitshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.SLASH;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    private ApiInfo prepareMetaData() {
        return new ApiInfo(
                "Kamil Kowalewski",
                "Spring Rest Api Fruit Shop",
                "1.0",
                "Feel Free",
                new Contact("Kamil Kowalewski",
                        "KamilKowalewski.github.io",
                        "kamilkowalewski@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping(SLASH)
                .apiInfo(prepareMetaData());
    }
}
