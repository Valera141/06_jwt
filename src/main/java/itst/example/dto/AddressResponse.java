// itst.example.dto.AddressResponse
package itst.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {
    private Integer id;
    private String street;
    private String number;
    private String neighborhood;
    private String postalCode;
    private String city;
    private String state;
}
