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

@SpringBootApplication(scanBasePackages = "teste.producer")
@EnableScheduling
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

}
