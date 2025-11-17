package itst.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressRequest {

    @NotBlank
    private String street;

    @NotBlank
    @Size(max = 20)
    private String number;

    @NotBlank
    private String neighborhood;

    @NotBlank
    @Size(max = 10)
    private String postalCode;

    @NotBlank
    private String city;

    @NotBlank
    private String state; 
}
