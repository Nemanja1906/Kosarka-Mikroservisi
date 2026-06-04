package rs.ac.uns.ftn.notification_service.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @RabbitListener(queues = "orderQueue")
    public void handleOrderNotification(String message) {
        System.out.println("STIŽE NOTIFIKACIJA: " + message);
    }
}