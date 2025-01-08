package ie.atu.userservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Account-Service", url = "http://localhost:8081")
public interface AccountServiceClient {
    @PostMapping("/accountsRegister")
    String getDetails(@RequestBody Person person);
}
