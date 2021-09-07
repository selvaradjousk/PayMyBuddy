package com.paymybuddy.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j2;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 */
@Log4j2
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	// URL link: /swagger-ui/index.html or /swagger-ui/
	// http://localhost:9090/v2/api-docs

    /**
	 * Api docket.
	 *
	 * @return the docket
	 */
	@Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo())
        		.select()
        		.apis(RequestHandlerSelectors.any())
        		.paths(PathSelectors.any())
        		.build();
    }

	private ApiInfo apiInfo() {
		log.info("Swagger UI requested...");
		return new ApiInfoBuilder().title("PayMyBuddy Web App")
				.description("PayMyBuddy project"
						+ " for Easy Money Transfer")
				.licenseUrl(
						"https://github.com/"
						+ "selvaradjousk/"
						+ "PayMyBuddy")
				.version("1.0").build();
	}
}