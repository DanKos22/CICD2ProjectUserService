package ie.atu.userservice;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "Please enter the transactiontype")
    private String transactionType;
    @NotNull(message = "The amount cannot be empty")
    private Double amount;

}
