package teste.producer;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "teste.producer")
@EnableScheduling
@EnableSwagger2
public class Config extends SpringBootServletInitializer {

    final static String queueName = "teste";

    public static void main(String[] args) throws Exception {
	SpringApplication.run(Config.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(Config.class);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory rabbitConnectionFactory) {
	RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitConnectionFactory);
	rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
	return rabbitTemplate;
    }

    @Bean
    public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2).select()
		.apis(RequestHandlerSelectors.basePackage("teste.producer")).paths(PathSelectors.any()).build()
		.apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
	Contact c = new Contact("TI Finaxis", "http://localhost:8080", "sustentacao@finaxis.com.br");
	ApiInfo apiInfo = new ApiInfo("Finaxis Web API", "Some custom description of API.", "1.0", "Terms of service",
		c, "License of API", "API license URL");
	return apiInfo;
    }

}
