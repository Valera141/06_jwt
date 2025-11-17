package itst.example.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import itst.example.dto.StudentRequest;
import itst.example.dto.StudentResponse;
import itst.example.service.StudentService;
import jakarta.validation.Valid;

@Controller
public class StudentGraphql {
    @Autowired
    private StudentService service;

    @QueryMapping
    public List<StudentResponse> findAll() {
        return service.findAll();
    }

    @QueryMapping
    public StudentResponse findById(@Argument Integer controlNumber) {
        return service.findById(controlNumber);
    }

    @MutationMapping
    public StudentResponse create(@Valid @Argument StudentRequest req) {
        return service.create(req);
    }
}
