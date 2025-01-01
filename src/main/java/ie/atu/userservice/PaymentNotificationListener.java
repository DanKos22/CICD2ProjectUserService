package ie.atu.userservice;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentNotificationListener {
    private final List<String> receivedMessages = new ArrayList<>();
    @RabbitListener(queues = "paymentQueue")
    public void handlePaymentNotification(String message) {

        System.out.println(message);
        //receivedMessages.add(message);
        //return "message received";
    }

    public List<String> getReceivedMessages(@RequestBody Payment payment) {
        return receivedMessages;
    }
}
