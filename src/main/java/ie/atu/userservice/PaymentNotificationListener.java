package ie.atu.userservice;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentNotificationListener {

    @RabbitListener(queues = "paymentQueue")
    public void handlePaymentNotification(String message) {
        System.out.println(message);
    }
}
