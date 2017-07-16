package teste.consumer;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "teste.consumer")
@EnableRabbit
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
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> prefetchTenRabbitListenerContainerFactory(
	    ConnectionFactory rabbitConnectionFactory) {
	SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	factory.setConnectionFactory(rabbitConnectionFactory);
	// factory.setPrefetchCount(3);
	// factory.setConcurrentConsumers(2);
	factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
	factory.setMessageConverter(new Jackson2JsonMessageConverter());
	return factory;
    }

}
