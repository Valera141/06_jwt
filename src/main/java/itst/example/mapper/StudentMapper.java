package itst.example.mapper;

import itst.example.dto.AddressRequest;
import itst.example.dto.StudentRequest;
import itst.example.dto.StudentResponse;
import itst.example.model.Address;
import itst.example.model.Student;

public final class StudentMapper {

    public static StudentResponse toResponse(Student student) {
        if (student == null) return null;

        return StudentResponse.builder()
                .controlNumber(student.getControlNumber())
                .name(student.getName())
                .lastname(student.getLastname())
                .address(AddressMapper.toResponse(student.getAddress()))
                .build();
    }

    public static Student toEntity(StudentRequest dto) {
        if (dto == null) return null;

        Student student = Student.builder()
                .name(dto.getName())
                .lastname(dto.getLastname())
                .build();

        AddressRequest addrDto = dto.getAddress();
        if (addrDto != null) {
            Address address = AddressMapper.toEntity(addrDto);
            if (address != null) {
                address.setStudent(student); // owning side
                student.setAddress(address); // inverse side
            }
        }
        return student;
    }

    public static void copyToEntity(StudentRequest dto, Student entity) {
        if (dto == null || entity == null) return;

        entity.setName(dto.getName());
        entity.setLastname(dto.getLastname());

        AddressRequest addrDto = dto.getAddress();
        if (addrDto != null) {
            Address address = entity.getAddress();
            if (address == null) {
                address = AddressMapper.toEntity(addrDto);
                if (address != null) {
                    address.setStudent(entity); // owning side
                    entity.setAddress(address); // inverse side
                }
            } else {
                AddressMapper.copyToEntity(addrDto, address);
            }
        }
    }
}
