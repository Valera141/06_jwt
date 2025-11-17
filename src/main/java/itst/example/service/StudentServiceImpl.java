package itst.example.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import itst.example.mapper.StudentMapper;
import itst.example.model.Student;
import itst.example.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import itst.example.dto.StudentRequest;
import itst.example.dto.StudentResponse;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository repository;

    @Override
    public List<StudentResponse> findAll() {
        return repository.findAll().stream()
                .map(StudentMapper::toResponse)
                .toList();
    }

    @Override
    public List<StudentResponse> findAll(int page, int pageSize) {
		PageRequest pageReq = PageRequest.of(page, pageSize);
		Page<Student> students = repository.findAll(pageReq);
		return students.getContent().stream()
                .map(StudentMapper::toResponse)
                .toList();
	}

    @Override
    public StudentResponse findById(Integer controlNumber) {
        Student student = repository.findById(controlNumber)
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + controlNumber));
        return StudentMapper.toResponse(student);
    }

    @Override
    public StudentResponse create(StudentRequest request) {
        Student saved = repository.save(StudentMapper.toEntity(request));
        return StudentMapper.toResponse(saved);
    }

    @Override
    public StudentResponse update(Integer controlNumber, StudentRequest dto) {
        Student existing = repository.findById(controlNumber)
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + controlNumber));
        StudentMapper.copyToEntity(dto, existing);
        Student saved = repository.save(existing);
        return StudentMapper.toResponse(saved);
    }

    @Override
    public void delete(Integer controlNumber) {
        if (!repository.existsById(controlNumber)) {
            throw new EntityNotFoundException("Student not found: " + controlNumber);
        }
        repository.deleteById(controlNumber);
    }

    @Override
    public List<StudentResponse> getStudentsByName(String name) {
        return repository.getStudentsByName(name).stream()
                .map(StudentMapper::toResponse)
                .toList();
	}
}
