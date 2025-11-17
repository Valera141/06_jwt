package itst.example.mapper;

import itst.example.dto.AddressRequest;
import itst.example.dto.AddressResponse;
import itst.example.model.Address;

public final class AddressMapper {

    public static AddressResponse toResponse(Address address) {
        if (address == null) return null;

        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .neighborhood(address.getNeighborhood())
                .postalCode(address.getPostalCode())
                .city(address.getCity())
                .state(address.getState())
                .build();
    }

    public static Address toEntity(AddressRequest dto) {
        if (dto == null) return null;

        return Address.builder()
                .street(dto.getStreet())
                .number(dto.getNumber())
                .neighborhood(dto.getNeighborhood())
                .postalCode(dto.getPostalCode())
                .city(dto.getCity())
                .state(dto.getState())
                .build();
    }

    public static void copyToEntity(AddressRequest dto, Address entity) {
        if (dto == null || entity == null) return;
        entity.setStreet(dto.getStreet());
        entity.setNumber(dto.getNumber());
        entity.setNeighborhood(dto.getNeighborhood());
        entity.setPostalCode(dto.getPostalCode());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
    }
}
