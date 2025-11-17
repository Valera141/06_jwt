package itst.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import itst.example.dto.StudentRequest;
import itst.example.dto.StudentResponse;
import itst.example.model.Student;
import itst.example.service.StudentService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping
    @Operation(summary = "Get all students")
    @ApiResponse(responseCode = "200", description = "List of registered students.", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Student.class))) })
    public List<StudentResponse> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "pagination", params = { "page", "pageSize" })
    @Operation(summary = "Get all students with pagination")
    public List<StudentResponse> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        if (page < 0 || pageSize < 0 || (page == 0 && pageSize == 0)) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page and pageSize cannot be negative and cannot both be 0.");
        }
        return service.findAll(page, pageSize);
    }

    @GetMapping("/{controlNumber}")
    public StudentResponse findById(@PathVariable Integer controlNumber) {
        return service.findById(controlNumber);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentRequest req) {
        StudentResponse created = service.create(req);
        return ResponseEntity
                .created(URI.create("/api/v1/students/" + created.getControlNumber()))
                .body(created);
    }

    @PutMapping("/{controlNumber}")    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public StudentResponse update(@PathVariable Integer controlNumber, @Valid @RequestBody StudentRequest req) {
        return service.update(controlNumber, req);
    }

    @DeleteMapping("/{controlNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer controlNumber) {
        service.delete(controlNumber);
    }

    @Operation(summary = "Get all students by name")
    @GetMapping("/search/{name}")
    public List<StudentResponse> getStudentsByName(@PathVariable String name) {
        return service.getStudentsByName(name);
    }

}
