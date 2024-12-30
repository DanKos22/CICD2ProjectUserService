package ie.atu.userservice;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class PaymentNotificationListener {
    @PostMapping("/payments-notifications")
    @RabbitListener(queues = "paymentQueue")
    public void handlePaymentNotification(String message) {
        System.out.println(message);
    }
}
