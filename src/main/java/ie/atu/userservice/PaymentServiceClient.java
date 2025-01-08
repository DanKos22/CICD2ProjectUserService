package ie.atu.userservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Payment-Service", url = "${feign.url2}")
public interface PaymentServiceClient {
    @PostMapping("paymentsNotify")
    String getPayments(@RequestBody Payment payment);
}
