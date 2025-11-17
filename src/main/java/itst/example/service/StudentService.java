package itst.example.service;

import java.util.List;
import itst.example.dto.StudentRequest;
import itst.example.dto.StudentResponse;

public interface StudentService {
	List<StudentResponse> findAll();

	StudentResponse findById(Integer controlNumber);

	StudentResponse create(StudentRequest req);

	StudentResponse update(Integer controlNumber, StudentRequest req);

	void delete(Integer controlNumber);

	public List<StudentResponse> getStudentsByName(String name);

	public List<StudentResponse> findAll(int page, int pageSize);
}