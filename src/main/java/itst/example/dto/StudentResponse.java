package itst.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    @JsonProperty("Control number")
    private Integer controlNumber;
    private String name;
    private String lastname;
    private AddressResponse address; 
}