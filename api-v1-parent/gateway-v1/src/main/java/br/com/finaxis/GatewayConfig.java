package br.com.finaxis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ClientCredentialsGrant;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "br.com.finaxis")
@EnableSwagger2
@Import({ ProducerConfig.class })

public class GatewayConfig extends SpringBootServletInitializer {

	final static String queueName = "teste";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GatewayConfig.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GatewayConfig.class);
	}

	@Bean
	public Docket api() {
		List<SecurityScheme> schemeList = new ArrayList<>();
		schemeList.add(oauth());
		Contact c = new Contact("TI Finaxis", null, "sustentacao@finaxis.com.br");
		ApiInfo apiInfo = new ApiInfo("Finaxis Web API", "Some custom description of API.", "1.0", "Terms of service", c, null, null);
		return new Docket(DocumentationType.SWAGGER_2).securitySchemes(schemeList).select().apis(RequestHandlerSelectors.basePackage("br.com.finaxis.controller")).paths(PathSelectors.any()).build().apiInfo(apiInfo);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public SecurityScheme oauth() {
		return new OAuthBuilder().name("oauth2").grantTypes(grantTypes()).scopes(scopes()).build();
	}

	private List<AuthorizationScope> scopes() {
		List<AuthorizationScope> a = new ArrayList();
		a.add(new AuthorizationScope("write", "write and read"));
		a.add(new AuthorizationScope("read", "read only"));
		return a;
	}

	private List<GrantType> grantTypes() {
		GrantType grantType = new ClientCredentialsGrant("credential");
		List<GrantType> a = new ArrayList();
		a.add(grantType);
		return a;
	}

}
