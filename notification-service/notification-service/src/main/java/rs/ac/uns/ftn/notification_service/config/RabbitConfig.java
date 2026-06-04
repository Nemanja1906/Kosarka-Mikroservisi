package rs.ac.uns.ftn.notification_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUE = "orderQueue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }
}