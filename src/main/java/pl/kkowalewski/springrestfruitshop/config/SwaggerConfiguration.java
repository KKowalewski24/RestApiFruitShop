package pl.kkowalewski.springrestfruitshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.SLASH;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping(SLASH);
    }
}
