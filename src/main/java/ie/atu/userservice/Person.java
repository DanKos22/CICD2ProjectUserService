package ie.atu.userservice;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@Document(collection = "people")
public class Person {

    @Id
    private String id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Please enter the account type")
    private String accountType;
}
