package br.com.finaxis;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerConfig {

    public static void main(String[] args) throws Exception {
	SpringApplication.run(ProducerConfig.class, args);
    }

}
